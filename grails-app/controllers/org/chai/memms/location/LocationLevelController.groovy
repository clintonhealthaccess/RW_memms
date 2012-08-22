package org.chai.memms.location;

import org.chai.memms.AbstractEntityController;

class LocationLevelController extends AbstractEntityController {

	def locationService
	
	def bindParams(def entity) {
		entity.properties = params
		
		if (params.names!=null) entity.names = params.names
	}

	def getModel(def entity) {
		[locationLevel: entity]
	}

	def getEntityClass(){
		return LocationLevel.class;
	}
	
	def getEntity(def id) {
		return LocationLevel.get(id);
	}

	def createEntity() {
		return new LocationLevel();
	}

	def deleteEntity(def entity) {
		if (entity.locations.size() != 0) {
			flash.message = message(code: 'location.level.haslocations', args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Location level {0} still has associated locations.')
		}
		else {
			super.deleteEntity(entity)
		}
	}
	
	def getTemplate() {
		return '/entity/location/createLocationLevel'
	}

	def getLabel() {
		return 'location.level.label';
	}

	def list = {
		adaptParamsForList()
		
		List<LocationLevel> locationLevels = LocationLevel.list(params);

		render (view: '/entity/list', model:[
			template:"location/locationLevelList",
			entities: locationLevels,
			entityCount: LocationLevel.count(),
			code: getLabel(),
			entityClass: getEntityClass()
		])
	}
	
}
