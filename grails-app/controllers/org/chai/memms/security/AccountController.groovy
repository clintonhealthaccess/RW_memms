package org.chai.memms.security

import org.apache.shiro.SecurityUtils;
import org.chai.memms.AbstractController


class AccountController extends  AbstractController{
	def languageService
	//def shiroSecurityManager
	
	def editAccount = {
		def user = User.findByUuid(SecurityUtils.subject?.principal)
		if (user != null) {
			render (view:'editAccount', model: [user: user, languages: languageService.availableLocales])
		}
		else {
			// to 404 error page
			response.sendError(404)
		}
	}
	
	def saveAccount = {
		def user = User.findByUuid(SecurityUtils.subject?.principal)
		if (user != null) {
			bindData(user, params, [include: ['firstname', 'lastname', 'defaultLanguage', 'phoneNumber', 'organisation']])
			
			if (!user.validate()) {
				render(view:'editAccount', model: [user: user, languages: languageService.availableLocales])
			}
			else {
				flash.message = message(code: 'myaccount.saved')
				
				user.save()
				redirect(uri: targetURI)
			}
		}
		else {
			// to 404 error page
			response.sendError(404)
		}
	}
}