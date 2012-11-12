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
		CalculationLocation  location = null
		if(params["dataLocation.id"]) location = CalculationLocation.get(params.int("dataLocation.id"))
		if(params["equipment.id"]) equipment = Equipment.get(params.int("equipment.id"))
		
		if(location)
			orders = preventiveOrderService.getPreventiveOrderByDataLocationManages(location,params)
		if(equipment)
			 orders = preventiveOrderService.getPreventiveOrderByEquipment(equipment,params)

		render (view: '/entity/list', model:[
			template:"preventiveOrder/preventiveOrderList",
			entities: orders,
			entityCount: orders.totalCount,
			code: getLabel(),
			equipment:equipment,
			names:names,
			entityClass: getEntityClass(),
			equipment:equipment,
			dataLocation:location
		])
	}
	
	

}
