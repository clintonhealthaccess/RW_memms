/**
 * 
 */
package org.chai.memms.report.listing

import java.util.Date;

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
	def statusChanges
	Date fromStatusChangesPeriod
	Date toStatusChangesPeriod

	static constraints = {
		obsolete nullable: false
		equipmentStatus nullable: true
		underWarranty nullable: false
		noAcquisitionPeriod nullable: false
		statusChanges nullable: true
		fromStatusChangesPeriod nullable: true
		toStatusChangesPeriod nullable: true
	}

	static mapping = {
		table "memms_equipment_report_listing"
		version false
	}
}
