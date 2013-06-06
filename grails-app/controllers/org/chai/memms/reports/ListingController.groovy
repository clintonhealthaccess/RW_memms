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
import org.chai.location.CalculationLocation;
import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location;
import org.chai.location.LocationLevel;
import org.chai.memms.AbstractController;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.corrective.maintenance.WorkOrder;
import org.chai.memms.corrective.maintenance.WorkOrderStatus;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.WorkOrderStatusChange;
import org.chai.memms.inventory.Department;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.memms.inventory.EquipmentStatus.EquipmentStatusChange;
import org.chai.memms.inventory.EquipmentType;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderStatus;
//import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderStatusChange;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventionResponsible;
import org.chai.memms.report.listing.CorrectiveMaintenanceReport;
import org.chai.memms.report.listing.EquipmentReport;
import org.chai.memms.report.listing.PreventiveMaintenanceReport;
import org.chai.memms.report.listing.SparePartReport
import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart;
//import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePartChange;
import org.chai.memms.spare.part.SparePartType;
import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;
import org.chai.memms.util.Utils;
import org.chai.memms.util.Utils.ReportType;
import org.chai.memms.util.Utils.ReportSubType;
import org.joda.time.DateTime;
import org.chai.memms.Warranty;

/**
 * @author Jean Kahigiso M.
 *
 */
class ListingController extends AbstractController{

	def equipmentListingReportService
	def sparePartListingReportService
	def workOrderListingReportService
	def preventiveOrderListingReportService
	//def grailsApplication
	def userService

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
		def listHtml = g.render(template:"/reports/listing/listing",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}

	def index ={
		redirect(action: "view", params: params)
	}

	def view ={
		redirect(action: "generalEquipmentsListing", params: params)
	}

	// default and predefined reports start

	// inventory

