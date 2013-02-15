
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
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		when:  
		List<SparePartType>sparePartTypes = sparePartTypeService.searchSparePartType(CODE(123),[:])
		then:
		sparePartTypes.size() == 1
		sparePartTypes[0].code.equals(CODE(123))
	
    }
	def "can search a spare part type by name"() {
		setup:
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		when:
		List<SparePartType>sparePartTypes = sparePartTypeService.searchSparePartType("names spare part type one",[:])
		then:
		sparePartTypes.size() == 1
		sparePartTypes[0].code.equals(CODE(123))
	}
	def "can search a spare part type by part number"() {
		setup:
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		when:  
		List<SparePartType>sparePartTypes = sparePartTypeService.searchSparePartType("7654-HGT",[:])
		then:
		sparePartTypes.size() == 1
		sparePartTypes[0].partNumber.equals("7654-HGT")
	}
	def "can search a spare part type by description"() {
		setup:
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		when:
		List<SparePartType>sparePartTypes = sparePartTypeService.searchSparePartType("descriptions spare part type one",[:])
		then:
		sparePartTypes.size() == 1
		sparePartTypes[0].code.equals(CODE(123))
	}
	
}