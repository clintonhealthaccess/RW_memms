/** 
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
		def sparePart = SparePart.get(params.long("id"))
		def html = g.render(template:"/templates/sparePartClueTip",model:[sparePart:sparePart])
		render(contentType:"text/plain", text:html)
	}
	
	def list = {
		adaptParamsForList()
		def type = SparePartType.get(params.long('type.id'))
		def spareParts = sparePartService.getSpareParts(user,type,params)
		if(request.xhr)
			this.ajaxModel(spareParts,type,"")
		else{
			render(view:"/entity/list",model: model(spareParts,type) << [
				template:"sparePart/sparePartList",
				listTop:"sparePart/listTop",
				filterTemplate:"sparePart/sparePartFilter",
				entities: spareParts,
				code: getLabel()
			])
		}
	}


	def search = {
		adaptParamsForList()
		def type = SparePartType.get(params.long('type.id'))	
		def spareParts = sparePartService.searchSparePart(params['q'],user,type,params)
		if(request.xhr)
			this.ajaxModel(spareParts,type,params['q'])
		else {
			render(view:"/entity/list", model: model(spareParts,type) << [
				template:"sparePart/sparePartList",
				filterTemplate:"sparePart/sparePartFilter",
				listTop:"sparePart/listTop"
			])
		}
	}
	
	def ajaxModel(def entities,def type,def searchTerm) {
		def model = model(entities,type) << [q:searchTerm]
		def listHtml = g.render(template:"/entity/sparePart/sparePartList",model:model)
		render(contentType:"text/json") { results = [listHtml] }
	}
	
	def model(def entities,def type) {
		return [
			entities: entities,
			entityCount: entities.totalCount,
			entityClass:getEntityClass(),
			code: getLabel(),
			type:type
			
		]
	}


	def filter = { FilterCommand cmd ->
		if (log.isDebugEnabled()) log.debug("spareParts.filter, command "+cmd)
		adaptParamsForList()
		def spareParts = sparePartService.filterSparePart(user.location,cmd.supplier,cmd.sparePartType,cmd.sparePartPurchasedBy,cmd.status,params)
		if(!request.xhr)
			response.sendError(404)
		else this.ajaxModel(spareParts,null,"")
	}
	
	
	
	def generalExport = { ExportFilterCommand cmd ->

		Set<CalculationLocation> calculationLocations = new HashSet<CalculationLocation>()
			params.list('calculationLocationids').each { id ->
				if (NumberUtils.isDigits(id)) {
					def calculationLocation = CalculationLocation.get(id)
					if (calculationLocation != null && !calculationLocations.contains(calculationLocation)) calculationLocations.add(calculationLocation);
				}
			}
			cmd.calculationLocations = calculationLocations
				
			Set<SparePartType> sparePartTypes = new HashSet<SparePartType>()
			params.list('sparePartTypeids').each { id ->
				if (NumberUtils.isDigits(id)) {
					def sparePartType = SparePartType.get(id)
					if (sparePartType != null && !sparePartTypes.contains(sparePartType)) sparePartTypes.add(sparePartType);
				}
			}
			cmd.sparePartTypes = sparePartTypes
		
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
				def sparePartExportTask = new SparePartExportFilter(
						calculationLocations:cmd.calculationLocations,
						sparePartTypes:cmd.sparePartTypes,
						suppliers:cmd.suppliers,
						sparePartPurchasedBy:cmd.sparePartPurchasedBy
						).save(failOnError: true,flush: true)

				params.exportFilterId = sparePartExportTask.id
				params.class = "SparePartExportTask"
				params.targetURI = "/sparePartView/generalExport"
				redirect(controller: "task", action: "create", params: params)
			}

			adaptParamsForList()
			render(view:"/entity/sparePart/sparePartExportPage", model:[
					template:"/entity/sparePart/sparePartExportFilter",
					filterCmd:cmd,
					dataLocationTypes:DataLocationType.list(),
					code: getLabel()
			])
	}
	
	def export = { FilterCommand cmd ->
		if (log.isDebugEnabled()) log.debug("spareParts.export, command "+cmd)
		
		adaptParamsForList()

		def spareParts = sparePartService.filterSparePart(location,cmd.supplier,cmd.sparePartType,cmd.sparePartPurchasedBy,params)
		File file = sparePartService.exporter(user.location,spareParts)

		response.setHeader "Content-disposition", "attachment; filename=${file.name}.csv"
		response.contentType = 'text/csv'
		response.outputStream << file.text
		response.outputStream.flush()
	}
}

class FilterCommand {
	DataLocation location
	SparePartType sparePartType
	SparePartStatus status
	Provider supplier
	SparePartPurchasedBy sparePartPurchasedBy
	
	static constraints = {

		sparePartType nullable:true
		supplier nullable:true
		sparePartPurchasedBy nullable:true
		status nullable: true

		location nullable:false, validator:{ val, obj ->
			return (obj.sparePartType != null || obj.supplier != null || obj.sparePartPurchasedBy!=null || obj.status!=null)?true:"select.atleast.one.value.text"
		}
	}

	String toString() {
		return "FilterCommand[DataLocation="+location+", SparePartType="+sparePartType+", Supplier="+supplier+",  sparePartPurchasedBy="+sparePartPurchasedBy+" status="+status+"]"
	}
}

class ExportFilterCommand {
	Set<CalculationLocation> calculationLocations
	Set<DataLocationType> locationTypes
	Set<SparePartType> sparePartTypes
	Set<Provider> suppliers
	SparePartPurchasedBy sparePartPurchasedBy
	 

	static constraints = {
		calculationLocations nullable:true
		locationTypes nullable:true
		sparePartTypes nullable:true
		suppliers nullable:true
		sparePartPurchasedBy nullable:true
	}

	String toString() {
		return "ExportFilterCommand[ CalculationLocations="+calculationLocations+", DataLocationTypes="+locationTypes+" , SparePartTypes="+sparePartTypes+", Suppliers="+suppliers+", sparePartPurchasedBy="+sparePartPurchasedBy+"]"
	}

}
