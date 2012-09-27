
package org.chai.memms.equipment

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.equipment.EquipmentStatus;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.equipment.EquipmentStatusController;

class EquipmentStatusControllerSpec extends IntegrationTests{
	
	def equipmentStatusController
	
	def "create equipment status with correct required data in fields - for english input"(){
		
		setup:
		setupLocationTree()
		setupEquipment()
		setupSystemUser()
		def user = newUser("user","user")
		def equipment = Equipment.findBySerialNumber(CODE(123))
		equipmentStatusController = new EquipmentStatusController();
		when:
		equipmentStatusController.params.reason = "reason bha"
		equipmentStatusController.params.current = true
		equipmentStatusController.params.status="DISPOSED"
		equipmentStatusController.params.dateOfEvent=Initializer.now()
		equipmentStatusController.params."equipment.id" = equipment.id
		equipmentStatusController.save()
		then:
		EquipmentStatus.count() == 1;
		EquipmentStatus.findByStatus(Status.DISPOSED).status == Status.DISPOSED
			
	}
}
