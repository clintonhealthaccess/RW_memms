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

import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location;
import org.chai.memms.AbstractController;
import org.chai.memms.inventory.Department;
import org.chai.memms.inventory.EquipmentType;
import org.chai.memms.util.Utils
import org.chai.memms.util.Utils.ReportType
import org.chai.memms.util.Utils.ReportSubType

/**
 * @author Jean Kahigiso M.
 *
 */
class ReportsController extends AbstractController{

	def locationService

	def dashboard ={
		if (log.isDebugEnabled()) log.debug("reports.dashboard, params:"+params)

		//TODO Pivot Access

		render(view: '/entity/reports', model: 
			[
				template:"/entity/reports/dashboard"
			])
	}	

	def listing ={
		if (log.isDebugEnabled()) log.debug("reports.listing, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()
		def dataLocations = getDataLocations()

		render(view: '/entity/reports', model: 
			[
				template:"/entity/reports/listing",
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations
			])
	}

	def step1 ={
		if (log.isDebugEnabled()) log.debug("reports.step1, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()
		// def dataLocations = getDataLocations()

		def step1Model = [
			reportType: reportType,
			reportSubType: reportSubType
			// dataLocations: dataLocations
		]

		def step1Params = [:]
    	step1Params.putAll params
    	step1Params.remove 'reportType'
    	step1Params.remove 'reportSubType'
    	step1Model << [step1Params: step1Params]
    	//TODO remove everything? or keep some params ex. dataLocations ???

		render(template:"/entity/reports/customizedReport/step1", 
			model: 
			[
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations,
				step1Params: step1Params
			])
	}

	def step2 ={
		if (log.isDebugEnabled()) log.debug("reports.step2, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()
		// def dataLocations = getDataLocations()

		def step2Model = [
			reportType: reportType,
			reportSubType: reportSubType,
			// dataLocations: dataLocations
			currencies: grailsApplication.config.site.possible.currency
		]

	    def step2Params = [:]
	    step2Params.putAll step2Model
	    step2Model << [step2Params: step2Params]

		render(template:"/entity/reports/customizedReport/step2", 
			model:step2Model)
	}

	def step3 ={
		if (log.isDebugEnabled()) log.debug("reports.step3, params:"+params)

		def reportType = getReportType()
		def reportSubType = getReportSubType()
		def dataLocations = getDataLocations()

		def step3Model = [
			reportType: reportType,
			reportSubType: reportSubType,
			dataLocations: dataLocations
		]

		switch(reportType){
			case ReportType.INVENTORY:
			case ReportType.CORRECTIVE:
			case ReportType.PREVENTIVE:
				def departments = getDepartments()
				def equipmentTypes = getEquipmentTypes()
				// TODO def fromCost = getCostPeriod('fromCost')
				// TODO def toCost = getCostPeriod('toCost')
				def costCurrency = params.get('costCurrency')
				step3Model << [
					departments: departments,
					equipmentTypes: equipmentTypes,
					// fromCost: fromCost,
					// toCost: toCost,
					costCurrency: costCurrency
				]
				break;
			case ReportType.SPAREPARTS:
				// TODO def sparePartTypes = getSparePartTypes()
				// step3Model << [
				// 	sparePartTypes: sparePartTypes
				// ]
				break;
			default:
				break;
		}

		switch(reportSubType){
			case ReportSubType.INVENTORY:
				// TODO def fromPeriod = getPeriods('fromAcquisitionPeriod')
				// TODO def toPeriod = getPeriods('toAcquisitionPeriod')
				// step3Model << [
				// 	fromAcquisitionPeriod: fromPeriod,
				// 	toAcquisitionPeriod: toPeriod
				// ]
				if(reportType == ReportType.INVENTORY){
					// TODO def equipmentStatus = getStatus('equipmentStatus')
					// TODO def fromPeriod = getPeriods('fromAcquisitionPeriod')
					// TODO def toPeriod = getPeriods('toAcquisitionPeriod')
					def obsolete = params.get('obsolete')
					def warranty = params.get('warranty')
					step3Model << [
						// equipmentStatus: equipmentStatus,
						obsolete: obsolete,
						warranty: warranty
					]	
				}
				if(reportType == ReportType.SPAREPARTS){
					// TODO def sparePartStatus = getStatus('sparePartStatus')
					// step3Model << [sparePartStatus: sparePartStatus]
				}
				break;
			case ReportSubType.WORKORDERS:
				// TODO def fromPeriod = getPeriods('fromWorkOrderPeriod')
				// TODO def toPeriod = getPeriods('toWorkOrderPeriod')
				// step3Model << [
				// 	fromWorkOrderPeriod: fromPeriod,
				// 	toWorkOrderPeriod: toPeriod
				// ]
				if(reportType == ReportType.CORRECTIVE){
					// TODO def workOrderStatus = getCorrectiveWorkOrderStatus()
					def warranty = params.get('warranty')
					step3Model << [
						// workOrderStatus: workOrderStatus,
						warranty: warranty
					]
				}
				if(reportType == ReportType.PREVENTIVE) {
					// TODO def workOrderStatus = getPreventiveWorkOrderStatus()
					def whoIsResponsible = params.list('whoIsResponsible')
					step3Model << [
						// workOrderStatus: workOrderStatus,
						whoIsResponsible: whoIsResponsible
					]
				}
				break;
			case ReportSubType.STATUSCHANGES:
				// TODO def fromPeriod = getPeriods('fromStatusChangesPeriod')
				// TODO def toPeriod = getPeriods('toStatusChangesPeriod')
				// step3Model << [
				// 	fromStatusChangesPeriod: fromPeriod,
				// 	toStatusChangesPeriod: toPeriod
				// ]
				if(reportType == ReportType.INVENTORY){
					// TODO def statusChanges = getInventoryStatusChanges()
					// step3Model << [statusChanges: statusChanges]
				}
				if(reportType == ReportType.CORRECTIVE){
					// TODO def statusChanges = getCorrectiveStatusChanges()
					def warranty = params.get('warranty')
					step3Model << [
						// statusChanges: statusChanges,
						warranty: warranty
					]
				}
				if(reportType == ReportType.PREVENTIVE) {
					// TODO def statusChanges = getPreventiveStatusChanges()
					def doneByWho = params.list('doneByWho')
					step3Model << [
						// statusChanges: statusChanges,
						doneByWho: doneByWho
					]
				}
				if(reportType == ReportType.SPAREPARTS){
					// TODO def statusChanges = getSparePartsStatusChanges()
					// step3Model << [statusChanges: statusChanges]
				}
				break;
			case ReportSubType.STOCKOUT:
				def stockOut = params.get('stockOut')
				def stockOutMonths = params.get('stockOutMonths')
				step3Model << [
					stockOut: stockOut,
					stockOutMonths: stockOutMonths
				]
				break;

		}

		render(template:"/entity/reports/customizedReport/step3", 
			model:step3Model)
	}

	def customizedListing ={
		if (log.isDebugEnabled()) log.debug("reports.customizedListing, params:"+params)

		// TODO
	}

	def customizedReportSubType ={
		if (log.isDebugEnabled()) 
			log.debug("reports.step1.customizedReportSubType, params:"+params)

		def reportType = getReportType()

		if (log.isDebugEnabled()) 
			log.debug("reports.step1.customizedReportSubType, reportType:"+reportType)

		render(template:"/entity/reports/customizedReport/customizedReportSubType",
			model: 
			[
				reportType: reportType
			])
	}
}
