/**
 * 
 */
package org.chai.memms.spare.part

import java.util.Set;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import org.chai.location.CalculationLocation;
import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location
import org.chai.location.LocationLevel

import org.chai.memms.AbstractController;
import org.chai.memms.spare.part.SparePart;
import org.chai.memms.spare.part.SparePartType;
import org.chai.memms.inventory.Provider;
import org.chai.memms.spare.part.SparePart.SparePartPurchasedBy;
import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart;


import org.chai.memms.inventory.Provider;

import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;
import org.chai.task.SparePartExportFilter;

/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartViewController extends AbstractController{
	def providerService
	def partService
	def sparePartStatusService
	def sparePartService
	def grailsApplication

	def getLabel() {
		return "spare.part.label";
	}

	def getEntityClass() {
		return SparePart.class;
	}
	
	def getSparePartClueTipsAjaxData = {
		def sparePart = SparePart.get(params.long("sparePart.id"))
		def html = g.render(template:"/templates/sparePartClueTip",model:[sparePart:sparePart])
		render(contentType:"text/plain", text:html)
	}
	//TODO By Aphrodice View Page not yet having codes "/entity/sparePart/summary"
	def view ={
		SparePart sparePart = SparePart.get(params.int("sparePart.id"))
		if(sparePart == null)	response.sendError(404)
		else render(view:"/entity/sparePart/summary", model:[sparePart: sparePart])
	}
	
	def list = {
		adaptParamsForList()
		List<SparePart> spareParts = SparePart.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc");
		if(request.xhr)
			this.ajaxModel(spareParts,"")
		else{
		render(view:"/entity/list",model:[
				template:"sparePart/sparePartList",
				listTop:"sparePart/listTop",
				entities: spareParts,
				entityCount: spareParts.totalCount,
				entityClass: getEntityClass(),
				code: getLabel(),
				names:names,
				//importTask:'EquipmentTypeImportTask',
				//exportTask:'EquipmentTypeExportTask'
			])
		}
	}
	
	/*def list={
		def location = DataLocation.get(params.int('location.id'))
		def spareParts= sparePartService.getSparePartsByDataLocationAndManages(location,params)
		adaptParamsForList()
		
		if (location != null){
			if(!user.canAccessCalculationLocation(location)) response.sendError(404)
			spareParts = sparePartService.getSparePartsByDataLocationAndManages(location,params)
		}
		else spareParts = sparePartService.getMySpareParts(user,params)
		
		if(request.xhr){
			 this.ajaxModel(spareParts,location,"")
		 }else{
			 log.debug("not an ajax request"+params)
			render(view:"/entity/list", model:[
						template:"sparePart/sparePartList",
						filterTemplate:"sparePart/sparePartFilter",
						listTop:"sparePart/listTop",
						location:location,
						entities: spareParts,
						entityCount: spareParts.totalCount,
						entityClass: getEntityClass(),
						code: getLabel()
						
						])
		}

	}*/
	
	//TODO don't think we need ajax for this
	def selectFacility = {
		adaptParamsForList()
		def locations = []
		locations.add(user.location as DataLocation)
		if((user.location as DataLocation).manages)
			locations.addAll((user.location as DataLocation).manages)
			
		render(view:"/entity/list", model:[
					listTop:"sparePart/listTop",
					template:"sparePart/selectFacility",
					locations:locations
				])
	}
	
	def search = {
		DataLocation location = DataLocation.get(params.int("location.id"))
		if (location != null && !user.canAccessCalculationLocation(location))
			response.sendError(404)
		else{
			adaptParamsForList()
			def spareParts = sparePartService.searchSparePart(params['q'],user,location,params)
			if(!request.xhr)
				response.sendError(404)
			else
				this.ajaxModel(spareParts,location,params['q'])
		}
	}
	
	def filter = { FilterCommand cmd ->
		if (log.isDebugEnabled()) log.debug("spareParts.filter, command "+cmd)
		if (cmd.location != null && !user.canAccessCalculationLocation(cmd.location))
			response.sendError(404)
		else{
			adaptParamsForList()
			def spareParts = sparePartService.filterSparePart(user,cmd.location,cmd.supplier,cmd.manufacturer,cmd.sparePartType,cmd.purchaser,cmd.sameAsManufacturer,cmd.status,params)
			if(!request.xhr)
				response.sendError(404)
			else this.ajaxModel(spareParts,cmd.location,"")
		}
	}
	
	def ajaxModel(def entities,def location,def searchTerm) {
		def model = [entities: entities,entityCount: entities.totalCount,location:location,q:searchTerm]
		def listHtml = g.render(template:"/entity/sparePart/sparePartList",model:model)
		render(contentType:"text/json") { results = [listHtml] }
	}

	def summaryPage = {
		if(user.location instanceof DataLocation) redirect(controller:"sparePartView",action:"list")

		def location = DataLocation.get(params.long('dataLocation'))
		def locationTypesFilter = getLocationTypes()
		def template = null
		def spareParts = null

		adaptParamsForList()

		def locationSkipLevels = partService.getSkipLocationLevels()

		if (location != null)
			spareParts = partService.getsparePartByLocation(location,locationTypesFilter,params)
	
		render (view: '/partSummaryPage/summaryPage', model: [
					spareParts:spareParts?.sparePartList,
					currentLocation: location,
					currentLocationTypes: locationTypesFilter,
					template: "/partSummaryPage/sectionTable",
					entityCount: spareParts?.totalCount,
					locationSkipLevels: locationSkipLevels
				])
	}

/*	def generalExport = { ExportFilterCommand cmd ->

		// we do this because automatic data binding does not work with polymorphic elements
		Set<CalculationLocation> calculationLocations = new HashSet<CalculationLocation>()
		params.list('calculationLocationids').each { id ->
			if (NumberUtils.isDigits(id)) {
				def calculationLocation = CalculationLocation.get(id)
				if (calculationLocation != null && !calculationLocations.contains(calculationLocation)) calculationLocations.add(calculationLocation);
			}
		}
		cmd.calculationLocations = calculationLocations

		Set<DataLocationType> locationTypes = new HashSet<DataLocationType>()
		params.list('locationTypeids').each { id ->
			if (NumberUtils.isDigits(id)) {
				def locationType = DataLocationType.get(id)
				if (locationType != null && !locationTypes.contains(locationType)) locationTypes.add(locationType);
			}
		}
		cmd.locationTypes = locationTypes

		Set<SparePartType> sparePartTypes = new HashSet<SparePartType>()
		params.list('sparePartTypeids').each { id ->
			if (NumberUtils.isDigits(id)) {
				def sparePartType = SparePartType.get(id)
				if (sparePartType != null && !sparePartTypes.contains(sparePartType)) sparePartTypes.add(sparePartType);
			}
		}
		cmd.sparePartTypes = sparePartTypes

		Set<Provider> manufacturers = new HashSet<Provider>()
		params.list('manufacturerids').each { id ->
			if (NumberUtils.isDigits(id)) {
				def manufacturer = Provider.get(id)
				if (manufacturer != null && !manufacturers.contains(manufacturer)) manufacturers.add(manufacturer);
			}
		}
		cmd.manufacturers = manufacturers

		Set<Provider> suppliers = new HashSet<Provider>()
		params.list('supplierids').each { id ->
			if (NumberUtils.isDigits(id)) {
				def supplier = Provider.get(id)
				if (supplier != null && !suppliers.contains(supplier)) suppliers.add(supplier);
			}
		}
		cmd.suppliers = suppliers

		if (log.isDebugEnabled()) log.debug("spareParts.export, command="+cmd+", params"+params)


		if(params.exported != null){
			def sparePartExportTask = new SparePartExportFilter(calculationLocations:cmd.calculationLocations,locationTypes:cmd.locationTypes,
					sparePartTypes:cmd.sparePartTypes,manufacturers:cmd.manufacturers,suppliers:cmd.suppliers,sparePartStatus:cmd.sparePartStatus,
					purchaser:cmd.purchaser,sameAsManufacturer:cmd.sameAsManufacturer).save(failOnError: true,flush: true)
			params.exportFilterId = sparePartExportTask.id
			params.class = "SparePartExportTask"
			params.targetURI = "/sparePartView/generalExport"
			redirect(controller: "task", action: "create", params: params)
		}
		adaptParamsForList()
		render(view:"/entity/sparePart/sparePartExportPage", model:[
					template:"/entity/sparePart/sparePartExportFilter",
					filterCmd:cmd,
					locationTypes:DataLocationType.list(),
					code: getLabel()
				])
	}*/

	/*def export = { FilterCommand cmd ->
		if (log.isDebugEnabled()) log.debug("spareParts.export, command "+cmd)
		def location = DataLocation.get(params.int('dataLocation.id'))
		adaptParamsForList()

		def spareParts = sparePartService.filterSparePart(user,location,cmd.supplier,cmd.manufacturer,cmd.sparePartType,cmd.purchaser,cmd.sameAsManufacturer,cmd.status,params)
		File file = sparePartService.exporter(location?:user.location,spareParts)

		response.setHeader "Content-disposition", "attachment; filename=${file.name}.csv"
		response.contentType = 'text/csv'
		response.outputStream << file.text
		response.outputStream.flush()
	}*/

	def updateSameAsManufacturer = {
		if (log.isDebugEnabled()) log.debug("updateSameAsManufacturer sparePart.sameAsManufacturer "+params['sparePart.id'])
		SparePart sparePart = SparePart.get(params.int(['sparePart.id']))
		def property = params['field'];
		if (sparePart == null || property ==null)
			response.sendError(404)
		else {
			def value= false; def entity = null;
			if(property.equals("sameAsManufacturer")){
				if(sparePart.sameAsManufacturer) sparePart.sameAsManufacturer = false
				else {
					sparePart.lastModified = user
					sparePart.sameAsManufacturer = true
				}
				entity = sparePart.save(flush:true)

			}
			if(entity!=null) value=true
			render(contentType:"text/json") { results = [value]}
		}
	}

	def getAjaxData = {
		List<SparePart> spareParts = sparePartService.searchSparePart(params['term'],user,null,[:])
		render(contentType:"text/json") {
			elements = array {
				spareParts.each { sparePart ->
					elem (
							key: sparePart.id,
							value: sparePart.code
							)
				}
			}
			htmls = array {
				spareParts.each { sparePart ->
					elem (
							key: sparePart.id,
							html: g.render(template:"/templates/sparePartFormSide",model:[sparePart:sparePart,cssClass:"form-aside-hidden",field:"sparePart"])
							)
				}
			}
		}

	}

}
class FilterCommand {
	DataLocation location
	SparePartType sparePartType
	Provider manufacturer
	Provider supplier
	StatusOfSparePart status = StatusOfSparePart.NONE
	SparePartPurchasedBy purchaser
	String sameAsManufacturer
	 

