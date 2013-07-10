/**
 * 
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.corrective.maintenance.WorkOrderStatus;
import org.chai.memms.corrective.maintenance.WorkOrder;
import org.chai.memms.security.User;
import org.chai.memms.util.Utils;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;
import org.chai.memms.report.listing.CorrectiveMaintenanceReport;
import org.chai.memms.util.Utils.ReportType
import org.chai.memms.util.Utils.ReportSubType
import org.joda.time.DateTime

/**
 * @author Aphrodice Rwagaju
 *
 */
class WorkOrderListingReportService {

	def workOrderService
	def today = new Date()

	def getAllWorkOrders(User user , Map<String, String> params){
		
		def criteria = WorkOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
		}	
	}

	def getWorkOrdersEscalatedToMMC(User user,Map<String, String> params) {

		def criteria = WorkOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
			eq ("currentStatus",OrderStatus.OPENATMMC)
		}
	}

	def getWorkOrdersOfLastMonth(User user,Map<String, String> params) {	
		DateTime todayDateTime = new DateTime(today)
		def lastMonthDateTimeFromNow = todayDateTime.minusDays(30)
		def lastMonthDateFromNow = lastMonthDateTimeFromNow.toDate()

		def criteria = WorkOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
			or{
				eq ("currentStatus",OrderStatus.OPENATMMC)
				eq ("currentStatus",OrderStatus.OPENATFOSA)
			}
			ge ("openOn",lastMonthDateFromNow)
		}
	}

	def getCustomReportOfWorkOrders(User user, def customWorkOrderParams, Map<String, String> params) {

		def customWorkOrders = []

		def reportType = customWorkOrderParams.get('reportType')
		def reportSubType = customWorkOrderParams.get('reportSubType')

		def dataLocations = customWorkOrderParams.get('dataLocations')
		def departments = customWorkOrderParams.get('departments')
		def equipmentTypes = customWorkOrderParams.get('equipmentTypes')
		def lowerLimitCost = customWorkOrderParams.('fromCost')
		def upperLimitCost = customWorkOrderParams.('toCost')
		def currency = customWorkOrderParams.get('costCurrency')
		def noCost = customWorkOrderParams.get('noCost')
		
		def criteria = WorkOrder.createCriteria();

		def criteriaWorkOrders = []

		if(reportSubType == ReportSubType.WORKORDERS){
			def workOrderStatus = customWorkOrderParams.get('workOrderStatus')
			def fromWorkOrderPeriod = customWorkOrderParams.get('fromWorkOrderPeriod')
			def toWorkOrderPeriod = customWorkOrderParams.get('toWorkOrderPeriod')
			def warranty = customWorkOrderParams.get('warranty')

			criteriaWorkOrders = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

				createAlias("equipment","equip")

					//Mandatory property
					inList('equip.dataLocation',dataLocations)
				if(departments != null && departments.size()>0)
					inList ("equip.department", departments)
				//Mandatory property
					inList ("equip.type", equipmentTypes)

				if(lowerLimitCost && lowerLimitCost!=null)
					gt ("equip.purchaseCost", lowerLimitCost)
				if(upperLimitCost && upperLimitCost!=null)
					lt ("equip.purchaseCost", upperLimitCost)
				if(currency && currency !=null)
					eq ("equip.currency",currency)

				if(workOrderStatus!=null && !workOrderStatus.empty)
					inList ("currentStatus",workOrderStatus)
				if(fromWorkOrderPeriod && fromWorkOrderPeriod != null)
				 	ge ("openOn", fromWorkOrderPeriod)
				if(toWorkOrderPeriod && toWorkOrderPeriod != null)
				 	le ("openOn", toWorkOrderPeriod)
				if(warranty!=null && warranty)
					lt ("equip.warrantyEndDate",today)
				if(noCost != null && noCost)
					eq ("equip.purchaseCost", null)
			}
			customWorkOrders = criteriaWorkOrders
		}

		if(reportSubType == ReportSubType.STATUSCHANGES){
			def statusChanges = customWorkOrderParams.get('statusChanges')
			def fromStatusChangesPeriod = customWorkOrderParams.get('fromStatusChangesPeriod')
			def toStatusChangesPeriod = customWorkOrderParams.get('toStatusChangesPeriod')

			criteriaWorkOrders = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

				createAlias("equipment","equip")

				//Mandatory property
					inList('equip.dataLocation',dataLocations)
				if(departments != null && departments.size()>0)
					inList ("equip.department", departments)
				//Mandatory property
					inList ("equip.type", equipmentTypes)

				if(lowerLimitCost && lowerLimitCost!=null)
					gt ("equip.purchaseCost", lowerLimitCost)
				if(upperLimitCost && upperLimitCost!=null)
					lt ("equip.purchaseCost", upperLimitCost)
				if(currency && currency !=null)
					eq ("equip.currency",currency)
				if(noCost != null && noCost)
					eq ("equip.purchaseCost", null)

				// TODO
				// if(fromStatusChangesPeriod != null)
				// 	gt ("TODO", fromStatusChangesPeriod)
				// if(toStatusChangesPeriod != null)
				// 	lt ("TODO", toStatusChangesPeriod)
			}
			if (log.isDebugEnabled()) log.debug("WORK ORDERS SIZE: "+ criteriaWorkOrders.size())

			// TODO build into criteria
			if(statusChanges != null && !statusChanges.empty){
				def statusChangesWorkOrders = []
				criteriaWorkOrders.each { workOrder ->
					def workOrderStatusChange = workOrderService.getWorkOrderTimeBasedStatusChange(workOrder, statusChanges)
					if(workOrderStatusChange != null) statusChangesWorkOrders.add(workOrder)
				}
				customWorkOrders = statusChangesWorkOrders
			}
		}
		return customWorkOrders;
	}

	public def saveWorkOrderReportParams(User user, def customWorkOrderParams, Map<String, String> params){
		def correctiveMaintenanceReport = new CorrectiveMaintenanceReport()

		def reportName = customWorkOrderParams.get('customizedReportName')
		def reportType = customWorkOrderParams.get('reportType')
		def reportSubType = customWorkOrderParams.get('reportSubType')

		def dataLocations = customWorkOrderParams.get('dataLocations')
		def departments = customWorkOrderParams.get('departments')
		def equipmentTypes = customWorkOrderParams.get('equipmentTypes')
		def lowerLimitCost = customWorkOrderParams.get('fromCost')
		def upperLimitCost = customWorkOrderParams.get('toCost')
		def currency = customWorkOrderParams.get('costCurrency')
		def noCost = customWorkOrderParams.get('noCost')
		
		def fromWorkOrderPeriod = customWorkOrderParams.get('fromWorkOrderPeriod')
		def toWorkOrderPeriod = customWorkOrderParams.get('toWorkOrderPeriod')
		def warranty = customWorkOrderParams.get('warranty')
		def workOrderStatus = customWorkOrderParams.get('workOrderStatus')
		def listingReportDisplayOptions = customWorkOrderParams.get('reportTypeOptions')
		
		def statusChanges = customWorkOrderParams.get('statusChanges')
		def fromStatusChangesPeriod = customWorkOrderParams.get('fromStatusChangesPeriod')
		def toStatusChangesPeriod = customWorkOrderParams.get('toStatusChangesPeriod')
		
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON EQUIPMENT CUSTOM REPORT: WARRANTY :"+warranty)
		
		correctiveMaintenanceReport.underWarranty=warranty=="on"?true:false
		correctiveMaintenanceReport.toDate=toWorkOrderPeriod
		correctiveMaintenanceReport.fromDate=fromWorkOrderPeriod
		correctiveMaintenanceReport.currency=currency
		correctiveMaintenanceReport.upperLimitCost=upperLimitCost
		correctiveMaintenanceReport.lowerLimitCost=lowerLimitCost
		correctiveMaintenanceReport.equipmentTypes=equipmentTypes
		correctiveMaintenanceReport.departments=departments
		correctiveMaintenanceReport.dataLocations=dataLocations
		correctiveMaintenanceReport.reportSubType=reportSubType
		correctiveMaintenanceReport.reportType=reportType
		correctiveMaintenanceReport.reportName=reportName
		correctiveMaintenanceReport.workOrderStatus=workOrderStatus
		correctiveMaintenanceReport.savedBy=user
		correctiveMaintenanceReport.noCostSpecified=noCost=="on"?true:false
		correctiveMaintenanceReport.displayOptions=listingReportDisplayOptions
		correctiveMaintenanceReport.statusChanges=statusChanges
		correctiveMaintenanceReport.fromStatusChangesPeriod=fromStatusChangesPeriod
		correctiveMaintenanceReport.toStatusChangesPeriod=toStatusChangesPeriod

		correctiveMaintenanceReport.save(failOnError:true)
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON EQUIPMENT CUSTOM REPORT SAVED CORRECTLY. THE REPORT ID IS :"+ correctiveMaintenanceReport.id)

		return correctiveMaintenanceReport
	}
	
	def getClosedWorkOrdersOfLastYear(User user,Map<String, String> params) {
		DateTime todayDateTime = new DateTime(today)
		def lastYaerDateTimeFromNow = todayDateTime.minusDays(365)
		def lastYearDateFromNow = lastYaerDateTimeFromNow.toDate()

		def criteria = WorkOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
			or{
				eq ("currentStatus",OrderStatus.CLOSEDFIXED)
				eq ("currentStatus",OrderStatus.CLOSEDFORDISPOSAL)
			}
			ge ("closedOn",lastYearDateFromNow)
		}
		
		if (log.isDebugEnabled()) log.debug("WORK ORDER SIZE: "+ criteria.size())
	}
}
