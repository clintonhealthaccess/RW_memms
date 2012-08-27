package org.chai.memms.equipment

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.chai.memms.location.LocationLevel;
import org.chai.memms.Inventory
import org.chai.memms.location.CalculationLocation;
import org.chai.memms.location.DataLocationType;
import org.chai.memms.location.DataLocation
import org.chai.memms.location.Location

class InventoryService {

    static transactional = true
	def locationService
	def equipmentService
	def grailsApplication
	
	public Set<LocationLevel> getSkipLocationLevels() {
		Set<LocationLevel> levels = new HashSet<LocationLevel>();
		for (String skipLevel : grailsApplication.config.inventory.skip.levels) {
			levels.add(locationService.findLocationLevelByCode(skipLevel));
		}
		return levels;
	}
	
	public List<Inventory> getInventoryByLocation(Location location,Set<DataLocationType> types) {
		List<Inventory> inventories = []
		Set<LocationLevel> skipLevels = getSkipLocationLevels()
		for(DataLocation dataLocation : location.getDataLocations(skipLevels,types)){
			inventories.add(new Inventory(dataLocation:dataLocation,equipmentCount:equipmentService.getEquipmentsByLocation(dataLocation,[:]).size()))
		}
		return inventories
	}
}
