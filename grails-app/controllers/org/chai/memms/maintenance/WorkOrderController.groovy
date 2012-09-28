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

import java.util.Date;
import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.equipment.EquipmentType;
import org.chai.memms.equipment.Provider;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.maintenance.MaintenanceProcess.ProcessType;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;
import org.chai.memms.security.User;

/**
 * @author Jean Kahigiso M.
 *
 */
class WorkOrderController extends AbstractEntityController{
	def workOrderService
	def grailsApplication
	def correctiveMaintenanceService

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
		List<WorkOrder> orders = null
		if(params["equipment"]){
			equipment = Equipment.get(params.long("equipment"))
			orders = workOrderService.filterWorkOrders(null,equipment,params)
		}else if(params["location"]){
			equipment = Equipment.get(params.int("equipment"))
			orders = workOrderService.filterWorkOrders(DataLocation.get(params.long('location')),null,params)
		}

		render(view:"/entity/list", model:[
					template:"workorder/workorderList",
					filterTemplate:"workorder/workOrderFilter",
					entities: orders,
					entityCount: orders.totalCount,
					code: getLabel(),
					entityClass: getEntityClass(),
				])
	}

	def summaryPage = {
		if(user.location instanceof DataLocation) redirect(uri: "/workOrder/list/" + user.location.id)

		def location = Location.get(params.int('location'))
		def dataLocationTypesFilter = getLocationTypes()
		def template = null
		def correctiveMaintenances = null

		adaptParamsForList()

		def locationSkipLevels = correctiveMaintenanceService.getSkipLocationLevels()


		if (location != null) {
			template = '/correctiveMaintenance/sectionTable'
			correctiveMaintenances = correctiveMaintenanceService.getCorrectiveMaintenancesByLocation(location,dataLocationTypesFilter,params)
		}

		render (view: '/correctiveMaintenance/summaryPage', model: [
					correctiveMaintenances:correctiveMaintenances?.correctiveMaintenanceList,
					currentLocation: location,
					currentLocationTypes: dataLocationTypesFilter,
					template: template,
					entityCount: correctiveMaintenances?.totalCount,
					locationSkipLevels: locationSkipLevels,
					entityClass: getEntityClass()
				])
	}


	def addProcess = {

		WorkOrder order = WorkOrder.get(params.int("order"))
		def type = params["type"]
		type = ProcessType."$type"
		def value = params["value"]
		def result = false

		if (order == null || type==null || value.equals(""))
			response.sendError(404)
		else {
			if (log.isDebugEnabled()) log.debug("addProcess params: "+params)
			def process = newProcess(order,type,value,now,user)
			if(process){
				order.processes.add(process)
				order.lastModifiedOn = now
				order.lastModifiedBy = user
				order.save(flush:true)
			}
			if(process!=null) result=true
			render(contentType:"text/json") { results = [result,process] }
		}
	}

	def removeProcess = {
		MaintenanceProcess  process = MaintenanceProcess.get(params.int("process"))
		WorkOrder order = process.workOrder
		def result = false
		if(!process) response.sendError(404)
		else{
			result = true
			process.delete()
			order.lastModifiedOn = now
			order.lastModifiedBy = user
			order.save(flush:true)
		}
		render(contentType:"text/json") { results = [result,process] }
	}

	def getWorkOrderClueTipsAjaxData = {
		def workOrder = WorkOrder.get(params.long("id"))
		render (text: "<a href='${createLinkWithTargetURI(controller:'equipment', action:'edit', params:[id: workOrder.equipment.id])}'> Edit </a><br/>Facility:${workOrder.equipment.dataLocation.names_en} <br/>Department:${workOrder.equipment.department.names_en}<br/>Room:${workOrder.equipment.room}<br/>Status:${workOrder.equipment.getCurrentState()?.status}")
	}


	def newProcess(def workOrder,def type,def name,def addedOn,def addedBy){
		return new MaintenanceProcess(workOrder:workOrder,type: type,name:name,addedOn:addedOn,addedBy:addedBy).save(failOnError:true, flush:true);
	}

	def search = {//Make sure user has access to this location
		adaptParamsForList()
		List<WorkOrder> workOrders = workOrderService.searchWorkOrder(params['q'],params.int("location"),params.int("equipment"),params)
		render (view: '/entity/list', model:[
					template:"workorder/workorderList",
					filterTemplate:"workorder/workOrderFilter",
					entities: workOrders,
					entityCount: workOrders.totalCount,
					code: getLabel(),
					q:params['q']
				])
	}
}

class FilterCommand {
	Date openOn
	Date closedOn
	String assistaceRequested
	String open
	Criticality criticality
	OrderStatus status
	DataLocation dataLocation
	Equipment equipment

	public boolean getAssistanceStatus(){
		if(assistaceRequested.equals("true")) return true
		else if(assistaceRequested.equals("false")) return false
	}

	public boolean getOpenStatus(){
		if(open.equals("true")) return true
		else if(open.equals("false")) return false
	}

	static constraints = {
		dataLocation  nullable:true
		equipment nullable:true
		open  nullable:true
		openOn nullable:true
		closedOn nullable:true
		assistaceRequested nullable:true
		status nullable:true
		criticality nullable:true
	}

	String toString() {
		return "FilterCommand[OrderStatus="+status+", Criticality="+criticality+", Open="+opne
		", closedOn="+closedOn+", openOn="+openOn+", Assistance Status="+ getAssistanceStatus() +", Open Status()"+getOpenStatus()+ "]"
	}
}

