package org.chai.memms.equipment

import org.apache.shiro.SecurityUtils;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.Contact
import org.chai.memms.Initializer;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.security.User;

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
	
	def deleteEntity(def entity) {
		
	}
	
	def getEntityClass() {
		return Equipment.class;
	}
	def bindParams(def entity) {		
		bindData(entity,params, [include:['status']])
		//entity.properties = params
		if(log.isDebugEnabled()) log.debug("parameter: "+params);
		
//		Initializer.newEquipmentStatus(Initializer.now(),User.findByUuid(SecurityUtils.subject.principal, [cache: true]),Status.INSTOCK, entity,true)
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
	def export = {
		
	}
	def importer = {
		
	}
	
}
