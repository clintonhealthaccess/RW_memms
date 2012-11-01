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
package org.chai.memms.inventory

import javax.persistence.Transient;

import org.chai.memms.Contact;
import org.chai.memms.Period;
import org.chai.memms.Warranty;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.memms.preventive.maintenance.PreventiveOrder;
import org.chai.memms.corrective.maintenance.WorkOrder;
import org.chai.location.DataLocation;
import org.apache.commons.lang.math.RandomUtils;

import groovy.transform.EqualsAndHashCode;
import i18nfields.I18nFields

/**
 * @author Jean Kahigiso M.
 *
 */
@i18nfields.I18nFields
@EqualsAndHashCode(includes='id')
public class Equipment {
	
	enum PurchasedBy{
		
		NONE('none'),
		BYMOH("by.moh"),
		BYFACILITY("by.facility"),
		BYDONOR("by.donor")
		
		String messageCode = "equipment.purchased"
		
		final String name
		PurchasedBy(String name){ this.name=name }
		String getKey() { return name() }
		
	}
	
	enum Donor{
		
		NONE('none'),
		MOHPARTNER("moh.partner"),
		OTHERNGO("other.ngo"),
		INDIVIDUAL("individual"),
		OTHERS("others")
		
		String messageCode = "equipment.donor"
		
		final String name
		Donor(String name){ this.name=name }
		String getKey() { return name() }
		
	}
	
	String serialNumber
	Double purchaseCost
	String currency
	String descriptions
	String model
	String room
	String code
	String donorName
	
	Period expectedLifeTime
	Period serviceContractPeriod
	Period warrantyPeriod
	Boolean obsolete
	
	Provider manufacturer
	Provider supplier
	Provider serviceProvider
	Warranty warranty
	
	PurchasedBy purchaser
	Donor donor
	
	Date manufactureDate
	Date purchaseDate
	Date serviceContractStartDate
	Date registeredOn
	
	static hasMany = [status: EquipmentStatus, workOrders: WorkOrder,preventiveOrders: PreventiveOrder]
	static belongsTo = [dataLocation: DataLocation, department: Department, type: EquipmentType]
	static i18nFields = ["observations","descriptions"]
	static embedded = ["warranty","serviceContractPeriod","expectedLifeTime","warrantyPeriod"]
	
	static constraints = {
		importFrom Contact
		code nullable: false, blank:false, unique:true
		supplier nullable: false
		manufacturer nullable: false
		serviceProvider nullable: true, validator:{val, obj ->
			if(val == null) return (obj.serviceContractStartDate==null && obj.serviceContractPeriod==null)
		}
		warranty nullable: true
		warrantyPeriod nullable: true, validator:{val, obj ->
			if (obj.warranty!=null) return (val!=null)
		}
		
		serialNumber nullable: false, blank: false,  unique: true
		purchaseCost nullable: true, blank: true, validator:{ if(it!=null) return (it>0) }
		purchaser nullable: false, inList:[PurchasedBy.BYFACILITY,PurchasedBy.BYMOH,PurchasedBy.BYDONOR]
		donor nullable:true,inList:[Donor.OTHERNGO,Donor.MOHPARTNER,Donor.OTHERS,Donor.INDIVIDUAL], validator:{ val, obj ->
			if(obj.purchaser == PurchasedBy.BYDONOR) return (val!=null)
		}
		donorName nullable:true,blank:true, validator:{val, obj ->
			if(obj.purchaser == PurchasedBy.BYDONOR || obj.donor !=null) return (val!=null && val!="")
		}
		currency  nullable: true, blank: true, inList: ["RWF","USD","EUR"], validator:{ val, obj ->
			if(obj.purchaseCost != null) return (val != null)
		}
		expectedLifeTime nullable: false
		serviceContractPeriod nullable: true, validator:{ val, obj ->
			if(val==null) return (obj.serviceContractStartDate==null && obj.serviceProvider==null)
		}
		serviceContractStartDate nullable: true, blank: true, validator:{ val, obj ->
			if(val!=null) return (val<=new Date() && (val.after(obj.purchaseDate) || (val.compareTo(obj.purchaseDate)==0)))
			if(val==null) return (obj.serviceContractPeriod==null && obj.serviceProvider==null)
		}
		room nullable: true, blank: true
		
		manufactureDate nullable: false, blank: false, validator:{it <= new Date()}
		purchaseDate nullable: false, blank: false, validator:{ val, obj ->
			return  ((val <= new Date()) && val.after(obj.manufactureDate) || (val.compareTo(obj.manufactureDate)==0))
		}
		registeredOn nullable: false, blank:false
		descriptions nullable: true, blank: true
		obsolete nullable: false
	}
	
	static mapping = {
		table "memms_equipment"
		version false
	}
	
	def genarateAndSetEquipmentCode() {
		if(!code){
			def randomInt = RandomUtils.nextInt(99999)
			def now = new Date()
			def equipmentCode = "${dataLocation.code}-${randomInt}-${now.month}-${now.year+1900}"
			if(log.isDebugEnabled()) log.debug("Generated code:" + equipmentCode)
			if(Equipment.findByCode(equipmentCode.toString()) == null) code = equipmentCode 
			else genarateEquipmentCode()
		}
	}
	
	@Transient
	def getCurrentState() {
		if(!status) return null
		for(EquipmentStatus state : status)
			if(state.isCurrent() && state.equals(getTimeBasedStatus())) return state 
		 setCurrentStatus()
		 return getTimeBasedStatus();
	}
	
	@Transient
	def setCurrentStatus(){
		def state = getTimeBasedStatus()
		status.each{ stat ->
			if(!stat.is(state)){
				stat.current = false
				stat.save(flush:true)
			}
		}
		state.current = true
		state.save(flush:true)
	}
	
	@Transient
	def getTimeBasedStatus(){
		EquipmentStatus currentStatus = status.asList()[0]
		for(EquipmentStatus state : status){
			if(state.dateOfEvent.after(currentStatus.dateOfEvent)){
				currentStatus= state;
			}else if(state.dateOfEvent.compareTo(currentStatus.dateOfEvent)==0){
				if(state.statusChangeDate.after(currentStatus.statusChangeDate))
					currentStatus = state
			}
		}
		return currentStatus
	}
	
	
	String toString() {
		return "Equipment[id=" + id + "]";
	}
}
