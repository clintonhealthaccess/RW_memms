package org.chai.memms.location;

import org.chai.memms.AbstractEntityController;
import org.chai.memms.equipment.Equipment;

class DataLocationController extends AbstractEntityController {
	def locationService	
	
	def bindParams(def entity) {
		entity.properties = params
	}

	def getModel(def entity) {
		def locations = []
		if (entity.location != null) locations << entity.location
		[	location: entity, 
			types: DataLocationType.list([cache: true]), 
			locations: locations
			]
	}

	def getEntityClass(){
		return DataLocation.class;
	}
	
	def getEntity(def id) {
		return DataLocation.get(id);
	}

	def createEntity() {
		return new DataLocation();
	}

	def getTemplate() {
		return '/entity/location/createDataLocation'
	}

	def getLabel() {
		return 'datalocation.label';
	}
	
	def deleteEntity(def entity) {
		if(Equipment.findByDataLocation(entity)!=null)
			flash.message = message(code: 'datalocation.hasequipment', args: [message(code: getLabel(), default: 'entity'), params.id], default: '{0} still has associated equipment.')
		else entity.delete()
	}

	def list = {
		adaptParamsForList()
		List<DataLocation> locations = DataLocation.list(offset:params.offset,max:params.max,sort:params.sort ?:names,order: params.order ?:"asc");

		render (view: '/entity/list', model:[
			template:"location/dataLocationList",
			entities: locations,
			entityCount: locations.totalCount,
			code: getLabel(),
			entityClass: getEntityClass(),
			names:names
		])
	}
	
	def search = {
		adaptParamsForList()
		List<DataLocation> locations = locationService.searchLocation(DataLocation.class, params['q'], params)
				
		render (view: '/entity/list', model:[
			template:"location/dataLocationList",
			entities: locations,
			entityCount: locations.totalCount,
			code: getLabel(),
			q:params['q'],
			names:names
		])
	}
	
	def getAjaxData = {
		List<DataLocation> dataLocations = locationService.searchLocation(DataLocation.class, params['term'], [:])
		
		render(contentType:"text/json") {
			elements = array {
				dataLocations.each { dataLocation ->
					elem (
						key: dataLocation.id,
						value: dataLocation.getNames(languageService.getCurrentLanguage()) + ' ['+dataLocation.location.getNames(languageService.getCurrentLanguage())+']'
					)
				}
			}
		}
	}
	
}
