package org.chai.memms.util

import java.util.Date;

import org.chai.memms.IntegrationTests

class UtilSpec extends IntegrationTests{
	def "get minimum date"() {
		setup:
		Date now = new Date()
		Date cleanedMinimumDate
		when:
		cleanedMinimumDate = Utils.getMinDateFromDateTime(now)
		then:
		cleanedMinimumDate <= now
	}
	
	def "get maximum date"() {
		setup:
		def newDate = Initializer.getDate(1, 1, 2010)
		Date cleanedMaximumDate
		when:
		cleanedMaximumDate = Utils.getMaxDateFromDateTime(now)
		then:
		cleanedMaximumDate >= now
	}
}
