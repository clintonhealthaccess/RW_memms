/**
 * 
 */
package org.chai.memms.spare.part

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.location.LocationLevel;
import org.chai.memms.Parts;
import org.chai.memms.Part;
import org.chai.location.CalculationLocation;

/**
 * @author aphrorwa
 *
 */
class PartService {
	 static transactional = true
	def locationService
	def sparePartService
	def grailsApplication
	
	public Set<LocationLevel> getSkipLocationLevels() {
		List<LocationLevel> levels = []
		for (String skipLevel : grailsApplication.config.location.sector.skip.level) {
			levels.add(locationService.findLocationLevelByCode(skipLevel));
		}
		return levels;
	}
	
	//If params are not in order return the whole list.
	public Parts getPartByLocation(Location location,Set<DataLocationType> types,Map<String, String> params) {
		List<Part> parts = []
		Set<LocationLevel> skipLevels = getSkipLocationLevels()
		for(DataLocation dataLocation : location.collectDataLocations(skipLevels,types)){
			parts.add(new Part(dataLocation:dataLocation,sparePartCount:sparePartService.getSparePartsByDataLocation(location,[:]).size()))
		}
		
		Parts part = new Parts()
		part.partList = parts[(params.offset) ..< ((params.offset + params.max) > parts.size() ? parts.size() : (params.offset + params.max))]

		part.totalCount = parts.size()
		return part
	}

}
