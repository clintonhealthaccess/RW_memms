/**
 * 
 */
package org.chai.memms.report.listing

import java.util.Date;

import org.chai.memms.corrective.maintenance.WorkOrderStatus;

/**
 * @author Aphrodice Rwagaju
 *
 */
class CorrectiveMaintenanceReport extends EquipmentGeneralReportParameters{

	def workOrderStatus
	boolean underWarranty
	def statusChanges
	Date fromStatusChangesPeriod
	Date toStatusChangesPeriod
	

	static constraints = {
		workOrderStatus nullable: true, blank: true
		underWarranty nullable: false
		statusChanges nullable: true
		fromStatusChangesPeriod nullable: true
		toStatusChangesPeriod nullable: true
	}

	static mapping = {
		table "memms_corrective_maintenance_report_listing"
		version false
	}
}
