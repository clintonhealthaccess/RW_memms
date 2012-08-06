package org.chai.memms.equipment

import org.chai.memms.IntegrationTests
import org.chai.memms.location.DataLocation;

class WarrantySpec extends IntegrationTests{

    def "can create and save a warranty"() {
		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		def equipment = newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],getDate(22,07,2010), getDate(22,07,2010),
				getDate(22,07,2010),equipmentModel,DataLocation.list().first(),department, equipmentType)
		
		def contact = newContact(["en":"testAddress"],"Test Name",'email@gmail.com', "0768-889-787", "Street 154")
		
		when:
		def warranty = new Warranty(code:CODE(123),startDate:getDate(10, 12, 2010),endDate:getDate(12, 12, 2012),
			descriptions:["en":"test Descriptions"],contact:contact, equipment:equipment)
		warranty.save(failOnError: true)
		then:
		Warranty.count() == 1
	}
}
