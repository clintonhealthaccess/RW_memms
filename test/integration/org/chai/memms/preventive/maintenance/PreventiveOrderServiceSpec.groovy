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
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2020/02/01')
		Date dateEndDaily = new Date().parse('yyyy/MM/dd', '2020/02/20')
		Date dateEndWeekly = new Date().parse('yyyy/MM/dd', '2020/02/29')
		Date dateEndMonthly = new Date().parse('yyyy/MM/dd', '2021/02/01')
		Date dateEndYearly = new Date().parse('yyyy/MM/dd', '2023/02/01')
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def dailyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description one",
			dateCreated,null,OccurencyType.DAILY,true,4,1,null)
		def weeklyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description two",
			dateCreated,null,OccurencyType.WEEKLY,true,4,1,[2,3,5])
		def monthlyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			dateCreated,null,OccurencyType.MONTHLY,true,3,1,null)
		def yearlyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			dateCreated,null,OccurencyType.YEARLY,true,1,3,null)
		//Work Based Orders
		def workBasedOrder = Initializer.newWorkBasedOrder( equipmentWorkBasedOrderOne, addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"testing"],"description one",
			dateCreated,null,WorkIntervalType.WEEK,2)
		when:
		def nextOccurencesDailyDurationBased = preventiveOrderService.findOccurrencesInRange(dailyDurationBasedOrder, dateCreated, dateEndDaily)
		def nextOccurencesWeeklyDurationBased = preventiveOrderService.findOccurrencesInRange(weeklyDurationBasedOrder, dateCreated, dateEndWeekly)
		def nextOccurencesMonthlyDurationBased = preventiveOrderService.findOccurrencesInRange(monthlyDurationBasedOrder, dateCreated, dateEndMonthly)
		def nextOccurencesYearlyDurationBased = preventiveOrderService.findOccurrencesInRange(yearlyDurationBasedOrder, dateCreated, dateEndYearly)
		//def nextOccurencesWorkBased = preventiveOrderService.findOccurrencesInRange(workBasedOrder, Initializer.now(), Initializer.now()+732)
		then:
		nextOccurencesDailyDurationBased.size() == 4
		nextOccurencesMonthlyDurationBased.size() == 3
		nextOccurencesYearlyDurationBased.size() == 2
		nextOccurencesWeeklyDurationBased.size() == 12
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
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2020/02/01')
		Date dateExpected = new Date().parse('yyyy/MM/dd', '2020/02/04')
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
		nextOccurenceYearlyDurationBased == dateExpected
	}
	
	def "can find next occurences"(){
		setup:
		Date dateCreated = new Date().parse('yyyy/MM/dd', '2020/02/01')
		Date dateExpectedDaily = new Date().parse('yyyy/MM/dd', '2020/02/05')
		Date dateExpectedWeekly = new Date().parse('yyyy/MM/dd', '2020/02/04')
		Date dateExpectedMonthly = new Date().parse('yyyy/MM/dd', '2020/05/01')
		Date dateExpectedYearly = new Date().parse('yyyy/MM/dd', '2022/02/01')
		setupLocationTree()
		def techInCharge = newOtherUserWithType("techInCharge", "techInCharge", DataLocation.findByCode(BUTARO),UserType.TECHNICIANDH)
		def addedBy = newUser("addedBy", CODE(123))
		def equipmentDurationBasedOrderOne = newEquipment(CODE(123),DataLocation.findByCode(KIVUYE))
		def equipmentWorkBasedOrderOne = newEquipment(CODE(124),DataLocation.findByCode(KIVUYE))
		
		def equipmentDurationBasedOrderTwo = newEquipment(CODE(125),DataLocation.findByCode(BUTARO))
		def equipmentWorkBasedOrderTwo = newEquipment(CODE(126),DataLocation.findByCode(BUTARO))
		
		def dailyDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description one",
			dateCreated,null,OccurencyType.DAILY,true,4,1,null)
		def nextWeeklyOccurenceDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderOne,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description two",
			dateCreated,null,OccurencyType.WEEKLY,true,4,1,[2,3,5])
		def nextMonthlyOccurenceDurationBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			dateCreated,null,OccurencyType.MONTHLY,true,3,1,null)
		def nextYearlyOccurenceBasedOrder = Initializer.newDurationBasedOrder(equipmentDurationBasedOrderTwo,addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"test"],"description three",
			dateCreated,null,OccurencyType.YEARLY,true,2,3,null)
		//Work Based Orders
		def workBasedOrder = Initializer.newWorkBasedOrder( equipmentWorkBasedOrderOne, addedBy,PreventiveOrderStatus.OPEN,PreventionResponsible.SERVIDEPROVIDER,null,["en":"testing"],"description one",
			Initializer.now()+1,null,WorkIntervalType.WEEK,2)
		when:
		def nextOccurencesDailyDurationBased = preventiveOrderService.findNextOccurrence(dailyDurationBasedOrder, dateCreated+1)
		def nextOccurenceWeeklyDurationBased = preventiveOrderService.findNextOccurrence(nextWeeklyOccurenceDurationBasedOrder, dateCreated+1)
		def nextOccurenceYearlyDurationBased = preventiveOrderService.findNextOccurrence(nextYearlyOccurenceBasedOrder, dateCreated+1)
		def nextOccurenceMonthlyDurationBased = preventiveOrderService.findNextOccurrence(nextMonthlyOccurenceDurationBasedOrder, dateCreated+1)
		//def nextOccurencesWorkBased = preventiveOrderService.findOccurrencesInRange(workBasedOrder, Initializer.now(), Initializer.now()+732)
		then:
		nextOccurencesDailyDurationBased == dateExpectedDaily
		nextOccurenceYearlyDurationBased == dateExpectedYearly
		nextOccurenceMonthlyDurationBased == dateExpectedMonthly
		nextOccurenceWeeklyDurationBased == dateExpectedWeekly
		//nextOccurencesWorkBased.size() == 1
	}
}
