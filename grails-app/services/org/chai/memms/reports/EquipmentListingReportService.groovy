/**
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.report.listing.EquipmentReport;
import org.chai.memms.security.User;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.inventory.Equipment.PurchasedBy;
import org.chai.memms.inventory.Equipment.Donor;
import org.chai.memms.util.Utils.ReportType;
import org.chai.memms.util.Utils.ReportSubType;
import org.chai.memms.util.Utils;
import org.joda.time.DateTime;
import org.chai.memms.Warranty;

/**
 * @author Aphrodice Rwagaju
 *
 */
class EquipmentListingReportService {

	def equipmentService
	def userService
	def today =new Date()

	public def getGeneralReportOfEquipments(User user,Map<String, String> params) {
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = Equipment.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
		}
	}

	public def getDisposedEquipments(User user,Map<String, String> params) {
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = Equipment.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
			eq ("currentStatus",Status.DISPOSED)
		}
	}

	public def getObsoleteEquipments(User user,Map<String, String> params) {
		Boolean obsolete
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = Equipment.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
			eq ("obsolete", (obsolete.equals('true'))?true:false)
		}
	}

	public def getUnderMaintenanceEquipments(User user,Map<String, String> params) {
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = Equipment.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
			eq ("currentStatus",Status.UNDERMAINTENANCE)
		}
	}

	public def getInStockEquipments(User user,Map<String, String> params) {
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = Equipment.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
			eq ("currentStatus",Status.INSTOCK)
		}
	}
	
	public def getUnderWarrantyEquipments(User user,Map<String, String> params) {
		def dataLocations = []

		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}

		def criteria = Equipment.createCriteria();

		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations)
				inList("dataLocation",dataLocations)
		}
	}

	public def getCustomReportOfEquipments(User user,def customEquipmentParams, Map<String, String> params) {

		def customEquipments = []

		def reportType = customEquipmentParams.get('reportType')
		def reportSubType = customEquipmentParams.get('reportSubType')

		def dataLocations = customEquipmentParams.get('dataLocations')
		def departments = customEquipmentParams.get('departments')
		def equipmentTypes = customEquipmentParams.get('equipmentTypes')
		def lowerLimitCost = customEquipmentParams.get('fromCost')
		def upperLimitCost = customEquipmentParams.get('toCost')
		def currency = customEquipmentParams.get('costCurrency')

		def criteria = Equipment.createCriteria();

		def criteriaEquipments = []

		if(reportSubType == ReportSubType.INVENTORY){
			def fromAcquisitionPeriod = customEquipmentParams.get('fromAcquisitionPeriod')
			def toAcquisitionPeriod = customEquipmentParams.get('toAcquisitionPeriod')
			def noAcquisitionPeriod = customEquipmentParams.get('noAcquisitionPeriod')
			def equipmentStatus = customEquipmentParams.get('equipmentStatus')
			def obsolete = customEquipmentParams.get('obsolete')
			def warranty = customEquipmentParams.get('warranty')

			criteriaEquipments = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				
				if(dataLocations != null && dataLocations.size() > 0)
					inList("dataLocation",dataLocations)
				if(equipmentTypes != null && equipmentTypes.size() > 0)
					inList ("type", equipmentTypes)
				and{
					if(departments != null && departments.size() > 0)
						inList ("department", departments)
					if(lowerLimitCost!=null)
						gt ("purchaseCost", lowerLimitCost)
					if(upperLimitCost!=null)
						lt ("purchaseCost", upperLimitCost)
					if(currency !=null)
						eq ("currency", currency)
					/*if(fromAcquisitionPeriod != null)
						gt ("purchaseDate", fromAcquisitionPeriod)
					if(toAcquisitionPeriod != null)
						lt ("purchaseDate", toAcquisitionPeriod)	*/
						//TODO
					/*if(noAcquisitionPeriod != null && noAcquisitionPeriod)
						eq ("purchaseDate", null)
						*/
					if(equipmentStatus!=null && !equipmentStatus.empty)
						inList ("currentStatus",equipmentStatus)
					if(obsolete != null && obsolete)
						eq ("obsolete", (obsolete.equals('true'))?true:false)
					if(warranty!=null && warranty)
						lt ("warrantyEndDate",today)
				}
			}
			if (log.isDebugEnabled()) log.debug("EQUIPMENTS SIZE: "+ criteriaEquipments.size())

			/*if(warranty != null && warranty){
				def underWarrantyEquipments = []
				
				criteriaEquipments.each{ equipment ->
					if (equipment.warranty?.startDate!=null && equipment.warrantyPeriod.numberOfMonths!=null && equipment.warrantyPeriod.months != null) {
						
						DateTime warrantyStatDateTime = new DateTime(equipment.warranty?.startDate)
						def warrantyExpirationDateTime = warrantyStatDateTime.plusMonths(equipment.warrantyPeriod.numberOfMonths)
						def warrantyExpirationDate = warrantyExpirationDateTime.toDate()
						if (log.isDebugEnabled())
							log.debug("WARRANTY EXPIRATION DATE TIME "+warrantyExpirationDateTime +"WARRANTY EXPIRATION DATE "+warrantyExpirationDate +" START DATE "+equipment.warranty.startDate +" WARRANTY PERIOD MONTHS "+equipment.warrantyPeriod.months +" WARRANTY PERIOD NUMBER OF MONTHS "+equipment.warrantyPeriod.numberOfMonths+" AND EXPIRATION DATE IS " +Utils.getDate(0, equipment.warrantyPeriod.numberOfMonths, 0))
						if (warrantyExpirationDate > new Date())
							underWarrantyEquipments.add(equipment)
					}
				}
				customEquipments = underWarrantyEquipments
			}*/
		}

		if(reportSubType == ReportSubType.STATUSCHANGES){
			def statusChanges = customEquipmentParams.get('statusChanges')
			def fromStatusChangesPeriod = customEquipmentParams.get('fromStatusChangesPeriod')
			def toStatusChangesPeriod = customEquipmentParams.get('toStatusChangesPeriod')

			criteriaEquipments = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				if(dataLocations)
					inList("dataLocation",dataLocations)
				if(departments != null)
					inList ("department", departments)
				if(equipmentTypes != null)
					inList ("type", equipmentTypes)

				if(lowerLimitCost!=null)
					gt ("purchaseCost", lowerLimitCost)
				if(upperLimitCost!=null)
					lt ("purchaseCost", upperLimitCost)
				if(currency !=null)
					eq ("currency",currency)

				// TODO
				// if(fromStatusChangesPeriod != null)
				// 	gt ("TODO", fromStatusChangesPeriod)
				// if(toStatusChangesPeriod != null)
				// 	lt ("TODO", toStatusChangesPeriod)
			}
			if (log.isDebugEnabled()) log.debug("EQUIPMENTS SIZE: "+ criteriaEquipments.size())

			if(statusChanges != null && !statusChanges.empty){
				def statusChangesEquipments = []
				criteriaEquipments.each { equipment ->
					def equipmentStatusChange = equipmentService.getEquipmentTimeBasedStatusChange(equipment,statusChanges)
					if(equipmentStatusChange != null) statusChangesEquipments.add(equipment)
					statusChangesEquipments.add(equipment)
				}
				customEquipments = statusChangesEquipments
			}
		}

		return customEquipments
	}

	public def saveEquipmentReportParams(User user, def equipmentReport,def customEquipmentParams, Map<String, String> params){
		def reportName = customEquipmentParams.get('customizedReportName')
		def reportType = customEquipmentParams.get('reportType')
		def reportSubType = customEquipmentParams.get('reportSubType')

		def dataLocations = customEquipmentParams.get('dataLocations')
		def departments = customEquipmentParams.get('departments')
		def equipmentTypes = customEquipmentParams.get('equipmentTypes')
		def lowerLimitCost = customEquipmentParams.get('fromCost')
		def upperLimitCost = customEquipmentParams.get('toCost')
		def currency = customEquipmentParams.get('costCurrency')
		
		def fromAcquisitionPeriod = customEquipmentParams.get('fromAcquisitionPeriod')
		def toAcquisitionPeriod = customEquipmentParams.get('toAcquisitionPeriod')
		def noAcquisitionPeriod = customEquipmentParams.get('noAcquisitionPeriod')
		def equipmentStatus = customEquipmentParams.get('equipmentStatus')
		def obsolete = customEquipmentParams.get('obsolete')
		def warranty = customEquipmentParams.get('warranty')
		
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON EQUIPMENT CUSTOM REPORT: EQUIPMENT STATUS :"+equipmentStatus)
		
		equipmentReport.underWarranty=warranty=="on"?true:false
		equipmentReport.obsolete=obsolete=="on"?true:false
		equipmentReport.equipmentStatus=equipmentStatus
		equipmentReport.noAcquisitionPeriod=noAcquisitionPeriod=="on"?true:false
		equipmentReport.toDate=toAcquisitionPeriod
		equipmentReport.fromDate=fromAcquisitionPeriod
		equipmentReport.currency=currency
		equipmentReport.upperLimitCost=upperLimitCost
		equipmentReport.lowerLimitCost=lowerLimitCost
		equipmentReport.equipmentTypes=equipmentTypes
		equipmentReport.departments=departments
		equipmentReport.dataLocations=dataLocations
		equipmentReport.reportSubType=reportSubType
		equipmentReport.reportType=reportType
		equipmentReport.reportName=reportName
		equipmentReport.savedBy=user
		
		equipmentReport.save(failOnError:true)
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON EQUIPMENT CUSTOM REPORT SAVED CORRECTLY. THE REPORT ID IS :"+ equipmentReport.id)
	}
}
