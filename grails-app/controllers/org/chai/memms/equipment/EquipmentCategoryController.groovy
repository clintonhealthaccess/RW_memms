package org.chai.memms.equipment

import org.chai.memms.AbstractEntityController;

class EquipmentCategoryController extends AbstractEntityController{

    def getEntity(def id) {
		return EquipmentCategory.get(id);
	}

	def createEntity() {
		return new EquipmentCategory();
	}

	def getTemplate() {
		return "/entity/equipmentCategory/createEquipmentCategory";
	}

	def getLabel() {
		return "equipment.category.label";
	}
	
	def deleteEntity(def entity) {
//		if(Equipment.findByDepartment(entity)==null)
//			flash.message = message(code: 'department.hasequipment', args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Department {0} still has associated equipment.')
	}
	
	def getEntityClass() {
		return EquipmentCategory.class;
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
		def equipmentCategories = EquipmentCategory.list(params)
		render(view:"/entity/list", model:[
			template:"equipmentCategory/equipmentCategoriesList",
			entities: equipmentCategories,
			entityCount: EquipmentCategory.count(),
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
}
