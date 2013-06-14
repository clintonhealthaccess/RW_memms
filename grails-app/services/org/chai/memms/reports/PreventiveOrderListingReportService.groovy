/**
 * 
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation
import org.chai.location.Location
import org.chai.memms.preventive.maintenance.PreventiveOrder;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventionResponsible;
import org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderStatus;
import org.chai.memms.security.User;
import org.chai.memms.util.Utils;

/**
 * @author Aphrodice Rwagaju
 *
 */
class PreventiveOrderListingReportService {
	
	def getAllPreventions(User user , Map<String, String> params){
		
		def criteria = PreventiveOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
		}
	}
	def getEquipmentsWithPreventionPlan(User user,Map<String, String> params) {

		def criteria = PreventiveOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
			eq ("status",PreventiveOrderStatus.OPEN)
		}
	}
	//TODO To complete the function of delayed prevention by adding condition of delay
	def getPreventionsDelayed(User user,Map<String, String> params) {
		def criteria = PreventiveOrder.createCriteria();
		def dataLocations = []
		if(user.location instanceof Location) dataLocations.addAll(user.location.collectDataLocations(null))
		else{
			dataLocations = []
			dataLocations.add(user.location as DataLocation)
		}
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			if(dataLocations)
				inList('equip.dataLocation',dataLocations)
			or{
				eq ("status",PreventiveOrderStatus.OPEN)
				//eq ("status",PreventiveOrderStatus.OPEN)
			}
		}
	}

	def getCustomReportOfPreventiveOrders(User user,def customPreventiveOrderParams,Map<String, String> params) {
		
		def dataLocations = customPreventiveOrderParams.get('dataLocations')
		def departments = customPreventiveOrderParams.get('departments')
		def equipmentTypes = customPreventiveOrderParams.get('equipmentTypes')
		def lowerLimitCost = customPreventiveOrderParams.('fromCost')
		def upperLimitCost = customPreventiveOrderParams.('toCost')
		def currency = customPreventiveOrderParams.get('costCurrency')
		def workOrderStatus = customPreventiveOrderParams.get('workOrderStatus')
		def responsibles = customPreventiveOrderParams.get('whoIsResponsible')
		
		def criteria = PreventiveOrder.createCriteria();
	
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			createAlias("equipment","equip")
			//Mandatory property
				inList('equip.dataLocation',dataLocations)
			if(departments != null && departments.size()>0)
				inList ("equip.department", departments)
			//Mandatory property
				inList ("equip.type", equipmentTypes)
			if(workOrderStatus!=null && !workOrderStatus.empty)
				inList ("status",workOrderStatus)
			if(lowerLimitCost && lowerLimitCost!=null)
			//Including lowerLimitCost by using ge
				ge ("equip.purchaseCost", lowerLimitCost)
			if(upperLimitCost && upperLimitCost!=null)
			//Including upperLimitCost by using le
				le ("equip.purchaseCost", upperLimitCost)
			if(currency && currency !=null)
				eq ("equip.currency",currency)	
			if(responsibles!=null && !responsibles.empty)
				inList ("preventionResponsible",responsibles)
		}
	}
	public def savePreventiveOrderReportParams(User user, def preventiveMaintenanceReport,def customPreventiveOrderParams, Map<String, String> params){
		def reportName = customPreventiveOrderParams.get('customizedReportName')
		def reportType = customPreventiveOrderParams.get('reportType')
		def reportSubType = customPreventiveOrderParams.get('reportSubType')

		def dataLocations = customPreventiveOrderParams.get('dataLocations')
		def departments = customPreventiveOrderParams.get('departments')
		def equipmentTypes = customPreventiveOrderParams.get('equipmentTypes')
		def lowerLimitCost = customPreventiveOrderParams.get('fromCost')
		def upperLimitCost = customPreventiveOrderParams.get('toCost')
		def currency = customPreventiveOrderParams.get('costCurrency')
		
		def fromWorkOrderPeriod = customPreventiveOrderParams.get('fromWorkOrderPeriod')
		def toWorkOrderPeriod = customPreventiveOrderParams.get('toWorkOrderPeriod')
		def workOrderStatus = customPreventiveOrderParams.get('workOrderStatus')
		def warranty = customPreventiveOrderParams.get('warranty')
		
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON PREVENTIVE MAINTENANCE CUSTOM REPORT: LOWER COST :"+lowerLimitCost+" UPPER COST :"+upperLimitCost)
		
		preventiveMaintenanceReport.preventiveOrderStatus=workOrderStatus
		preventiveMaintenanceReport.toDate=toWorkOrderPeriod
		preventiveMaintenanceReport.fromDate=fromWorkOrderPeriod
		preventiveMaintenanceReport.currency=currency
		preventiveMaintenanceReport.upperLimitCost=upperLimitCost
		preventiveMaintenanceReport.lowerLimitCost=lowerLimitCost
		preventiveMaintenanceReport.equipmentTypes=equipmentTypes
		preventiveMaintenanceReport.departments=departments
		preventiveMaintenanceReport.dataLocations=dataLocations
		preventiveMaintenanceReport.reportSubType=reportSubType
		preventiveMaintenanceReport.reportType=reportType
		preventiveMaintenanceReport.reportName=reportName
		
		preventiveMaintenanceReport.save(failOnError:true)
		if (log.isDebugEnabled()) log.debug("PARAMS TO BE SAVED ON PREVENTIVE MAINTENANCE CUSTOM REPORT SAVED CORRECTLY. THE REPORT ID IS :"+ preventiveMaintenanceReport.id)
	}
}
