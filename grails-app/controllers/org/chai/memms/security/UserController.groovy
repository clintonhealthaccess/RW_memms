package org.chai.memms.security

import org.chai.memms.AbstractController

class UserController{
	//static scaffold = User
	def index(){
		
	}
	def getEntity(def id) {
		return User.get(id)
	}
//
//	def createEntity() {
//		return new User()
//	}
}
