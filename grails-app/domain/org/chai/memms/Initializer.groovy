/**
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms

import java.util.Date;
import org.apache.shiro.crypto.hash.Sha256Hash
import org.chai.memms.Contact;
import org.chai.memms.equipment.Department;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.equipment.EquipmentModel;
import org.chai.memms.equipment.EquipmentStatus;
import org.chai.memms.equipment.EquipmentType
import org.chai.memms.equipment.EquipmentType.Observation;
import org.chai.memms.equipment.Warranty;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.location.CalculationLocation;
import org.chai.memms.location.DataLocation;
import org.chai.memms.location.DataLocationType;
import org.chai.memms.location.Location;
import org.chai.memms.location.LocationLevel;
import org.chai.memms.security.Role
import org.chai.memms.security.User
import org.chai.memms.security.UserType
//import org.codehaus.groovy.grails.commons.ConfigurationHolder as CONF

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

			def userAdmin = new User(userType: UserType.PERSON,code:"admin", location: CalculationLocation.findByCode(RWANDA), username: "admin", 
				firstname: "memms", lastname: "memms", email:'memms@memms.org', passwordHash: new Sha256Hash("admin").toHex(), active: true, 
				confirmed: true, uuid:'admin', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"user:*")

			userAdmin.addToRoles(adminRole)
			userAdmin.save(failOnError: true, flush:true)
			
			def userClerk= new User(userType: UserType.PERSON,code:"clerk", location: CalculationLocation.findByCode(KIVUYE), username: "user", 
				firstname: "user", lastname: "user", email:'user@memms.org', passwordHash: new Sha256Hash("user").toHex(), active: true, 
				confirmed: true, uuid:'user', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"*")
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
			def surgery = newDepartment(['en':'Surgery'],'SURGERY',['en':'Surgery Dep'])
			def pediatry = newDepartment(['en':'Pediatry'],'PEDIATRY',[:])
			def emeregency = newDepartment(['en':'Emeregency'],'EMERGENCY',['en':'Emeregency Dep'])
			def consultation = newDepartment(['en':'Consultation'],'CONSULTATION',['fr':'Consultation Dep'])
		}
		
		if(!EquipmentModel.count()){
			//Add Equipment Model
			def modelOne = newEquipmentModel(['en':'Model One'],'MODEL1',['en':'Model One'])
			def modelTwo= newEquipmentModel(['fr':'Model Two'],'MODEL2',['en':'Model Two'])
			def modelThree = newEquipmentModel([:],'MODEL3',['en':'Model Three'])
		}
		
		if(!EquipmentType.count()){
			//Add equipment types as defined in ecri
			def typeOne = newEquipmentType("15810", ["en":"Accelerometers"],["en":"used in memms"],Observation.USEDINMEMMS,now(),now())
			def typeTwo = newEquipmentType("15819", ["en":"X-Ray Film Cutter"],["en":"used in memms"],Observation.USEDINMEMMS,now(),now())
			def typeThree = newEquipmentType("15966", ["en":"Video Systems"],["en":"used in memms"],Observation.USEDINMEMMS,now(),now())
			def typeFour = newEquipmentType("10035", ["en":"Adhesives, Aerosol"],["en":"not used in memms"],Observation.RETIRED,now(),now())
		}
		
		//newEquipmentType(def code, def names,def descriptions, def observation, def addedOn, def lastModifiedOn)
		
		if(!Equipment.count()){
			def equipmentOne = newEquipment(
				"SERIAL10"
				,"2900.23",
				['en':'Equipment Descriptions'],
				['en':'Equipment Observation'],
				getDate(22,07,2010),
				getDate(10,10,2010),
				now(),
				EquipmentModel.findByCode('MODEL1'),
				DataLocation.findByCode(BUTARO),
				Department.findByCode('SURGERY'),
				EquipmentType.findByCode("15819")
				)
			def manufacture = newContact(['en':'Address Descriptions '],"Manufacture","jkl@yahoo.com","0768-889-787","Street 154")
			def supplier = newContact([:],"Supplier","jk@yahoo.com","0768-888-787","Street 1654")
			def contact = newContact([:],"Contact","jk@yahoo.com","0768-888-787","Street 654")
			def warranty = newWarranty("Code",['en':'warranty'],'warranty name','email@gmail.com',"0768-889-787","Street 154",getDate(10, 12, 2010),getDate(12, 12, 2012),[:],equipmentOne)
			def status= newEquipmentStatus(now(),User.findByUsername("admin"),Status.INSTOCK,equipmentOne,true)
			
			warranty.contact=contact
			equipmentOne.manufacture=manufacture
			equipmentOne.supplier=supplier
			equipmentOne.warranty=warranty
			equipmentOne.addToStatus(status)
			
			equipmentOne.save(failOnError:true)
		}
		
		
	}
	
	
	
	//Models definition
	public static def newEquipment(def serialNumber,def purchaseCost,def descriptions,def observations,def manufactureDate, def purchaseDate,def registeredOn,def model,def dataLocation,def department, def type){
		def equipment = new Equipment(serialNumber:serialNumber,purchaseCost:purchaseCost,manufactureDate:manufactureDate,purchaseDate:purchaseDate,registeredOn:registeredOn,model:model,dataLocation:dataLocation,department:department,type:type);
		setLocaleValueInMap(equipment,descriptions,"Descriptions")
		setLocaleValueInMap(equipment,observations,"Observations")	
		return equipment.save(failOnError: true)
	}
	
	
	public static def newEquipmentStatus(def statusChangeDate,def changedBy,def value, def equipment,def current){
		return new EquipmentStatus(statusChangeDate:statusChangeDate,changedBy:changedBy,value:value,equipment:equipment,current:current).save(failOnError: true)
	}

	public static def newContact(def addressDescriptions,def contactName,def email, def phone, def address){
		def contact = new Contact(contactName:contactName,email:email,phone:phone,address:address)
		setLocaleValueInMap(contact,addressDescriptions,"AddressDescriptions")
		return contact;
	}
	public static def newWarranty(def code,def contact, def startDate,def endDate,def descriptions,def equipment){
		def warranty = new Warranty(code:code,contact:contact,startDate:startDate,endDate:endDate,equipment:equipment)
		setLocaleValueInMap(warranty,descriptions,"Descriptions")
		return warranty.save(failOnError: true)
	}
	public static def newWarranty(def code,def addressDescriptions,def contactName,def email, def phone, def address,def startDate,def endDate,def descriptions,def equipment){
		def warranty = new Warranty(code:code,contactName:contactName,email:email,phone:phone,address:address,startDate:startDate,endDate:endDate,equipment:equipment)
		setLocaleValueInMap(warranty,descriptions,"Descriptions")
		return warranty.save(failOnError: true)
	}
	public static def newEquipmentModel(def names,def code,def descriptions){
		def model = new EquipmentModel(code:code)
		setLocaleValueInMap(model,names,"Names")
		setLocaleValueInMap(model,descriptions,"Descriptions")
		return model.save(failOnError: true)
	}
	
	public static def newEquipmentType(def code, def names,def descriptions, def observation, def addedOn, def lastModifiedOn){
		def type = new EquipmentType(code:code,observation:observation,addedOn:addedOn,lastModifiedOn:lastModifiedOn)
		setLocaleValueInMap(type,names,"Names")
		setLocaleValueInMap(type,descriptions,"Descriptions")
		return type.save(failOnError: true)
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
	   //TODO replace with CONF variable if this fails
	   def grailsApplication = new Initializer().domainClass.grailsApplication
	   grailsApplication.config.i18nFields.locales.each{ loc ->
		   if(map.get(loc) != null)
			   object."$methodName"(map.get(loc),new Locale(loc))
		   else
			   object."$methodName"("",new Locale(loc))
	   }
   }
   public static def now(){
	   return new Date()
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
