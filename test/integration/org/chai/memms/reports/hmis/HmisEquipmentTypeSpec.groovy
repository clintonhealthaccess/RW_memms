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
package org.chai.memms.reports.hmis
import org.chai.memms.IntegrationTests
import org.chai.memms.Initilalizer
/**
 *
 * @author Dusabe Eric, Kahigiso M. Jean
 */

class HmisEquipmentTypeSpec extends IntegrationTests{
	def "can create and save a hmisEquipmentType"() {
		when:
		def hmisEquipmentType = new HmisEquipmentType(hmisCodeEquipmentType:CODE(123))
		Utils.setLocaleValueInMap(hmisEquipmentType,['en':"testName"],"Names")
		hmisEquipmentType.save(failOnError: true)
		def hmisEquipmentTypeOne = Initilalizer.newHmisEquipmentType(CODE(1234),['en':"testName", 'fr':"NameTest"])
		then:
		HmisEquipmentType.count() == 2
	}

	def "can't create and save hmisEquipmentType when code is null"() {
		when:
		def hmisEquipmentType = new HmisEquipmentType()
		Utils.setLocaleValueInMap(hmisEquipmentType,['en':"testName"],"Names")
		hmisEquipmentType.save()
		then:
		HmisEquipmentType.count() == 0
	}

	def "can't create and save a hmisEquipmentType when code is duplicated"() {
		def hmisEquipmentTypeOne = Initilalizer.newHmisEquipmentType(CODE(123),['en':"testName", 'fr':"NameTest"])
		def hmisEquipmentType = new HmisEquipmentType(hmisCodeEquipmentType:CODE(123))
		Utils.setLocaleValueInMap(hmisEquipmentType,['en':"testName"],"Names")
		hmisEquipmentType.save()
		then:
		HmisEquipmentType.count() == 1
	}

	def "can't create and save a hmisEquipmentType when lastUpdate is in future"() {
		when:
		def today = new Date()
		def hmisEquipmentType = new HmisEquipmentType(hmisCodeEquipmentType:CODE(123))
		Utils.setLocaleValueInMap(hmisEquipmentType,['en':"testName"],"Names")
		hmisEquipmentType.lastUpdate = today + 1
		hmisEquipmentType.save()
		def hmisEquipmentType = new HmisEquipmentType(hmisCodeEquipmentType:CODE(124))
		Utils.setLocaleValueInMap(hmisEquipmentType,['en':"testName"],"Names")
		hmisEquipmentType.lastUpdate = today
		hmisEquipmentType.save(failOnError:true)
		then:
		HmisEquipmentType.count() == 1
	}

}