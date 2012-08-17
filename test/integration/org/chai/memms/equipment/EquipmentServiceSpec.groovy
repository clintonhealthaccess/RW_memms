package org.chai.memms.equipment

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.location.DataLocation;
import org.chai.memms.security.User;
import org.chai.memms.IntegrationTests
import org.chai.memms.location.DataLocation;
import org.chai.memms.equipment.Provider.Type;

class EquipmentServiceSpec extends IntegrationTests{
	
	def equipmentService
	def "can search equipment by serial number, description and observation"() {
		setup:
		setupLocationTree()
		
		def manufactureContact = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154","6353")
		def supplierContact = Initializer.newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654","6353")
		
		def manufacture = Initializer.newProvider(CODE(123), Type.MANUFACTURE,manufactureContact)
		def supplier = Initializer.newProvider(CODE(124), Type.SUPPLIER,supplierContact)
		def user  = newUser("admin", "Admin UID")
		def department = Initializer.newDepartment(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentModel = Initializer.newEquipmentModel(['en':"testName"], CODE(123),['en':"testDescription"])
		def equipmentType = Initializer.newEquipmentType(CODE(15810),["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,Initializer.now(),Initializer.now())
		
		Initializer.newEquipment("SERIAL10",true,false,32,"ROOM A1","2900.23",['en':'Equipment Descriptions one'],Initializer.getDate(22,07,2010)
			,Initializer.getDate(10,10,2010),new Date(),"equipmentModel",DataLocation.list().first(),department,equipmentType,manufacture,supplier)
		Initializer.newEquipment("SERIAL11",true,false,32,"ROOM A1","2900.23",['en':'Equipment Descriptions two'],Initializer.getDate(22,07,2010)
			,Initializer.getDate(10,10,2010),new Date(),"equipmentModel",DataLocation.list().first(),department,equipmentType,manufacture,supplier)
		def List<Equipment> equipments
		
		when://Searching by serial number
		
		equipments = equipmentService.searchEquipment("SERIAL11", [:])
		then:
		equipments.size() == 1
		equipments[0].serialNumber.equals("SERIAL11")
		
		when://Searching by observation
		
		equipments = equipmentService.searchEquipment("one", [:])
		then:
		equipments.size() == 1
		equipments[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions one')
		
		when://Searching by description
		
		equipments = equipmentService.searchEquipment("Two", [:])
		then:
		equipments.size() == 1
		equipments[0].getDescriptions(new Locale("en")).equals('Equipment Descriptions two')
		
	}
}
