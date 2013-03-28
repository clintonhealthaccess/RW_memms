package org.chai.memms.inventory

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.inventory.EquipmentTypeController;
import org.chai.memms.inventory.EquipmentTypeService;
import org.chai.memms.inventory.EquipmentType.Observation;
import org.chai.memms.inventory.Provider.Type;
import org.chai.memms.spare.part.SparePartType;

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
		equipmentTypeController.params.expectedLifeTime = "struct"
		equipmentTypeController.params.expectedLifeTime_years = "1"
		equipmentTypeController.params.expectedLifeTime_months = "3"
		equipmentTypeController.save()
		
		equipmentType = equipmentTypeService.searchEquipmentType(CODE(123),null,null,[:])[0]

		then:

		equipmentType.code.equals(CODE(123))
		equipmentType.expectedLifeTime.numberOfMonths == 15

		grailsApplication.config.i18nFields.locales.each{
			equipmentType.getNames(new Locale("$it")).equals("X-Ray Film Cutter $it")
			equipmentType.getDescriptions(new Locale("$it")).equals("some kind of description $it")
		}
	}
	
	def "list equipment types"(){
		setup:
		equipmentTypeController = new EquipmentTypeController();
		Initializer.newEquipmentType(CODE(123), ["en":"names equipment type one"], ["en":"descriptions equipment type one"],Observation.RETIRED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(124), ["en":"names equipment type two"], ["en":"descriptions equipment type two"],Observation.NOTINSCOPE ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(125), ["en":"names equipment type three"], ["en":"descriptions equipment type three"],Observation.TOODETAILED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(126), ["en":"names equipment type four"], ["en":"descriptions equipment type four"],Observation.USEDINMEMMS ,  Initializer.now(),)
		
		when: "none ajax"
		equipmentTypeController.list()
		then:
		EquipmentType.count() == 4
		equipmentTypeController.modelAndView.model.entities.size() == 4
		
		when: "with ajax"
		equipmentTypeController.request.makeAjaxRequest()
		equipmentTypeController.list()
		then:
		EquipmentType.count() == 4
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(123)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(124)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(125)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(126)).code)
	}

	
	def "list equipment types with a compatible a spare part type"(){
		setup:
		
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		
		equipmentTypeService = new EquipmentTypeService();
		Initializer.newEquipmentType(CODE(123), ["en":"names equipment type one"], ["en":"descriptions equipment type one"],Observation.RETIRED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(124), ["en":"names equipment type two"], ["en":"descriptions equipment type two"],Observation.NOTINSCOPE ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(125), ["en":"names equipment type three"], ["en":"descriptions equipment type three"],Observation.TOODETAILED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(126), ["en":"names equipment type four"], ["en":"descriptions equipment type four"],Observation.USEDINMEMMS ,  Initializer.now(),)
	
		Initializer.newSparePartType(CODE(126),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		
		def equipmentType = EquipmentType.findByCode(CODE(126))
		def sparePartType = SparePartType.findByCode(CODE(126))
		equipmentType.addToSparePartTypes(sparePartType)
		equipmentType.save(failOnError:true)
		sparePartType.addToCompatibleEquipmentTypes(equipmentType)
		sparePartType.save(failOnError:true)
		
		
		when: "none ajax"
		equipmentTypeController.list(sparePartType)

		then:
		EquipmentType.count() == 4
		equipmentType.size()==1
		
		when: "with ajax"
		equipmentTypeController.request.makeAjaxRequest()
		equipmentTypeController.list(sparePartType)
		
		then:
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(123)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(124)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(125)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(126)).code)
	}
			
	def "search equipment types"(){
		setup:
		equipmentTypeController = new EquipmentTypeController();
		Initializer.newEquipmentType(CODE(123), ["en":"names equipment type one"], ["en":"descriptions equipment type one"],Observation.RETIRED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(124), ["en":"names equipment type two"], ["en":"descriptions equipment type two"],Observation.NOTINSCOPE ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(125), ["en":"names equipment type three"], ["en":"descriptions equipment type three"],Observation.TOODETAILED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(126), ["en":"names equipment type four"], ["en":"descriptions equipment type four"],Observation.USEDINMEMMS ,  Initializer.now(),)
		
		when: "none ajax fails"
		equipmentTypeController.params.q = "three"
		equipmentTypeController.search()
		then:
		EquipmentType.count() == 4
		equipmentTypeController.modelAndView.model.entities == [EquipmentType.findByCode(CODE(125))]
		
		when: "with ajax"
		equipmentTypeController.params.q = "three"
		equipmentTypeController.request.makeAjaxRequest()
		equipmentTypeController.search()
		then:
		EquipmentType.count() == 4
		!equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(123)).code)
		!equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(124)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(125)).code)
		!equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(126)).code)
	}
	
	def "search equipment types with compatible spare part type"(){
		setup:
		equipmentTypeController = new EquipmentTypeController();
		Initializer.newEquipmentType(CODE(123), ["en":"names equipment type one"], ["en":"descriptions equipment type one"],Observation.RETIRED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(124), ["en":"names equipment type two"], ["en":"descriptions equipment type two"],Observation.NOTINSCOPE ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(125), ["en":"names equipment type three"], ["en":"descriptions equipment type three"],Observation.TOODETAILED ,  Initializer.now(),)
		Initializer.newEquipmentType(CODE(126), ["en":"names equipment type four"], ["en":"descriptions equipment type four"],Observation.USEDINMEMMS ,  Initializer.now(),)
		
		Initializer.newSparePartType(CODE(126),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		
		def equipmentType = EquipmentType.findByCode(CODE(125))
		def sparePartType = SparePartType.findByCode(CODE(125))
		sparePartType.addToCompatibleEquipmentTypes(equipmentType)
		sparePartType.save(failOnError:true)
		equipmentType.addToSparePartTypes(sparePartType)
		equipmentType.save(failOnError:true)
		
		when: "none ajax fails"
		equipmentTypeController.params.q = "three"
		equipmentTypeController.search(sparePartType)
		then:
		EquipmentType.count() == 4
		equipmentTypeController.modelAndView.model.entities == [EquipmentType.findByCode(CODE(125))]
		
		when: "with ajax"
		equipmentTypeController.params.q = "three"
		equipmentTypeController.request.makeAjaxRequest()
		equipmentTypeController.search(sparePartType)
		then:
		EquipmentType.count() == 4
		!equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(123)).code)
		!equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(124)).code)
		equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(125)).code)
		!equipmentTypeController.response.json.results[0].contains(EquipmentType.findByCode(CODE(126)).code)
	}
	//TODO find a way to test if the export works by calling the task plugin. Task plugin is supposed to have it's own tests
}