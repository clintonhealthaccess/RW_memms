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

import org.chai.location.CalculationLocation;
import org.chai.memms.security.Role;
import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;

import java.util.Date;

import grails.util.GrailsUtil;

import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import org.chai.memms.Initializer;
import org.omg.CORBA.INITIALIZE;

class BootStrap {

    def init = { servletContext ->
		
		switch (GrailsUtil.environment) {
			case "development":
			Initializer.createDummyStructure();
			Initializer.createUsers();
			Initializer.createInventoryStructure()
			Initializer.createCorrectiveMaintenanceStructure()
			break;
			case "production":					
				//Default roles
				def defaultAdminRole 
				if(Role.findByName("Admin")) defaultAdminRole = Role.findByName("Admin") 
				else defaultAdminRole = new Role(name: "Admin")
				defaultAdminRole.addToPermissions("*")
				defaultAdminRole.save(failOnError: true)
				
				def defaultSystemRole
				if(Role.findByName("System")) defaultSystemRole = Role.findByName("System")
				else defaultSystemRole = new Role(name:"System")
				defaultSystemRole.addToPermissions("*")
				defaultSystemRole.save(failOnError: true)
				
				def defaultTechnicianDHRole
				if(Role.findByName("Technician DH")) defaultTechnicianDHRole = Role.findByName("Technician DH")
				else defaultTechnicianDHRole = new Role(name:"Technician DH")
				defaultTechnicianDHRole.addToPermissions("*")
				defaultTechnicianDHRole.save(failOnError: true)
				
				def defaultTechnicianMMCRole
				if(Role.findByName("Technician MMC")) defaultTechnicianMMCRole = Role.findByName("Technician MMC")
				else defaultTechnicianMMCRole = new Role(name: "Technician MMC")
				defaultTechnicianMMCRole.addToPermissions("*")
				defaultTechnicianMMCRole.save(failOnError: true)
				
				def defaultTitulaireHCRole
				if(Role.findByName("Titulaire HC")) defaultTitulaireHCRole = Role.findByName("Titulaire HC")
				else defaultTitulaireHCRole = new Role(name: "Titulaire HC")
				defaultTitulaireHCRole.addToPermissions("*")
				defaultTitulaireHCRole.save(failOnError: true)
				
				def defaultHospitalDepartmentRole 
				if(Role.findByName("Hospital Department")) defaultHospitalDepartmentRole = Role.findByName("Hospital Department")
				else defaultHospitalDepartmentRole = new Role(name: "Hospital Department")
				defaultHospitalDepartmentRole.addToPermissions("*")
				defaultHospitalDepartmentRole.save(failOnError: true)
				
				def defaultDataClerkRole
				if(Role.findByName("Data Clerk")) defaultDataClerkRole = Role.findByName("Data Clerk") 
				else defaultDataClerkRole = new Role(name: "Data Clerk")
				defaultDataClerkRole.addToPermissions("home:*")
				defaultDataClerkRole.addToPermissions("menu:home,inventory,correctivemaintenance")
				defaultDataClerkRole.addToPermissions("equipmentType:getAjaxData")
				defaultDataClerkRole.addToPermissions("provider:getAjaxData")
				defaultDataClerkRole.addToPermissions("department:getAjaxData")
				defaultDataClerkRole.addToPermissions("department:getAjaxData")
				defaultDataClerkRole.addToPermissions("equipment:filter,export,summaryPage,index,list,save,create,updateObsolete,edit")
				defaultDataClerkRole.addToPermissions("workOrder:*")
				defaultDataClerkRole.addToPermissions("notification:*")
				defaultDataClerkRole.addToPermissions("equipmentStatus:list,save,delete,edit,create")
				//Default user permission
				defaultDataClerkRole.addToPermissions("account:editAccount")
				defaultDataClerkRole.addToPermissions("account:saveAccount")
				defaultDataClerkRole.addToPermissions("auth:newPassword")
				defaultDataClerkRole.addToPermissions("auth:saveAccount")
				defaultDataClerkRole.addToPermissions("auth:setPassword")
				defaultDataClerkRole.addToPermissions("auth:retrievePassword")				
				defaultDataClerkRole.save(failOnError: true)
				
				
				def userClerk
				if(User.findByUsername("user")){ 
					userClerk = User.findByUsername("user")
					userClerk.userType=UserType.TITULAIREHC
					userClerk.save(failOnError: true)
					}
				else
				{
					userClerk = new User(userType: UserType.TITULAIREHC, location: CalculationLocation.findByCode(327), username: "user",
					firstname: "Data", lastname: "Clerk", email:'user@memms.org', passwordHash: new Sha256Hash("user").toHex(), active: true,
					confirmed: true, uuid:'user', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org')
				userClerk.addToRoles(defaultDataClerkRole)
				userClerk.save(failOnError: true)
				}
				
				def userTechnicianFacility
				if(User.findByUsername("techf")){ 
					userTechnicianFacility = User.findByUsername("techf")
					userTechnicianFacility.userType = UserType.TECHNICIANDH
					userTechnicianFacility.save(failOnError: true)
				}
				else
				{
					userTechnicianFacility = new User(userType: UserType.TECHNICIANDH, location: CalculationLocation.findByCode(327), username: "techf",
					firstname: "technician", lastname: "facility", email:'techf@memms.org', passwordHash: new Sha256Hash("techf").toHex(), active: true,
					confirmed: true, uuid:'techf', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org')
					userTechnicianFacility.addToRoles(defaultTechnicianDHRole)
					userTechnicianFacility.save(failOnError: true)
				}
				
				def userTechnicianMoH
				if(User.findByUsername("techMoH")){
					 userTechnicianMoH = User.findByUsername("techMoH")
					 userTechnicianMoH.userType=UserType.TECHNICIANMMC
					 userTechnicianMoH.save(failOnError: true)
				}
				else
				{
					userTechnicianMoH= new User(userType: UserType.TECHNICIANMMC, location: CalculationLocation.findByCode(0), username: "techMoH",
					firstname: "technician", lastname: "MoH", email:'techMoH@memms.org', passwordHash: new Sha256Hash("techMoH").toHex(), active: true,
					confirmed: true, uuid:'techMoH', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org')
					userTechnicianMoH.addToRoles(defaultTechnicianMMCRole)
					userTechnicianMoH.save(failOnError: true)
				}
			break;
		}
    }
    def destroy = {
    }
}
