package org.chai.memms

import java.util.Date;
import org.apache.shiro.crypto.hash.Sha256Hash
import org.chai.memms.equipment.Contact;
import org.chai.memms.equipment.Department;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.equipment.EquipmentCategory;
import org.chai.memms.equipment.EquipmentCategoryLevel;
import org.chai.memms.equipment.EquipmentModel;
import org.chai.memms.equipment.Warranty;
import org.chai.memms.location.CalculationLocation;
import org.chai.memms.location.DataLocation;
import org.chai.memms.location.DataLocationType;
import org.chai.memms.location.Location;
import org.chai.memms.location.LocationLevel;
import org.chai.memms.security.Role
import org.chai.memms.security.User
import org.chai.memms.security.UserType
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CONF

public class Initializer {
	static final String HEALTH_CENTER_GROUP = "Health Center"
	static final String DISTRICT_HOSPITAL_GROUP = "District Hospital"
	
	static final String NATIONAL = "National"
	static final String PROVINCE = "Province"
	static final String DISTRICT = "District"
	static final String SECTOR = "Sector"
	
	static final String RWANDA = "Rwanda"
	static final String KIGALI_CITY = "Kigali City"
	static final String NORTH = "North"
	static final String SOUTH = "South"
	static final String WEST = "West"
	static final String EAST = "East"
	static final String BURERA = "Burera"
	static final String BUTARO = "Butaro DH"
	static final String KIVUYE = "Kivuye HC"
	static final String BUNGWE = "Bungwe HC"

	static def createUsers() {
		if(!User.count()){
			def adminRole = new Role(name: "Admin")
			adminRole.addToPermissions("*")
			adminRole.save(failOnError: true, flush:true)
			
			def dataClerkRole = new Role(name: "Clerk")
			dataClerkRole.addToPermissions("*")
			dataClerkRole.save(failOnError: true, flush:true)
	
			def userAdmin = new User(userType: UserType.PERSON,code:"admin", location: CalculationLocation.findByCode(RWANDA), username: "admin", firstname: "memms", lastname: "memms", email:'memms@memms.org', passwordHash: new Sha256Hash("admin").toHex(), active: true, confirmed: true, uuid:'admin', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"user:*")
			userAdmin.addToRoles(adminRole)
			userAdmin.save(failOnError: true, flush:true)
			
			def userClerk= new User(userType: UserType.PERSON,code:"clerk", location: CalculationLocation.findByCode(KIVUYE), username: "user", firstname: "user", lastname: "user", email:'user@memms.org', passwordHash: new Sha256Hash("user").toHex(), active: true, confirmed: true, uuid:'user', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"*")
			userClerk.addToRoles(dataClerkRole)
			userClerk.save(failOnError: true, flush:true)
		}
	}
	
