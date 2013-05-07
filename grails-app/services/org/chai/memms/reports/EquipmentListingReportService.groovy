/**
 * 
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.security.User;
import org.chai.memms.inventory.Equipment;

/**
 * @author aphrorwa
 *
 */
class EquipmentListingReportService {
	
	def userService
	def equipmentService
	
	public def getReportOfEquipmentsByDataLocationAndManages(DataLocation dataLocation,Map<String, String> params) {
		equipmentService.getEquipmentsByDataLocationAndManages(dataLocation, params)
	}
	
	public def getReportOfMyEquipments(User user,Map<String, String> params) {
		equipmentService.getMyEquipments(user, params)
	}
	
	//Reporting method for obsolete and disposed
	public def searchEquipmentByCriteria(String text,User user,DataLocation currentDataLocation,Map<String, String> params) {
		def dataLocations = []
		text = text.trim()
		if(currentDataLocation) dataLocations.add(currentDataLocation)
		else if(user.location instanceof Location) dataLocations.addAll(user.location.getDataLocations([].toSet(), [].toSet()))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
			if(userService.canViewManagedEquipments(user)) dataLocations.addAll(((DataLocation)user.location).manages)
		}
		
		def criteria = Equipment.createCriteria();
		
		return  criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
				if(dataLocations)
					inList('dataLocation',dataLocations)
				or{
					ilike("obsolete","%"+text+"%")
					ilike("currentStatus","%"+text+"%")
				}
		}
	}

}
