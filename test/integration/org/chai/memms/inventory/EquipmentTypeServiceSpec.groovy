package org.chai.memms.inventory

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests
import org.chai.memms.inventory.EquipmentType.Observation;
import org.chai.memms.inventory.EquipmentType;
import org.chai.memms.inventory.Provider.Type;
import org.chai.memms.spare.part.SparePartType;

class EquipmentTypeServiceSpec extends IntegrationTests{
	
	def equipmentTypeService
	
	
	def "can search an equipment type by code"() {
		setup:
		def sparePartType
		def observation = Observation.USEDINMEMMS;
		Initializer.newEquipmentType(CODE(123),["en":"testName"],["en":"testObservations"],observation,Initializer.now())
		when:
		List<EquipmentType> equipmentTypes = equipmentTypeService.searchEquipmentType(CODE(123),null,sparePartType,[:])
		then:
		equipmentTypes.size() == 1
		equipmentTypes[0].code.equals(CODE(123))
	}

	def "can search  by code an equipment type with compatible spare part type"() {
		setup:
		def observation = Observation.USEDINMEMMS
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newEquipmentType(CODE(123),["en":"testName"],["en":"testObservations"],observation,Initializer.now())
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		
		def equipmentType = EquipmentType.findByCode(CODE(123))
		def sparePartType = SparePartType.findByCode(CODE(123))
		sparePartType.addToCompatibleEquipmentTypes(equipmentType)
		sparePartType.save(failOnError:true)
		equipmentType.addToSparePartTypes(sparePartType)
		equipmentType.save(failOnError:true)
		
		when:
		List<EquipmentType> equipmentTypes= equipmentTypeService.searchEquipmentType(CODE(123),null,sparePartType,[:])
		then:
		
		EquipmentType.list().size()==1
		equipmentTypes.size()==1
		equipmentTypes[0].code.equals(CODE(123))		
		
	}
	
	def "can search an equipment type by name"() {
		setup:
		def sparePartType
		def observation = Observation.USEDINMEMMS;
		Initializer.newEquipmentType(CODE(123),["en":"test Name"],["en":"testObservations"],observation,Initializer.now())
		when:
		List<EquipmentType> equipmentTypes = equipmentTypeService.searchEquipmentType("Na",null,sparePartType,[:])
		then:
		equipmentTypes.size() == 1
		equipmentTypes[0].code.equals(CODE(123))
	}
	
	def "can search by name an equipment type with compatible spare part type "() {
		setup:
		def observation = Observation.USEDINMEMMS;
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newEquipmentType(CODE(123),["en":"test Name"],["en":"testObservations"],observation,Initializer.now())
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		
		def equipmentType = EquipmentType.findByCode(CODE(123))
		def sparePartType = SparePartType.findByCode(CODE(123))
		sparePartType.addToCompatibleEquipmentTypes(equipmentType)
		sparePartType.save(failOnError:true)
		equipmentType.addToSparePartTypes(sparePartType)
		equipmentType.save(failOnError:true)
		
		when:
		List<EquipmentType> equipmentTypes = equipmentTypeService.searchEquipmentType("Na",null,sparePartType,[:])
		
		then:
		equipmentTypes.size() == 1
		equipmentTypes[0].code.equals(CODE(123))
	}
	
	def "can search an equipment type by observation"() {
		setup:
		def sparePartType
		def observation = Observation.USEDINMEMMS;
		Initializer.newEquipmentType(CODE(123),["en":"test Name"],["en":"testObservations"],observation,Initializer.now())
		when:
		List<EquipmentType> equipmentTypes = equipmentTypeService.searchEquipmentType("used",null,sparePartType,[:])
		then:
		equipmentTypes.size() == 1
		equipmentTypes[0].code.equals(CODE(123))
	}
	
	def "can search by observation an equipment type with compatible spare part type"() {
		setup:
		def observation = Observation.USEDINMEMMS;
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newEquipmentType(CODE(123),["en":"testName"],["en":"testObservations"],observation,Initializer.now())
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		
		def equipmentType = EquipmentType.findByCode(CODE(123))
		def sparePartType = SparePartType.findByCode(CODE(123))
		sparePartType.addToCompatibleEquipmentTypes(equipmentType)
		sparePartType.save(failOnError:true)
		equipmentType.addToSparePartTypes(sparePartType)
		equipmentType.save(failOnError:true)
		
		
		when:
		List<EquipmentType> equipmentTypes = equipmentTypeService.searchEquipmentType("used",null,sparePartType,[:])
		then:
		equipmentTypes.size() == 1
		equipmentTypes[0].code.equals(CODE(123))
	}
	
	
	
