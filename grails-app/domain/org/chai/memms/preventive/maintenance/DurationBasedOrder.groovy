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

/**
 * @author Jean Kahigiso M.
 *
 */
@i18nfields.I18nFields
class DurationBasedOrder extends PreventiveOrder {
	
	enum OccurencyType{
		
		NONE("none"),
		DAILY("daily"),
		WEEKLY("weekly"),
		MONTHLY("monthly"),
		TRIMESTRIAL("trimestrial"), //Occurring every three month
		QUARTERLY("quarterly"), //Occur every four month
		SEMESTRIAL("semestrial"), //Happen every six month
		YEARLY("yearly"),
		OTHER("other")
		
		String messageCode = "occurance.type"
		String name
		OccurencyType(String name){this.name=name}
		String getKey(){ return name() }
	}
	
	enum DurationIntervalType{
		
		NONE("none"),
		DAY("day"),
		WEEK("week"),
		MONTH("month"),
		YEAR("year")
		
		String messageCode = "interval.type"
		String name
		DurationIntervalType(String name){this.name=name}
		String getKey(){ return name() }
		
	}
	OccurencyType occurency
	Integer occurInterval = 1
	DurationIntervalType intervalType
	
	
	static mapping = {
		table "memms_preventive_order_duration_based"
		version false
	}
	
	static constraints ={
		importFrom PreventiveOrder
		occurency nullable: false, inList:[OccurencyType.DAILY,OccurencyType.WEEKLY,OccurencyType.MONTHLY,OccurencyType.QUARTERLY,OccurencyType.TRIMESTRIAL,OccurencyType.SEMESTRIAL,OccurencyType.YEARLY,OccurencyType.OTHER]
		intervalType nullable: true, inList:[DurationIntervalType.DAY,DurationIntervalType.WEEK,DurationIntervalType.MONTH,DurationIntervalType.YEAR], validator:{ val, obj ->
			if(intervalType!=null) return (occurency==OccurencyType.OTHER && occurInterval!=null)
		}
	}
	
	Integer getPlannedPrevention(){
		//TODO
		return null
	}

	@Override
	public String toString() {
		return "DurationBasedOrder [id= "+id+" occurency=" + occurency + "]";
	}	
	
}
