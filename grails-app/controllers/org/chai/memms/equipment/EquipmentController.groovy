package org.chai.memms.equipment

import org.apache.shiro.SecurityUtils;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.Contact
import org.chai.memms.Initializer;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.security.User;
import org.chai.memms.equipment.Provider
import org.chai.memms.location.CalculationLocation;

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
		def equipments
//		log.debug("++++++++++++++++Equipment lists \r\n")
//		log.debug("++++++++++++++++  user: " + getUser().username +" \r\n")
//		log.debug("++++++++++++++++  permissins: " + getUser().getPermissions().each{it} +" \r\n")
//		log.debug("++++++++++++++++  security utils: " + SecurityUtils.subject.isPermitted("equipment:list") +" \r\n")
		
		if(SecurityUtils.subject.isPermitted("*:*")){
			log.debug("+++++++++++++++ loged in user is admin \r\n")
			equipments = Equipment.list(params)
//			log.debug("+++++++++++++++ Total equipments: " + equipments.size() + "\r\n")
		}else if(SecurityUtils.subject.isPermitted("equipment:list")){
			def user = getUser()
//			log.debug("+++++++++++++++ loged in user is: " + user.username + "\r\n")
			if(user != null){
				equipments = equipmentService.myEquipments(user.location)
//				log.debug("+++++++++++++++ number of equipments : " + equipments.size()+ "\r\n")
			}
		}
		render(view:"/entity/list", model:[
			template:"equipment/equipmentList",
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
