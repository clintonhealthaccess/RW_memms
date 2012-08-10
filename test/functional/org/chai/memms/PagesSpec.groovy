package org.chai.memms

import org.chai.memms.user.UserListPage;
import geb.spock.GebSpec;

class PagesSpec extends FunctionalSpec {

	def "user list page"() {
		given:
		login()
		
		when:
		to UserListPage
		
		then:
		at UserListPage
	}
	
}
