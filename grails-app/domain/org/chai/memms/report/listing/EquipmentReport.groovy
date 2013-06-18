/**
 * 
 */
package org.chai.memms.report.listing

import org.chai.memms.inventory.EquipmentStatus.Status;

/**
 * @author Aphrodice Rwagaju
 *
 */
class EquipmentReport extends EquipmentGeneralReportParameters {

	boolean obsolete
	def equipmentStatus
	boolean underWarranty
	boolean noAcquisitionPeriod

	static constraints = {
		obsolete nullable: false
		equipmentStatus nullable: true
		underWarranty nullable: false
		noAcquisitionPeriod nullable: false
		
	}

	static mapping = {
		table "memms_equipment_report_listing"
		version false
	}
}
