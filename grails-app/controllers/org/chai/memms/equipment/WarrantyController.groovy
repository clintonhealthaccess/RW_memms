package org.chai.memms.equipment

import org.chai.memms.AbstractEntityController;

class WarrantyController extends AbstractEntityController{

    def getEntity(def id) {
		return Warranty.get(id);
	}

	def createEntity() {
		return new Warranty();
	}

	def getTemplate() {
		return "/entity/warranty/createWarranty";
	}

	def getLabel() {
		return "warranty.label";
	}
	
	def deleteEntity(def entity) {
//		if(Equipment.findByDepartment(entity)==null)
//			flash.message = message(code: 'department.hasequipment', args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Department {0} still has associated equipment.')
	}
	
	def getEntityClass() {
		return Warranty.class;
	}
	def bindParams(def entity) {
		entity.properties = params
	}
	
	def getModel(def entity) {
		[
			model:entity
		]
	}
	
	def list={
		adaptParamsForList()
		def warranties = Warranty.list(params)
		render(view:"/entity/list", model:[
			template:"warranty/warrantiesList",
			entities: warranties,
			entityCount: Warranty.count(),
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
}
