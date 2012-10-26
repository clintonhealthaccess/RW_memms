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

package org.chai.memms.equipment

import java.util.List;
import java.util.Map;

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.Equipment.Donor;
import org.chai.memms.equipment.Equipment.PurchasedBy;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.location.DataLocation;
import org.chai.memms.security.User;
import org.chai.memms.IntegrationTests
import org.chai.location.DataLocation;
import org.chai.memms.equipment.Provider.Type;
import java.io.File;

class EquipmentServiceSpec extends IntegrationTests{

	def equipmentService
	def "can search equipment by serial number, description and observation"() {
		setup:
		setupLocationTree()

		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def supplierContact = Initializer.newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654","6353")

		def manufacture = Initializer.newProvider(CODE(123), Type.MANUFACTURER,manufactureContact)
		def supplier = Initializer.newProvider(CODE(124), Type.SUPPLIER,supplierContact)
		def user  = newUser("admin", "Admin UID")
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())

		Initializer.newEquipment("SERIAL10",PurchasedBy.BYDONOR,Donor.OTHERNGO,"Internews",false,Initializer.newPeriod(32),"ROOM A1","",['en':'Equipment Descriptions one'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),'',Initializer.now(),"equipmentModel",DataLocation.list().first(),department,equipmentType,manufacture,supplier)
		Initializer.newEquipment("SERIAL11",PurchasedBy.BYMOH,null,null,false,Initializer.newPeriod(32),"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"RWF",Initializer.now(),"equipmentModel",DataLocation.list().first(),department,equipmentType,manufacture,supplier)
		def List<Equipment> equipments

		when://Searching by serial number

		equipments = equipmentService.searchEquipment("SERIAL11",DataLocation.list().first(), [:])
		then:
		equipments.size() == 1
		equipments[0].serialNumber.equals("SERIAL11")

		when://Searching by observation

		equipments = equipmentService.searchEquipment("one",DataLocation.list().first(), [:])
		then:
		equipments.size() == 1
		equipments[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions one')

		when://Searching by description

		equipments = equipmentService.searchEquipment("Two",DataLocation.list().first(), [:])
		then:
		equipments.size() == 1
		equipments[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions two')
	}

	def "get equipment by datalocation"() {
		setup:
		setupLocationTree()

		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def supplierContact = Initializer.newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654","6353")

		def manufacture = Initializer.newProvider(CODE(123), Type.MANUFACTURER,manufactureContact)
		def supplier = Initializer.newProvider(CODE(124), Type.SUPPLIER,supplierContact)
		def user  = newUser("admin", "Admin UID")
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())

		Initializer.newEquipment("SERIAL10",PurchasedBy.BYDONOR,Donor.MOHPARTNER,"CHAI",,false,Initializer.newPeriod(32),"ROOM A1","",['en':'Equipment Descriptions one'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"",Initializer.now(),"equipmentModel",DataLocation.findByCode('Kivuye HC'),department,equipmentType,manufacture,supplier)
		Initializer.newEquipment("SERIAL11",PurchasedBy.BYFACILITY,null,null,false,Initializer.newPeriod(32),"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"USD",Initializer.now(),"equipmentModel",DataLocation.findByCode('Butaro DH'),department,equipmentType,manufacture,supplier)
		def List<Equipment> equipments

		when:
		equipments = equipmentService.getEquipmentsByDataLocation(DataLocation.findByCode('Kivuye HC'), [:])

		then:
		equipments.size() == 1
		equipments[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions one')
	}

	def "filter equipments"() {
		setup:
		setupLocationTree()

		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def supplierContact = Initializer.newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654","6353")
		def manufacture = Initializer.newProvider(CODE(111), Type.MANUFACTURER,manufactureContact)
		def supplier = Initializer.newProvider(CODE(222), Type.SUPPLIER,supplierContact)
		
		def user  = newUser("admin", "Admin UID")
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())

		def equipmentOne = Initializer.newEquipment("SERIAL10",PurchasedBy.BYDONOR,Donor.MOHPARTNER,"Intra-health",false,Initializer.newPeriod(32),"ROOM A1","",['en':'Equipment Descriptions one'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"",Initializer.now(),"equipmentModel",DataLocation.findByCode('Kivuye HC'),department,equipmentType,manufacture,supplier)
		def equipmentTwo = Initializer.newEquipment("SERIAL11",PurchasedBy.BYFACILITY,null,null,true,Initializer.newPeriod(32),"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"USD",Initializer.now(),"equipmentModel",DataLocation.findByCode('Butaro DH'),department,equipmentType,manufacture,supplier)
		
		
		def List<Equipment> equipmentsOne, equipmentsTwo, equipmentsThree
		def equipmentStatusOneActive = Initializer.newEquipmentStatus(Initializer.now(),User.findByUsername("admin"),Status.INSTOCK,equipmentOne,false,[:])
		def equipmentStatusOneInActive = Initializer.newEquipmentStatus(Initializer.now(),User.findByUsername("admin"),Status.OPERATIONAL,equipmentOne,true,[:])
		def equipmentStatusTwo = Initializer.newEquipmentStatus(Initializer.now(),User.findByUsername("admin"),Status.DISPOSED,equipmentOne,true,[:])
		
		equipmentOne.addToStatus(equipmentStatusOneInActive).save(failOnError:true,flush: true)
		equipmentOne.addToStatus(equipmentStatusOneActive).save(failOnError:true,flush: true)
		equipmentTwo.addToStatus(equipmentStatusTwo).save(failOnError:true,flush: true)
		
		when://Search by defaults
		
		equipmentsOne = equipmentService.filterEquipment(DataLocation.findByCode('Butaro DH'),
			supplier, manufacture,equipmentType,PurchasedBy.NONE,Donor.NONE,'true',Status.DISPOSED,[:])

		then:
		Equipment.count() == 2
		equipmentsOne.size() == 1
		equipmentsOne[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions two')
		
		when://Search by active status
		
		equipmentsTwo = equipmentService.filterEquipment(DataLocation.findByCode('Kivuye HC'),
			supplier, manufacture,equipmentType,PurchasedBy.BYDONOR,Donor.NONE,'false',Status.OPERATIONAL,[:])
		equipmentsThree = equipmentService.filterEquipment(DataLocation.findByCode('Kivuye HC'),
			supplier, manufacture,equipmentType,PurchasedBy.BYDONOR,Donor.NONE,'false',Status.INSTOCK,[:])

		then:
		Equipment.count() == 2
		equipmentsTwo.size() == 1
		equipmentsThree.size() == 0
		equipmentsTwo[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions one')
		equipmentsTwo[0].status.size() == 2
		
		when://Search by donor
		equipmentsTwo = equipmentService.filterEquipment(DataLocation.findByCode('Kivuye HC'),
			supplier, manufacture,equipmentType,null,Donor.MOHPARTNER,'false',Status.OPERATIONAL,[:])

		then:
		Equipment.count() == 2
		equipmentsTwo.size() == 1
		equipmentsTwo[0].status.size() == 2
	}

	def "can export equipments"(){
		setup:
		setupLocationTree()

		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def supplierContact = Initializer.newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654","6353")
		def manufacture = Initializer.newProvider(CODE(111), Type.MANUFACTURER,manufactureContact)
		def supplier = Initializer.newProvider(CODE(222), Type.SUPPLIER,supplierContact)
		def warrantyContact = Initializer.newContact(['fr':'Warranty Address Descriptions One'],"Warranty","jk@yahoo.com","0768-888-787","Street 654","8988")
		def warranty = Initializer.newWarranty(warrantyContact,Initializer.getDate(10, 12, 2010),false,[:])
		
		def user  = newUser("user", "user", true, true)
		setupSecurityManager(user)
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())

		def equipmentOne = Initializer.newEquipment("SERIAL10",PurchasedBy.BYFACILITY,null,null,true,Initializer.newPeriod(32),"ROOM A1","",['en':'Equipment Descriptions one'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"",Initializer.now(),"equipmentModel",DataLocation.findByCode(KIVUYE),department,equipmentType,manufacture,supplier)
		def equipmentTwo = Initializer.newEquipment("SERIAL11",PurchasedBy.BYFACILITY,null,null,,true,Initializer.newPeriod(32),"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
				,Initializer.getDate(10,10,2010),"EUR",Initializer.now(),"equipmentModel",DataLocation.findByCode(KIVUYE),department,equipmentType,manufacture,supplier)
		def equipmentThree = Initializer.newEquipment("SERIAL12",PurchasedBy.BYFACILITY,null,null,,true,Initializer.newPeriod(32),"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
			,Initializer.getDate(10,10,2010),"EUR",Initializer.now(),"equipmentModel",DataLocation.findByCode(KIVUYE),department,equipmentType,manufacture,supplier)
		def equipmentFour = Initializer.newEquipment("SERIAL13",PurchasedBy.BYFACILITY,null,null,,true,Initializer.newPeriod(32),"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
			,Initializer.getDate(10,10,2010),"EUR",Initializer.now(),"equipmentModel",DataLocation.findByCode(BUTARO),department,equipmentType,manufacture,supplier)
	
		
		def List<Equipment> equipmentsOne, equipmentsTwo, equipmentsThree
		def equipmentStatusOneActive = Initializer.newEquipmentStatus(Initializer.now(),User.findByUsername("user"),Status.INSTOCK,equipmentOne,false,[:])
		def equipmentStatusOneInActive = Initializer.newEquipmentStatus(Initializer.now(),User.findByUsername("user"),Status.OPERATIONAL,equipmentOne,true,[:])
		
		equipmentOne.warranty=warranty
		equipmentOne.warrantyPeriod = Initializer.newPeriod(22)
		equipmentOne.addToStatus(equipmentStatusOneActive).save(failOnError:true,flush: true)
		equipmentTwo.warranty=warranty
		equipmentTwo.warrantyPeriod = Initializer.newPeriod(20)
		equipmentTwo.addToStatus(equipmentStatusOneActive).save(failOnError:true,flush: true)
		equipmentThree.warranty=warranty
		equipmentThree.warrantyPeriod = Initializer.newPeriod(12)
		equipmentThree.addToStatus(equipmentStatusOneActive).save(failOnError:true,flush: true)
		List<Equipment> equipments = [equipmentOne,equipmentTwo,equipmentThree]

		when:
		File csvFile = equipmentService.exporter(DataLocation.findByCode(KIVUYE),equipments)
		
		then:
		csvFile != null
	}
}