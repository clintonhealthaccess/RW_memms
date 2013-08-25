package org.chai.memms.reports.dashboard

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.chai.memms.IntegrationTests




class DashboardServiceSpec  extends IntegrationTests{
def dashboardService
   def  "get Current Memms Report"() {
                setup:
		when:
		def result = dashboardService.getCurrentMemmsReport()
        
		then:
                 assert result!=null
                
        
    }
    
    
}
