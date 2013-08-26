package org.chai.memms.reports.dashboard

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.chai.memms.IntegrationTests
import org.chai.memms.reports.dashboard.DashboardInitializer
class IndicatorCategoryServiceSpec extends IntegrationTests {
def indicatorCategoryService 


    def "list All Indicator Categories test"() {
    
                setup:
		when:
                  def code=DashboardInitializer.CORRECTIVE_MAINTENANCE+"TESTTunique"
                 
                  def indicatorCategory=IndicatorCategory.findByCode(code)
                  if(indicatorCategory==null)
                     indicatorCategory=new IndicatorCategory(code:code,name_en:"Corrective maintenance",redToYellowThreshold:60,yellowToGreenThreshold:80).save(failOnError: true, flush:true)
       
		   def result=indicatorCategoryService.listAllIndicatorCategories()
                   
		then:
                assert result!=null
                 println" This test passed wit listing result :"+result
        
        
    }
}
