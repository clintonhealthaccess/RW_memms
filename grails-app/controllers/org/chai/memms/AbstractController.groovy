package org.chai.memms

import org.apache.shiro.SecurityUtils;
public abstract class AbstractController {
	def grailsApplication
	
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