	static def createDummyStructure() {
		
		if (!Location.count()) {
			//Add Location types
			def hc = newDataLocationType(['en':HEALTH_CENTER_GROUP], HEALTH_CENTER_GROUP);
			def dh = newDataLocationType(['en':DISTRICT_HOSPITAL_GROUP], DISTRICT_HOSPITAL_GROUP);
			//Add Location Level
			def country = newLocationLevel(['en':NATIONAL], NATIONAL)
			def province = newLocationLevel(['en':PROVINCE], PROVINCE)
			def district = newLocationLevel(['en':DISTRICT], DISTRICT)
			def sector = newLocationLevel(['en':SECTOR], SECTOR)
			//Add Location
			def rwanda = newLocation(['en':RWANDA], RWANDA,null,country)
			def kigali = newLocation(['en':KIGALI_CITY],KIGALI_CITY,rwanda,province)
			def north = newLocation(['en':NORTH], NORTH, rwanda, province)
			def south = newLocation(['en':SOUTH],SOUTH,rwanda,province)
			def west = newLocation(['en':WEST], WEST,rwanda,province)
			def east = newLocation(['en':EAST], EAST,rwanda,province)
			def burera = newLocation(['en':BURERA], BURERA, north, district)
			
			//Add DataLocation
			def butaro = newDataLocation(['en':BUTARO],BUTARO,burera,dh)
			def kivuye = newDataLocation(['en':KIVUYE],KIVUYE,burera,hc)
			def bungwe = newDataLocation(['en':BUNGWE],BUNGWE,burera,hc)
		}		
	}
	static def createInventoryStructure(){
		if(!Department.count()){
			//Add Department
//			def test = new Department(code:'testCode',names:['en':'Surgery'],descriptions:['en':'Surgery Dep']).save(failOnError:true)
//			def test = new Department(code:'testCode',names_en:'Surgery',descriptions_fr:'Surgery Dep').save(failOnError:true)
			def surgery = newDepartment(['en':'Surgery'],'SURGERY',['en':'Surgery Dep'])
			def pediatry = newDepartment(['en':'Pediatry'],'PEDIATRY',[:])
			def emeregency = newDepartment(['en':'Emeregency'],'EMERGENCY',['en':'Emeregency Dep'])
			def consultation = newDepartment(['en':'Consultation'],'CONSULTATION',['fr':'Consultation Dep'])
		}
		if(!EquipmentCategoryLevel.count()){
			//Add Equipment Category Level
			def firstLevel = newEquipmentCategoryLevel(['en':'First Level'],'firstLevel',['en':'First Level'])
			def secondLevel = newEquipmentCategoryLevel(['en':'Second Level'],'secondLevel',['en':'Second Level'])
			def thridLevel = newEquipmentCategoryLevel(['en':'Thrid Level'],'thridLevel',['rw':'Thrid Level'])
			def fourthLevel = newEquipmentCategoryLevel(['en':'Fourth Level'],'fourthLevel',[:])
		}	
		if(!EquipmentCategory.count()){
			//Add Equipment Category
			def equipmentCatOne = newEquipmentCategory(['en':'Category One'],'equipmentCatOne',['rw':'Category One'],null, EquipmentCategoryLevel.findByCode('firstLevel'))
			def equipmentCatTwo = newEquipmentCategory(['en':'Category Two'],'equipmentCatTwo',['en':'Category Two'],equipmentCatOne, EquipmentCategoryLevel.findByCode('secondLevel'))
			def equipmentCatThree = newEquipmentCategory(['en':'Category Three'],'equipmentCatThree',[:],equipmentCatOne, EquipmentCategoryLevel.findByCode('secondLevel'))
			def equipmentCatFour = newEquipmentCategory(['en':'Category Four'],'equipmentCatFour',['en':'Category Four'],equipmentCatTwo, EquipmentCategoryLevel.findByCode('fourthLevel'))
			
		}
		if(!EquipmentModel.count()){
			//Add Equipment Model
			def modelOne = newEquipmentModel(['en':'Model One'],'MODEL1',['en':'Model One'])
			def modelTwo= newEquipmentModel(['fr':'Model Two'],'MODEL2',['en':'Model Two'])
			def modelThree = newEquipmentModel([:],'MODEL3',['en':'Model Three'])
		}
//		if(!Equipment.count()){
//			def equipmentOne = newEquipment(
//				"CODE1",
//				"SERIAL10"
//				,"2900.23",
//				['en':'Equipment Descriptions'],
//				['en':'Equipment Observation'],
//				getDate(22,07,2010),
//				getDate(10,10,2010),
//				new Date(),
//				EquipmentModel.findByCode('MODEL1'),
//				DataLocation.findByCode(BUTARO),
//				Department.findByCode('SURGERY')
//				)
//			//def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154",equipmentOne)
//			def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654",equipmentOne)
//			def warranty = newWarranty(['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipmentOne)
//		}
//		
		
	}
	
	
	
	//Models definition
	
	public static def newEquipment(def code,def serialNumber,def purchaseCost,def descriptions,def observations,def manufactureDate, def purchaseDate,def registeredOn,def model,def dataLocation,def department){
		def equipment = new Equipment(code:code,serialNumber:serialNumber,purchaseCost:purchaseCost,manufactureDate:manufactureDate,purchaseDate:purchaseDate);
		setLocaleValueInMap(equipment,descriptions,"Descriptions")
		setLocaleValueInMap(equipment,observations,"Observations")
//		equipment.save(failOnError: true)
//		manufacture.equipment=equipment
//		supplier.equipment=equipment
//		warranty.equipment=equipment
//		
//		manufacture.save(failOnError: true)
//		supplier.save(failOnError: true)
//		warranty.save(failOnError: true)
//		
//		equipment.manufacture=manufacture;
//		equipment.supplier=supplier
//		equipment.warranty=warranty
		
		return equipment.save(failOnError: true)
	}

