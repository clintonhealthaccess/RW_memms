/**
 * 
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.security.User;
import org.chai.memms.spare.part.SparePart;
import org.chai.memms.spare.part.SparePart.SparePartStatus;
import org.chai.memms.util.Utils.ReportType;
import org.chai.memms.util.Utils.ReportSubType;
import org.chai.memms.util.Utils;
import org.joda.time.DateTime;


/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartListingReportService {
	def sparePartService
	def userService
	def today = new Date()

	public def getGeneralReportOfSpareParts(User user,Map<String, String> params) {
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedSpareParts(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = SparePart.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
		}
	}

	public def getPendingOrderSparePartsReport(User user,Map<String, String> params) {
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedSpareParts(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = SparePart.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
			eq ("status",SparePartStatus.PENDINGORDER)
		}
	}

	public def getCustomReportOfSpareParts(User user,def customSparePartsParams, Map<String, String> params) {

		def customSpareParts = []

		def reportType = customSparePartsParams.get('reportType')
		def reportSubType = customSparePartsParams.get('reportSubType')

		def dataLocations = customSparePartsParams.get('dataLocations')
		def sparePartTypes = customSparePartsParams.get('sparePartTypes')

		def criteria = SparePart.createCriteria();

		def criteriaSpareParts = []

		if(reportSubType == ReportSubType.INVENTORY){
			def fromAcquisitionPeriod = customSparePartsParams.get('fromAcquisitionPeriod')
			def toAcquisitionPeriod = customSparePartsParams.get('toAcquisitionPeriod')
			def noAcquisitionPeriod = customSparePartsParams.get('noAcquisitionPeriod')
			def sparePartStatus = customSparePartsParams.get('sparePartStatus')

			criteriaSpareParts = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

				if(dataLocations != null && dataLocations.size() > 0)
					inList("dataLocation",dataLocations)
				if(sparePartTypes != null && sparePartTypes.size() > 0)
					inList ("type", sparePartTypes)
				and{
					/*if(fromAcquisitionPeriod != null)
					 gt ("purchaseDate", fromAcquisitionPeriod)
					 if(toAcquisitionPeriod != null)
					 lt ("purchaseDate", toAcquisitionPeriod)	*/
					//TODO
					/*if(noAcquisitionPeriod != null && noAcquisitionPeriod)
					 eq ("purchaseDate", null)
					 */
					if(sparePartStatus!=null && !sparePartStatus.empty)
						inList ("status",sparePartStatus)
				}
			}
			if (log.isDebugEnabled()) log.debug("SPARE PARTS SIZE: "+ criteriaSpareParts.size())
		}

		if(reportSubType == ReportSubType.STATUSCHANGES){
			def statusChanges = customSparePartsParams.get('statusChanges')
			def fromStatusChangesPeriod = customSparePartsParams.get('fromStatusChangesPeriod')
			def toStatusChangesPeriod = customSparePartsParams.get('toStatusChangesPeriod')

			criteriaSpareParts = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				if(dataLocations != null && dataLocations.size() > 0)
					inList("dataLocation",dataLocations)
				if(sparePartTypes != null && sparePartTypes.size() > 0)
					inList ("type", sparePartTypes)

				// TODO
				// if(fromStatusChangesPeriod != null)
				// 	gt ("TODO", fromStatusChangesPeriod)
				// if(toStatusChangesPeriod != null)
				// 	lt ("TODO", toStatusChangesPeriod)
			}
			if (log.isDebugEnabled()) log.debug("SPARE PARTS SIZE: "+ criteriaSpareParts.size())
			//TODO add getSparePartTimeBasedStatusChange method into sparePartService
			/*if(statusChanges != null && !statusChanges.empty){
			 def statusChangesSpareParts = []
			 criteriaSpareParts.each { sparePart ->
			 def sparePartStatusChange = sparePartService.getSparePartTimeBasedStatusChange(sparePart,statusChanges)
			 if(sparePartStatusChange != null) statusChangesSpareParts.add(sparePart)
			 statusChangesSpareParts.add(sparePart)
			 }
			 customSpareParts = statusChangesSpareParts
			 }*/
		}
		if(reportSubType == ReportSubType.USERATE){
			DateTime todayDateTime = new DateTime(today)
			def lastYearDateTimeFromNow = todayDateTime.minusDays(365)
			def lastYearDateFromNow = lastYearDateTimeFromNow.toDate()
			criteriaSpareParts = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				if(dataLocations != null && dataLocations.size() > 0)
					inList("dataLocation",dataLocations)
				if(sparePartTypes != null && sparePartTypes.size() > 0)
					inList ("type", sparePartTypes)
					gt("deliveryDate", lastYearDateFromNow)
					projections{
						property("id")
						groupProperty("type")
						rowCount("inStockQuantity")
					}
			}
			if (log.isDebugEnabled()) log.debug("SPARE PARTS SIZE ON USE RATE: "+ criteriaSpareParts.size())
		}
		
		if(reportSubType == ReportSubType.STOCKOUT){
			DateTime todayDateTime = new DateTime(today)
			def lastYearDateTimeFromNow = todayDateTime.minusDays(365)
			def lastYearDateFromNow = lastYearDateTimeFromNow.toDate()
			criteriaSpareParts = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				if(dataLocations != null && dataLocations.size() > 0)
					inList("dataLocation",dataLocations)
				if(sparePartTypes != null && sparePartTypes.size() > 0)
					inList ("type", sparePartTypes)
					gt("deliveryDate", lastYearDateFromNow)
					projections{
						property("id")
						groupProperty("type")
						rowCount("inStockQuantity")
					}
			}
			if (log.isDebugEnabled()) log.debug("SPARE PARTS SIZE ON USE RATE: "+ criteriaSpareParts.size())
		}
		return customSpareParts
	}
	public def saveSparePartReportParams(User user, def sparePartReport,def customSparePartParams, Map<String, String> params){
		def reportName = customSparePartParams.get('customizedReportName')
		def reportType = customSparePartParams.get('reportType')
		def reportSubType = customSparePartParams.get('reportSubType')

		def dataLocations = customSparePartParams.get('dataLocations')
		def sparePartTypes = customSparePartParams.get('sparePartTypes')

		def fromAcquisitionPeriod = customSparePartParams.get('fromAcquisitionPeriod')
		def toAcquisitionPeriod = customSparePartParams.get('toAcquisitionPeriod')
		def noAcquisitionPeriod = customSparePartParams.get('noAcquisitionPeriod')
		def sparePartStatus = customSparePartParams.get('sparePartStatus')

		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON SPARE PART CUSTOM REPORT: SPARE PART STATUS :"+sparePartStatus)
		sparePartReport.sparePartStatus=sparePartStatus
		sparePartReport.noAcquisitionPeriod=noAcquisitionPeriod=="on"?true:false
		sparePartReport.toDate=toAcquisitionPeriod
		sparePartReport.fromDate=fromAcquisitionPeriod
		sparePartReport.sparePartTypes=sparePartTypes
		sparePartReport.dataLocations=dataLocations
		sparePartReport.reportSubType=reportSubType
		sparePartReport.reportType=reportType
		sparePartReport.reportName=reportName
		sparePartReport.savedBy=user

		sparePartReport.save(failOnError:true)
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON SPARE PART CUSTOM REPORT SAVED CORRECTLY. THE REPORT ID IS :"+ sparePartReport.id)
	}
}
