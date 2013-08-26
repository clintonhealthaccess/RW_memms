
package org.chai.memms.reports.dashboard
import org.chai.memms.reports.dashboard.UserDefinedVariable
import grails.plugin.spock.UnitSpec;
/**
 *
 * @author donatien
 */

class UserDefinedVariableSpec extends UnitSpec {
	
     
    
     def "user defined valiable is valid"() {
          setup:
          mockForConstraintsTests(UserDefinedVariable)
          mockDomain(UserDefinedVariable)

          when:
          
           def userDefVal=new UserDefinedVariable(code:"WO_REINCIDENCE_DAYS test",name_en:"Work order re-incidence period(days)",name_fr:"Work order re-incidence period(days)",currentValue:365.0).save(failOnError: true, flush:true)
             userDefVal.validate()
            
         def userDefValCanotbeSaved=new UserDefinedVariable(code:"WO_REINCIDENCE_DAYS test",name_en:"Work order re-incidence period(days)",name_fr:"Work order re-incidence period(days)",currentValue:365.0).save(failOnError: true, flush:true)
             userDefVal.validate()
          then:
          !userDefVal.errors.hasFieldErrors("name_en")
          !userDefVal.errors.hasFieldErrors("name_fr")
          !userDefVal.errors.hasFieldErrors("code")
          !userDefVal.errors.hasFieldErrors("currentValue")
          
         assert userDefVal!=null
          
          assert userDefValCanotbeSaved==null
          
           
          

   }
    
}

