package org.chai.memms

import javax.servlet.ServletRequest;

import geb.Browser;
import geb.spock.GebSpec;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.chai.memms.security.User;
import org.chai.memms.security.UserType;

class FunctionalSpec extends GebSpec {

	def cleanup() {
		logout()
		User.executeUpdate("delete User")
	}
	
	def logout() {
		Browser.drive() {
			go 'auth/signOut'
		}
	}
	
	def login() {
		newUser('admin', 'admin')
		Browser.drive() {
			to LoginPage
			at LoginPage
			assert $('title').text() == 'Login'
			
			username = 'admin'
			password = 'admin'
			$('button').click()
		} 
	}
	
	def newUser(def username, def password) {
		return new User(userType: UserType.OTHER, username: username, email: 'admin@admin.com',
			passwordHash: new Sha256Hash(password).toString(), active: true, confirmed: true, uuid: 'uuid',
			firstname: 'first', lastname: 'last', permissionString: '*',
			organisation: 'org', phoneNumber: '+250 11 111 11 11').save(failOnError: true)
	}
	
}
