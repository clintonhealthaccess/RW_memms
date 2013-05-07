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

	def getEntityClass() {
		return Equipment.class;
	}
	def getLabel() {
		return "equipment.listing.report.label";
	}
	
	def model(def entities, def dataLocation) {
		return [
			entities: entities,
			entityCount: entities.totalCount,
			dataLocation:dataLocation,
			entityClass:getEntityClass(),
			code: getLabel()
		]
	}
	def ajaxModel(def entities,def dataLocation,def searchTerm) {
		def model = model(entities, dataLocation) << [q:searchTerm]
		def listHtml = g.render(template:"/entity/reports/listing",model:model)
		render(contentType:"text/json") { results = [listHtml] }
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
	//static final String NYANZA = "Kivuye HC"

	def listEquipments={
		def dataLocation = DataLocation.get(params.int('dataLocation.id'))
		//def dataLocation = DataLocation.findByCode(NYANZA)
		//def location = DataLocation.get(params.long('dataLocation'))
		
		//def dataLocation = DataLocation.find(user.location as DataLocation)
		adaptParamsForList()
		def location=user.location
		
		//TODO To be checked Yesterday morning from EquipmentService
		def dataLocations = []
		if(location instanceof Location) dataLocations.addAll(location.getDataLocations([:], [:]))
		else{
			dataLocations.add((DataLocation)location)
			dataLocations.addAll((location as DataLocation).manages)
		}
		
		
		def equipments
		

		if (dataLocation != null){
			if(!user.canAccessCalculationLocation(dataLocation)) response.sendError(404)
			equipments = equipmentListingReportService.getReportOfEquipmentsByDataLocationAndManages(dataLocation,params)
		}
		else equipments = equipmentListingReportService.getReportOfMyEquipments(user,params)

		if(request.xhr){
			this.ajaxModel(equipments,dataLocation,"")
		}else{
			render(view:"/entity/reports", model: model(equipments, dataLocation) << [
				template:"/entity/reports/listing",
			])
		}
	}

	def searchEquipmentsByCriteria= {
		DataLocation dataLocation = DataLocation.get(params.int("dataLocation.id"))
		if (dataLocation != null && !user.canAccessCalculationLocation(dataLocation))
			response.sendError(404)
		else{
			adaptParamsForList()
			def equipments = equipmentListingReportService.searchEquipmentByCriteria(params['q'],user,dataLocation,params)
			if(!request.xhr)
				render(view:"/entity/reports", model: model(equipments, dataLocation) << [
					template:"/entity/reports/listing",
				])
			else
				this.ajaxModel(equipments,dataLocation,params['q'])
		}
	}
}