	def "can search an equipment type by specified observation"() {
		setup:
		def sparePartType
		Initializer.newEquipmentType(CODE(123),["en":"retired equipment"],["en":"retired equipment Type"],Observation.RETIRED,Initializer.now())
		Initializer.newEquipmentType(CODE(1234),["en":"test Name"],["en":"testObservations"],Observation.USEDINMEMMS,Initializer.now())
		when:
		List<EquipmentType> equipmentTypes = EquipmentType.list()
		List<EquipmentType> searchEquipmentTypes = equipmentTypeService.searchEquipmentType("test",Observation.USEDINMEMMS,sparePartType,[:])
		then:
		equipmentTypes.size() == 2
		searchEquipmentTypes.size() == 1
		searchEquipmentTypes[0].code.equals(CODE(1234))
	}
	def "can search by specified observation an equipment type with compatible spare part type"() {
		setup:
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		Initializer.newSparePartType(CODE(1234),["en":"names spare part type two"],["en":"descriptions spare part type two"],"76545-HGT",manufacturer,new Date())
		
		Initializer.newEquipmentType(CODE(123),["en":"retired equipment"],["en":"retired equipment Type"],Observation.RETIRED,Initializer.now())
		Initializer.newEquipmentType(CODE(1234),["en":"test Name"],["en":"testObservations"],Observation.USEDINMEMMS,Initializer.now())
		
		def equipmentTypeOne = EquipmentType.findByCode(CODE(123))
		def equipmentTypeTwo = EquipmentType.findByCode(CODE(1234))
		
		def sparePartTypeOne = SparePartType.findByCode(CODE(123))
		def sparePartTypeTwo = SparePartType.findByCode(CODE(1234))
		
		equipmentTypeOne.addToSpareParts(sparePartTypeOne)
		equipmentTypeTwo.addToSpareParts(sparePartTypeTwo)
		equipmentTypeOne.save(failOnError:true)
		equipmentTypeOne.save(failOnError:true)
		
		sparePartTypeOne.addToCompatibleEquipmentTypes(equipmentTypeOne)
		sparePartTypeTwo.addToCompatibleEquipmentTypes(equipmentTypeTwo)
		sparePartTypeOne.save(failOnError:true)
		sparePartTypeTwo.save(failOnError:true)
		
		when:
		List<EquipmentType> equipmentTypes = EquipmentType.list()
		List<EquipmentType> searchEquipmentTypes = equipmentTypeService.searchEquipmentType("test",Observation.USEDINMEMMS,sparePartTypeTwo,[:])
		
		then:
		equipmentTypes.size() == 2
		searchEquipmentTypes.size() == 1
		searchEquipmentTypes[0].code.equals(CODE(1234))
	}
		
	
	def "can search an equipment type by specified observation only"() {
		setup:
		def sparePartType
		Initializer.newEquipmentType(CODE(123),["en":"retired equipment"],["en":"retired equipment Type"],Observation.RETIRED,Initializer.now())
		Initializer.newEquipmentType(CODE(1234),["en":"test Name"],["en":"testObservations"],Observation.USEDINMEMMS,Initializer.now())
		when:
		List<EquipmentType> equipmentTypes = EquipmentType.list()
		List<EquipmentType> searchEquipmentTypes = equipmentTypeService.searchEquipmentType("",Observation.RETIRED,sparePartType,[:])
		then:
		equipmentTypes.size() == 2
		searchEquipmentTypes.size() == 1
		searchEquipmentTypes[0].code.equals(CODE(123))
	}
	def "can search by specified observation only an equipment type with compatible spare part type "() {
		setup:
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def manufacturer = Initializer.newProvider("TEST" + CODE(1), Type.MANUFACTURER,manufactureContact)
		Initializer.newEquipmentType(CODE(123),["en":"retired equipment"],["en":"retired equipment Type"],Observation.RETIRED,Initializer.now())
		Initializer.newEquipmentType(CODE(1234),["en":"test Name"],["en":"testObservations"],Observation.USEDINMEMMS,Initializer.now())
		Initializer.newSparePartType(CODE(123),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		Initializer.newSparePartType(CODE(1234),["en":"names spare part type one"],["en":"descriptions spare part type one"],"7654-HGT",manufacturer,new Date())
		
		def equipmentTypeOne = EquipmentType.findByCode(CODE(123))
		def equipmentTypeTwo = EquipmentType.findByCode(CODE(1234))
		
		def sparePartTypeOne = SparePartType.findByCode(CODE(123))
		def sparePartTypeTwo = SparePartType.findByCode(CODE(1234))
		
		equipmentTypeOne.addToSpareParts(sparePartTypeOne)
		equipmentTypeTwo.addToSpareParts(sparePartTypeTwo)
		equipmentTypeOne.save(failOnError:true)
		equipmentTypeOne.save(failOnError:true)
		
		sparePartTypeOne.addToCompatibleEquipmentTypes(equipmentTypeOne)
		sparePartTypeTwo.addToCompatibleEquipmentTypes(equipmentTypeTwo)
		sparePartTypeOne.save(failOnError:true)
		sparePartTypeTwo.save(failOnError:true)
			
		when:
		List<EquipmentType> equipmentTypes = EquipmentType.list()
		List<EquipmentType> searchEquipmentTypes = equipmentTypeService.searchEquipmentType("",Observation.RETIRED,sparePartTypeOne,[:])
		then:
		equipmentTypes.size() == 2
		searchEquipmentTypes.size() == 1
		searchEquipmentTypes[0].code.equals(CODE(123))
	}
}