package org.chai.memms.equipment

import org.chai.memms.AbstractEntityController;

class EquipmentStatusController extends AbstractEntityController{

    def getEntity(def id) {
		return EquipmentStatus.get(id);
	}

	def createEntity() {
		return new EquipmentStatus();
	}

	def getTemplate() {
		return "/entity/equipmentStatus/createEquipmentStatus";
	}

	def getLabel() {
		return "equipment.status.label";
	}
	
	def deleteEntity(def entity) {
//		if(Equipment.findByDepartment(entity)==null)
//			flash.message = message(code: 'department.hasequipment', args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Department {0} still has associated equipment.')
	}
	
	def getEntityClass() {
		return EquipmentStatus.class;
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
		def equipmentStatuses = EquipmentStatus.list(params)
		render(view:"/entity/list", model:[
			template:"equipmentStatus/equipmentStatusList",
			entities: equipmentStatuses,
			entityCount: EquipmentStatus.count(),
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
}
