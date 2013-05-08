/**
 * 
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.security.User;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus.Status;

/**
 * @author aphrorwa
 *
 */
class EquipmentListingReportService {
	
	def userService
	def equipmentService
	
	public def getReportOfEquipmentsByDataLocationAndManages(def user, def dataLocation,def obsolete,def status,Map<String, String> params) {
	//TODO TO BE USED AFTER TESTING THIS
		//public def getReportOfEquipmentsByDataLocationAndManages(DataLocation dataLocation,Map<String, String> params) {
		//equipmentService.getEquipmentsByDataLocationAndManages(dataLocation, params)
		def dataLocations = []
		if(dataLocation) dataLocations.add(dataLocation)
		else if(user.location instanceof Location) dataLocations.addAll(user.location.getDataLocations([:], [:]))
		else{
			dataLocations.add((DataLocation)user.location)
			dataLocations.addAll((user.location as DataLocation).manages)
		}
		
		def criteria = Equipment.createCriteria();
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations != null)
				inList('dataLocation',dataLocations)
			
			if(obsolete)
				eq ("obsolete", (obsolete.equals('true'))?true:false)
			if(status && !status.equals(Status.NONE))
				eq ("currentStatus",status)
		}
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
	public def filterEquipment(def user, def dataLocation,
		def obsolete,def status,Map<String, String> params){
		
		def dataLocations = []
		if(dataLocation) dataLocations.add(dataLocation)
		else if(user.location instanceof Location) dataLocations.addAll(user.location.getDataLocations([:], [:]))
		else{
			dataLocations.add((DataLocation)user.location)
			dataLocations.addAll((user.location as DataLocation).manages)
		}
		
		def criteria = Equipment.createCriteria();
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			if(dataLocations != null)
				inList('dataLocation',dataLocations)
			
			if(obsolete)
				eq ("obsolete", (obsolete.equals('true'))?true:false)
			if(status && !status.equals(Status.NONE))
				eq ("currentStatus",status)
		}
	}

}
