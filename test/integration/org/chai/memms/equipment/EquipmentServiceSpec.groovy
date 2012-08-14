package org.chai.memms.equipment

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.location.DataLocation;
import org.chai.memms.security.User;
import org.chai.memms.IntegrationTests
import org.chai.memms.location.DataLocation;

class EquipmentServiceSpec extends IntegrationTests{
	
	def equipmentService
	def "can search equipment by serial number, description and observation"() {
		setup:
		setupLocationTree()
		def user  = newUser("admin", "Admin UID")
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
		Initializer.newEquipment("SERIAL10","2900.23",['en':'Equipment Descriptions one'],['en':'Equipment Observation one'],Initializer.getDate(22,07,2010)
			,Initializer.getDate(10,10,2010),new Date(),equipmentModel,DataLocation.list().first(),department,equipmentType)
		Initializer.newEquipment("SERIAL11","2900.23",['en':'Equipment Descriptions two'],['en':'Equipment Observation Two'],Initializer.getDate(22,07,2010)
			,Initializer.getDate(10,10,2010),new Date(),equipmentModel,DataLocation.list().first(),department,equipmentType)
		def List<Equipment> equipments
		
		when://Searching by serial number
		
		equipments = equipmentService.searchEquipment("SERIAL11", [:])
		then:
		equipments.size() == 1
		equipments[0].serialNumber.equals("SERIAL11")
		
		when://Searching by observation
		
		equipments = equipmentService.searchEquipment("one", [:])
		then:
		equipments.size() == 1
		equipments[0].getObservations(new Locale("en")).equals('Equipment Observation one')
		
		when://Searching by description
		
		equipments = equipmentService.searchEquipment("Two", [:])
		then:
		equipments.size() == 1
		equipments[0].getObservations(new Locale("en")).equals('Equipment Observation Two')
		
	}
}
