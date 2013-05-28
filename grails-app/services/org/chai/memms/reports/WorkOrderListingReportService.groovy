/**
 * 
 */
package org.chai.memms.reports

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.Location;
import org.chai.memms.corrective.maintenance.WorkOrderStatus;
import org.chai.memms.corrective.maintenance.WorkOrder;
import org.chai.memms.security.User;
import org.chai.memms.util.Utils;
import org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus;
import org.chai.memms.util.Utils.ReportType
import org.chai.memms.util.Utils.ReportSubType

/**
 * @author Aphrodice Rwagaju
 *
 */
class WorkOrderListingReportService {
	
	def getAllWorkOrders(User user , Map<String, String> params){
		
		def criteria = WorkOrder.createCriteria();
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
	def getWorkOrdersEscalatedToMMC(User user,Map<String, String> params) {

		def criteria = WorkOrder.createCriteria();
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
			eq ("currentStatus",OrderStatus.OPENATMMC)
		}
	}
	def getWorkOrdersOfLastMonth(User user,Map<String, String> params) {

		def lastMonth= (new Date())-30
		def criteria = WorkOrder.createCriteria();
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
				eq ("currentStatus",OrderStatus.OPENATMMC)
				eq ("currentStatus",OrderStatus.OPENATFOSA)
			}
			ge ("openOn",lastMonth)
		}
	}

	def getCustomReportOfWorkOrders(User user, def customWorkOrderParams, Map<String, String> params) {

		def customWorkOrders = []

		def reportType = customWorkOrderParams.get('reportType')
		def reportSubType = customWorkOrderParams.get('reportSubType')

		def dataLocations = customWorkOrderParams.get('dataLocations')
		def departments = customWorkOrderParams.get('departments')
		def equipmentTypes = customWorkOrderParams.get('equipmentTypes')
		def lowerLimitCost = customWorkOrderParams.('fromCost')
		def upperLimitCost = customWorkOrderParams.('toCost')
		def currency = customWorkOrderParams.get('costCurrency')
		
		def criteria = WorkOrder.createCriteria();

		def criteriaWorkOrders = []

		if(reportSubType == ReportSubType.WORKORDERS){
			def workOrderStatus = customWorkOrderParams.get('workOrderStatus')
			def fromWorkOrderPeriod = customWorkOrderParams.get('fromWorkOrderPeriod')
			def toWorkOrderPeriod = customWorkOrderParams.get('toWorkOrderPeriod')

			criteriaWorkOrders = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

				createAlias("equipment","equip")

				if(dataLocations!=null && dataLocations.size()>0)
					inList('equip.dataLocation',dataLocations)
				if(departments != null && departments.size()>0)
					inList ("equip.department", departments)
				if(equipmentTypes != null && equipmentTypes.size()>0)
					inList ("equip.type", equipmentTypes)

				if(lowerLimitCost!=null)
					gt ("equip.purchaseCost", lowerLimitCost)
				if(upperLimitCost!=null)
					lt ("equip.purchaseCost", upperLimitCost)
				if(currency !=null)
					eq ("equip.currency",currency)

				if(workOrderStatus!=null && !workOrderStatus.empty)
					inList ("currentStatus",workOrderStatus)
				// TODO
				// if(fromWorkOrderPeriod != null)
				// 	gt ("TODO", fromWorkOrderPeriod)
				// if(toWorkOrderPeriod != null)
				// 	lt ("TODO", toWorkOrderPeriod)
			}
			customWorkOrders = criteriaWorkOrders
		}

		if(reportSubType == ReportSubType.STATUSCHANGES){
			def statusChanges = customWorkOrderParams.get('statusChanges')
			def fromStatusChangesPeriod = customWorkOrderParams.get('fromStatusChangesPeriod')
			def toStatusChangesPeriod = customWorkOrderParams.get('toStatusChangesPeriod')

			criteriaWorkOrders = criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

				createAlias("equipment","equip")

				if(dataLocations!=null && dataLocations.size()>0)
					inList('equip.dataLocation',dataLocations)
				if(departments != null && departments.size()>0)
					inList ("equip.department", departments)
				if(equipmentTypes != null && equipmentTypes.size()>0)
					inList ("equip.type", equipmentTypes)

				if(lowerLimitCost!=null)
					gt ("equip.purchaseCost", lowerLimitCost)
				if(upperLimitCost!=null)
					lt ("equip.purchaseCost", upperLimitCost)
				if(currency !=null)
					eq ("equip.currency",currency)

				// TODO
				// if(fromStatusChangesPeriod != null)
				// 	gt ("TODO", fromStatusChangesPeriod)
				// if(toStatusChangesPeriod != null)
				// 	lt ("TODO", toStatusChangesPeriod)
			}
			if (log.isDebugEnabled()) log.debug("WORK ORDERS SIZE: "+ criteriaWorkOrders.size())

			if(statusChanges != null && !statusChanges.empty){
				def statusChangesWorkOrders = []
				criteriaWorkOrders.each { workOrder ->
					def workOrderStatusChange = workOrder.getTimeBasedStatusChange(statusChanges)
					if(workOrderStatusChange != null) statusChangesWorkOrders.add(workOrder)
				}
				customWorkOrders = statusChangesWorkOrders
			}
		}
		return customWorkOrders;
	}
}
