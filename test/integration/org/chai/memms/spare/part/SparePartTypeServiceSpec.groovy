
package org.chai.memms.spare.part

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.spare.part.SparePartType;
import org.chai.memms.spare.part.SparePartTypeService;
import org.chai.memms.inventory.Provider.Type;
import java.util.Date;

class SparePartTypeServiceSpec extends IntegrationTests{
	
	def sparePartTypeService
	
	def "can search a spare part type by code"() {
		setup:
		def sparePartTypes =[]
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		def sparePartTypeOne = new SparePartType(code:CODE(124),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
		def sparePartTypeTwo = new SparePartType(code:CODE(125),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT", manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
		def sparePartTypeThree = new SparePartType(code:CODE(126),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
		when:                        
		sparePartTypes = sparePartTypeService.searchSparePartType(CODE(126),[:])
		then:
		SparePartType.count() == 3
		sparePartTypes.size() == 1
		sparePartTypes[0].code.equals(CODE(126))
	
    }
	def "can search a spare part type by name"() {
		setup:
		def sparePartTypes =[]
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		def sparePartTypeOne = new SparePartType(code:CODE(124),names:["en":"names"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
		def sparePartTypeTwo = new SparePartType(code:CODE(125),names:["en":"xyz"],descriptions:["en":"descriptions"],partNumber:"7654-HGT", manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
		def sparePartTypeThree = new SparePartType(code:CODE(126),names:["en":"abc"],descriptions:["en":"descriptions"],partNumber:"7654-HGT",manufacturer:manufacturer,discontinuedDate:new Date()).save(failOnError: true)
		when:
		sparePartTypes = sparePartTypeService.searchSparePartType("abc",[:])
		then:
		SparePartType.count() == 3
		sparePartTypes.size() == 1
		sparePartTypes[0].code.equals(CODE(126))
	}
	
	
}