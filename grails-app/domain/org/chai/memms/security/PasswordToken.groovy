package org.chai.memms.security

class PasswordToken {

	String token
	
	static belongsTo = [user:User]
	
    static constraints = {
    }
	
	static mapping = {
		table "memms_password_token"
		cache true
		version false
	}
}