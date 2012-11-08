
package org.chai.memms.inventory

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.inventory.EquipmentStatus.Status;
import org.chai.memms.inventory.EquipmentStatusController;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus;

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
		equipmentStatusController.params.status = "DISPOSED"
		equipmentStatusController.params.dateOfEvent=Initializer.now()
		equipmentStatusController.params."equipment.id" = equipment.id
		equipmentStatusController.save()
		then:
		EquipmentStatus.count() == 1;
		EquipmentStatus.findByStatus(Status.DISPOSED).status == Status.DISPOSED
		equipment.timeBasedStatus.status == Status.DISPOSED
			
	}
}
