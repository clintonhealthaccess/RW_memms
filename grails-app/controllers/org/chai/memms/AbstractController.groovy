package org.chai.memms

import org.apache.shiro.SecurityUtils;

public abstract class AbstractController {
	
	def getTargetURI() {
		return params.targetURI?: "/"
	}
	
	def getUser() {
		return User.findByUuid(SecurityUtils.subject.principal, [cache: true])
	}
	
	def adaptParamsForList() {
		log.debug("Grails application value: " + grailsApplication)
		params.max = Math.min(params.max ? params.int('max') : grailsApplication.config.site.entity.list.max, 100)
		params.offset = params.offset ? params.int('offset'): 0
	}
}