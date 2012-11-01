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
package org.chai.memms.corrective.maintenance

import org.chai.location.CalculationLocation;
import java.util.Date;
import java.util.Map;
import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.corrective.maintenance.Comment;
import org.chai.memms.corrective.maintenance.MaintenanceProcess;
import org.chai.memms.corrective.maintenance.WorkOrder;
import org.chai.memms.corrective.maintenance.WorkOrderStatus;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.inventory.EquipmentType;
import org.chai.memms.inventory.Provider;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.memms.corrective.maintenance.MaintenanceProcess.ProcessType;
import org.chai.memms.corrective.maintenance.WorkOrder.Criticality;
import org.chai.memms.corrective.maintenance.WorkOrder.FailureReason;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;
import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;


/**
 * @author Jean Kahigiso M.
 *
 */
class WorkOrderController extends AbstractEntityController{
	
	def grailsApplication
	def locationService
	def equipmentStatusService
	def workOrderStatusService
	def notificationWorkOrderService
	def userService

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
			orderClosed:(entity.currentStatus == OrderStatus.CLOSEDFIXED || entity.currentStatus == OrderStatus.CLOSEDFORDISPOSAL)? true:false,
			//entity can be null
			technicians : userService.getActiveUserByTypeAndLocation(UserType.TECHNICIANFACILITY,entity.equipment?.dataLocation, [:])
		]
	}

	def bindParams(def entity) {
		if(!entity.id){
			entity.addedBy = user
			entity.openOn = now
			entity.failureReason = FailureReason.NOTSPECIFIED
			entity.currentStatus = OrderStatus.OPENATFOSA
		}else{
			entity.lastModifiedOn = now
			entity.lastModifiedBy = user
			if(entity.currentStatus == OrderStatus.CLOSEDFIXED || entity.currentStatus == OrderStatus.CLOSEDFORDISPOSAL)
				entity.closedOn = now
			else entity.closedOn = null
			
		}
		params.oldStatus = entity.currentStatus
		entity.properties = params
	}
		
	def saveEntity(def entity) {
		def currentEquipmentStatus
		def currentWorkOrderStatus
		def newEntity = false
		def escalation = false
		def users = []
		
		//Change Equipment Status and Create first workOrderStatus to the new workOrder
		if(entity.id==null){
			newEntity=true
			currentEquipmentStatus = equipmentStatusService.createEquipmentStatus(now,user,Status.UNDERMAINTENANCE,entity.equipment,true,now,[:])
			currentWorkOrderStatus = workOrderStatusService.createWorkOrderStatus(entity,OrderStatus.OPENATFOSA,user,now,escalation)
		}else{
			if(log.isDebugEnabled()) log.debug("Old status stored in params: "+params.oldStatus)
			//If status has be changed
			if(entity.currentStatus != params.oldStatus){
				//Escalate
				if(entity.currentStatus == OrderStatus.OPENATMMC && params.oldStatus == OrderStatus.OPENATFOSA) escalation = true			
				//Change Equipment Status When closing workorder
				if(entity.currentStatus == OrderStatus.CLOSEDFIXED)
					currentEquipmentStatus = equipmentStatusService.createEquipmentStatus(now,user,Status.OPERATIONAL,entity.equipment,true,now,[:])
				if(entity.currentStatus == OrderStatus.CLOSEDFORDISPOSAL)
					currentEquipmentStatus = equipmentStatusService.createEquipmentStatus(now,user,Status.FORDISPOSAL,entity.equipment,true,now,[:])
				currentWorkOrderStatus = workOrderStatusService.createWorkOrderStatus(entity,entity.currentStatus,user,now,escalation)
			}
		}
		
		entity.save(failOnError: true)
		if(log.isDebugEnabled()) log.debug("Created WorkOrder: "+entity)
		if(newEntity || escalation){ //TODO define default message
			users = userService.getNotificationWorkOrderGroup(entity,user,escalation)
			notificationWorkOrderService.sendNotifications(entity,message(code:"workorder.creation.default.message"),user,users)
		}
		(!currentEquipmentStatus)?:currentEquipmentStatus.save(flush:true)
		(!currentWorkOrderStatus)?:currentWorkOrderStatus.save(flush:true)
		
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

}


