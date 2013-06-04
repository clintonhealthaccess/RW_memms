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

	def obsolete
	def equipmentStatus
	def underWarranty
	def noAcquisitionPeriod

	static constraints = {
		obsolete nullable: true, blank: true
		equipmentStatus nullable: true
		underWarranty nullable: true, blank: true
		noAcquisitionPeriod nullable: true, blank: true
		
	}

	static mapping = {
		table "memms_equipment_report_listing"
		version false
	}
}
