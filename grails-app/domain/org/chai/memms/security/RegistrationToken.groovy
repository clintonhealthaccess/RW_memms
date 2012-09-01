package org.chai.memms.security

class RegistrationToken {

	String token
	Boolean used = false
	
	static belongsTo = [user:User]
	
	static constraints = {
	}
	static mapping = {
		table "memms_registration_token"
		cache true
		version false
	}
}