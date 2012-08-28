package org.chai.memms.equipment

import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.EquipmentType.Observation;

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
		equipmentTypeController.save()
		
		equipmentType = equipmentTypeService.searchEquipmentType(CODE(123),["":""])[0]

		then:

		equipmentType.code.equals(CODE(123))

		grailsApplication.config.i18nFields.locales.each{
			equipmentType.getNames(new Locale("$it")).equals("X-Ray Film Cutter $it")
			equipmentType.getDescriptions(new Locale("$it")).equals("some kind of description $it")
		}
	}
}