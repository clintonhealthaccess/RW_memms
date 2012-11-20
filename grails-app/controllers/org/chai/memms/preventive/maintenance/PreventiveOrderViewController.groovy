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
package org.chai.memms.preventive.maintenance

import org.chai.location.CalculationLocation;
import org.chai.location.DataLocation
import org.chai.memms.AbstractController
import org.chai.memms.AbstractEntityController;
import org.chai.memms.inventory.Equipment;

/**
 * @author Jean Kahigiso M.
 *
 */
class PreventiveOrderViewController extends AbstractController {
	
	def preventiveOrderService
	def equipmentService
	def maintenanceService
	
	def getEntityClass() {
		return DurationBasedOrder.class;
	}
	
	def getLabel() {
		return "preventive.order.label";
	}
	
	def list = {	
		adaptParamsForList()
		List<DurationBasedOrder> orders = []
		Equipment equipment = null
		CalculationLocation  dataLocation = null
		if(params["dataLocation.id"]) dataLocation = CalculationLocation.get(params.int("dataLocation.id"))
		if(params["equipment.id"]) equipment = Equipment.get(params.int("equipment.id"))
		
		if(location)
			orders = preventiveOrderService.getPreventiveOrderByDataLocationManages(dataLocation,params)
		if(equipment)
			orders = preventiveOrderService.getPreventiveOrderByEquipment(equipment,params)
		 if(request.xhr){
			 this.ajaxModel(orders,dataLocation,equipment,"")
		 }else{
			render (view: '/entity/list', model:[
				template:"preventiveOrder/preventiveOrderList",
				filterTemplate:"preventiveOrder/preventiveOrderFilter",
				listTop:"preventiveOrder/listTop",
				dataLocation:dataLocation,		
				equipment:equipment,
				entities: orders,
				entityCount: orders.totalCount,
			])
		}
	}
	
	def ajaxModel(def entities,def dataLocation,def equipment, def searchTerm) {
		def model = [entities: entities,entityCount: entities.totalCount,dataLocation:dataLocation,equipment:equipment,q:searchTerm]
		def listHtml = g.render(template:"/entity/preventive/preventiveOrderList",model:model)
		render(contentType:"text/json") { results = [listHtml] }
	}
	
	def summaryPage = {
		if(user.location instanceof DataLocation) redirect (controller: "preventiveOrderView", action: "list",params:['dataLocation.id':user.location.id])

		def location = Location.get(params.int('location'))
		def dataLocationTypesFilter = getLocationTypes()
		def template = null
		def preventiveMaintenances = null

		adaptParamsForList()
		def locationSkipLevels = maintenanceService.getSkipLocationLevels()

		if (location != null) {
			template = '/preventiveSummaryPage/sectionTable'
			preventiveMaintenances = maintenanceService.getPreventiveMaintenancesByLocation(location,dataLocationTypesFilter,params)
		}
		render (view: '/preventiveSummaryPage/summaryPage', model: [
					preventiveMaintenances:preventiveMaintenances?.preventiveMaintenanceList,
					currentLocation: location,
					currentLocationTypes: dataLocationTypesFilter,
					template: template,
					entityCount: preventiveMaintenances?.totalCount,
					locationSkipLevels: locationSkipLevels
				])
	}
	
	

}