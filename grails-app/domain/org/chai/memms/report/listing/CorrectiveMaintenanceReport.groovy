/**
 * 
 */
package org.chai.memms.report.listing

import org.chai.memms.corrective.maintenance.WorkOrderStatus;

/**
 * @author Aphrodice Rwagaju
 *
 */
class CorrectiveMaintenanceReport extends EquipmentGeneralReportParameters{

	def workOrderStatus
	def underWarranty

	static constraints = {
		workOrderStatus nullable: true, blank: true
		underWarranty nullable: true, blank: true
	}

	static mapping = {
		table "memms_corrective_maintenance_report_listing"
		version false
	}
}
