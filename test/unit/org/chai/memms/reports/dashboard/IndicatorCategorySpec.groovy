
package org.chai.memms.reports.dashboard
import grails.plugin.spock.UnitSpec;
import org.chai.memms.reports.dashboard.IndicatorCategory
import grails.test.mixin.TestFor
import grails.plugin.spock.UnitSpec;
import org.chai.memms.reports.dashboard.DashboardInitializer
import grails.plugin.spock.UnitSpec;
/**
 *
 * @author donatien Antoine
 */
@TestFor(IndicatorCategory)

class IndicatorCategorySpec extends UnitSpec {
	 def "indicator category is valid"() {
          setup:
          mockForConstraintsTests(IndicatorCategory)
          mockDomain(IndicatorCategory)

          when:
          def code=DashboardInitializer.CORRECTIVE_MAINTENANCE+"TEST"
          def category=new IndicatorCategory(code:code,name_en:"Corrective maintenance",redToYellowThreshold:60,yellowToGreenThreshold:80).save(failOnError: true, flush:true)
          
          then:
          assert(category.validate())
          !category.errors.hasFieldErrors("name_en")
          !category.errors.hasFieldErrors("name_fr")
          !category.errors.hasFieldErrors("redToYellowThreshold")
          !category.errors.hasFieldErrors("yellowToGreenThreshold")
          assert category!=null
       

   }
   // category.validate() should fail
    def "indicator category  with null code is inavlid"(){
          setup:
          mockForConstraintsTests(IndicatorCategory)
          mockDomain(IndicatorCategory)

          when:
         
          def category=new IndicatorCategory(code:null,name_en:"Corrective maintenance",redToYellowThreshold:60,yellowToGreenThreshold:80)
          then:
          assert !category.validate()
          assert category.errors.hasFieldErrors("code")
          assert !category.errors.hasFieldErrors("name_en")
          assert !category.errors.hasFieldErrors("name_fr")
          assert  !category.errors.hasFieldErrors("redToYellowThreshold")
          assert  !category.errors.hasFieldErrors("yellowToGreenThreshold")
         
         
   }
    
}

