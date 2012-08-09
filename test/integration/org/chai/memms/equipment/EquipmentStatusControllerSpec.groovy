package org.chai.memms.equipment

import org.chai.memms.IntegrationTests;
import org.chai.memms.Initializer
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.location.DataLocation;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.security.User

class EquipmentStatusControllerSpec extends IntegrationTests{
	
	def equipmentStatusController
	
	def "create equipment status with correct required data in fields - for english input"(){
		
		setup:
		equipmentStatusController = new EquipmentStatusController();
		setupLocationTree()
		def user  = newUser("admin", "Admin UID")
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
		def equipment = Initializer.newEquipment(
					"SERIAL10"
					,"2900.23",
					['en':'Equipment Descriptions'],
					['en':'Equipment Observation'],
					Initializer.getDate(22,07,2010),
					Initializer.getDate(10,10,2010),
					new Date(),
					equipmentModel,
					DataLocation.list().first(),
					department,
					equipmentType
					)
		when:
		equipmentStatusController.params.statusChangeDate = new Date()
		equipmentStatusController.params.changedBy = User.findByUsername("admin")
		equipmentStatusController.params.value = Status.INSTOCK
		equipmentStatusController.params.current = true
		equipmentStatusController.params.equipment = equipment
		equipmentStatusController.save(failOnError: true)
		
		then:
		EquipmentStatus.count() == 1;
		EquipmentStatus.findByValue(Status.INSTOCK).value == Status.INSTOCK
	}
}
