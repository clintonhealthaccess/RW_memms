package org.chai.memms.spare.part

import org.chai.memms.AbstractEntityController;
import org.chai.memms.spare.part.SparePartStatus;
import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart;

class SparePartStatusController extends AbstractEntityController{
	def sparePartStatusService
	def sparePartService
	def grailsApplication

    def index() {
		
	}

	
	def bindParams(def entity) {
		if(log.isDebugEnabled()) log.debug("SparePart status params: "+params)
		if (entity.id != null)
			response.sendError(404)
		else{
			entity.changedBy = user
		}
		if(!entity.sparePart?.statusOfSparePart?.equals(StatusOfSparePart.DISPOSED))
			entity.properties = params
	}

	
	def getModel(def entity) {
		[
			statusOfSparePart:entity,
			sparePart:entity.sparePart,
			numberOfStatusToDisplay: grailsApplication.config.status.to.display.on.sparePart.form
		]
	}

	
	def getEntity(Object id) {
		return SparePartStatus.get(id);
	}

	
	def createEntity() {
		return new SparePartStatus();
	}

	
	def getTemplate() {
		return "/entity/sparePartStatus/createSparePartStatus";
	}

	
	def getLabel() {
		return "sparePart.status.label";
	}

	
	def getEntityClass() {
		return SparePartStatus.class;
	}
	
	def deleteEntity(def entity) {
		def sparePart = entity.sparePart
		if(sparePart.statusOfSparePart && sparePart.statusOfSparePart.size()==1)
			flash.message = message(code: "sparePart.without.status", args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Status {0} cannot be deleted')
		else{
			sparePart.statusOfSparePart.remove(entity)
			super.deleteEntity(entity);
			sparePartService.updateCurrentSparePartStatus(sparePart,null,user)
		}
	}
	
	def saveEntity(def entity) {
		if(!entity.sparePart?.statusOfSparePart?.equals(StatusOfSparePart.DISPOSED))
			sparePartService.updateCurrentSparePartStatus(entity.sparePart,entity,user)
		else flash.message = message(code: "error.cannot.modify.disposed.sparePart", default: 'Cannot modify a disposed sparePart, please reactivate it')
	}
	
	
	def list={
		adaptParamsForList()
		def sparePart = SparePart.get(params.int("sparePart.id"))
		
		if (sparePart == null)
			response.sendError(404)
		else{
			def sparePartStatus  = sparePartStatusService.getSparePartStatusBySparePart(sparePart,params)
			if(request.xhr)
				this.ajaxModel(sparePart,sparePartStatus)
			else{
				render(view:"/entity/list", model:[
					template: "sparePartStatus/sparePartStatusList",
					listTop:"sparePartStatus/listTop",
					sparePart: sparePart,
					entities: sparePartStatus,
					entityCount: sparePartStatus.totalCount,
					code: getLabel(),
					entityClass: getEntityClass()
					])
			}
		}
	}
	def ajaxModel(def sparePart,def entities) {
		def model = [entities: entities,entityCount: entities.totalCount,sparePart:sparePart,entityClass:getEntityClass()]
		def listHtml = g.render(template:"/entity/sparePartStatus/sparePartStatusList",model:model)
		render(contentType:"text/json") { results = [listHtml] }
	}
}
