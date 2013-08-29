
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
         
          then:
          !userDefVal.errors.hasFieldErrors("name_en")
          !userDefVal.errors.hasFieldErrors("name_fr")
          !userDefVal.errors.hasFieldErrors("code")
          !userDefVal.errors.hasFieldErrors("currentValue")
          assert userDefVal.validate()
          assert userDefVal!=null
         
      
   }
    // validation should fail
     def "user defined valiable with null code or null currentValue  is invalid"() {
          setup:
          mockForConstraintsTests(UserDefinedVariable)
          mockDomain(UserDefinedVariable)

          when:
          
           def userDefVal=new UserDefinedVariable(code:null,name_en:"Work order re-incidence period(days)",name_fr:"Work order re-incidence period(days)",currentValue:null)
             
          then:
          !userDefVal.errors.hasFieldErrors("name_en")
          !userDefVal.errors.hasFieldErrors("name_fr")  
          !userDefVal.errors.hasFieldErrors("currentValue")
          assert !userDefVal.validate()
          
        
          
           
          

   }
    
}

