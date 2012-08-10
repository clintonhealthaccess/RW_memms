package org.chai.memms.equipment

import org.chai.memms.AbstractEntityController;
import org.chai.memms.Contact

class EquipmentController extends AbstractEntityController{

    def getEntity(def id) {
		return Equipment.get(id);
	}

	def createEntity() {
		return new Equipment();
	}

	def getTemplate() {
		return "/entity/equipment/createEquipment";
	}

	def getLabel() {
		return "equipment.label";
	}
	
	def getEntityClass() {
		return Equipment.class;
	}
	def bindParams(def entity) {
		entity.properties = params
		if(log.isDebugEnabled()) log.debug("parameterS:"+params);
	}
	
	def getModel(def entity) {
		[
			equipment:entity,
			departments:Department.list()
		]
	}
	
	def list={
		adaptParamsForList()
		def equipments = Equipment.list(params)
		render(view:"/entity/list", model:[
			template:"equipment/equipmentsList",
			entities: equipments,
			entityCount: Equipment.count(),
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
	
}
