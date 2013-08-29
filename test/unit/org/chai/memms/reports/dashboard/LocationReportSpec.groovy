
package org.chai.memms.reports.dashboard
import grails.test.mixin.TestFor
import grails.plugin.spock.UnitSpec;
import org.chai.memms.reports.dashboard.LocationReport
import org.chai.location.CalculationLocation

import org.chai.location.Location
import org.chai.location.LocationLevel
/**
/**
 *
 * @author donatien,Antoine
 */
@TestFor(LocationReport)
class LocationReportSpec extends UnitSpec {
	
	  def "location report is valid and persistable"() {
          setup:
          mockForConstraintsTests(LocationReport)
          mockDomain(LocationReport)
          mockDomain(MemmsReport)
          
          when:
            def memmsReport=new MemmsReport(eventDate:new Date()).save(failOnError: true, flush:true)
            memmsReport.validate()
            def locationReport = new LocationReport(eventDate: new Date(), memmsReport: memmsReport, location:null).save()
      
             locationReport.validate()
         
          then:
           assert locationReport.validate()
           assert locationReport!=null  
          
   }
   
     def "location report with null event date is inavalid"() {
          setup:
          mockForConstraintsTests(LocationReport)
          mockDomain(LocationReport)
          mockDomain(MemmsReport)
         
           when:
             def memmsReport=new MemmsReport(eventDate:new Date()).save(failOnError: true, flush:true)
             def locationReportNotInit = new LocationReport(eventDate: null, memmsReport: memmsReport, location:null)
             
          then:
            assert !locationReportNotInit.validate()
       
   }
}

