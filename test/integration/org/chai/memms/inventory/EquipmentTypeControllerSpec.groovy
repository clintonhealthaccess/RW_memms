package org.chai.memms.inventory

import org.chai.memms.IntegrationTests;
import org.chai.memms.inventory.EquipmentTypeController;
import org.chai.memms.inventory.EquipmentType.Observation;

class EquipmentTypeControllerSpec extends IntegrationTests{

	def equipmentTypeController
	def equipmentTypeService

	def "can create and save an equipment type with default values - all locales"(){
		setup:
		equipmentTypeController = new EquipmentTypeController()
		def equipmentType
		when:

		equipmentTypeController.params.code = CODE(123)
		
		grailsApplication.config.i18nFields.locales.each{
			equipmentTypeController.params."names_$it" = "X-Ray Film Cutter $it"
			equipmentTypeController.params."descriptions_$it" = "some kind of description $it"
		}
		equipmentTypeController.params.observation = "USEDINMEMMS"
		equipmentTypeController.params.addedOn = new Date()
		equipmentTypeController.params.lastModifiedOn = new Date()
		equipmentTypeController.params.expectedLifeTime = "struct"
		equipmentTypeController.params.expectedLifeTime_years = "1"
		equipmentTypeController.params.expectedLifeTime_months = "3"
		equipmentTypeController.save()
		
		equipmentType = equipmentTypeService.searchEquipmentType(CODE(123),["":""])[0]

		then:

		equipmentType.code.equals(CODE(123))
		equipmentType.expectedLifeTime.numberOfMonths == 15

		grailsApplication.config.i18nFields.locales.each{
			equipmentType.getNames(new Locale("$it")).equals("X-Ray Film Cutter $it")
			equipmentType.getDescriptions(new Locale("$it")).equals("some kind of description $it")
		}
	}
}