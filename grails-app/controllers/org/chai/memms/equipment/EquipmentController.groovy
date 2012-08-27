package org.chai.memms.equipment

import org.apache.shiro.SecurityUtils;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.Contact
import org.chai.memms.Initializer;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.security.User;
import org.chai.memms.security.UserType;
import org.chai.memms.equipment.Provider
import org.chai.memms.location.DataLocation;
import org.chai.memms.location.CalculationLocation;
import org.chai.memms.location.DataLocationType;
import org.chai.memms.location.Location
import org.chai.memms.location.LocationLevel

import java.util.HashSet;
import java.util.Set


class EquipmentController extends AbstractEntityController{
	
	def providerService
	def equipmentService
	def inventoryService
	
	def index = {
		redirect(action: "summaryPage", params: params)
	}
	
	def summaryPage = {
		def location = Location.get(params.int('location'))
		
		def dataLocationTypesFilter = getLocationTypes()
		
		def template = null
		def inventories = null
		
		def locationSkipLevels = new HashSet<LocationLevel>()
		
		if (location != null) {
			template = '/inventory/sectionTable'
			inventories = inventoryService.getInventoryByLocation(location,dataLocationTypesFilter)			
		}
		
		render (view: '/inventory/summaryPage', model: [
			inventories:inventories,
			currentLocation: location,
			currentLocationTypes: dataLocationTypesFilter,
			template: template,
			locationSkipLevels: locationSkipLevels
		])
	}
    def getEntity(def id) {
		return Equipment.get(id);
	}

	def createEntity() {
		def entity = new Equipment();
		def location = DataLocation.get(params.int("location"));
		if(!params["dataLocation"]) entity.location = location;
		return entity;
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
		if(!entity.id)
			entity.registeredOn=new Date()
		bindData(entity,params, [exclude:['status','dateOfEvent']])
	}
	
	def validateEntity(def entity) {
		boolean valid = true
		if(entity.id!=null && params["status"].equals("NONE") && params["dateOfEvent"]){
			valid = false
			entity.errors.rejectValue("status","equipment.status.notselected", "You have to select a status.");
		}
		return (valid & entity.validate())
	}
	
	def saveEntity(def entity) {
		entity.save()
		if(params['status'] && params["dateOfEvent"]){
			def status = params['status']
			status = Status."$status"
			def currentStatus = newEquipmentStatus(new Date(),getUser(),status,entity,true,params["dateOfEvent"])
		}
	}
	 
	def getModel(def entity) {
		[
			equipment:entity,
			departments:Department.list(),
			manufactures: providerService.getManufacturesAndBoth(),
			suppliers: providerService.getSuppliersAndBoth(),
			types: EquipmentType.list(),
			dataLocations: DataLocation.list()
		]
	}

	def list={
		adaptParamsForList()
		def user = getUser()
		List<Equipment> equipments= (user.userType == UserType.ADMIN)?  equipmentService.getEquipments(params):equipmentService.getEquipmentsByLocation(user.location,params)
		
		render(view:"/entity/list", model:[
			template:"equipment/equipmentList",
			entities: equipments,
			entityCount: equipments.totalCount,
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
			entityCount: equipments.totalCount,
			code: getLabel(),
			q:params['q']
		])
		
	}
	def filter = {
		
	}
	
	def export = {
		
	}
	def importer = {
		
	}
	static def newEquipmentStatus(def statusChangeDate,def changedBy,def value, def equipment,def current,def dateOfEvent){
		return new EquipmentStatus(statusChangeDate:statusChangeDate,changedBy:changedBy,value:value,equipment:equipment,current:current,dateOfEvent:dateOfEvent).save(failOneError:true,flush:true)
	}
	
}
