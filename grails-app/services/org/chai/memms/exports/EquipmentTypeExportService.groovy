/**
 * Copyright (c) 2011, Clinton Health Access Initiative.
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
package org.chai.memms.exports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chai.memms.util.Utils;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author Jean Kahigiso M.
 */
public class EquipmentTypeExportService extends ExportService {

	private static final Log log = LogFactory.getLog(EquipmentTypeExportService.class);
	private Set<String> skipLevels;

    @Override
	public File exportData(String language) throws IOException{
		if (log.isDebugEnabled()) log.debug("exportData("+export+")");
		
//		Set<DataLocationType> types = new HashSet<DataLocationType>();
//		List<CalculationLocation> calculationLocations = new ArrayList<CalculationLocation>();
//		List<CalculationLocation> locations = new ArrayList<CalculationLocation>();
//		List<DataLocation> dataLocations = new ArrayList<DataLocation>();
//		Set<LocationLevel> skips = reportService.getSkipReportLevels(skipLevels);		
//
//		for(String code: export.getTypeCodes()){
//			DataLocationType type = locationService.findDataLocationTypeByCode(code);
//			if(type!=null) types.add(type);
//		}
//		
//		for(CalculationLocation location : export.getLocations())
//			location.collectLocations(locations, dataLocations, skips, types);
//		
//		calculationLocations.addAll(Utils.removeDuplicates(locations));
//		calculationLocations.addAll(Utils.removeDuplicates(dataLocations));
//			
//		if (log.isDebugEnabled()) log.debug(" Exporter calculationLocations "+calculationLocations+")");
		return this.exportCalculations(languageService.getText(export.getDescriptions(), language),calculationLocations,export.getPeriods(),((CalculationExport) export).getCalculations(),types,language);
	}
		
	public File exportCalculations(String fileName,List<CalculationLocation> calculationLocations,Set<Period> periods,Set<Calculation<CalculationPartialValue>> calculations,Set<DataLocationType> types, String language) throws IOException{
		if (log.isDebugEnabled()) log.debug(" exportDataElement(String "+fileName+" List<CalculationLocation>: " + calculationLocations + " List<Period>: "+ periods + " Set<Calculation<CalculationPartialValue>>: " + calculations + ")");
		File csvFile = File.createTempFile(fileName, ImportExportConstant.CSV_FILE_EXTENSION);
		FileWriter csvFileWriter = new FileWriter(csvFile);
		ICsvListWriter writer = new CsvListWriter(csvFileWriter, CsvPreference.EXCEL_PREFERENCE);
		this.writeCalculation(writer, calculationLocations, periods, calculations,types, language);
		return csvFile;
	}

	private void writeCalculation(ICsvListWriter writer, List<CalculationLocation> calculationLocations, Set<Period> periods,Set<Calculation<CalculationPartialValue>> calculations,Set<DataLocationType> types, String language) throws IOException {
//		try{
//			String[] csvHeaders = null;
//			// headers
//			if(csvHeaders == null){
//				csvHeaders = this.getExportDataHeaders(language).toArray(new String[getExportDataHeaders(language).size()]);
//				writer.writeHeader(csvHeaders);
//			}
//			for(CalculationLocation location: calculationLocations)
//				for(Period period: periods)
//					for(Calculation<CalculationPartialValue> calculation: calculations){
//						List<List<String>> lines=this.getExportLineForValue(location,period,calculation,types,language);
//						for(List<String> line: lines)
//							writer.write(line);
//					}
//		} catch (IOException ioe){
//			// TODO throw something that make sense
//			throw ioe;
//		} finally {
//			writer.close();
//		}
	}
	
//	public List<List<String>> getExportLineForValue(CalculationLocation location,Period period, Calculation<CalculationPartialValue> calculation,Set<DataLocationType> types, String language){
//		DataPointVisitor dataPointVisitor = new DataPointVisitor();
//		if(calculation!=null){
//			CalculationValue<CalculationPartialValue> calculationPartialValue = valueService.getCalculationValue(calculation, location, period,types);
//			if(calculationPartialValue!=null){
//				List<String> basicInfo = this.getBasicInfo(location,period,calculation, language);
//				dataPointVisitor.setBasicInfo(basicInfo);
//				calculation.getType().visit(calculationPartialValue.getValue(), dataPointVisitor);
//			}
//			
//			sessionFactory.getCurrentSession().evict(calculationPartialValue);
//		}
//		return dataPointVisitor.getLines();
//	}
	
	public List<String> getBasicInfo(CalculationLocation location,Period period, Calculation<CalculationPartialValue> calculation, String language){
		List<String> basicInfo = new ArrayList<String>();
//		basicInfo.add(location.getCode());
//		basicInfo.add(languageService.getText(location.getNames(), language));
//		
//		if(location instanceof Location)
//			basicInfo.add(languageService.getText(((Location) location).getLevel().getNames(), language));
//		else basicInfo.add("");
//		
//		if(location instanceof DataLocation)
//			basicInfo.add(languageService.getText(((DataLocation) location).getType().getNames(), language));
//		else basicInfo.add("");
//		
//		basicInfo.add(period.getCode()+"");
//		basicInfo.add("[ "+period.getStartDate().toString()+" - "+period.getEndDate().toString()+" ]");
//		basicInfo.add(calculation.getClass().getSimpleName());
//		basicInfo.add(calculation.getCode()+"");
		return basicInfo;
	}
	
	@Override
	public List<String> getExportDataHeaders(String language) {
		List<String> headers = new ArrayList<String>();
//		headers.add(ImportExportConstant.DATA_LOCATION_CODE);
//		headers.add(ImportExportConstant.DATA_LOCATION_NAME);
//		headers.add(ImportExportConstant.LOCATION_LEVEL);
//		headers.add(ImportExportConstant.LOCATION_TYPE);
//		headers.add(ImportExportConstant.PERIOD_CODE);
//		headers.add(ImportExportConstant.PERIOD);
//		headers.add(ImportExportConstant.DATA_CLASS);
//		headers.add(ImportExportConstant.DATA_CODE);
//		headers.add(ImportExportConstant.DATA_VALUE);
		return headers;
	}
}
