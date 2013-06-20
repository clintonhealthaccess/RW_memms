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
	boolean underWarranty

	static constraints = {
		workOrderStatus nullable: true, blank: true
		underWarranty nullable: false
	}

	static mapping = {
		table "memms_corrective_maintenance_report_listing"
		version false
	}
}
