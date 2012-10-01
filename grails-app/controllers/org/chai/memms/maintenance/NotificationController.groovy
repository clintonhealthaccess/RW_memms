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

/**
 * @author Jean Kahigiso M.
 *
 */
class NotificationController extends AbstractEntityController{
	def notificationService
	
	def getEntity(def id) {
		return Notification.get(id);
	}

	def createEntity() {
		return new Notification();
	}

	def getTemplate() {
		return "/entity/notification/createNotification";
	}

	def getLabel() {
		return "notification.label";
	}
	
	def deleteEntity(def entity) {
		super.deleteEntity(entity)
	}
	
	def getEntityClass() {
		return Notification.class;
	}
	def bindParams(def entity) {
		entity.properties = params
	}
	
	def getModel(def entity) {
		[
			notification:entity
		]
	}
	
	def list={
		adaptParamsForList()
		Boolean read = false
		if(params.workOrderId) read  = params.boolean("read")
		List<Notification> notifications = notificationService.filterNotifications(WorkOrder.get(params.long("workOrderId")), null, null,read, params)
		render(view:"/entity/list", model:[
			template:"notification/notificationList",
			notifications: notifications,
			entityCount: notifications.totalCount,
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
	
	def search = {
		adaptParamsForList()
//		List<Department> departments = departmentService.searchDepartment(params['q'], params)
//				
//		render (view:"/entity/list", model:[
//			template:"department/departmentList",
//			entities: departments,
//			entityCount: departments.totalCount,
//			code: getLabel(),
//			names:names,
//			q:params['q']
//		])
		
	}
	def getAjaxData = {
//		List<Department> departments = departmentService.searchDepartment(params['term'], [:])
//		render(contentType:"text/json") {
//			elements = array {
//				departments.each { department ->
//					elem (
//						key: department.id,
//						value: department.getNames(languageService.getCurrentLanguage())
//					)
//				}
//			}
//		}
		
	}
}
