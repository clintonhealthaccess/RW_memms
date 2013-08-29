
package org.chai.memms.reports.dashboard
import grails.test.mixin.TestFor
import grails.plugin.spock.UnitSpec;
import org.chai.memms.reports.dashboard.MemmsReport

/**
 *
 * @author donatien,Antoine
 */
@TestFor(MemmsReport)
class MemmsReportSpec extends UnitSpec {
    
	  def "Memms Report is valid and can be saved"() {
          setup:
          mockForConstraintsTests(MemmsReport)
          mockDomain(MemmsReport)

          when:
          def memmsReport=new MemmsReport(eventDate:new Date()).save(failOnError: true, flush:true)
          then:
           assert !memmsReport.errors.hasFieldErrors("eventDate")
           assert memmsReport.validate()
           assert  memmsReport!=null
       
   }
   
    // validation should fail
    def "memms Report without event date is invalid"() {
          setup:
          mockForConstraintsTests(MemmsReport)
          mockDomain(MemmsReport)

          when:
            def memmsReport=new MemmsReport(eventDate:null)
          then:
            
           assert !memmsReport.validate()
           println" out put from null :"+memmsReport
       
   }
   
     
}

