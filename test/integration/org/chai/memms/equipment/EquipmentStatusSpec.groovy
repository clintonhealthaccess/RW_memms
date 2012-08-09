package org.chai.memms.equipment

import org.chai.memms.IntegrationTests;
import org.chai.memms.Initializer
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.location.DataLocation;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.security.User

class EquipmentStatusSpec extends IntegrationTests{

    def "can create and save an equipment status"(){
		setup:
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
		def statusOne = Initializer.newEquipmentStatus(new Date(),User.findByUsername("admin"),Status.INSTOCK,equipment,true)
		def statusTwo = new EquipmentStatus(statusChangeDate:new Date(),changedBy:User.findByUsername("admin"),value:Status.INSTOCK,
			equipment:equipment,current:true).save(failOnError: true)
		then:
		EquipmentStatus.count() == 2
	}
}
