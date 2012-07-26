class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		// homepage in home controller
		"/"(controller:"home", action:"index")
		"404"(view:'/404')
		"500"(view:'/error')
	}
}
