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

import org.chai.location.CalculationLocation;
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
	def locationService

	def getEntity(def id) {
		return WorkOrder.get(id)
	}

	def createEntity() {
		return new WorkOrder();
	}

	def getModel(entity) {
		def equipments =  []
		if(entity.equipment) equipments << entity.equipment
		[
			order:entity,
			equipments: equipments,
			currencies: grailsApplication.config.site.possible.currency,
			closed:(entity.status==OrderStatus.CLOSEDFIXED || entity.status == OrderStatus.CLOSEDFORDISPOSAL)? true:false
		]
	}

	def bindParams(def entity) {
		if(!entity.id){
			entity.addedBy = user
			entity.openOn = now
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
		List<WorkOrder> orders= []
		Equipment equipment = null
		CalculationLocation  location = null
		if(params["location"]) location = CalculationLocation.get(params.int("location.id"))
		if(params["equipment"]) equipment = Equipment.get(params.int("equipment.id"))
		
		if(location)
			orders = workOrderService.getWorkOrdersByCalculationLocation(location, orders, params)
			
		if(equipment){
		 	orders= workOrderService.getWorkOrdersByEquipment(equipment,params)
		}else{
			orders= workOrderService.getWorkOrdersByEquipment(null,params)
		}

		render(view:"/entity/list", model:[
					template:"workorder/workOrderList",
					filterTemplate:"workorder/workOrderFilter",
					entities: orders,
					entityCount: orders.totalCount,
					code: getLabel(),
					entityClass: getEntityClass(),
					equipment:equipment,
					dataLocation:location
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
		WorkOrder order = WorkOrder.get(params.int("order.id"))
		def type = params["type"].toUpperCase()
		type = ProcessType."$type"
		def value = params["value"]
		def result = false
		def html =""
		if (order == null || type==null || value.equals(""))
			response.sendError(404)
		else {
				if (log.isDebugEnabled()) log.debug("addProcess params: "+params)
				def process = newProcess(order,type,value,now,user)	
				if(process!=null){
					order.addToProcesses(process)
					order.lastModifiedOn = now
					order.lastModifiedBy = user
					order.save(flush:true)
					result=true
					def processes = (type==ProcessType.ACTION)? order.actions:order.materials
					html = g.render(template:"/templates/processList",model:[processes:processes,type:type.name])
				}
				render(contentType:"text/json") { results = [result,html,type.name] }
		}
	}

	def removeProcess = {
		MaintenanceProcess  process = MaintenanceProcess.get(params.int("process.id"))
		def result = false
		def html =""
		def type =null
		if(!process) response.sendError(404)
		else{
			type = process.type
			WorkOrder order = process.workOrder
			result = true
			order.processes.remove(process)
			process.delete()
			order.lastModifiedOn = now
			order.lastModifiedBy = user
			order.save(flush:true)
			def processes = (type==ProcessType.ACTION)? order.actions:order.materials
			html = g.render(template:"/templates/processList",model:[processes:processes,type:type.name])
		}
		render(contentType:"text/json") { results = [result,html,type.name]}
	}
	
	def addComment ={
		WorkOrder order = WorkOrder.get(params.int("order.id"))
		def html =""
		def content = params["content"]
		def result = false
		if (order == null || content.equals("") )
			response.sendError(404)
		else {
			def comment = newComment(order,user, now,content)
			if(comment==null) response.sendError(404)
			else{ 
				order.addToComments(comment)
				order.lastModifiedOn = now
				order.lastModifiedBy = user
				order.save(flush:true)
				result=true
				html = g.render(template:"/templates/comments",model:[order:order])
			}
		}
		render(contentType:"text/json") { results = [result,html] }
	}
	//TODO not complete
	def escalate ={
		WorkOrder order = WorkOrder.get(params.int("order.id"))
		def content = "Please review work order on equipment serial number: ${order.equipment.serialNumber}"
		def result = false
		if (order == null)
			response.sendError(404)
		else {
			def sent = workOrderService.escalateWorkOrder(order,content, user)
			result=true 
		}
		render(contentType:"text/json") { results = [result] }
	}

	def getWorkOrderClueTipsAjaxData = {
		def workOrder = WorkOrder.get(params.long("id"))
		def html = g.render(template:"/templates/workOrderClueTip",model:[workOrder:workOrder])
		render(contentType:"text/plain", text:html)
	}

	def removeComment = {
		Comment comment = Comment.get(params.int("comment.id"))
		WorkOrder order
		def html =""
		def result = false
		if(!comment) response.sendError(404)
		else{
			order = comment.workOrder
			order.comments.remove(comment)
			comment.delete()
			order.lastModifiedOn = now
			order.lastModifiedBy = user
			order.save(flush:true)
			result = true
			html = g.render(template:"/templates/comments",model:[order:order])
		}
		render(contentType:"text/json") { results = [result,html] }
	}
	
	def newProcess(def workOrder,def type,def name,def addedOn,def addedBy){
		return new MaintenanceProcess(workOrder:workOrder,type: type,name:name,addedOn:addedOn,addedBy:addedBy).save(failOnError:true, flush:true);
	}
	def newComment(def workOrder, def writtenBy, def writtenOn, def content){
		return new Comment(workOrder: workOrder, writtenBy: writtenBy, writtenOn: writtenOn, content: content ).save(failOnError: true, flush:true)
	}

	def search = {
		adaptParamsForList()
		Equipment equipment = null
		DataLocation dataLocation = null
		if(params["equipment"]){
			equipment = Equipment.get(params.long("equipment.id"))
		}else if(params["dataLocation"]){
			dataLocation = DataLocation.get(params.long('dataLocation.id'))
		}
		List<WorkOrder> workOrders = workOrderService.searchWorkOrder(params['q'],dataLocation,equipment,params)
		render (view: '/entity/list', model:[
					template:"workorder/workOrderList",
					filterTemplate:"workorder/workOrderFilter",
					entities: workOrders,
					entityCount: workOrders.totalCount,
					code: getLabel(),
					equipment:equipment,
					dataLocation:dataLocation,
					q:params['q']
				])
	}

	def filter = { FilterWorkOrderCommand cmd ->
		if(log.isDebugEnabled()) log.debug(cmd)
		adaptParamsForList()
		List<WorkOrder> orders = workOrderService.filterWorkOrders(cmd.dataLocation,cmd.equipment,cmd.openOn,cmd.closedOn,cmd.getAssistanceStatus(),cmd.criticality,cmd.status,params)

		render(view:"/entity/list", model:[
					template:"workorder/workOrderList",
					filterTemplate:"workorder/workOrderFilter",
					entities: orders,
					entityCount: orders.totalCount,
					code: getLabel(),
					equipment:cmd.equipment,
					dataLocation:cmd.dataLocation,
					entityClass: getEntityClass(),
					filterCmd:cmd
				])
	}
}

class FilterWorkOrderCommand {
	
	Date openOn
	Date closedOn
	String assistaceRequested
	Criticality criticality
	OrderStatus status
	DataLocation dataLocation
	Equipment equipment

	public boolean getAssistanceStatus(){
		if(assistaceRequested.equals("true")) return true
		else if(assistaceRequested.equals("false")) return false
		else return null
	}

	static constraints = {
		dataLocation  nullable:true
		equipment nullable:true
		openOn nullable:true
		closedOn nullable:true
		assistaceRequested nullable:true
		status nullable:true
		criticality nullable:true
	}

	String toString() {
		return "FilterCommand[OrderStatus="+status+", Criticality="+criticality+ 
		", closedOn="+closedOn+", openOn="+openOn+", Assistance Status="+ getAssistanceStatus() + "]"
	}
}