	public boolean getSameAsManufacturerStatus(){
		if(sameAsManufacturer) return null
		else if(sameAsManufacturer.equals("true")) return true
		else if(sameAsManufacturer.equals("false")) return false
	}

	static constraints = {

		sparePartType nullable:true
		manufacturer nullable:true
		supplier nullable:true
		status nullable:true
		purchaser nullable:true
		sameAsManufacturer nullable:true

		location nullable:false, validator:{val, obj ->
			return (obj.sparePartType != null || obj.manufacturer != null ||obj.supplier != null || (obj.status != null && obj.status != StatusOfSparePart.NONE) || obj.purchaser || obj.sameAsManufacturer)?true:"select.atleast.one.value.text"
		}
	}

	String toString() {
		return "FilterCommand[DataLocation="+location+", SparePartType="+sparePartType+
		", Manufacturer="+manufacturer+", Supplier="+supplier+", StatusOfSparePart="+status+", donated="+purchaser+", sameAsManufacturer="+sameAsManufacturer+
		"]"
	}
}

class ExportFilterCommand {
	Set<CalculationLocation> calculationLocations
	Set<DataLocationType> locationTypes
	Set<SparePartType> sparePartTypes
	Set<Provider> manufacturers
	Set<Provider> suppliers
	StatusOfSparePart sparePartStatus
	SparePartPurchasedBy purchaser
	String sameAsManufacturer
	 

	public boolean getSameAsManufacturerStatus(){
		if(sameAsManufacturer) return null
		else if(sameAsManufacturer.equals("true")) return true
		else if(sameAsManufacturer.equals("false")) return false
	}

	static constraints = {
		calculationLocations nullable:true
		locationTypes nullable:true
		sparePartTypes nullable:true
		manufacturers nullable:true
		suppliers nullable:true
		sparePartStatus nullable:true
		purchaser nullable:true
		sameAsManufacturer nullable:true
	}

	String toString() {
		return "ExportFilterCommand[ CalculationLocations="+calculationLocations+", DataLocationTypes="+locationTypes+" , SparePartTypes="+sparePartTypes+
		", Manufacturers="+manufacturers+", Suppliers="+suppliers+", StatusOfSparePart="+sparePartStatus+", donated="+purchaser+", sameAsManufacturer="+sameAsManufacturer+
		"]"
	}

}
