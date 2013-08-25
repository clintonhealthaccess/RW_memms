package org.chai.memms.reports.dashboard

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.chai.memms.IntegrationTests

class IndicatorCategoryServiceSpec extends IntegrationTests {
def indicatorCategoryService 


    def "list All Indicator Categories test"() {
    
                setup:
		when:
               
		def result=indicatorCategoryService.listAllIndicatorCategories()
		then:
                assert result!=null
                 println" This test passed wit listing result :"+result
        
        
    }
}
