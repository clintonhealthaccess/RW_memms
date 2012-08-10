package org.chai.memms

import geb.Page;

class HomePage extends Page {
	
	static url = "home/landingPage"
	
	static at = {
		title ==~ /MEMMS - Welcome/
	}

	static content = {
		
	}
	
}
