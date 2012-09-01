package org.chai.memms.location;

import org.chai.memms.AbstractEntityController;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


class LocationController extends AbstractEntityController {

	def locationService
	
	def bindParams(def entity) {
		entity.properties = params		
	}

	def getModel(def entity) {
		def locations = []
		if (entity.parent != null) locations << entity.parent
		[	location: entity, 
			locations: locations, 
			levels: LocationLevel.list([cache: true])
			]
	}

	def getEntityClass(){
		return Location.class;
	}
	
	def getEntity(def id) {
		return Location.get(id);
	}

	def createEntity() {
		return new Location();
	}

	def deleteEntity(def entity) {
		if (entity.children.size() != 0) {
			flash.message = message(code: 'location.haschildren', args: [message(code: getLabel(), default: 'entity'), params.id], default: '{0} still has associated children.')
		}
		else if (entity.dataLocations.size() != 0) {
			flash.message = message(code: 'location.hasdataentities', args: [message(code: getLabel(), default: 'entity'), params.id], default: '{0} still has associated facility.')
		}
		else {
			super.deleteEntity(entity)
		}
	}
	
	def getTemplate() {
		return '/entity/location/createLocation'
	}

	def getLabel() {
		return 'location.label';
	}

	def list = {
		adaptParamsForList()
		List<Location> locations = Location.list(offset:params.offset,max:params.max,sort:params.sort ?:"level",order: params.order ?:"asc");
		render (view: '/entity/list', model:[
			template:"location/locationList",
			entities: locations,
			entityCount: locations.totalCount,
			code: getLabel(),
			entityClass: getEntityClass(),
			names:names
		])
	}
	
	def search = {
		adaptParamsForList()
		
		List<Location> locations = locationService.searchLocation(Location.class, params['q'], params)
				
		render (view: '/entity/list', model:[
			template:"location/locationList",
			entities: locations,
			entityCount: locations.totalCount,
			code: getLabel(),
			q:params['q'],
			names:names
		])
	}
	
	def getAjaxData = {
		def clazz = Location.class
		if (params['class'] != null) clazz = Class.forName('org.chai.memms.location.'+params['class'], true, Thread.currentThread().contextClassLoader)
		
		def locations = locationService.searchLocation(clazz, params['term'], [:])
		render(contentType:"text/json") {
			elements = array {
				locations.each { location ->
					elem (
						key: location.id,
						value: location.names + ' ['+location.class.simpleName+']'
					)
				}
			}
		}
	}
	
}
