package org.chai.memms

import geb.Page;

class LoginPage extends Page {

	static url = "auth/login"
	
	static at = {
		title ==~ /Login/
	}

	static content = {
		username { $('input', name: 'username') }
		password { $('input', name: 'password') }
		submit { $("button", type: "submit") }
	}

}
