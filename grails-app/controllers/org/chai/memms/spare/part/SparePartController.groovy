/**
 * 
 */
package org.chai.memms.spare.part

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.chai.memms.AbstractEntityController;
import org.chai.memms.spare.part.SparePartStatus;
import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart;
import org.chai.memms.Contact
import org.chai.memms.Initializer;
import org.chai.memms.spare.part.SparePart.SparePartPurchasedBy;
import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;
import org.chai.memms.util.Utils;
import org.chai.memms.spare.part.SparePart;
import org.chai.memms.inventory.Provider;
import org.chai.location.DataLocation;
import org.chai.location.CalculationLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location
import org.chai.location.LocationLevel

import java.util.HashSet;
import java.util.Set

/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartController extends AbstractEntityController{
	def sparePartStatusService


	def index = {
		redirect(controller: "sparePartView", action: "summaryPage", params: params)
	}

	def getEntity(def id) {
		return SparePart.get(id);
	}

	def createEntity() {
		return new SparePart();
	}

	def getTemplate() {
		return "/entity/sparePart/createSparePart";
	}
	
	def getLabel() {
		return "spare.part.label";
	}

	def getEntityClass() {
		return SparePart.class;
	}
	

	def bindParams(def entity) {
		if(log.isDebugEnabled()) log.debug("SparePart params: before bind "+params)
		if(!entity.id){
			entity.addedBy = user
		}else{
		    params.oldStatus =  entity.statusOfSparePart
			entity.lastModified = user
			if(params["warranty.sameAsSupplier"]=="on"){
				params["warranty.contact.contactName"]=""
				params["warranty.contact.email"]=""
				params["warranty.contact.phone"]=""
				params["warranty.contact.poBox"]=""
				params["warranty.contact.city"]=""
				params["warranty.contact.country"]=""
				grailsApplication.config.i18nFields.locales.each{loc ->
					params["warranty.contact.addressDescriptions_"+loc] = ""
				}
				entity.warranty.contact=null
			}
		}

		//Making sure a disposed sparePart cannot be modified 
		//TODO add this check to method that modified sparePart
		if(!params.oldStatus.equals(StatusOfSparePart.DISPOSED))
			bindData(entity,params,[exclude:["statusOfSparePart","dateOfEvent"]])
		if(log.isDebugEnabled()) log.debug("SparePart params: after bind  "+entity)
	}

	def validateEntity(def entity) {
		boolean validStatus = true
		if(entity.id==null){
			//Checking if the dateOfEvent is not after parchase date and add error
			if(!(entity.purchaseDate.before(params.cmd.dateOfEvent) || entity.purchaseDate.compareTo(params.cmd.dateOfEvent)==0)) 
				params.cmd.errors.rejectValue('dateOfEvent','date.of.event.before.parchase.date')
			validStatus = (!params.cmd.hasErrors()) 
			if(log.isDebugEnabled()) log.debug("Rejecting SparePartStatus: "+params.cmd.errors)
		}
		entity.getGenerateAndSetCode()
		return (validStatus & entity.validate())
	}

	def saveEntity(def entity) {
		SparePartStatus status
		if(entity.dataLocation) hasAccess(entity.dataLocation)
		if(entity.id==null){
			entity.statusOfSparePart = StatusOfSparePart."$params.cmd.statusOfSparePart"
			sparePartStatusService.createSparePartStatus(user,params.cmd.statusOfSparePart,entity,params.cmd.dateOfEvent,[:])
		}
		else entity.save(failOnError:true)
	}

	def save = { StatusCommand cmd ->
		params.cmd = cmd
		super.saveWithoutTokenCheck()
	}

	def getModel(def entity) {
		//def manufacturers = []; 
		def suppliers = []; def types = []; def dataLocations = [];
		//if (entity.manufacturer != null) manufacturers << entity.manufacturer
		if (entity.supplier != null) suppliers << entity.supplier
		if (entity.type!=null) types << entity.type
		if (entity.dataLocation!=null) dataLocations << entity.dataLocation
		[
					sparePart: entity,
					//manufacturers: manufacturers,
					suppliers: suppliers,
					types: types,
					dataLocations: dataLocations,
					numberOfStatusToDisplay: grailsApplication.config.status.to.display.on.spare.part.form,
					cmd:params.cmd,
					currencies: grailsApplication.config.site.possible.currency

				]
	}
}

class StatusCommand {
	StatusOfSparePart statusOfSparePart
	Date dateOfEvent

	static constraints = {
		statusOfSparePart nullable: false, inList: [StatusOfSparePart.DISPOSED,StatusOfSparePart.INSTOCK,StatusOfSparePart.PENDINGORDER,StatusOfSparePart.OPERATIONAL]
		dateOfEvent nullable: false
	}

	String toString(){
		return "StatusCommand [Status "+statusOfSparePart+" dateOfEvent: "+dateOfEvent+"]";
	}
}
