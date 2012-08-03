package org.chai.memms.equipment

import java.util.Date;

import org.apache.commons.el.parser.Token;
import org.chai.memms.Contact;
import org.chai.memms.IntegrationTests

import org.chai.memms.location.DataLocation;

class EquipmentModelSpec extends IntegrationTests {
	
    def "can create and save an equipment model"() {
		setup:
		setupLocationTree()
		
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModelSetUp = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		
		def equipmentOne = newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],getDate(22,07,2010), getDate(22,07,2010),
			getDate(22,07,2010),equipmentModelSetUp,DataLocation.list().first(),department)
		
		def equipmentTwo = newEquipment("test345","3,600",['en':"testDescription"],['en':'Equipment Observation'],getDate(22,07,2010), getDate(22,07,2010),
			getDate(22,07,2010),equipmentModelSetUp,DataLocation.list().first(),department)
		when:
		def equipmentModel = new EquipmentModel(code:CODE(123),descriptions:['en':"testDescription"],names:['en':"testNames"])
		equipmentModel.equipments = [equipmentOne,equipmentTwo]
		equipmentModel.save(failOnError: true)
		then:
		EquipmentModel.count() == 2
		equipmentModel.equipments.size() == 2
	}
	
	def "can create and save an equipment model without the code"() {
		setup:
		setupLocationTree()
		
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModelSetUp = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		
		def equipmentOne = newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],getDate(22,07,2010), getDate(22,07,2010),
			getDate(22,07,2010),equipmentModelSetUp,DataLocation.list().first(),department)
		
		def equipmentTwo = newEquipment("test345","3,600",['en':"testDescription"],['en':'Equipment Observation'],getDate(22,07,2010), getDate(22,07,2010),
			getDate(22,07,2010),equipmentModelSetUp,DataLocation.list().first(),department)
		
		when:
		def equipmentModel = new EquipmentModel(descriptions:['en':"testDescription"],names:['en':"testNames"])
		equipmentModel.equipments = [equipmentOne,equipmentTwo]
		equipmentModel.save()
		then:
		EquipmentModel.count() == 1
		equipmentModel.errors.hasFieldErrors('code') == true
	}
}
