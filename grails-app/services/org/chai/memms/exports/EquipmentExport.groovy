/**
 * 
 */
package org.chai.memms.exports

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chai.memms.equipment.Equipment;
import org.chai.memms.equipment.EquipmentType
import org.chai.memms.util.ImportExportConstant;
import org.chai.memms.util.Utils;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author JeanKahigiso
 *
 */
class EquipmentExport extends Exporter{
	
	public File exportData() throws IOException {
		if (log.isDebugEnabled()) log.debug("exportData");
		File csvFile = File.createTempFile(getBasicInfo()+"_temp", ImportExportConstant.CSV_FILE_EXTENSION);
		FileWriter csvFileWriter = new FileWriter(csvFile);
		ICsvListWriter writer = new CsvListWriter(csvFileWriter, CsvPreference.EXCEL_PREFERENCE);
		this.writeFile(writer);
		return csvFile;
	}
	
	private void writeFile(ICsvListWriter writer,List<Equipment> equipments) throws IOException {
		try{
			String[] csvHeaders = null;
			// headers
			if(csvHeaders == null){
				csvHeaders = this.getExportDataHeaders()
				writer.writeHeader(csvHeaders);
			}
			for(Equipment equipment: equipments){
				if (log.isDebugEnabled()) log.debug(" Current Equipment to Export:"+equipment)
				List<String> line = [
					equipment.dataLocation.names_en,
					equipment.department.code,
					equipment.department.names_en,
					equipment.serialNumber,
					equipment.purchaseDate,
					equipment.type.code,
					equipment.type.names_en,
					equipment.status.status,
					equipment.status.dateOfEvent
					]
				writer.write(line)
			}
		} catch (IOException ioe){
			// TODO throw something that make sense
			throw ioe;
		} finally {
			writer.close();
		}
	}
	
	
	public List<String> getBasicInfo(){
		List<String> basicInfo = new ArrayList<String>();
		basicInfo.add("equipment.export")
		return basicInfo;
	}
	

	public List<String> getExportDataHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("dataLocation");
		headers.add("departmentCode");
		headers.add("departmentNames");
		headers.add("serialNumber");
		headers.add("purchaseDate");
		headers.add("typeCode");
		headers.add("typeNames");
		headers.add("currentStatus");
		
		return headers;
	}

	

}
