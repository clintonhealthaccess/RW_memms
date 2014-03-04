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
package org.chai.memms.reports.hmis

import java.util.List;
import java.util.Map;
import org.chai.location.Location;
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;
import org.chai.memms.util.Utils;
import org.chai.memms.inventory.EquipmentService;
import org.chai.location.DataLocation
/**
 * @author Eric Dusabe, Jean Kahigiso M.
 *
 */
class HmisReportService {

	static transactional = true
	def languageService;
	def locationService
	def grailsApplication
	def equipmentService

	public def generateHmisReport() {
		def hmisReport = newHmisReport("Q1-2014", ["en":"First Quarter", "fr":"Premier Trimestre"])
		def dataLocations = equipmentService.getAllDataLocationsWithEquipment()
		def hmisEquipmentTypes = HmisEquipmentType.list()

		if(hmisReport) {
			for(dataLocation:dataLocations) {
				for(hmisEquipmentType:hmisEquipmentTypes) {
					def types = hmisEquipmentType.equipmentTypes
					def criteria = Equipment.createCriteria()
					def numberOfOpEquipment = criteria.get{
						projections {
							rowCount
						}
						eq("dataLocation",dataLocation)
						'in'("type", types)
						'in'("currentStatus", [Status.OPERATIONAL,Status.INSTOCK])
					}

				}
				newHmisFacilityReport(hmisReport, dataLocation, hmisEquipmentType, numberOfOpEquipment)
			}
		}
	}


	private def getQuartPeriod(){
		def toDay = new Date();
	}
	
	private def newHmisReport(def code, def names) {
		def hmisReport = new HmisReport(code:code, names:names)
		return hmisReport.save(failOnError:true)
	}

	private def newHmisFacilityReport(def hmisReport, def dataLocation, def hmisEquipmentType, def numberOfOpEquipment) {
		def newHmisFacilityReport = new HmisFacilitReport(dataLocation:dataLocation, hmisEquipmentType:hmisEquipmentType, numberOfOpEquipment:numberOfOpEquipment)
		hmisReport.addToHmisFacilityReports(newHmisFacilityReport)
		return hmisReport.save(failOnError:true,flush:true)
	}

}
