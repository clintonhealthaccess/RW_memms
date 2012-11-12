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

import org.chai.memms.inventory.Equipment;
import org.chai.memms.maintenance.MaintenanceOrder;
import org.chai.memms.security.User;

/**
 * @author Jean Kahigiso M.
 *
 */
@i18nfields.I18nFields
public abstract class PreventiveOrder extends MaintenanceOrder {
	
	enum PreventiveOrderType{
		
		NONE("none"),
		DURATIONBASED("duration.based"),
		WORKBASED("work.based")
		
		String messageCode = "preventive.order.type"
		String name
		PreventiveOrderType(String name){this.name=name}
		String getKey(){ return name() }
	}
	
	enum PreventiveOrderStatus{
		
		OPEN("open"),
		CLOSED("closed")
		
		String messageCode = "preventive.order.status"
		String name
		PreventiveOrderStatus(String name){this.name=name}
		String getKey(){ return name() }
	}
	
	enum PreventionResponsible{
		
		NONE("none"),
		SERVIDEPROVIDER("service.provider"),
		DEPARTMENTUSER("department.user"),
		HCTITULAIRE("hc.titulaire"),
		HCTECHNICIAN("hc.technician")
		
		String messageCode = "prevention.in.charge"
		String name
		PreventionResponsible(String name){this.name=name}
		String getKey(){ return name() }
		
	}
	String names
	String description
	PreventionResponsible  preventionResponsible
	
	User technicianInCharge
	PreventiveOrderType type
	PreventiveOrderStatus status
	
	static belongsTo = [equipment: Equipment]
	static hasMany = [preventions: Prevention]
	
	static constraints = {
		importFrom MaintenanceOrder, exclude:["closedOn"]
		type nullable: false, inList:[PreventiveOrderType.DURATIONBASED,PreventiveOrderType.WORKBASED]
		status nullable:false, inList:[PreventiveOrderStatus.OPEN,PreventiveOrderStatus.CLOSED]

		closedOn nullable:true, validator:{ val, obj ->
			if(val!=null) return (val<=new Date() && obj.status.equals(PreventiveOrderStatus.CLOSED))
		}
		preventionResponsible nullable:false,inList:[PreventionResponsible.HCTECHNICIAN,PreventionResponsible.HCTITULAIRE,PreventionResponsible.SERVIDEPROVIDER,PreventionResponsible.DEPARTMENTUSER]
		technicianInCharge nullable:true, validator:{ val, obj ->
			if(obj.preventionResponsible.equals(PreventionResponsible.HCTECHNICIAN)) return (val!=null)
		}
		description nullable:false
		
	}
	static mapping = {
		table "memms_preventive_order_abstract"
		tablePerHierarchy false 
		cache true
		version false

	}
	
	
	abstract Integer getPlannedPrevention();

}
