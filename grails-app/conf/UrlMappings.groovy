class UrlMappings {

	static mappings = {
		// TODO serve those statically, they shouldn't be here
		// since they are implementation-specific
		"/about"(controller:'home', action:"about")
		"/helpdesk"(controller:'home', action:"helpdesk")
		"/contact"(controller:'home', action:"contact")
		"/upgrade"(controller:'home', action:"upgrade")
		
		
		
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/equipment/$action/$dataLocation?/$id?"(controller:"equipment")
		"/equipmentStatus/$action/$equipment/$id?"(controller:"equipmentStatus")

		// homepage in home controller
		"/"(controller:"home", action:"index")
		"404"(view:'/404')
		"500"(view:'/error')
	}
}
