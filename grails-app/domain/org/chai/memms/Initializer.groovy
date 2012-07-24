package org.chai.memms

import org.apache.shiro.crypto.hash.Sha256Hash
import org.chai.memms.security.Role
import org.chai.memms.security.User
import org.chai.memms.security.UserType

public class Initializer {

	static def createUsers() {
		def adminRole = new Role(name: "Admin")
		adminRole.addToPermissions("*")
		adminRole.save(failOnError: true, flush:true)
		
		def dataClerkRole = new Role(name: "Clerk")
		dataClerkRole.addToPermissions("*")
		dataClerkRole.save(failOnError: true, flush:true)

		def userAdmin = new User(userType: UserType.PERSON,code:"admin", locationId: 12.0, username: "admin", firstname: "memms", lastname: "memms", email:'memms@memms.org', passwordHash: new Sha256Hash("admin").toHex(), active: true, confirmed: true, uuid:'memms_uuid', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"user:*")
		userAdmin.addToRoles(adminRole)
		userAdmin.save(failOnError: true, flush:true)
		
		def userClerk= new User(userType: UserType.PERSON,code:"Clerk", locationId: 13.0, username: "user", firstname: "user", lastname: "user", email:'user@memms.org', passwordHash: new Sha256Hash("user").toHex(), active: true, confirmed: true, uuid:'user_uuid', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"*")
		userClerk.addToRoles(dataClerkRole)
		userClerk.save(failOnError: true, flush:true)
	}
}
