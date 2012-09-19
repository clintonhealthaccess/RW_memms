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
package org.chai.memms.maintenance

import org.chai.memms.AbstractEntityController;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;

/**
 * @author Jean Kahigiso M.
 *
 */
class WorkOrderController extends AbstractEntityController{
	def workOrderService
	def grailsApplication
	
	def getEntity(def id) {
		return WorkOrder.get(id)
	}
	
	def createEntity() {
		def entity = new WorkOrder();
		if(!params["equipment.id"] || params["equipment"]!=null) entity.equipment = Equipment.get(params.int("equipment"))
		return entity;
	}
	
	def getModel(entity) {
		def equipments =  []
		if(entity.equipment) equipments << entity.equipment
		[
			order:entity,
			equipments: equipments,
			currencies: grailsApplication.config.site.possible.currency
		]
	}

	def bindParams(def entity) {
		if(!entity.id){
			entity.addedBy = user
			entity.openOn = new Date()
			entity.assistaceRequested = false
			entity.status = OrderStatus.OPEN
		}else{
		}
		entity.properties = params
	}

	def getTemplate() {
		return "/entity/workOrder/createWorkOrder";
	}

	def getLabel() {
		return "work.order.label";
	}

	def getEntityClass() {
		return WorkOrder.class;
	}
	
	def list = {
		adaptParamsForList()
		Equipment equipment = null
		
		if(params["equipment"]) equipment = Equipment.get(params.int("equipment"))
		List<WorkOrder> orders = workOrderService.getWorkOrders(equipment,params)
		
		render(view:"/entity/list", model:[
		 template:"workorder/workorderList",
		 entities: orders,
		 entityCount: orders.totalCount,
		 code: getLabel(),
		 entityClass: getEntityClass(),
		])
	}	
	
	def addProcess = {
		
		WorkOrder order = WorkOrder.get(params.int("order"))
		def type = params["type"]
		def value = params["value"]
		def result = false
		
		if (order == null || (!type.equals("action-perfomed") && !type.equals("materials-used")))
			response.sendError(404)
		else {
				if (log.isDebugEnabled()) log.debug("addProcess params: "+params)
				def process = newProcess(order,value,now,user)
				
				if(type.equals("action-perfomed")) order.performedActions.add(process)
				else order.materialsUsed.add(process)
				
				order.lastModifiedOn = now
				order.lastModifiedBy = user
				order.save(flush:true)
				
				if(process!=null) result=true
				render(contentType:"text/json") { results = [result]}
		}
	}
	
	def newProcess(def workOrder,def name,def addedOn,def addedBy){
		return new MaintenanceProcess(workOrder:workOrder,name:name,addedOn:addedOn,addedBy:addedBy).save(flush:true);
	}

}
