package org.chai.memms


import grails.plugin.spock.UnitSpec;


class PeriodUtilSpec extends UnitSpec {

	def "test getYears and getMonths"() {
		when:
		Period period = new Period(3,8)
		then:
		period.years == 3
		period.months == 8
		period.numberOfMonths == 44
		
	}
	
	def "test numberOfMonths can be null"() {
		when:
		Period period = new Period(null,null)
		then:
		period.years == null
		period.months == null
		period.numberOfMonths == null
		
	}
	
	def "test can set months only"() {
		when:
		Period period = new Period(null,25)
		then:
		period.years == 2
		period.months == 1
		period.numberOfMonths == 25
		
	}
	
	def "test can set years only"() {
		when:
		Period period = new Period(5,null)
		then:
		period.years == 5
		period.months == null
		period.numberOfMonths == 60
		
	}
	
}
