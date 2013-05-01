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

		def step1Params = [:]
    	step1Params.putAll params
    	step1Params.remove 'reportType'
    	step1Params.remove 'reportSubType'
    	//TODO remove everything? or keep some "static params" ex. dataLocations ???

		render(template:"/entity/reports/step1", 
			model: 
			[
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations,
				step1Params: step1Params
				// TODO linkParams
			])
	}

	def step2 ={
		if (log.isDebugEnabled()) log.debug("reports.step2, params:"+params)

		def reportType = getReportType('reportType')
		def reportSubType = getReportType('reportSubType')

		//report type inventory, report subtype list of inventory

		//inventory - facilities, TODO departments, equipment type, cost
		def dataLocations = getDataLocations()
			//inventory - TODO equipment status, period of acquisition, obsolete, warranty
			//status changes - TODO status changes, period of status changes

		//TODO corrective
		//TODO preventive
		//TODO spare parts

	    def step2Params = [:]
	    step2Params.putAll params
	    step2Params.remove 'dataLocations'

		render(template:"/entity/reports/step2", 
			model: 
			[
				reportType: reportType,
				reportSubType: reportSubType,
				dataLocations: dataLocations,
				step2Params: step2Params
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

	def customizedListing ={
		if (log.isDebugEnabled()) log.debug("reports.customizedListing, params:"+params)

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
}
