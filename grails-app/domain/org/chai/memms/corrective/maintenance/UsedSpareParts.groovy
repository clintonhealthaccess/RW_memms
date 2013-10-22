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

import org.chai.memms.spare.part.SparePartType;
import org.chai.memms.maintenance.MaintenanceOrder
import org.chai.memms.preventive.maintenance.Prevention
import org.chai.memms.preventive.maintenance.PreventiveOrder

/**
 * @author Jean Kahigiso M.
 *
 */
public class UsedSpareParts {

	MaintenanceOrder maintenanceOrder
	Prevention prevention
	SparePartType sparePartType
	Integer quantity
	
	static constraints ={
		maintenanceOrder nullable:false
		prevention nullable:true, validator:{ val, obj ->
			//prevention should be null if the workorde is preventive
			if(obj.maintenanceOrder instanceof PreventiveOrder) return (val!=null)
		}
		sparePartType nullable: false
		quantity nullable:false, min:1
	}
	static mapping ={
		table "memms_maintenance_order_used_spare_part"
		version false
	}

	@Override
	public String toString() {
		return "UsedSparePart [id= " + id + " , prevention= "+prevention+" , sparePartType=" + sparePartType + " , quantity= "+quantity+"]";
	}  
}
