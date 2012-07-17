package org.chai.memms.security

//import grails.validation.ValidationException;

import org.chai.memms.IntegrationTests
import org.chai.memms.security.UserType;
import org.chai.memms.security.User;

class UserSpec  extends IntegrationTests {

    def "can create and save a user"() {
	
		when:
			def user = new User(
				username: 'test', code: 'test', uuid: 'test', permissionString: '',
				passwordHash: '', email: 'test@test.com', firstname: 'test', lastname: 'test',
				phoneNumber: '123', organisation: 'test', userType: UserType.OTHER, locationId:12.0
			).save(failOnError: true)
			
			def foundUser = User.get(user.id)
		then:
			user.count() == 1
			foundUser.username == user.username
	}
	
	def "can create and save a user and their role"() {
		
			when:
			def user = new User(
				username: 'test', code: 'test', uuid: 'test', permissionString: '',
				passwordHash: '', email: 'test@test.com', firstname: 'test', lastname: 'test',
				phoneNumber: '123', organisation: 'test', userType: UserType.OTHER, locationId:12.0
			)
			
			def roleOne = new Role(name:"test role one",permissionString:"new:*")
			def roleTwo = new Role(name:"test role two",permissionString:"new:*")
			
			user.addToRoles(roleTwo)
			user.addToRoles(roleOne)
			
			then:
			user.roles.size() == 2
		}
}