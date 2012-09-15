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
package org.chai.memms.exports

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.equipment.EquipmentStatus.Status;
import org.chai.memms.equipment.EquipmentType
import org.chai.memms.task.DataExportTask;
import org.chai.memms.task.EquipmentExportFilter;
import org.chai.memms.task.Progress;
import org.chai.memms.task.Task;
import org.chai.memms.util.ImportExportConstant;
import org.chai.memms.util.Utils;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;
import org.chai.memms.task.Exporter

class EquipmentExport implements Exporter{
	def equipmentService
	public File exportData(DataExportTask task) throws IOException{
		if (log.isDebugEnabled()) log.debug("exporting data");
		
		File csvFile = File.createTempFile("equipment.export.temp", ImportExportConstant.CSV_FILE_EXTENSION);
		FileWriter csvFileWriter = new FileWriter(csvFile);
		ICsvListWriter writer = new CsvListWriter(csvFileWriter, CsvPreference.EXCEL_PREFERENCE);
		this.writeFile(writer,task,task.exportFilterId);
		return csvFile;
		
	}
		
	private void writeFile(ICsvListWriter writer, Progress progress, Long equipmentExportFilterId) throws IOException {
		try{
			String[] csvHeaders = null;
			// headers
			if(csvHeaders == null){
				csvHeaders = this.getExportDataHeaders()
				writer.writeHeader(csvHeaders);
			}
			def equipmentExportFilter = EquipmentExportFilter.get(equipmentExportFilterId)
			List<Equipment> equipments = []
			if(equipmentExportFilter != null){
				equipments = filterEquipment(equipmentExportFilter)
			}
			//Task.get(progress.id)?.setMaximum(equipments.size())
			progress.setMaximum(equipments.size())
			
			for(Equipment equipment: equipments){
				if (log.isDebugEnabled()) log.debug("exporting equipment=" + equipment)
//				if (log.isDebugEnabled()) log.debug(" exportEquipment values code:"+equipmentType.code+", names:([en: "+equipmentType.getNames(new Locale("en"))+"], [fr: "+equipmentType.getNames(new Locale("fr"))+"]), "
//					+", descriptions:([en: "+equipmentType.getDescriptions(new Locale("en"))+"], [fr: "+equipmentType.getDescriptions(new Locale("fr"))+"]), Observation: "+equipmentType.observation);
//				List<String> line = [equipmentType.code,equipmentType.getNames(new Locale("en")),equipmentType.getNames(new Locale("fr")),
//					equipmentType.getDescriptions(new Locale("en")),equipmentType.getDescriptions(new Locale("fr")),equipmentType.observation]
//				writer.write(line)
				progress.incrementProgress()
			}
		} catch (IOException ioe){
			// TODO throw something that make sense
			throw ioe;
		} finally {
			writer.close();
		}
	}
	
	//dataLocationTypes:DataLocationType,
	
	public List<Equipment> filterEquipment(EquipmentExportFilter equipmentExportFilter){
		
		def criteria = Equipment.createCriteria();
		return criteria.list(sort:"id",order:"desc"){
			if(equipmentExportFilter.calculationLocations != null && equipmentExportFilter.calculationLocations.size() > 0)
				('dataLocation' in equipmentExportFilter.calculationLocations)
			if(equipmentExportFilter.suppliers != null && equipmentExportFilter.suppliers.size() > 0)
				("supplier" in equipmentExportFilter.suppliers)
			if(equipmentExportFilter.manufacturers != null && equipmentExportFilter.manufacturers.size() > 0)
				("manufacturer" in equipmentExportFilter.manufacturers)
			if(equipmentExportFilter.equipmentTypes != null && equipmentExportFilter.equipmentTypes.size() > 0)
				("type" in equipmentExportFilter.equipmentTypes)
			if(equipmentExportFilter.donated)
				eq ("donation", (equipmentExportFilter.donated.equals('true'))?true:false)
			if(equipmentExportFilter.obsolete)
				eq ("obsolete", (equipmentExportFilter.obsolete.equals('true'))?true:false)
			if(!equipmentExportFilter.equipmentStatus.equals(Status.NONE)){
				createAlias("status","t")
				and{
					eq ("t.status", equipmentExportFilter.equipmentStatus)
					eq ("t.current", true)
				}
			}
				
		}
	}
	
	public List<String> getBasicInfo(){
		List<String> basicInfo = new ArrayList<String>();
		basicInfo.add("equipment.export")
		return basicInfo;
	}
	

	public List<String> getExportDataHeaders() {
		List<String> headers = new ArrayList<String>()
		headers.add(ImportExportConstant.EQUIPMENT_SERIAL_NUMBER)
		headers.add(ImportExportConstant.EQUIPMENT_TYPE)
		headers.add(ImportExportConstant.EQUIPMENT_MODEL)
		headers.add(ImportExportConstant.EQUIPMENT_MANUFACTURER)
		headers.add(ImportExportConstant.EQUIPMENT_SUPPLIER)
		headers.add(ImportExportConstant.EQUIPMENT_LOCATION)
		headers.add(ImportExportConstant.EQUIPMENT_STATUS)
		headers.add(ImportExportConstant.EQUIPMENT_DONATION)
		headers.add(ImportExportConstant.EQUIPMENT_OBSOLETE)
		return headers;
	}
}
