/** 
 * Copyright (c) 2011, Clinton Health Access Initiative.
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
package org.chai.memms.equipment

import org.chai.memms.location.DataLocation;

import i18nfields.I18nFields
/**
 * @author Jean Kahigiso M.
 *
 */
@i18nfields.I18nFields
public abstract class Equipment {
	
	String code
	String serialNumber
	String purchaseCost
	String observations
	
	Contact manufacture
	Contact supplier
	EquipmentWarranty warranty
	
	Date manufactureDate
	Date purchaseDate
	
	
	static belongsTo = [model: EquipmentModel, dataLocation: DataLocation, department: Department]
	
	static i18nFields = ["observations"]
	static embedded=["manufacture","supplier","observations"]
	
	
	static constraints = {
		
		supplier nullable: false
		warranty nullable: false
		manufacture nullable: false
		
		code blank: false, nullable: false, unique: true
		serialNumber blank: false, nullable: false
		purchaseCost blank: false, nullable: false
		
		manufactureDate blank: false, nullable: false, validator:{it <= new Date()}
		purchaseDate blank: false, nullable: false, validator:{it <= new Date()}
		
		observations nullable: true, blank: true
		
	}
	
	static mapping = {
		table "memms_equipment"
		dataLocation column: "data_location_id"
		version false
		tablePerSubclass true
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.is(obj))
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}		
	
}
