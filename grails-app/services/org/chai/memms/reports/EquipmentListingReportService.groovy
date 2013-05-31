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
import org.chai.memms.security.User;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.inventory.Equipment.PurchasedBy;
import org.chai.memms.inventory.Equipment.Donor;
import org.chai.memms.util.Utils.ReportType
import org.chai.memms.util.Utils.ReportSubType

/**
 * @author Aphrodice Rwagaju
 *
 */
class EquipmentListingReportService {
	
	def equipmentService
	def userService
	
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
					inList('dataLocation',dataLocations)
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
					inList('dataLocation',dataLocations)
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
					inList('dataLocation',dataLocations)
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
					inList('dataLocation',dataLocations)
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
					inList('dataLocation',dataLocations)
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
					inList('dataLocation',dataLocations)
		}
	}

	public def getCustomReportOfEquipments(User user,def customEquipmentParams, Map<String, String> params) {

		def customEquipments = []

		def reportType = customEquipmentParams.get('reportType')
		def reportSubType = customEquipmentParams.get('reportSubType')

		def dataLocations = customEquipmentParams.get('dataLocations')
		def departments = customEquipmentParams.get('departments')
		def equipmentTypes = customEquipmentParams.get('equipmentTypes')
		def lowerLimitCost = customEquipmentParams.('fromCost')
		def upperLimitCost = customEquipmentParams.('toCost')
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
				if(dataLocations != null)
					inList('dataLocation',dataLocations)
				if(departments != null)
					inList ("department", departments)
				if(equipmentTypes != null)
					inList ("type", equipmentTypes)

				and {
					if(lowerLimitCost!=null)
						gt ("purchaseCost", lowerLimitCost)
					if(upperLimitCost!=null)
						lt ("purchaseCost", upperLimitCost)
					if(currency !=null && !currency.empty)
						eq ("currency",currency)
					if(fromAcquisitionPeriod != null)
						gt ("purchaseDate", fromAcquisitionPeriod)
					if(toAcquisitionPeriod != null)
						lt ("purchaseDate", toAcquisitionPeriod)
					// TODO
					// if(noAcquisitionPeriod != null && noAcquisitionPeriod)
					// 	eq ("purchaseDate", null)
					if(equipmentStatus!=null && !equipmentStatus.empty)
						inList ("currentStatus",equipmentStatus)
					if(obsolete != null && obsolete)
						eq ("obsolete", (obsolete.equals('true'))?true:false)
				}
			}
			if (log.isDebugEnabled()) log.debug("EQUIPMENTS SIZE: "+ criteriaEquipments.size())
			customEquipments = criteriaEquipments

			// TODO build into criteria
			// if(warranty != null && warranty){
			// 	def underWarrantyEquipments = []
			// 	criteriaEquipments.each{ equipment ->
			// 		if (equipment.warranty.startDate!=null && equipment.warrantyPeriod.numberOfMonths!=null && equipment.warrantyPeriod.months != null) {
			// 			def warrantyExpirationDate = (equipment.warranty.startDate).plus((equipment.warrantyPeriod.numberOfMonths))
			// 			if (log.isDebugEnabled()) 
			// 				log.debug("CALCULATED DATE "+warrantyExpirationDate +"START DATE "+equipment.warranty.startDate +"WARRANTY PERIOD "+equipment.warrantyPeriod.months)
			// 			if (warrantyExpirationDate > new Date())
			// 				underWarrantyEquipments.add(equipment)
			// 		}
			// 	}
				// customEquipments = underWarrantyEquipments
			// }
		}

		if(reportSubType == ReportSubType.STATUSCHANGES){
			def statusChanges = customEquipmentParams.get('statusChanges')
			def fromStatusChangesPeriod = customEquipmentParams.get('fromStatusChangesPeriod')
			def toStatusChangesPeriod = customEquipmentParams.get('toStatusChangesPeriod')

			criteriaEquipments = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				if(dataLocations)
					inList('dataLocation',dataLocations)
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

			// TODO build into criteria
			if(statusChanges != null && !statusChanges.empty){
				def statusChangesEquipments = []
				criteriaEquipments.each { equipment ->
					def equipmentStatusChange = equipmentService.getEquipmentTimeBasedStatusChange(equipment,statusChanges)
					if(equipmentStatusChange != null) statusChangesEquipments.add(equipment)
				}
				customEquipments = statusChangesEquipments
			}
		}
		return customEquipments
	}

}
