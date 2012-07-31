package org.chai.memms

import java.nio.charset.Charset;

class FormTagLib {

	def languageService
	def grailsApplication
		
	def input = { attrs, body ->
		if (attrs["type"] == null) attrs["type"] = 'text'
		out << render(template:"/tags/form/input", model: attrs)
	}
	
	
	def i18nInput = { attrs, body ->
		if (attrs["type"] == null) attrs["type"] = 'text'
		attrs["locales"] = languageService.getAvailableLanguages();
		out << render(template:"/tags/form/i18nInput", model: attrs)
	}
	
	def textarea = { attrs, body ->
		if (attrs["type"] == null) attrs["type"] = 'text'
		if (attrs["rows"] == null) attrs["rows"] = '1'
		out << render(template:"/tags/form/textarea", model: attrs)
	}
	
	def i18nTextarea = { attrs, body ->
		if (attrs["type"] == null) attrs["type"] = 'text'
		if (attrs["rows"] == null) attrs["rows"] = '4'
		if (attrs["width"] == null) attrs["width"] = '300'
		if (attrs["readonly"] == null) attrs["readonly"] = false
		attrs["locales"] = languageService.getAvailableLanguages();
		out << render(template:"/tags/form/i18nTextarea", model: attrs)
	}
		
	def selectFromEnum = { attrs, body ->
		out << render(template:"/tags/form/selectFromEnum", model: attrs)
	}
	
	def selectFromList = { attrs, body ->
		out << render(template:"/tags/form/selectFromList", model: attrs)
	}
}
