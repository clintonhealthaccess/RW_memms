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
				phoneNumber: '123', organisation: 'test', userType: UserType.OTHER, location: new Location()
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
				phoneNumber: '123', organisation: 'test', userType: UserType.OTHER, location: 12,
				)

		def roleOne = new Role(name:"test role one",permissionString:"new:*")
		def roleTwo = new Role(name:"test role two",permissionString:"new:*")

		user.addToRoles(roleTwo)
		user.addToRoles(roleOne)

		then:
		user.roles.size() == 2
	}

	def "can add permisions to roles"() {

		when:
		def user = new User(
				username: 'test', code: 'test', uuid: 'test', permissionString: '',
				passwordHash: '', email: 'test@test.com', firstname: 'test', lastname: 'test',
				phoneNumber: '123', organisation: 'test', userType: UserType.OTHER, location: 12.01,
				)

		def roleOne = new Role(name:"test role one",permissionString:"new:*;none:ugin:*")
		def roleTwo = new Role(name:"test role two",permissionString:"new:*")

		user.addToRoles(roleTwo)
		user.addToRoles(roleOne)

		then:
		roleOne.getPermissionString() == "new:*;none:ugin:*"
	}
	
	def "user must have a type"() {
		when:
		new User(
			username: 'test', code: 'test', uuid: 'test', permissionString: '',
			passwordHash: '', email: 'test@test.com', firstname: 'test', lastname: 'test',
			phoneNumber: '123', organisation: 'test',userType: UserType.Person,location: 12,
		).save(failOnError: true)
		
		then:
		thrown ValidationException
		
		when:
		new User(
			username: 'test', code: 'test', uuid: 'test', permissionString: '',
			passwordHash: '', email: 'test@test.com', firstname: 'test', lastname: 'test',
			phoneNumber: '123', organisation: 'test', userType: UserType.Person,location: 12,
		).save(failOnError: true)
		
		then:
		User.count() == 1
	}
}