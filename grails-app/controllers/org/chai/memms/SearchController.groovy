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
package org.chai.memms

import java.util.Set;
import org.apache.commons.lang.math.NumberUtils;
import org.chai.memms.AbstractController;
import org.chai.location.CalculationLocation;
import org.chai.memms.spare.part.SparePart.StockLocation;



/**
 * @author Jean Kahigiso M.
 *
 */
class SearchController extends AbstractController {
	
	def sparePartService
	def equipmentService

	def findEquipments = {
		Set<CalculationLocation> locations = new HashSet<CalculationLocation>();
		params.list('locationIds').each { id ->
			if (NumberUtils.isDigits(id)) {
				def location = CalculationLocation.get(id)
				if(location) locations.add(location);
			}
		}
		adaptParamsForList()
		if(request.xhr){
			def equipments = equipmentService.findEquipments(params["term"],locations as List,params);
			def listHtml = g.render(template:"/templates/foundEquipments",model:[entities:equipments])
			render(contentType:"text/json") { results = [listHtml] }
		}else {
			render(view:"/entity/searchList",model:[template:"/templates/searchEquipmentForm",entityName:'equipment',cmd:null])
		}
	}

	def findSpareParts = {
		def stockLocation = params["stockLocation"]
		Set<CalculationLocation> locations = new HashSet<CalculationLocation>();
		params.list('locationIds').each { id ->
			if (NumberUtils.isDigits(id)) {
				def location = CalculationLocation.get(id)
				if(location) locations.add(location);
			}
		}
		if(stockLocation) stockLocation = StockLocation."$stockLocation"
		adaptParamsForList()
		if(request.xhr){
			def spareParts = sparePartService.findSpareParts(params["term"],locations as List,stockLocation,params);
			def listHtml = g.render(template:"/templates/foundSpareParts",model:[entities:spareParts])
			render(contentType:"text/json") { results = [listHtml] }
		}else {
			render(view:"/entity/searchList",model:[template:"/templates/searchSparePartForm",entityName:'spare.part',cmd:null])
		}

	}

}

