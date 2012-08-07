/**
 * 
 */
package org.chai.memms.equipment

import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.location.DataLocation;
import org.chai.memms.security.User;

/**
 * @author Jean Kahigiso M.
 *
 */
class EquipmentSpec extends IntegrationTests {
	
	def "Equipment can be added"(){
		setup:
		setupLocationTree()
		
		when:
		def user = newUser("username","password",true, true)
		def dataLocation = DataLocation.findByCode(BUNGWE)
		def modelOne = Initializer.newEquipmentModel(['en':'Model One'],'MODEL1',['en':'Model One'])
		def surgery = Initializer.newDepartment(['en':'Surgery'],'SURGERY',['en':'Surgery Dep'])
		def manufacture = Initializer.newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
		
		def equipment = Initializer.newEquipment(
			"SERIAL10"
			,"2900.23",
			['en':'Equipment Descriptions'],
			['en':'Equipment Observation'],
			Initializer.getDate(22,07,2010),
			Initializer.getDate(10,10,2010),
			new Date(),
			EquipmentModel.findByCode('MODEL1'),
			DataLocation.findByCode(BUTARO),
			Department.findByCode('SURGERY')
			)
		
		
		def supplier = Initializer.newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
		def contact = Initializer.newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
		def warranty = Initializer.newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",Initializer.getDate(10, 12, 2010),Initializer.getDate(12, 12, 2012),[:],equipment)
		def status= Initializer.newEquipmentStatus(new Date(),User.findByUsername("username"),Status.INSTOCK,equipment,true)
		
		warranty.contact=contact
		equipment.manufacture=manufacture
		equipment.supplier=supplier
		equipment.warranty=warranty
		equipment.addToStatus(status)
		
		equipment.save(failOnError:true)
		
		then:
		Equipment.list().size()==1
		
	}

}
