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

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.MaintenanceProcess.ProcessType;
import org.chai.memms.maintenance.WorkOrder.Criticality;
import org.chai.memms.maintenance.WorkOrder.FailureReason;
import org.chai.memms.maintenance.WorkOrder.OrderStatus;
import org.chai.memms.security.User;
import org.chai.memms.maintenance.WorkOrderController

/**
 * @author Jean Kahigiso M.
 *
 */
class WorkOrderControllerSpec extends IntegrationTests{
	
	def workOrderController
	
	def "can save workOrder test"(){
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		def user = newUser("user","user")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		workOrderController = new WorkOrderController()
		when:
		workOrderController.params."equipment.id" = equipment.id
		workOrderController.params.description = "equiment description"
		workOrderController.params.criticality = "NORMAL"
		workOrderController.save()
		then:
		WorkOrder.count()==1
	}
	
	def "can andProcess test"(){
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		def user = newUser("user","user")
		def workOrder = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, User.findByUsername("user"), new Date(),,FailureReason.NOTSPECIFIED)
		workOrderController = new WorkOrderController()
		when:
		workOrderController.params.'order.id' = workOrder.id
		workOrderController.params.type = "action"
		workOrderController.params."value" = "text action"
		workOrderController.addProcess()
		then:
		workOrder.actions.size() == 1
	}
	
	def "can deleteProcess test"(){
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		def user = newUser("user","user")
		def workOrder = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, User.findByUsername("user"), new Date(),FailureReason.NOTSPECIFIED)
		def process = Initializer.newMaintenanceProcess(workOrder, ProcessType.ACTION, "Process", Initializer.now(),User.findByUsername("user"))
		workOrder.addToProcesses(process)
		workOrder.save(flush:true)
		workOrderController = new WorkOrderController()
		when:
		workOrderController.params.'process.id' = process.id
		workOrderController.removeProcess()
		then:
		workOrder.actions.size() == 0
	}
	
	def "can andComment test"(){
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		def user = newUser("user","user")
		def workOrder = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, User.findByUsername("user"), new Date(),FailureReason.NOTSPECIFIED)
		workOrderController = new WorkOrderController()
		when:
		workOrderController.params.'order.id' = workOrder.id
		workOrderController.params.content = "comment content text"
		workOrderController.addComment()
		then:
		workOrder.comments.size() == 1
	}
	
	def "can deleteComment test"(){
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		def user = newUser("user","user")
		def workOrder = Initializer.newWorkOrder(Equipment.findBySerialNumber(CODE(123)), "Nothing yet", Criticality.NORMAL, OrderStatus.OPEN, User.findByUsername("user"), new Date(),FailureReason.NOTSPECIFIED)
		def comment = Initializer.newComment(workOrder, User.findByUsername("user"), Initializer.now(), "Test comment")
		workOrder.addToComments(comment)
		workOrder.save(flush:true)
		workOrderController = new WorkOrderController()
		when:
		workOrderController.params.'comment.id' = comment.id
		workOrderController.removeComment()
		then:
		workOrder.comments.size() == 0
	}
}
