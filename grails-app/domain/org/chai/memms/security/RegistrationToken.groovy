package org.chai.memms.security

class RegistrationToken {

	String token
	Boolean used = false
	
	static belongsTo = [user:User]
	
	static constraints = {
	}
}