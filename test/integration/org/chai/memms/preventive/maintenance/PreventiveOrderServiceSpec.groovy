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

import java.util.Date;

import org.chai.memms.preventive.maintenance.DurationBasedOrder.OccurencyType;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventionResponsible;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderStatus;
import org.chai.memms.preventive.maintenance.WorkBasedOrder.WorkIntervalType;
import org.chai.memms.security.User.UserType;
import org.chai.memms.util.Utils;
import org.chai.location.DataLocationType
import org.chai.location.Location
import org.chai.location.DataLocation
import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests

/**
 * @author Jean Kahigiso M.
 *
 */
class PreventiveOrderServiceSpec extends IntegrationTests {
	def preventiveOrderService
	def "can find occurences in range"(){
		setup:
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def dailyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description one",
			Initializer.now()+1,null,OccurencyType.DAILY,true,4,1,null)
		def weeklyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description two",
			Initializer.now()+1,null,OccurencyType.WEEKLY,true,4,1,[2,3,5])
		def monthlyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			Initializer.now()+1,null,OccurencyType.MONTHLY,true,3,1,null)
		def yearlyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			Initializer.now()+1,null,OccurencyType.YEARLY,true,1,3,null)
		//Work Based Orders
		def workBasedOrder = Initializer.newWorkBasedOrder( equipmentWorkBasedOrderOne, addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"testing"],"description one",
			Initializer.now()+1,null,WorkIntervalType.WEEK,2)
		when:
		def nextOccurencesDailyDurationBased = preventiveOrderService.findOccurrencesInRange(dailyDurationBasedOrder, Initializer.now(), Initializer.now()+20)
		def nextOccurencesWeeklyDurationBased = preventiveOrderService.findOccurrencesInRange(weeklyDurationBasedOrder, Initializer.now(), Initializer.now()+366)
		def nextOccurencesMonthlyDurationBased = preventiveOrderService.findOccurrencesInRange(monthlyDurationBasedOrder, Initializer.now(), Initializer.now()+12)
		def nextOccurencesYearlyDurationBased = preventiveOrderService.findOccurrencesInRange(yearlyDurationBasedOrder, Initializer.now(), Initializer.now()+1098)
		//def nextOccurencesWorkBased = preventiveOrderService.findOccurrencesInRange(workBasedOrder, Initializer.now(), Initializer.now()+732)
		then:
		nextOccurencesDailyDurationBased.size() == 5
		nextOccurencesMonthlyDurationBased.size() == 4
		nextOccurencesYearlyDurationBased.size() == 5
		nextOccurencesWeeklyDurationBased.size() == 104
		//nextOccurencesWorkBased.size() == 1
	}
	
	def "can find next daily occurences"(){
		setup:
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2020/02/01')
		Date dateExpected = new Date().parse('yyyy/MM/dd', '2020/02/05')
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def dailyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description one",
			dateCreated,null,OccurencyType.DAILY,true,4,1,null)
		when:
		def nextOccurenceDailyDurationBased = preventiveOrderService.findNextDailyOccurrence(dailyDurationBasedOrder,dateCreated+1)
		then:
		nextOccurenceDailyDurationBased == dateExpected
	}
	
	def "can find next weekly occurences"(){
		setup:
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2013/02/14')
		Date dateExpected = new Date().parse('yyyy/MM/dd', '2022/02/01')
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def weeklyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description two",
			dateCreated,null,OccurencyType.WEEKLY,true,4,1,[2,3,5])
		when:
		def nextOccurenceWeeklyDurationBased = preventiveOrderService.findNextWeeklyOccurrence(weeklyDurationBasedOrder, dateCreated+1)
		then:
		nextOccurenceWeeklyDurationBased == dateExpected
	}
	
	def "can find next monthly occurences"(){
		setup:
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2020/02/01')
		Date dateExpected = new Date().parse('yyyy/MM/dd', '2020/05/01')
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def nextMonthlyOccurenceDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			dateCreated,null,OccurencyType.MONTHLY,true,3,1,null)
		when:
		def nextOccurencesMonthlyDurationBased = preventiveOrderService.findNextMonthlyOccurrence(nextMonthlyOccurenceDurationBasedOrder, dateCreated+1)
		then:
		nextOccurencesMonthlyDurationBased == dateExpected
	}
	
	def "can find next yearly occurences"(){
		setup:
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2020/02/01')
		Date dateExpected = new Date().parse('yyyy/MM/dd', '2022/02/01')
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def nextYearlyOccurenceBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			dateCreated,null,OccurencyType.YEARLY,true,2,3,null)
		when:
		def nextOccurenceYearlyDurationBased = preventiveOrderService.findNextYearlyOccurrence(nextYearlyOccurenceBasedOrder, dateCreated+1)
		then:
		//This will fail in a leap year
		nextOccurenceYearlyDurationBased == dateExpected
	}
}
