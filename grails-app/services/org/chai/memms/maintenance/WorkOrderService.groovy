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

import org.chai.location.DataLocation
import org.chai.memms.equipment.Equipment
import org.chai.memms.maintenance.WorkOrder.Criticality
import org.chai.memms.maintenance.WorkOrder.OrderStatus
import org.chai.memms.security.User;
import java.util.Map;
import org.chai.location.CalculationLocation;
import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.maintenance.MaintenanceProcess.ProcessType;

/**
 * @author Jean Kahigiso M.
 *
 */
class WorkOrderService {	
	def equipmentService
	def locationService
	static transactional = true
	def languageService;
	def notificationService
	/**
	 * Searches for a WorkOrder that contains the search term
	 * Pass a null value for the criteria you want to be ignored in the search other than the search text
	 * NB workOrdersEquipment is named like this to avoid conflicting with the navigation property equipment
	 * @param text
	 * @param location
	 * @param workOrdersEquipment
	 * @param params
	 * @return
	 */
	public List<WorkOrder> searchWorkOrder(String text,DataLocation location,Equipment workOrdersEquipment,Map<String, String> params) {
		def dbFieldTypeNames = 'names_'+languageService.getCurrentLanguagePrefix();
		def dbFieldDescriptions = 'descriptions_'+languageService.getCurrentLanguagePrefix();
		def criteria = WorkOrder.createCriteria()
		
		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(workOrdersEquipment)  
				eq('equipment',workOrdersEquipment)
			if(location) 
				equipment{eq('dataLocation',location)}
			or{
				ilike("description","%"+text+"%")
				equipment{
					or{
						ilike("serialNumber","%"+text+"%")
						ilike(dbFieldDescriptions,"%"+text+"%")
						type{
							or{
								ilike(dbFieldTypeNames,"%"+text+"%")
								ilike("code","%"+text+"%")
							}
						}
					}
				}
			}
		}
	}
	
				

	/**
	 * Returns a filtered list of WorkOrders according to the passed criteria
	 * Pass a null value for the criteria you want to be ignored in the filter
	 * NB workOrdersEquipment is named like this to avoid conflicting with the navigation property equipment
	 * @param dataLocation
	 * @param workOrdersequipment
	 * @param openOn
	 * @param closedOn
	 * @param assistaceRequested
	 * @param open
	 * @param criticality
	 * @param status
	 * @param params
	 * @return
	 */
	List<WorkOrder> filterWorkOrders(DataLocation dataLocation,Equipment workOrdersEquipment, Date openOn, Date closedOn, Boolean assistaceRequested,
	Criticality criticality, OrderStatus status,Map<String, String> params) {
		def criteria = WorkOrder.createCriteria();
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocation)
				equipment{
					eq('dataLocation',dataLocation)
				}
				
			if(workOrdersEquipment)
				eq("equipment",workOrdersEquipment)
			if(openOn)
				ge("openOn",openOn)
			if(closedOn)
				le("closedOn",closedOn)
			if(assistaceRequested)
				eq("assistaceRequested",assistaceRequested)
			if(criticality)
				eq("criticality",criticality)
			if(status)
				eq("status",status)
		}
	}
	
	def escalateWorkOrder(WorkOrder workOrder,String content, User escalatedBy){
		if(workOrder.assistaceRequested)
			notificationService.sendNotifications(workOrder,"${content}\nPlease follow up on this work order.",escalatedBy)
		else{
			workOrder.assistaceRequested = true
			workOrder.save(flush:true,failOnError: true)
			notificationService.newNotification(workOrder,"${content}\nPlease follow up on this work order.",escalatedBy)
		}
	}
	
	List<WorkOrder> getWorkOrdersByEquipment(Equipment equipment, Map<String, String> params){
		def criteria = WorkOrder.createCriteria();
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(equipment)
				eq("equipment",equipment)
		}
	}
	
	List<WorkOrder> getWorkOrdersByCalculationLocation(CalculationLocation location,List<DataLocationType> types, Map<String, String> params){
		def equipments =[]
		def criteria = WorkOrder.createCriteria();
		
		if(location instanceof DataLocation)
			equipments = equipmentService.getEquipmentsByDataLocation(location, [:])
		else{
			def dataLocations = locationService.getDataLocations(null,(types)? types:null)
			for(DataLocation dataLocation: dataLocations)
				equipments.addAll( equipmentService.getEquipmentsByDataLocation(dataLocation, [:]))
		}
		
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			or{
				equipments.each { equipment ->
					eq("equipment",equipment)
				}
			}
		}
	}
}
