/**
 * 
 */
package org.chai.memms.report.listing

import java.util.Date;

import org.chai.memms.security.User;
import org.chai.location.DataLocation;
import org.chai.memms.spare.part.SparePartType;

/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartReport {
	String reportName
	String reportType
	String reportSubType
	Date dateCreated
	Date fromDate
	Date toDate
	User savedBy
	def sparePartStatus
	boolean noAcquisitionPeriod

	static hasMany = [dataLocations:DataLocation,sparePartTypes:SparePartType]
	static constraints = {
		dataLocations nullable: true, blank: true
		sparePartTypes nullable: true, blank: true
		fromDate nullable: true, blank: true
		toDate nullable: true, blank: true
		savedBy nullable: false
		sparePartStatus nullable: true, blank: true
		noAcquisitionPeriod nullable: false
	}

	static mapping = {
		table "memms_spare_part_report_listing"
		version false
		dataLocations joinTable:[name:"memms_spare_part_data_location_report_listing",key:"data_location_id",column:"report_listing_id"]
		sparePartTypes joinTable:[name:"memms_spare_part_type_report_listing",key:"spare_part_type_id",column:"report_listing_id"]
	}

}
