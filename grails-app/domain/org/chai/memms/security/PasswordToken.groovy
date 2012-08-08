package org.chai.memms.security

class PasswordToken {

	String token
	
	static belongsTo = [user:User]
	
    static constraints = {
    }
}