	public static def newContact(def addressDescriptions,def contactName,def email, def phone, def address,def equipment){
		def contact = new Contact(contactName:contactName,email:email,phone:phone,address:address,equipment:equipment)
		setLocaleValueInMap(contact,addressDescriptions,"AddressDescriptions")
//		if(equipment!=null)
//			equipment.contact=contact
		return contact.save(failOnError: true);
	}
	public static def newWarranty(def addressDescriptions,def contactName,def email, def phone, def address,def startDate,def endDate,def descriptions,def equipment){
		def warranty = new Warranty(contactName:contactName,email:email,phone:phone,address:address,startDate:startDate,endDate:endDate,equipment:equipment)
		setLocaleValueInMap(warranty,addressDescriptions,"AddressDescriptions")
		setLocaleValueInMap(warranty,descriptions,"Descriptions")
//		if(equipment!=null)
//			equipment.warranty=warranty
		return warranty.save(failOnError: true);
	}
	public static def newEquipmentModel(def names,def code,def descriptions){
		def model = new EquipmentModel(code:code)
		setLocaleValueInMap(model,names,"Names")
		setLocaleValueInMap(model,descriptions,"Descriptions")
		return model.save(failOnError: true)
	}
	public static def newEquipmentCategory(def names,def code, def descriptions,def parent, def level){
		def category = new EquipmentCategory(code: code,parent: parent,level: level)
		setLocaleValueInMap(category,names,"Names")
		setLocaleValueInMap(category,descriptions,"Descriptions")
		if(parent!=null){
			parent.addToChildren(category)
			parent.save(failOnError: true)
		}
		if(level!=null){
			level.addToCategories(category)
			level.save(failOnError: true)
		}
		return category.save(failOnError: true)
	}
	
	public static def newEquipmentCategoryLevel(def names,def code, def descriptions){
		def level = new EquipmentCategoryLevel(code:code)
		setLocaleValueInMap(level,names,"Names") 
		setLocaleValueInMap(level,descriptions,"Descriptions")
		return level.save(failOnError: true)
	}
	
	public static def newDepartment(def names,def code, def descriptions){
		def department = new Department(code:code)
		setLocaleValueInMap(department,names,"Names") 
		setLocaleValueInMap(department,descriptions,"Descriptions")
		return department.save(failOnError: true)
	}
	
	public static def newDataLocationType(def names, def code) {
		def dataLocationType = new DataLocationType(code: code)
		setLocaleValueInMap(dataLocationType,names,"Names")
		return dataLocationType.save(failOnError: true)
	}
	
	public static def newLocationLevel(def names, def code) {
		def locationLevel = new LocationLevel(code: code)
		setLocaleValueInMap(locationLevel,names,"Names")
		return locationLevel.save(failOnError: true)
	}
	
	public static def newLocation(def names, def code, def parent, def level) {
		def location = new Location(code: code, parent: parent, level: level)
		setLocaleValueInMap(location,names,"Names")
		location.save(failOnError: true)
		level.addToLocations(location)
		level.save(failOnError: true)
		if (parent != null) {
			parent.addToChildren(location)
			parent.save(failOnError: true)
		}
		return location
	}
	
	public static def newDataLocation(def names, def code, def location, def type) {
		def dataLocation = new DataLocation(code: code, location: location, type: type)
		setLocaleValueInMap(dataLocation,names,"Names")
		dataLocation.save(failOnError: true)
		if (location != null) {
			location.addToDataLocations(dataLocation)
			location.save(failOnError: true)
		}
		if (type != null) {
			type.addToDataLocations(dataLocation)
			type.save(failOnError: true)
	   }
		return dataLocation
	}
	
	static mapping ={
		table "memms_initializer_data"
		version false
	}
	
	/**
	* fieldName has to start with capital letter as
	* it is used to create setter of the object field
	* @param object
	* @param map
	* @param fieldName
	* @return
	*/
   public static def setLocaleValueInMap(def object, def map, def fieldName){
	   def methodName = 'set'+fieldName
	   CONF.config.i18nFields.locales.each{ loc ->
		   if(map.get(loc) != null)
			   object."$methodName"(map.get(loc),new Locale(loc))
		   else
			   object."$methodName"("",new Locale(loc))
	   }
   }
   
   public static Date getDate( int day, int month, int year) {
	   final Calendar calendar = Calendar.getInstance();

	   calendar.clear();
	   calendar.set( Calendar.YEAR, year );
	   calendar.set( Calendar.MONTH, month - 1 );
	   calendar.set( Calendar.DAY_OF_MONTH, day );

	   return calendar.getTime();
   }
}
