package org.chai.memms.equipment

import org.chai.memms.IntegrationTests
import org.chai.memms.location.DataLocation;

class EquipmentSpec extends IntegrationTests{

	def "can create and save an equipment"() {

		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		when:
		def equipment = new Equipment(serialNumber:"test123", purchaseCost:"1,200",manufactureDate:getDate(22,07,2010),purchaseDate:getDate(22,07,2010),
				registeredOn:getDate(22,07,2010), model:equipmentModel, department:department, dataLocation:DataLocation.list().first(),
				observations:['en':'Equipment Observation'],descriptions:['en':'Equipment Descriptions'], type:equipmentType)

		def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipment)

		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty

		equipment.save(failOnError: true)
		then:
		Equipment.count() == 1
	}

	def "can't create and save an equipment without a serial number"() {

		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		when:
		def equipment = new Equipment(purchaseCost:"1,200",manufactureDate:getDate(22,07,2010),purchaseDate:getDate(22,07,2010),
				registeredOn:getDate(22,07,2010), model:equipmentModel, department:department, dataLocation:DataLocation.list().first(),
				observations:['en':'Equipment Observation'],descriptions:['en':'Equipment Descriptions'], type:equipmentType)

		def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipment)

		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty

		equipment.save()
		then:
		Equipment.count() == 0
		equipment.errors.hasFieldErrors('serialNumber') == true
	}

	def "can't create and save an equipment without a purchase cost"() {

		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		when:
		def equipment = new Equipment(serialNumber:"test123",manufactureDate:getDate(22,07,2010),purchaseDate:getDate(22,07,2010),
				registeredOn:getDate(22,07,2010), model:equipmentModel, department:department, dataLocation:DataLocation.list().first(),
				observations:['en':'Equipment Observation'],descriptions:['en':'Equipment Descriptions'],type:equipmentType)

		def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipment)

		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty

		equipment.save()
		then:
		Equipment.count() == 0
		equipment.errors.hasFieldErrors('purchaseCost') == true
	}

	def "can't create and save an equipment with a duplicate serial number"() {

		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		newEquipment("test123","3,600",['en':"testDescription"],['en':'Equipment Observation'],getDate(22,07,2010), getDate(22,07,2010),
				getDate(22,07,2010),equipmentModel,DataLocation.list().first(),department, equipmentType)
		when:
		def equipment = new Equipment(serialNumber:"test123",purchaseCost:"1,200",manufactureDate:getDate(22,07,2010),purchaseDate:getDate(22,07,2010),
				registeredOn:getDate(22,07,2010), model:equipmentModel, department:department, dataLocation:DataLocation.list().first(),
				observations:['en':'Equipment Observation'],descriptions:['en':'Equipment Descriptions'],type:equipmentType)

		def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipment)

		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty

		equipment.save()
		then:
		Equipment.count() == 1
		equipment.errors.hasFieldErrors('serialNumber') == true
	}

	def "manufacture date must be after today"() {

		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		when:
		def equipment = new Equipment(serialNumber:"test123", purchaseCost:"1,200",manufactureDate:new Date().next(),purchaseDate:getDate(22,07,2010),
				registeredOn:getDate(22,07,2010), model:equipmentModel, department:department, dataLocation:DataLocation.list().first(),
				observations:['en':'Equipment Observation'],descriptions:['en':'Equipment Descriptions'],type:equipmentType)

		def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipment)

		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty

		equipment.save()
		then:
		Equipment.count() == 0
		equipment.errors.hasFieldErrors('manufactureDate') == true
	}

	def "purchase date must be after today"() {

		setup:
		setupLocationTree()
		def department = newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = newEquipmentType("15810", ["en":"Accelerometers"], true,["en":"used in memms"])
		when:
		def equipment = new Equipment(serialNumber:"test123", purchaseCost:"1,200",manufactureDate:getDate(22,07,2010),purchaseDate:new Date().next(),
				registeredOn:getDate(22,07,2010), model:equipmentModel, department:department, dataLocation:DataLocation.list().first(),
				observations:['en':'Equipment Observation'],descriptions:['en':'Equipment Descriptions'],type:equipmentType)

		def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipment)

		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty

		equipment.save()
		then:
		Equipment.count() == 0
		equipment.errors.hasFieldErrors('purchaseDate') == true
	}
}
