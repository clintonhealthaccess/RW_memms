/**
 * 
 */
package org.chai.memms.equipment

import org.chai.memms.AbstractEntityController;
import org.chai.memms.equipment.Provider.Type;

/**
 * @author Jean Kahigiso M.
 *
 */
class ProviderController  extends AbstractEntityController {
	
    def providerService
	
	def  getEntity(def  id) {
		return Provider.get(id);
	}

	def createEntity() {
		return new Provider();
	}

	def getTemplate() {
		return "/entity/provider/createProvider";
	}

	def getLabel() {
		return "provider.label";
	}

	def getEntityClass() {
		return Provider.class;
	}
	
	def deleteEntity(def entity) {
		if(entity.manufactures.size() > 0 || entity.suppliers.size() > 0 ){
			
		}else super.delete(entity)
	}
	def getModel(def entity) {
	     [
			 provider: entity
		 ]
	}	
	
	def bindParams(def entity) {
		entity.properties = params
	}
	
	def list = {
		adaptParamsForList()
		List<Provider> providers = Provider.list(params)
		render(view:"/entity/list", model:[
			template:"provider/providerList",
			entities: providers,
			entityCount: Provider.count(),
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
	
	def getAjaxData = {
		def type =params['type']
		type = Type."$type";
		List<Provider> providers = providerService.searchProvider(type, params['term'], [:])		
		render(contentType:"text/json") {
			elements = array {
				providers.each { provider ->
					elem (
						key: provider.id,
						value: provider.contact.contactName + ' ['+provider.code+']' 
					)
				}
			}
		}
	}
	def search = {
		adaptParamsForList()
		List<Provider> providers = providerService.searchProvider(null, params['q'], params)
				
		render (view: '/entity/list', model:[
			template:"provider/providerList",
			entities: providers,
			entityCount: providerService.countProvider(null, params['q']),
			code: getLabel()
		])
		
	}
	def importer ={
		
	}
	def export = {
		
	}


}
