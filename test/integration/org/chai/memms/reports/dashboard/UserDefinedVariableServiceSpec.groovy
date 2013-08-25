package org.chai.memms.reports.dashboard
import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.chai.memms.IntegrationTests


class UserDefinedVariableServiceSpec extends IntegrationTests {
def userDefinedVariableService
   
    def  "list All User Defined Variables"() {
                setup:
		when:
		def result=userDefinedVariableService.listAllUserDefinedVariables()
		then:
                assert result!=null
                 println" Test passed wit listing result :"+result
        
    }
}
