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
			if (!Role.count()) {
				//Defining Role			
				def defaultAdminRole = new Role(name: "Admin")
				defaultAdminRole.addToPermissions("*")
				defaultAdminRole.save(failOnError: true)
				
				def defaultClercRole = new Role(name: "Clerk")
				defaultClercRole.addToPermissions("equipment:")
				defaultClercRole.addToPermissions("equipmentStatus:*")
				defaultClercRole.addToPermissions("home:*")
				defaultClercRole.addToPermissions("menu:home")
				defaultClercRole.addToPermissions("menu:inventory")
				defaultClercRole.addToPermissions("provider:getAjaxData")
				defaultClercRole.addToPermissions("equipmentType:getAjaxData")
				defaultClercRole.addToPermissions("department:getAjaxData")
				defaultClercRole.addToPermissions("account:editAccount")
				defaultClercRole.addToPermissions("account:saveAccount")
				defaultClercRole.addToPermissions("auth:newPassword")
				defaultClercRole.addToPermissions("auth:saveAccount")
				defaultClercRole.addToPermissions("auth:setPassword")
				defaultClercRole.addToPermissions("auth:retrievePassword")
				defaultClercRole.save(failOnError: true)
			}
			if (!User.count()) {
				//Defining User
				//User with admin role
				def admin = new User(userType: UserType.ADMIN,code:"admin", location: CalculationLocation.findByCode(0), username: "admin", 
					firstname: "memms", lastname: "memms", email:'memms@memms.org', passwordHash: new Sha256Hash("admin").toHex(), active: true, 
					confirmed: true, uuid:'admin', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org')
				admin.addToRoles(Role.findByName("Admin"))
				admin.save(failOnError: true)
				
				//User with default clerk role
				def userClerkOne= new User(userType: UserType.OTHER,code:"user", location: CalculationLocation.findByCode(040403), username: "user", 
					firstname: "user", lastname: "user", email:'user@memms.org', passwordHash: new Sha256Hash("user").toHex(), active: true, 
					confirmed: true, uuid:'user', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org')
				userClerkOne.addToRoles(Role.findByName("Clerk"))
				userClerkOne.save(failOnError: true)
			}
			break;
		}
    }
    def destroy = {
    }
}
