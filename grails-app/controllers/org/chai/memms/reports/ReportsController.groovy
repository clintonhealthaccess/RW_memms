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
package org.chai.memms.reports

import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location;
import org.chai.memms.AbstractController;
import org.chai.memms.util.Utils;

/**
 * @author Jean Kahigiso M.
 *
 */
class ReportsController extends AbstractController{

	def locationService

	def dashboard ={
		if (log.isDebugEnabled()) log.debug("reports.dashboard, params:"+params)

		//TODO Pivot Access

		render(view: '/entity/reports', model: 
			[
				template:"/entity/reports/dashboard"
			])
	}	

	def listing ={
		if (log.isDebugEnabled()) log.debug("reports.listing, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')
		def dataLocations = getDataLocations()

		//TODO Rwagaju

		render(view: '/entity/reports', model: 
			[
				template:"/entity/reports/listing",
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations
			])
	}

	def step1 ={
		if (log.isDebugEnabled()) log.debug("reports.step1, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')
		def dataLocations = getDataLocations()

		render(template:"/entity/reports/step1", 
			model: 
			[
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations
				// TODO linkParams
			])
	}

	def step2 ={
		if (log.isDebugEnabled()) log.debug("reports.step2, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')

		//report type inventory, report subtype list of inventory

		//facilities (list)
		def dataLocations = getDataLocations()
		def dataLocationTree = locationService.getRootLocation().collectDataLocations(DataLocationType.list())

		//departments (list)

		//equipment type (list)

		//equipment status (list)

		//obsolete equipment (boolean)

		//period of acquisition (2 to/from dates)

		//cost (2 to/from numbers, 1 currency string)

		render(template:"/entity/reports/step2", 
			model: 
			[
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations,
				dataLocationTree: dataLocationTree
				// TODO linkParams
			])
	}

	def step3 ={
		if (log.isDebugEnabled()) log.debug("reports.step3, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')

		def dataLocations = getDataLocations()

		render(template:"/entity/reports/step3", 
			model: 
			[
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations
				// TODO linkParams
			])
	}

	def customizedReportSubType ={
		if (log.isDebugEnabled()) 
			log.debug("reports.step1.customizedReportSubType, params:"+params)

		def reportType = getReportType('reportType')

		render(template:"/entity/reports/customizedReportSubType", 
			model: 
			[
				reportType: reportType
			])
	}

	def getAjaxData = {		
		List<DataLocation> dataLocations = locationService.searchLocation(DataLocation.class, params['term'], [:])
		render(contentType:"text/json") {
			elements = array {
				dataLocations.each { dataLocation ->
					elem (
						key: dataLocation.id,
						value: dataLocation.names + ' ['+dataLocation.location.names+']'
					)
				}
			}
			htmls = array {
				dataLocations.each { dataLocation ->
					elem (
							key: dataLocation.id,
							html: g.render(template:"/templates/dataLocationFormSide",model:[dataLocation:dataLocation,label:label,cssClass:"form-aside-hidden",field:'dataLocation'])
							)
				}
			}
		}
	}
}
