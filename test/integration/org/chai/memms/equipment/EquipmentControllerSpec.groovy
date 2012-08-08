package org.chai.memms.equipment

import org.chai.memms.IntegrationTests;
import org.chai.memms.Initializer;

class EquipmentControllerSpec extends IntegrationTests{

	def equipmentController
	
	def "create equipment with correct required data in fields - for english input"(){
		
		setup:
		equipmentController = new EquipmentController();
		when:
		equipmentController.params.serialNumber = "test serial"
		equipmentController.params.purchaseCost = "32000"
		equipmentController.params.descriptions_en = "test english descriptions"
		equipmentController.params.observations_en = "test eng observation"
		equipmentController.params.manufactureDate = Initializer.getDate(1,1,2012)
		equipmentController.params.purchaseDate = Initializer.getDate(1,1,2012)
		equipmentController.params.registeredOn = Initializer.getDate(1,1,2012)
		equipmentController.save()
		
		then:
		Equipment.count() == 1;
//		Department.findByCode(CODE(123)).code.equals(CODE(123))
//		Department.findByNames_en("testNames").getNames(new Locale("en")).equals("testNames")
//		Department.findByDescriptions_en("test description").getDescriptions(new Locale("en")).equals("test description")
	}
}
