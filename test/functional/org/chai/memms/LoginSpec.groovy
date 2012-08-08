package org.chai.memms

import org.apache.shiro.crypto.hash.Sha256Hash;

import geb.spock.GebSpec;

class LoginSpec extends FunctionalSpec {

	def "login"() {
		given:
		newUser('admin', 'admin')	
		to LoginPage
		
		expect:
		at LoginPage
		
		when:
		username = 'admin'
		password = 'admin'
		submit.click()
		
		then:
		at HomePage
	}
	
}
