package org.chai.memms.equipment

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.location.DataLocation;

class WarrantySpec extends IntegrationTests{

    def "can create and save a warranty"() {
		setup:
		setupLocationTree()
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType("15810", ["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
		def equipment = Initializer.newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],Initializer.getDate(22,07,2010), Initializer.getDate(22,07,2010),
				Initializer.getDate(22,07,2010),equipmentModel,DataLocation.list().first(),department, equipmentType)
		when:
		def warranty = Initializer.newWarranty(CODE(123),["en":"testAddress"],"Test Name","email@gmail.com", "0768-889-787", "Street 154",Initializer.getDate(10, 12, 2010),Initializer.getDate(12, 12, 2012),["en":"test Descriptions"],equipment)
		then:
		Warranty.count() == 1
	}
	
	def "can't create and save a warranty without a code"() {
		setup:
		setupLocationTree()
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType("15810", ["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
		def equipment = Initializer.newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],Initializer.getDate(22,07,2010), Initializer.getDate(22,07,2010),
				Initializer.getDate(22,07,2010),equipmentModel,DataLocation.list().first(),department, equipmentType)
		when:
		def warrantyOne = Initializer.newWarranty(CODE(123),["en":"testAddress"],"Test Name","email@gmail.com", "0768-889-787", "Street 154",Initializer.getDate(10, 12, 2010),Initializer.getDate(12, 12, 2012),["en":"test Descriptions"],equipment)
		def warrantyTwo =  new Warranty(startDate:Initializer.getDate(10, 12, 2010),endDate:Initializer.getDate(12, 12, 2012),equipment:equipment)
		warrantyTwo.save(flush:true)
		then:
		Warranty.count() == 1
		warrantyTwo.errors.hasFieldErrors('code') == true
	}
	
//	def "can't create and save a warranty with duplicate code"() {
//		setup:
//		setupLocationTree()
//		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
//		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
//		def equipmentType = Initializer.newEquipmentType("15810", ["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
//		def equipment = Initializer.newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],Initializer.getDate(22,07,2010), Initializer.getDate(22,07,2010),
//				Initializer.getDate(22,07,2010),equipmentModel,DataLocation.list().first(),department, equipmentType)
//		when:
//		def warrantyTwo = Initializer.newWarranty(CODE(1233),["en":"testAddress"],"Test Name","email@gmail.com", "0768-889-787", "Street 154",Initializer.getDate(10, 12, 2010),Initializer.getDate(12, 12, 2012),["en":"test Descriptions"],equipment)
//		def warrantyOne = new Warranty(code:"CODE1233",startDate:Initializer.getDate(10, 12, 2010),endDate:Initializer.getDate(12, 12, 2012),equipment:equipment)
//		warrantyOne.save(flush:true)
//		then:
//		Warranty.count() == 1
//		warrantyOne.errors.hasFieldErrors('code') == true
//	}
}
