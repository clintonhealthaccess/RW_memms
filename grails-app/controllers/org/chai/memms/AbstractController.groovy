package org.chai.memms

public abstract class AbstractController {
	
	def getTargetURI() {
		return params.targetURI?: "/"
	}
	
	def getUser() {
		return User.findByUuid(SecurityUtils.subject.principal, [cache: true])
	}
	
	def adaptParamsForList() {
		params.max = Math.min(params.max ? params.int('max') : grailsApplication.config.site.entity.list.max, 100)
		params.offset = params.offset ? params.int('offset'): 0
	}
}