	def generalEquipmentsListing={
		if (log.isDebugEnabled()) log.debug("listing.generalEquipmentsListing start, params:"+params)

		adaptParamsForList()
		def equipments = equipmentListingReportService.getGeneralReportOfEquipments(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: ReportType.INVENTORY,
				reportSubType: ReportSubType.INVENTORY,
				selectedReport: message(code:'default.all.equipments.label'),
				template:"/reports/listing/listing"
			])
	}

	def obsoleteEquipments={
		if (log.isDebugEnabled()) log.debug("listing.obsoleteEquipments start, params:"+params)

		adaptParamsForList()
		def equipments = equipmentListingReportService.getObsoleteEquipments(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: ReportType.INVENTORY,
				reportSubType: ReportSubType.INVENTORY,
				selectedReport: message(code:'default.obsolete.label'),
				template:"/reports/listing/listing",
			])
	}

	def disposedEquipments={
		if (log.isDebugEnabled()) log.debug("listing.disposedEquipments start, params:"+params)

		adaptParamsForList()
		def equipments = equipmentListingReportService.getDisposedEquipments(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: ReportType.INVENTORY,
				reportSubType: ReportSubType.INVENTORY,
				selectedReport: message(code:'default.disposed.label'),
				template:"/reports/listing/listing",
			])
	}

	def underMaintenanceEquipments={
		if (log.isDebugEnabled()) log.debug("listing.underMaintenanceEquipments start, params:"+params)

		adaptParamsForList()
		def equipments = equipmentListingReportService.getUnderMaintenanceEquipments(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: ReportType.INVENTORY,
				reportSubType: ReportSubType.INVENTORY,
				selectedReport: message(code:'default.under.maintenance.label'),
				template:"/reports/listing/listing",
			])
	}

	def inStockEquipments={
		adaptParamsForList()
		def equipments = equipmentListingReportService.getInStockEquipments(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: ReportType.INVENTORY,
				reportSubType: ReportSubType.INVENTORY,
				selectedReport: message(code:'default.in.stock.label'),
				template:"/reports/listing/listing",
			])
	}

	def underWarrantyEquipments={
		adaptParamsForList()
		def warrantyExpirationDate
		def equipments = equipmentListingReportService.getUnderWarrantyEquipments(user,params)

		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: ReportType.INVENTORY,
				reportSubType: ReportSubType.INVENTORY,
				//TODO selectedReport: message(code:'default.in.stock.label'),
				template:"/reports/listing/listing"
			])
	}

	// corrective

	def generalWorkOrdersListing={
		adaptParamsForList()
		def workOrders = workOrderListingReportService.getAllWorkOrders(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(workOrders, "") <<
			[
				reportType: ReportType.CORRECTIVE,
				reportSubType: ReportSubType.WORKORDERS,
				selectedReport: message(code:'default.all.work.order.label'),
				template:"/reports/listing/listing",
			])
	}

	def lastMonthWorkOrders={
		adaptParamsForList()
		def workOrders = workOrderListingReportService.getWorkOrdersOfLastMonth(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(workOrders, "") <<
			[
				reportType: ReportType.CORRECTIVE,
				reportSubType: ReportSubType.WORKORDERS,
				selectedReport: message(code:'default.work.order.last.month.label'),
				template:"/reports/listing/listing",
			])
	}

	def workOrdersEscalatedToMMC={
		adaptParamsForList()
		def workOrders = workOrderListingReportService.getWorkOrdersEscalatedToMMC(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(workOrders, "") <<
			[
				reportType: ReportType.CORRECTIVE,
				reportSubType: ReportSubType.WORKORDERS,
				selectedReport: message(code:'default.work.order.escalated.to.mmc.label'),
				template:"/reports/listing/listing",
			])
	}

	// preventive

	def generalPreventiveOrdersListing={
		adaptParamsForList()
		def preventiveOrders = preventiveOrderListingReportService.getAllPreventions(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(preventiveOrders, "") <<
			[
				reportType: ReportType.PREVENTIVE,
				reportSubType: ReportSubType.WORKORDERS,
				selectedReport: message(code:'default.all.preventive.order.label'),
				template:"/reports/listing/listing",
			])
	}

	def equipmentsWithPreventionPlan={
		adaptParamsForList()
		def preventiveOrders = preventiveOrderListingReportService.getEquipmentsWithPreventionPlan(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(preventiveOrders, "") <<
			[
				reportType: ReportType.PREVENTIVE,
				reportSubType: ReportSubType.WORKORDERS,
				selectedReport: message(code:'default.equipments.with.prevention.label'),
				template:"/reports/listing/listing",
			])
	}
	
	//TODO see how to deal with periodic times either weekly, monthly, or any other
	def preventionsDelayed={
		adaptParamsForList()
		def preventiveOrders = preventiveOrderListingReportService.getEquipmentsWithPreventionPlan(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(preventiveOrders, "") <<
			[
				reportType: ReportType.PREVENTIVE,
				reportSubType: ReportSubType.WORKORDERS,
				selectedReport: message(code:'default.preventions.delayed.label'),
				template:"/reports/listing/listing",
			])
	}

	// spare parts
	def generalSparePartsListing={
		if (log.isDebugEnabled()) log.debug("listing.generalSparePartsListing start, params:"+params)

		adaptParamsForList()
		def spareParts = sparePartListingReportService.getGeneralReportOfSpareParts(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(spareParts, "") <<
			[
				reportType: ReportType.SPAREPARTS,
				reportSubType: ReportSubType.INVENTORY,
				template:"/reports/listing/listing"
			])
	}

	// Done
	def pendingOrderSparePartsListing={
		if (log.isDebugEnabled()) log.debug("listing.generalSparePartsListing start, params:"+params)

		adaptParamsForList()
		def spareParts = sparePartListingReportService.getPendingOrderSparePartsReport(user,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(spareParts, "") <<
			[
				reportType: ReportType.SPAREPARTS,
				reportSubType: ReportSubType.INVENTORY,
				template:"/reports/listing/listing"
			])
	}

	// default and predefined reports end

	// customized report wizard steps start

	def step1 ={
		if (log.isDebugEnabled()) log.debug("listing.step1 start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		//params to pass along to step 2
		def step1Params = [:]

		//params to load step 1
		def step1Model = [
			reportType: reportType,
			reportSubType: reportSubType
		]
		step1Model << [step1Params: step1Params]

		if (log.isDebugEnabled()) log.debug("listing.step1 end, step1Model:"+step1Model)
		render(template:"/reports/listing/customizedReport/step1",
		model: step1Model <<
		[
			reportType: reportType,
			reportSubType: reportSubType,
			dataLocations: dataLocations,
			step1Params: step1Params
		])
	}

	// customized report wizard step1 'remoteFunction'
	def customizedReportSubType ={
		if (log.isDebugEnabled())
			log.debug("listing.step1.customizedReportSubType start, params:"+params)

		def reportType = getReportType()

		if (log.isDebugEnabled()) log.debug("listing.step1.customizedReportSubType end, reportType:"+reportType)
		render(template:"/reports/listing/customizedReport/customizedReportSubType",
		model:
		[
			reportType: reportType
		])
	}

	def step2 ={
		if (log.isDebugEnabled()) log.debug("listing.step2 start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		//params from step 1 to pass along to step 3
		def step2Params = [:]
		step2Params.putAll params

		//params to load step 2
		def step2Model = [
			reportType: reportType,
			reportSubType: reportSubType,
			currencies: grailsApplication.config.site.possible.currency
		]
		step2Model << [step2Params: step2Params]

		if (log.isDebugEnabled()) log.debug("listing.step2 end, step2Model:"+step2Model)
		render(template:"/reports/listing/customizedReport/step2", model:step2Model)
	}

	def step3 ={
		if (log.isDebugEnabled()) log.debug("listing.step3 start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		//params from step 2 to pass along to step 4
		def step3Params = [:]
		step3Params.putAll params

		//params to load step 3
		def step3Model = [
			reportType: reportType,
			reportSubType: reportSubType
		]
		step3Model << [step3Params: step3Params]

		if (log.isDebugEnabled()) log.debug("listing.step3 end, step3Model:"+step3Model)
		render(template:"/reports/listing/customizedReport/step3", model:step3Model)
	}

	def step4 ={
		if (log.isDebugEnabled()) log.debug("listing.step4 start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		//params from step 3 to pass along to customized listing
		def step4Params = [:]
		step4Params.putAll params

		//params to load step 4
		def step4Model = [
			reportType: reportType,
			reportSubType: reportSubType
		]
		step4Model << [step4Params: step4Params]

		if (log.isDebugEnabled()) log.debug("listing.step4 end, step4Model:"+step4Model)
		render(template:"/reports/listing/customizedReport/step4", model:step4Model)
	}

	// customized report wizard steps end

	// customized report listing start

	def customizedListing ={
		if (log.isDebugEnabled()) log.debug("listing.customizedListing start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		def customizedListingParams = [:]
		customizedListingParams.putAll params

		def customizedReportName = params.get('customizedReportName')
		if(customizedReportName == null || customizedReportName.empty){
			def customizedReportTimestamp = new Date()
			def reportTypeTimestamp = message(code:'reports.type.'+reportType?.reportType)
			def reportSubTypeTimestamp = message(code:'reports.subType.'+reportSubType?.reportSubType)
			customizedReportName = 
				"Custom Report "+reportTypeTimestamp+" "+reportSubTypeTimestamp+" "+customizedReportTimestamp.format('yyyyMMddHHmmss')
		}
		def customizedReportSave = params.get('customizedReportSave')
		customizedListingParams << [
			customizedReportName:customizedReportName,
			customizedReportSave:customizedReportSave
		]

		if (log.isDebugEnabled()) log.debug("listing.customizedListing end, customizedListingParams:"+customizedListingParams)
		switch(reportType){
			case ReportType.INVENTORY:
				redirect(action: "customEquipmentListing", params: customizedListingParams)
				break;
			case ReportType.CORRECTIVE:
				redirect(action: "customWorkOrderListing", params: customizedListingParams)
				break;
			case ReportType.PREVENTIVE:
				redirect(action: "customPreventiveOrderListing", params: customizedListingParams)
				break;
			case ReportType.SPAREPARTS:
				redirect(action: "customSparePartsListing", params: customizedListingParams)
				break;
			default:
				break;
		}
	}

	def savedCustomizedListing ={
		if (log.isDebugEnabled()) log.debug("listing.savedCustomizedListing start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()
		
		// TODO get the user's saved report id
		// TODO get the saved report parameters

		// TODO set the saved report parameters
		def savedCustomizedListingParams = [:]
		def savedCustomizedListingReport = null
		
		switch(reportType){
			case ReportType.INVENTORY:
				// TODO get the savedCustomizedListingParams for the saved report
				savedCustomizedListingReport = equipmentListingReportService.getCustomReportOfEquipments(user,savedCustomizedListingParams,null)
				if (log.isDebugEnabled()) log.debug("listing.savedCustomizedListing # of equipments:"+savedCustomizedListingReport.size())
				break;
			case ReportType.CORRECTIVE:
				savedCustomizedListingReport = workOrderListingReportService.getCustomReportOfWorkOrders(user,savedCustomizedListingParams,null)
				if (log.isDebugEnabled()) log.debug("listing.savedCustomizedListing # of workOrders:"+savedCustomizedListingReport.size())
				break;
			case ReportType.PREVENTIVE:
				// TODO
				break;
			case ReportType.SPAREPARTS:
				// TODO
				break;
			default:
				break;
		}

		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(savedCustomizedListingReport, "") <<
			[
				reportType: reportType,
				reportSubType: reportSubType,
				//reportTypeOptions: reportTypeOptions,
				//customizedReportName: customizedReportName,
				template:"/reports/listing/listing"
			])
	}

	// inventory
	def customEquipmentListing ={
		adaptParamsForList()
		if (log.isDebugEnabled()) log.debug("listing.customEquipmentListing start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		def dataLocations = getDataLocations()
		def departments = getDepartments()
		def equipmentTypes = getEquipmentTypes()

		def fromCost = null
		if(params.get('fromCost') != null && !params.get('fromCost').empty)
			fromCost = Double.parseDouble(params.get('fromCost'))
		def toCost = null
		if(params.get('toCost') != null && !params.get('toCost').empty)
			toCost = Double.parseDouble(params.get('toCost'))
		def costCurrency = params.get('costCurrency')
		def equipmentReport=new EquipmentReport()

		def customEquipmentParams = [
			reportType: reportType,
			reportSubType: reportSubType,
			dataLocations: dataLocations,
			departments: departments,
			equipmentTypes: equipmentTypes,
			fromCost: fromCost,
			toCost: toCost,
			costCurrency: costCurrency
		]

		if(reportSubType == ReportSubType.INVENTORY){
			def fromAcquisitionPeriod = getPeriod('fromAcquisitionPeriod')
			def toAcquisitionPeriod = getPeriod('toAcquisitionPeriod')
			def noAcquisitionPeriod = params.get('noAcquisitionPeriod')
			def equipmentStatus = getInventoryStatus()
			def obsolete = params.get('obsolete')
			def warranty = params.get('warranty')
			customEquipmentParams << [
				fromAcquisitionPeriod: fromAcquisitionPeriod,
				toAcquisitionPeriod: toAcquisitionPeriod,
				noAcquisitionPeriod: noAcquisitionPeriod,
				equipmentStatus: equipmentStatus,
				obsolete: obsolete,
				warranty: warranty
			]
		}

		if(reportSubType == ReportSubType.STATUSCHANGES){
			def statusChanges = getInventoryStatusChanges()
			def fromStatusChangesPeriod = getPeriod('fromStatusChangesPeriod')
			def toStatusChangesPeriod = getPeriod('toStatusChangesPeriod')
			customEquipmentParams << [
				statusChanges: statusChanges,
				fromStatusChangesPeriod: fromStatusChangesPeriod,
				toStatusChangesPeriod: toStatusChangesPeriod
			]
		}

		def reportTypeOptions = getReportTypeOptions('inventoryOptions')
		def customizedReportName = params.get('customizedReportName')
		def customizedReportSave = params.get('customizedReportSave')
		customEquipmentParams << [
			reportTypeOptions: reportTypeOptions,
			customizedReportName: customizedReportName,
			customizedReportSave: customizedReportSave
		]

		if (log.isDebugEnabled()) log.debug("listing.customEquipmentListing end, customEquipmentParams:"+customEquipmentParams)

		if(customizedReportSave){
			equipmentListingReportService.saveEquipmentReportParams(user,equipmentReport, customEquipmentParams, params)
		}

		def equipments = equipmentListingReportService.getCustomReportOfEquipments(user,customEquipmentParams,params)
		if (log.isDebugEnabled()) log.debug("listing.customEquipmentListing # of equipments:"+equipments.size())

		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(equipments, "") <<
			[
				reportType: reportType,
				reportSubType: reportSubType,
				reportTypeOptions: reportTypeOptions,
				customizedReportName: customizedReportName,
				customizedReportSave: customizedReportSave,
				customEquipmentParams: customEquipmentParams,
				template:"/reports/listing/listing"
			])
	}

	// corrective
	def customWorkOrderListing ={
		if (log.isDebugEnabled()) log.debug("listing.customWorkOrderListing start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		def dataLocations = getDataLocations()
		def departments = getDepartments()
		def equipmentTypes = getEquipmentTypes()

		def fromCost = null
		if(params.get('fromCost') != null && !params.get('fromCost').empty)
			fromCost = Double.parseDouble(params.get('fromCost'))
		def toCost = null
		if(params.get('toCost') != null && !params.get('toCost').empty)
			toCost = Double.parseDouble(params.get('toCost'))
		def costCurrency = params.get('costCurrency')

		def warranty = params.get('warranty')
		def correctiveMaintenanceReport = new CorrectiveMaintenanceReport()

		def customWorkOrderParams = [
			reportType: reportType,
			reportSubType: reportSubType,
			dataLocations: dataLocations,
			departments: departments,
			equipmentTypes: equipmentTypes,
			fromCost: fromCost,
			toCost: toCost,
			costCurrency: costCurrency,
			warranty: warranty
		]

		if(reportSubType == ReportSubType.WORKORDERS){
			def workOrderStatus = getCorrectiveStatus()
			def fromWorkOrderPeriod = getPeriod('fromWorkOrderPeriod')
			def toWorkOrderPeriod = getPeriod('toWorkOrderPeriod')
			customWorkOrderParams << [
				workOrderStatus: workOrderStatus,
				fromWorkOrderPeriod: fromWorkOrderPeriod,
				toWorkOrderPeriod: toWorkOrderPeriod
			]
		}

		def statusChanges = null
		if(reportSubType == ReportSubType.STATUSCHANGES){
			statusChanges = getCorrectiveStatusChanges()
			def fromStatusChangesPeriod = getPeriod('fromStatusChangesPeriod')
			def toStatusChangesPeriod = getPeriod('toStatusChangesPeriod')
			customWorkOrderParams << [
				statusChanges: statusChanges,
				fromStatusChangesPeriod: fromStatusChangesPeriod,
				toStatusChangesPeriod: toStatusChangesPeriod
			]
		}

		def reportTypeOptions = getReportTypeOptions('correctiveOptions')
		def customizedReportName = params.get('customizedReportName')
		def customizedReportSave = params.get('customizedReportSave')
		customWorkOrderParams << [
			reportTypeOptions: reportTypeOptions,
			customizedReportName: customizedReportName,
			customizedReportSave: customizedReportSave
		]

		if (log.isDebugEnabled()) log.debug("listing.customWorkOrderListing, customWorkOrderParams:"+customWorkOrderParams)

		if(customizedReportSave){
			workOrderListingReportService.saveWorkOrderReportParams(user, correctiveMaintenanceReport, customWorkOrderParams, params)
		}

		adaptParamsForList()
		def displayableWorkOrders=[]
		def warrantyExpirationDate
		def workOrders = []

		def workOrderz = workOrderListingReportService.getCustomReportOfWorkOrders(user,customWorkOrderParams,params)
		if (log.isDebugEnabled()) log.debug("llisting.customWorkOrderListing # of workOrders:"+workOrderz.size())
		
		//TODO move this into service method
		if(warranty != null && !warranty.empty){
			for(WorkOrder workOrder: workOrderz){
				if (workOrder.equipment.warranty.startDate!=null && workOrder.equipment.warrantyPeriod.numberOfMonths!=null && workOrder.equipment.warrantyPeriod.months != null) {
					warrantyExpirationDate= (workOrder.equipment.warranty.startDate).plus((workOrder.equipment.warrantyPeriod.numberOfMonths))
					if (log.isDebugEnabled()) log.debug("CALCULATED DATE "+warrantyExpirationDate +"START DATE "+workOrder.equipment.warranty.startDate +"WARRANTY PERIOD "+workOrder.equipment.warrantyPeriod.months)
					if (warrantyExpirationDate > new Date())
						displayableWorkOrders.add(workOrder)
				}
				warrantyExpirationDate=null
			}
		}else{
			displayableWorkOrders=workOrderz
		}
		workOrders=displayableWorkOrders

		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(workOrders, "") <<
			[
				reportType: reportType,
				reportSubType: reportSubType,
				reportTypeOptions: reportTypeOptions,
				customizedReportName: customizedReportName,
				customizedReportSave: customizedReportSave,
				customWorkOrderParams: customWorkOrderParams,
				template:"/reports/listing/listing"
			])
	}

	// preventive
	def customPreventiveOrderListing ={
		if (log.isDebugEnabled()) log.debug("listing.customPreventiveOrderListing start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		def dataLocations = getDataLocations()
		def departments = getDepartments()
		def equipmentTypes = getEquipmentTypes()

		def fromCost = null
		if(params.get('fromCost') != null && !params.get('fromCost').empty)
			fromCost = Double.parseDouble(params.get('fromCost'))
		def toCost = null
		if(params.get('toCost') != null && !params.get('toCost').empty)
			toCost = Double.parseDouble(params.get('toCost'))
		def costCurrency = params.get('costCurrency')
		
		def preventiveMaintenanceReport= new PreventiveMaintenanceReport()

		def customPreventiveOrderParams = [
			reportType: reportType,
			reportSubType: reportSubType,
			dataLocations: dataLocations,
			departments: departments,
			equipmentTypes: equipmentTypes,
			fromCost: fromCost,
			toCost: toCost,
			costCurrency: costCurrency
		]

		if(reportSubType == ReportSubType.WORKORDERS){
			def workOrderStatus = getPreventiveStatus()
			def fromWorkOrderPeriod = getPeriod('fromWorkOrderPeriod')
			def toWorkOrderPeriod = getPeriod('toWorkOrderPeriod')
			def whoIsResponsible = getPreventionResponsible('whoIsResponsible')
			customPreventiveOrderParams << [
				workOrderStatus: workOrderStatus,
				fromWorkOrderPeriod: fromWorkOrderPeriod,
				toWorkOrderPeriod: toWorkOrderPeriod,
				whoIsResponsible: whoIsResponsible
			]
		}

		def reportTypeOptions = getReportTypeOptions('preventiveOptions')
		def customizedReportName = params.get('customizedReportName')
		def customizedReportSave = params.get('customizedReportSave')
		customPreventiveOrderParams << [
			reportTypeOptions: reportTypeOptions,
			customizedReportName: customizedReportName,
			customizedReportSave: customizedReportSave
		]

		if (log.isDebugEnabled()) log.debug("listing.customPreventiveOrderListing, customPreventiveOrderParams:"+customPreventiveOrderParams)

		if(customizedReportSave){
			preventiveOrderListingReportService.savePreventiveOrderReportParams(user, preventiveMaintenanceReport, customPreventiveOrderParams, params)
		}

		adaptParamsForList()
		def preventiveOrders = preventiveOrderListingReportService.getCustomReportOfPreventiveOrders(user,customPreventiveOrderParams,params)
		if(!request.xhr)
			render(view:"/reports/reports",
			model: model(preventiveOrders, "") <<
			[
				reportType: reportType,
				reportSubType: reportSubType,
				reportTypeOptions: reportTypeOptions,
				customizedReportName: customizedReportName,
				customizedReportSave: customizedReportSave,
				customPreventiveOrderParams: customPreventiveOrderParams,
				template:"/reports/listing/listing"
			])
	}

	// spare parts
	def customSparePartsListing ={
		if (log.isDebugEnabled()) log.debug("listing.customSparePartsListing start, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()

		def dataLocations = getDataLocations()
		def sparePartTypes = getSparePartTypes()
		
		def sparePartReport= new SparePartReport()

		def customSparePartsParams = [
			reportType: reportType,
			reportSubType: reportSubType,
			dataLocations: dataLocations,
			sparePartTypes: sparePartTypes
		]

		if(reportSubType == ReportSubType.INVENTORY){
			def sparePartStatus = getSparePartStatus()
			def fromAcquisitionPeriod = getPeriod('fromAcquisitionPeriod')
			def toAcquisitionPeriod = getPeriod('toAcquisitionPeriod')
			def noAcquisitionPeriod = params.get('noAcquisitionPeriod')
			customSparePartsParams << [
				sparePartStatus: sparePartStatus,
				fromAcquisitionPeriod: fromAcquisitionPeriod,
				toAcquisitionPeriod: toAcquisitionPeriod,
				noAcquisitionPeriod: noAcquisitionPeriod
			]
		}

		if(reportSubType == ReportSubType.STOCKOUT){
			def stockOut = params.get('stockOut')
			def stockOutMonths = params.get('stockOutMonths')
			customSparePartsParams << [
				stockOut: stockOut,
				stockOut: stockOutMonths
			]
		}

		if(reportSubType == ReportSubType.USERATE){ }

		def reportTypeOptions = getReportTypeOptions('spartPartsOptions')
		def customizedReportName = params.get('customizedReportName')
		def customizedReportSave = params.get('customizedReportSave')
		customSparePartsParams << [
			reportTypeOptions: reportTypeOptions,
			customizedReportName: customizedReportName,
			customizedReportSave: customizedReportSave
		]

		if (log.isDebugEnabled()) log.debug("listing.customSparePartsListing, customSparePartsParams:"+customSparePartsParams)

		if(customizedReportSave){
			
			sparePartListingReportService.saveSparePartReportParams(user, sparePartReport, customSparePartsParams, params)
		
			
			
			
			}

		// TODO
		// adaptParamsForList()
		// def equipments = workOrderListingReportService.getCustomReportOfSpareParts(user,customSparePartsParams,params)

		if(!request.xhr)
			render(view:"/reports/reports",
			model: [
				reportType: reportType,
				reportSubType: reportSubType,
				reportTypeOptions: reportTypeOptions,
				customizedReportName: customizedReportName,
				customizedReportSave: customizedReportSave,
				template:"/reports/listing/listing"
			])
	}

	// customized report listing end

	// customized report wizard params start

	def getReportType(){
		ReportType reportType = params.get('reportType')
		if(log.isDebugEnabled())
			log.debug("abstract.reportType param:"+reportType+")")
		if(reportType == null) reportType = ReportType.INVENTORY
		return reportType
	}
	def getReportSubType(){
		ReportSubType reportSubType = params.get('reportSubType')
		if(log.isDebugEnabled())
			log.debug("abstract.reportSubType param:"+reportSubType+")")
		if(reportSubType == null) reportSubType = ReportSubType.INVENTORY
		return reportSubType
	}

	public Set<DataLocation> getDataLocations() {
		if(log.isDebugEnabled()) log.debug("abstract.dataLocations params:"+params)
		Set<DataLocation> dataLocations = new HashSet<DataLocation>()
		if(params.get('allDataLocations')){
			if(log.isDebugEnabled()) log.debug("abstract.dataLocations ALL")
			if(user.location instanceof Location)
				dataLocations.addAll(user.location.collectDataLocations(null))
			else{
				dataLocations = []
				dataLocations.add(user.location as DataLocation)
				if(userService.canViewManagedEquipments(user))
					dataLocations.addAll(((DataLocation)user.location).manages)
			}
		}
		else if (params.list('dataLocations') != null && !params.list('dataLocations').empty) {
			if(log.isDebugEnabled()) log.debug("abstract.dataLocations CUSTOM")
			def types = params.list('dataLocations')
			dataLocations.addAll(types.collect{ it ->
				if(log.isDebugEnabled())
					log.debug("abstract.dataLocations dataLocation:"+it+", isNumber:"+NumberUtils.isNumber(it as String))
				NumberUtils.isNumber(it as String) ? DataLocation.get(it) : null
			} - null)
		}

		return dataLocations
	}
	public Set<Department> getDepartments() {
		if(log.isDebugEnabled()) log.debug("abstract.departments params:"+params)
		Set<Department> departments = new HashSet<Department>()
		if(params.get('allDepartments')){
			if(log.isDebugEnabled()) log.debug("abstract.departments ALL")
			// TODO ?
			departments = Department.list()
		}
		else if (params.list('departments') != null && !params.list('departments').empty) {
			if(log.isDebugEnabled()) log.debug("abstract.departments CUSTOM")
			def types = params.list('departments')
			departments.addAll(types.collect{ it ->
				if(log.isDebugEnabled())
					log.debug("abstract.departments department:"+it+", isNumber:"+NumberUtils.isNumber(it as String))
				NumberUtils.isNumber(it as String) ? Department.get(it) : null
			} - null)
		}

		return departments
	}
	public Set<EquipmentType> getEquipmentTypes() {
		if(log.isDebugEnabled()) log.debug("abstract.equipmentTypes params:"+params)
		Set<EquipmentType> equipmentTypes = new HashSet<EquipmentType>()
		if(params.get('allEquipmentTypes')){
			if(log.isDebugEnabled()) log.debug("abstract.equipmentTypes ALL")
			//TODO ?
			equipmentTypes = EquipmentType.list()
		}
		else if (params.list('equipmentTypes') != null && !params.list('equipmentTypes').empty) {
			if(log.isDebugEnabled()) log.debug("abstract.equipmentTypes CUSTOM")
			def types = params.list('equipmentTypes')
			equipmentTypes.addAll(types.collect{ it ->
				if(log.isDebugEnabled())
					log.debug("abstract.equipmentTypes equipmentType:"+it+", isNumber:"+NumberUtils.isNumber(it as String))
				NumberUtils.isNumber(it as String) ? EquipmentType.get(it) : null
			} - null)
		}
		return equipmentTypes
	}
	public Set<SparePartType> getSparePartTypes() {
		if(log.isDebugEnabled()) log.debug("abstract.sparePartTypes params:"+params)
		Set<SparePartType> sparePartTypes = new HashSet<SparePartType>()
		if(log.isDebugEnabled()) log.debug("abstract.sparePartTypes ALL")
		if(params.get('allSparePartTypes')){
			//TODO ?
			sparePartTypes = SparePartType.list()
		}
		else if (params.list('sparePartTypes') != null && !params.list('sparePartTypes').empty) {
			if(log.isDebugEnabled()) log.debug("abstract.sparePartTypes CUSTOM")
			def types = params.list('sparePartTypes')
			sparePartTypes.addAll(types.collect{ it ->
				if(log.isDebugEnabled())
					log.debug("abstract.sparePartTypes sparePartType:"+it+", isNumber:"+NumberUtils.isNumber(it as String))
				NumberUtils.isNumber(it as String) ? SparePartType.get(it) : null
			} - null)
		}
		return sparePartTypes
	}

	public Date getPeriod(String periodParam){
		def date = null
		def period = params.get(periodParam);
		if(log.isDebugEnabled()) log.debug("abstract.getPeriod period param:"+periodParam+", value:"+period+", class:"+period?.class)
		if(period != null && !period.empty) {
			date  = Utils.parseDate(period)
			if(log.isDebugEnabled()) log.debug("abstract.getPeriod date param:"+periodParam+", value:"+date+", class:"+date?.class)
			return date
		}
		return date
	}

	public Set<Status> getInventoryStatus(){
		if(log.isDebugEnabled()) log.debug("abstract.inventoryStatus params:"+params)
		Set<Status> inventoryStatus = new HashSet<Status>()
		if (params.list('equipmentStatus') != null && !params.list('equipmentStatus').empty) {
			def types = params.list('equipmentStatus')
			types.each{ it ->
				if(log.isDebugEnabled()) log.debug("abstract.equipmentStatus equipmentStatus:"+it)
				if(it != null) inventoryStatus.add(Enum.valueOf(Status.class, it))
			}
		}
		return inventoryStatus
	}
	public List<EquipmentStatusChange> getInventoryStatusChanges(){
		List<EquipmentStatusChange> inventoryStatusChanges = []
		if(log.isDebugEnabled()) log.debug("abstract.inventoryStatusChanges start params:"+params)
		if (params.list('statusChanges') != null && !params.list('statusChanges').empty) {
			def statusChanges = params.list('statusChanges')
			if(log.isDebugEnabled()) log.debug("abstract.inventoryStatusChanges statusChanges:"+statusChanges)
			statusChanges.each { it ->
				if(log.isDebugEnabled()) log.debug("abstract.inventoryStatusChanges statusChange:"+it)
				if(it != null) inventoryStatusChanges.add(Enum.valueOf(EquipmentStatusChange.class, it))
			}
		}
		if(log.isDebugEnabled())
			log.debug("abstract.inventoryStatusChanges end statusChanges:"+inventoryStatusChanges)
		return inventoryStatusChanges
	}

	public Set<OrderStatus> getCorrectiveStatus(){
		if(log.isDebugEnabled()) log.debug("abstract.correctiveStatus params:"+params)
		Set<OrderStatus> correctiveStatus = new HashSet<OrderStatus>()
		if (params.list('workOrderStatus') != null && !params.list('workOrderStatus').empty) {
			def types = params.list('workOrderStatus')
			types.each{ it ->
				if(log.isDebugEnabled()) log.debug("abstract.workOrderStatus workOrderStatus:"+it)
				if(it != null) correctiveStatus.add(Enum.valueOf(OrderStatus.class, it))
			}
		}
		return correctiveStatus
	}
	public List<WorkOrderStatusChange> getCorrectiveStatusChanges(){
		List<WorkOrderStatusChange> correctiveStatusChanges = []
		if(log.isDebugEnabled()) log.debug("abstract.correctiveStatusChanges start params:"+params)
		if (params.list('statusChanges') != null && !params.list('statusChanges').empty) {
			def statusChanges = params.list('statusChanges')
			if(log.isDebugEnabled()) log.debug("abstract.correctiveStatusChanges statusChanges:"+statusChanges)
			statusChanges.each { it ->
				if(log.isDebugEnabled()) log.debug("abstract.correctiveStatusChanges statusChange:"+it)
				if(it != null) correctiveStatusChanges.add(Enum.valueOf(WorkOrderStatusChange.class, it))
			}
		}
		if(log.isDebugEnabled())
			log.debug("abstract.correctiveStatusChanges end statusChanges:"+correctiveStatusChanges)
		return correctiveStatusChanges
	}

	public Set<PreventiveOrderStatus> getPreventiveStatus(){
		if(log.isDebugEnabled()) log.debug("abstract.preventiveStatus params:"+params)
		Set<PreventiveOrderStatus> preventiveStatus = new HashSet<PreventiveOrderStatus>()
		if (params.list('workOrderStatus') != null && !params.list('workOrderStatus').empty) {
			def types = params.list('workOrderStatus')
			types.each{ it ->
				if(log.isDebugEnabled()) log.debug("abstract.preventiveStatus preventiveStatus:"+it)
				if(it != null) preventiveStatus.add(Enum.valueOf(PreventiveOrderStatus.class, it))
			}
		}
		return preventiveStatus
	}
	public List<PreventionResponsible> getPreventionResponsible(String preventionResponsibleParam){
		if(log.isDebugEnabled()) log.debug("abstract.PreventionResponsible params:"+params)
		List<PreventionResponsible> preventionResponsible = []
		if (params.list(preventionResponsibleParam) != null && !params.list(preventionResponsibleParam).empty) {
			def types = params.list(preventionResponsibleParam)
			types.each{ it ->
				if(log.isDebugEnabled()) log.debug("abstract.PreventionResponsible PreventionResponsible:"+it)
				if(it != null) preventionResponsible.add(Enum.valueOf(PreventionResponsible.class, it))
			}
		}
		return preventionResponsible
	}

	public Set<StatusOfSparePart> getSparePartStatus(){
		if(log.isDebugEnabled()) log.debug("abstract.sparePartStatus params:"+params)
		Set<StatusOfSparePart> sparePartStatus = new HashSet<StatusOfSparePart>()
		if (params.list('sparePartStatus') != null && !params.list('sparePartStatus').empty) {
			def types = params.list('sparePartStatus')
			types.each{ it ->
				if(log.isDebugEnabled()) log.debug("abstract.sparePartStatus sparePartStatus:"+it)
				if(it != null) sparePartStatus.add(Enum.valueOf(StatusOfSparePart.class, it))
			}
		}
		return sparePartStatus
	}

	public Set<String> getReportTypeOptions(String reportTypeOptionParam){
		if(log.isDebugEnabled()) log.debug("abstract.reportTypeOptions params:"+params)
		Set<String> reportTypeOptions = new HashSet<String>()
		if (params.list(reportTypeOptionParam) != null && !params.list(reportTypeOptionParam).empty) {
			def options = params.list(reportTypeOptionParam)
			options.each{ it ->
				if(log.isDebugEnabled()) log.debug("abstract.reportTypeOption reportTypeOption:"+it)
				if(it != null) reportTypeOptions.add(it)
			}
		}
		return reportTypeOptions
	}
	
	// customized report wizard params end
	
	// customized report wizard end
}
