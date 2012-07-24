package org.chai.memms

import org.apache.shiro.crypto.hash.Sha256Hash
import org.chai.memms.security.Role
import org.chai.memms.security.User
import org.chai.memms.security.UserType

public class Initializer {

	static def createUsers() {
		def adminRole = new Role(name: "Admin")
		//adminRole.addToPermissions("*:*")
		adminRole.addToPermissions("user:list")
		adminRole.save()
		
		//log.debug("created admin")

		//def surveyAllReadonly = new Role(name: "Maintenance")
		//surveyAllReadonly.addToPermissions("User:*")
		//surveyAllReadonly.save()

		def user = new User(userType: UserType.PERSON,code:"admin", locationId: 12.0, username: "admin", firstname: "memms", lastname: "memms", email:'memms@memms.org', passwordHash: new Sha256Hash("admin").toHex(), active: true, confirmed: true, uuid:'memms_uuid', defaultLanguage:'en', phoneNumber: '+250 11 111 11 11', organisation:'org',permissionString:"user:*")
		user.addToRoles(adminRole)
		// access to site
		user.save(failOnError: true)
	}
}
