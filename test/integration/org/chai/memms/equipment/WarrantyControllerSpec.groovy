package org.chai.memms.equipment

import org.chai.memms.IntegrationTests;
import org.chai.memms.Initializer;
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.location.DataLocation;

class WarrantyControllerSpec extends IntegrationTests{
	
	def warrantyController
	
	def "can create and save a warranty"() {
		setup:
		setupLocationTree()
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType("15810", ["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
		def equipment = Initializer.newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],Initializer.getDate(22,07,2010), Initializer.getDate(22,07,2010),
				Initializer.getDate(22,07,2010),equipmentModel,DataLocation.list().first(),department, equipmentType)
		warrantyController = new WarrantyController()
		
		when:
		
		warrantyController.params.code = CODE(123)
		warrantyController.params.startDate = Initializer.getDate(10, 12, 2010)
		warrantyController.params.endDate = Initializer.getDate(12, 12, 2012)
		warrantyController.params.contact = Initializer.newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654","")
		warrantyController.params.equipment = equipment
		
		grailsApplication.config.i18nFields.locales.each{
			warrantyController.params."descriptions_$it" = "description in $it"
		}
		warrantyController.save()
		
		then:
		Warranty.count() == 1
		Warranty.findByCode(CODE(123)).code.equals(CODE(123))
	}
}