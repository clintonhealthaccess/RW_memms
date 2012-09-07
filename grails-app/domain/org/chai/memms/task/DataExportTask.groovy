package org.chai.memms.task;

import java.util.Map;
import org.chai.memms.task.Task.TaskStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.chai.memms.exports.EquipmentTypeExportService;

class DataExportTask extends Task {

	Long exportId
	
	def languageService
	def equipmentTypeExportService

	private def getExport() {
		def export = DataElementExport.get(exportId)
		if (export == null) export = CalculationExport.get(exportId)
		return export
	}
		
	def executeTask() {
		def csvFile = null
		Task.withTransaction {
			def export = getExport()
			if (export != null) {
				def language = user.defaultLanguage != null ? user.defaultLanguage : languageService.fallbackLanguage
				
				if (export instanceof DataElementExport) csvFile = dataElementExportService.exportData(export, language) 
				else if (export instanceof CalculationExport) csvFile = calculationExportService.exportData(export, language)
				else {} // TODO exception
			}
		}
		if (csvFile != null) {
			File outputFile = new File(getFolder(), getOutputFilename())
			FileUtils.moveFile(csvFile, outputFile);
		}
	}
	
	boolean isUnique() {
		def task = DataExportTask.findByExportId(exportId)
		return task == null || task.status == TaskStatus.COMPLETED || task.status == TaskStatus.ABORTED
	}
	
	String getOutputFilename() {
		return 'exportOutput.csv'
	}
	
	String getInformation() {
		return languageService.getText(getExport().descriptions)
	}

	String getFormView() {
		return null
	}
	
	Map getFormModel() {
		return null
	}

}
