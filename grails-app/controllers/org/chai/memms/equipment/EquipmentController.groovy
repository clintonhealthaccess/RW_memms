package org.chai.memms.equipment

import org.apache.shiro.SecurityUtils;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.Contact
import org.chai.memms.Initializer;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.security.User;
import org.chai.memms.equipment.Provider

class EquipmentController extends AbstractEntityController{
	
	def providerService
	def equipmentService
	
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
	
	def deleteEntity(def entity) {
		
	}
	
	def getEntityClass() {
		return Equipment.class;
	}
	def bindParams(def entity) {	
		if(log.isDebugEnabled()) log.debug("parameter before binding: "+params);
		bindData(entity,params, [exclude:['status']])
		if(log.isDebugEnabled()) log.debug("data after binding: "+params);
	}
	
	def getModel(def entity) {
		[
			equipment:entity,
			departments:Department.list(),
			manufactures: providerService.getManufacturesAndBoth(),
			suppliers: providerService.getSuppliersAndBoth(),
			types: EquipmentType.list()
		]
	}
	
	def list={
		adaptParamsForList()
		def equipments = Equipment.list(params)
		render(view:"/entity/list", model:[
			template:"equipment/equipmentList",
			entities: equipments,
			entityCount: Equipment.count(),
			code: getLabel(),
			entityClass: getEntityClass()
			])
	}
	def search = {
		adaptParamsForList()
		List<Equipment> equipments = equipmentService.searchEquipment(params['q'], params)	
		render (view: '/entity/list', model:[
			template:"equipment/equipmentList",
			entities: equipments,
			entityCount: equipmentService.countEquipment(params['q']),
			code: getLabel()
		])
		
	}
	def filter = {
		
	}
	
	def export = {
		
	}
	def importer = {
		
	}
	
}
