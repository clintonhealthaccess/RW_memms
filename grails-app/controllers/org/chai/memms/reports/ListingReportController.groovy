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
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.chai.location.DataLocation;
import org.chai.memms.AbstractController;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.location.DataLocation;
import org.chai.location.CalculationLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location
import org.chai.location.LocationLevel
import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;

/**
 * @author Jean Kahigiso M.
 *
 */
class ListingReportController extends AbstractController{

	def equipmentListingReportService
	def grailsApplication
	def workOrderListingReportService
	def preventiveOrderListingReportService

	def getEntityClass() {
		return Equipment.class;
	}
	def getLabel() {
		return "equipment.listing.report.label";
	}

	def model(def entities, def dataLocation) {
		return [
			entities: entities,
			entityCount: entities.size(),
			dataLocation:dataLocation,
			entityClass:getEntityClass(),
			code: getLabel()
		]
	}
	def ajaxModel(def entities,def dataLocation,def searchTerm) {
		def model = model(entities, dataLocation) << [q:searchTerm]
		def listHtml = g.render(template:"/entity/reports/listing",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}

	def defaultEquipmentsView ={
		render(view: '/entity/reports', model:
		[
			template:"/entity/reports/listing"
		])
	}

	def step1 ={
		if (log.isDebugEnabled()) log.debug("reports.step1, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')

		render(template:"/entity/reports/step1",
		model:
		[
			reportType: reportType,
			reportSubType: reportSubType
		])
	}

	def step2 ={
		if (log.isDebugEnabled()) log.debug("reports.step2, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')

		render(template:"/entity/reports/step2",
		model:
		[
			reportType: reportType,
			reportSubType: reportSubType
		])
	}

	def step3 ={
		if (log.isDebugEnabled()) log.debug("reports.step3, params:"+params)

		def reportType = params.get('reportType')
		def reportSubType = params.get('reportSubType')

		render(template:"/entity/reports/step3",
		model:
		[
			reportType: reportType,
			reportSubType: reportSubType
		])
	}

	def step4 ={
		if (log.isDebugEnabled()) log.debug("reports.step4, params:"+params)

		def reportType = params.get('reportType')
		def reportSubType = params.get('reportSubType')

		render(template:"/entity/reports/step4",
		model:
		[
			reportType: reportType,
			reportSubType: reportSubType
		])
	}

	def customizedListing ={
		// TODO
	}

	def customizedReportSubType ={
		if (log.isDebugEnabled())
			log.debug("reports.step1.customizedReportSubType, params:"+params)

		def reportType = getReportType('reportType')

		render(template:"/entity/reports/customizedReportSubType",
		model:
		[
			reportType: reportType
		])
	}

	def generalEquipmentsListing={
		adaptParamsForList()
		def equipments = equipmentListingReportService.getGeneralReportOfEquipments(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(equipments, "") << [
				template:"/entity/reports/listing",
			])
	}

	def disposedEquipments={
		adaptParamsForList()
		def equipments = equipmentListingReportService.getDisposedEquipments(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(equipments, "") << [
				template:"/entity/reports/listing",
			])
	}

	def underMaintenanceEquipments={
		adaptParamsForList()
		def equipments = equipmentListingReportService.getUnderMaintenanceEquipments(user,params)
		if (log.isDebugEnabled()) log.debug("EQUIPMENTS SIZE "+equipments.size())
		if(!request.xhr)
			render(view:"/entity/reports", model: model(equipments, "") << [
				template:"/entity/reports/listing",
			])
		else
			this.ajaxModel(equipments,"","")
	}
	
	def underWarrantyEquipments={
		adaptParamsForList()
		def displayableEquipments=[]
		def warrantyExpirationDate
		def equipments = equipmentListingReportService.getUnderWarrantyEquipments(user,params)
		for(Equipment equipment: equipments){
			if (equipment.warranty.startDate!=null && equipment.warrantyPeriod.numberOfMonths!=null && equipment.warrantyPeriod.months != null) {
				warrantyExpirationDate= (equipment.warranty.startDate).plus((equipment.warrantyPeriod.numberOfMonths))
				if (log.isDebugEnabled()) log.debug("CALCURATED DATE "+warrantyExpirationDate +"START DATE "+equipment.warranty.startDate +"WARRANTY PERIOD "+equipment.warrantyPeriod.months)
				if (warrantyExpirationDate > new Date())
					displayableEquipments.add(equipment)
			}
			warrantyExpirationDate=null
		}
		equipments=displayableEquipments
		if(!request.xhr)
			render(view:"/entity/reports", model: model(equipments, "") << [
				template:"/entity/reports/listing"
			])
	}

	def obsoleteEquipments={
		adaptParamsForList()
		def equipments = equipmentListingReportService.getObsoleteEquipments(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(equipments, "") << [
				template:"/entity/reports/listing",
			])
	}
	def inStockEquipments={
		adaptParamsForList()
		def equipments = equipmentListingReportService.getInStockEquipments(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(equipments, "") << [
				template:"/entity/reports/listing",
			])
	}
	//WorkOrders
	def lastMonthWorkOrders={
		adaptParamsForList()
		def workOrders = workOrderListingReportService.getWorkOrdersOfLastMonth(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(workOrders, "") << [
				template:"/entity/reports/listing",
			])
	}
	def workOrdersEscalatedToMMC={
		adaptParamsForList()
		def workOrders = workOrderListingReportService.getWorkOrdersEscalatedToMMC(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(workOrders, "") << [
				template:"/entity/reports/listing",
			])

	}

	//PreventiveOrders
	def equipmentsWithPreventionPlan={
		adaptParamsForList()
		def preventiveOrders = preventiveOrderListingReportService.getEquipmentsWithPreventionPlan(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(preventiveOrders, "") << [
				template:"/entity/reports/listing",
			])
	}
	//TODO see how to deal with periodic times either weekly, monthly, or any other
	def preventionsDelayed={
		adaptParamsForList()
		def preventiveOrders = preventiveOrderListingReportService.getEquipmentsWithPreventionPlan(user,params)
		if(!request.xhr)
			render(view:"/entity/reports", model: model(preventiveOrders, "") << [
				template:"/entity/reports/listing",
			])

	}
}
