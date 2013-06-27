/**
 * 
 */
package org.chai.memms.report.listing

/**
 * @author Aphrodice Rwagaju
 *
 */
class ListingReportDisplayOption {
	
	String displayOption
	
	//static belongsTo = [equipmentReport: EquipmentReport, sparePartReport: SparePartReport]
	
	static constraints = {
		//equipmentReport nullable: true, blank: true
		//sparePartReport nullable: true, blank: true
		displayOption nullable: false, blank: false
	}
	static mapping = {
		table "memms_display_options_report_listing"
		version false
	}
}
