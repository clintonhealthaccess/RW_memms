package org.chai.memms.spare.part

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.spare.part.SparePartTypeController;
import org.chai.memms.spare.part.SparePartType;
import org.chai.memms.inventory.Provider.Type;
import java.util.Date;
 


class SparePartTypeControllerSpec extends IntegrationTests {
	
  def sparePartTypeController
  def sparePartTypeService



 def "can create and save a spare part type with default values - all locales"(){
	 setup:
	
	sparePartTypeController = new SparePartTypeController()
	
	def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
	def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
	when:
	
	sparePartTypeController.params.code = CODE(123)
	
	grailsApplication.config.i18nFields.locales.each{
		sparePartTypeController.params."names_$it" = "Spare part name $it"
		sparePartTypeController.params."descriptions_$it" = "some kind of description $it"
	}
	sparePartTypeController.params.partNumber="7654-HGT"
	sparePartTypeController.params."manufacturer.id"=manufacturer.id
	sparePartTypeController.save()
	then:
	
	SparePartType.count() == 1
	SparePartType.list()[0].code.equals(CODE(123))
}
def "list spare part types"(){
	setup:
	sparePartTypeController = new SparePartTypeController();
	def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
	def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
    def sparePartTypeOne = new SparePartType(code:CODE(123),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	def sparePartTypeTwo = new SparePartType(code:CODE(124),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT", manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	def sparePartTypeThree = new SparePartType(code:CODE(125),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	def sparePartTypeFour = new SparePartType(code:CODE(126),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	
	when: "none ajax"
	sparePartTypeController.list()
	then:
	SparePartType.count() == 4
	sparePartTypeController.modelAndView.model.entities.size() == 4
	
	when: "with ajax"
	
	sparePartTypeController.request.makeAjaxRequest()
	sparePartTypeController.list()
	then:
	SparePartType.count() == 4
	sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(123)).code)
	sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(124)).code)
	sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(125)).code)
	sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(126)).code)
}

def "search spare part types"(){
	setup:
	sparePartTypeController = new SparePartTypeController();
	def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
	def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
    def sparePartTypeOne = new SparePartType(code:CODE(123),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	def sparePartTypeTwo = new SparePartType(code:CODE(124),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT", manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	def sparePartTypeThree = new SparePartType(code:CODE(125),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	def sparePartTypeFour = new SparePartType(code:CODE(126),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
	
	when: "none ajax fails"
	sparePartTypeController.params.q = "three"
	sparePartTypeController.search()
	
	then:
	SparePartType.count() == 4
	sparePartTypeController.response.status == 404
	
	when: "with ajax"
	sparePartTypeController.params.q = "three"
	sparePartTypeController.request.makeAjaxRequest()
	sparePartTypeController.search()
	then:
	SparePartType.count() == 4
	!sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(123)).code)
	!sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(124)).code)
	!sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(125)).code)
	!sparePartTypeController.response.json.results[0].contains(SparePartType.findByCode(CODE(126)).code)
}

//TODO find a way to test if the export works by calling the task plugin. Task plugin is supposed to have it's own tests
}


