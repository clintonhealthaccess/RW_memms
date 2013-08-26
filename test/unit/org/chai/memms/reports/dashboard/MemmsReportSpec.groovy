
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
    
	  def "Memms Report is valid"() {
          setup:
          mockForConstraintsTests(MemmsReport)
          mockDomain(MemmsReport)

          when:
          def memmsReport=new MemmsReport(generatedAt:new Date()).save(failOnError: true, flush:true)
            memmsReport.validate()
            println" category is :"+memmsReport
          then:
           memmsReport!=null
           !memmsReport.errors.hasFieldErrors("generatedAt")
           assert memmsReport!=null
       
   }
   
     
}

