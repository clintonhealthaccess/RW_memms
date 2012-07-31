package org.chai.memms.location;

import org.chai.memms.AbstractEntityController;

class DataLocationTypeController extends AbstractEntityController {

	def bindParams(def entity) {
		entity.properties = params
		
		if (params.names!=null) entity.names = params.names
	}

	def getModel(def entity) {
		[dataLocationType: entity]
	}

	def getEntityClass(){
		return DataLocationType.class;
	}
	
	def getEntity(def id) {
		return DataLocationType.get(id);
	}

	def createEntity() {
		return new DataLocationType();
	}
	
	def deleteEntity(def entity) {
		if (DataLocation.findAllByType(entity).size() != 0) {
			flash.message = message(code: 'dataLocationType.hasentities', args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Type {0} still has associated entities.')
		}
		else {
			super.deleteEntity(entity)
		}
	}
	
	def getTemplate() {
		return '/entity/location/createDataLocationType'
	}

	def getLabel() {
		return 'datalocationtype.label';
	}
	
	def list = {
		adaptParamsForList()
		
		List<DataLocationType> types = DataLocationType.list(params);

		render (view: '/entity/list', model:[
			template:"location/dataLocationTypeList",
			entities: types,
			entityCount: DataLocationType.count(),
			code: getLabel(),
			entityClass: getEntityClass()
		])
	}
}
