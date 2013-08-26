
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
	
	  def "location report is valid"() {
          setup:
          mockForConstraintsTests(LocationReport)
          mockDomain(LocationReport)
          mockDomain(MemmsReport)
          def locationReportNot
          def exceptionMesasage=""

          when:
            def memmsReport=new MemmsReport(generatedAt:new Date()).save(failOnError: true, flush:true)
            memmsReport.validate()
            def locationReport = new LocationReport(generatedAt: new Date(), memmsReport: memmsReport, location:null).save()
      
             locationReport.validate()
         
          then:
           assert locationReport.validate()
           assert locationReport!=null
           
           when:
          
           
              def locationReportNotInit = new LocationReport(generatedAt: null, memmsReport: memmsReport, location:null)
              
              locationReportNot=locationReportNotInit.save()
        
         
          then:
            assert !locationReportNotInit.validate()
            assert locationReportNot==null
           
           
           
          

   }
   
    
    
}

