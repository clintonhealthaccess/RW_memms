package org.chai.memms.equipment

import org.chai.memms.IntegrationTests;

class EquipmentModelControllerSpec extends IntegrationTests{
	
	def equipmentModelController
	
	def "create equipment model with correct required data in fields - for english input"(){
		
		setup:
		equipmentModelController = new EquipmentModelController();
		when:
		equipmentModelController.params.code = CODE(123)
		equipmentModelController.params.names_en = "testNames"
		equipmentModelController.params.descriptions_en = "test description"
		equipmentModelController.save(failOnError: true)
		
		then:
		EquipmentModel.count() == 1
		EquipmentModel.findByCode(CODE(123)).code.equals(CODE(123))
		EquipmentModel.findByNames_en("testNames").getNames(new Locale("en")).equals("testNames")
		EquipmentModel.findByDescriptions_en("test description").getDescriptions(new Locale("en")).equals("test description")
	}
	
	def "create equipment model with correct required data in fields - for all locales"(){
		
		setup:
		equipmentModelController = new EquipmentModelController();
		when:
		equipmentModelController.params.code = CODE(123)
		grailsApplication.config.i18nFields.locales.each{
		equipmentModelController.params."names_$it" = "testNames $it"
		equipmentModelController.params."descriptions_$it" = "test description $it"
	}
		equipmentModelController.save(failOnError: true)
		
		then:
		EquipmentModel.count() == 1
		EquipmentModel.findByCode(CODE(123)).code.equals(CODE(123))
		grailsApplication.config.i18nFields.locales.each{
		EquipmentModel."findByNames_$it"("testNames $it").getNames(new Locale("en")).equals("testNames $it")
		EquipmentModel."findByDescriptions_$it"("test description $it").getDescriptions(new Locale("$it")).equals("test description $it")
		}
//		setup:
//		equipmentModelController = new DepartmentController();
//		when:
//		equipmentModelController.params.code = CODE(123)
//		grailsApplication.config.i18nFields.locales.each{
//			equipmentModelController.params."names_$it" = "testNames "+it
//			equipmentModelController.params."descriptions_$it" = "test description "+it
//		}
//		
//		departmentController.save(failOnError: true)
//		
//		then:
//		log.debug("locales " + grailsApplication.config.i18nFields.locales.each{it})
//		Department.count() == 1;
//		Department.findByCode(CODE(123)).code.equals(CODE(123))
//		
//		grailsApplication.config.i18nFields.locales.each{
//			
//			Department."findByNames_$it"("testNames $it").getNames(new Locale("$it")).equals("testNames $it")
//			Department."findByDescriptions_$it"("test description $it").getDescriptions(new Locale("$it")).equals("test description $it")
//		}
	}
}