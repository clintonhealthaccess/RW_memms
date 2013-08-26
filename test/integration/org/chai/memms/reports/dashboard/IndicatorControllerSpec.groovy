package org.chai.memms.reports.dashboard

import static org.junit.Assert.*
import org.chai.memms.IntegrationTests

import org.chai.memms.IntegrationTests


import org.chai.memms.util.Utils;



class IndicatorControllerSpec extends IntegrationTests{

    def indicatorController
    
    
    def "indicators search test"(){
        
               setup:
               indicatorController = new IndicatorController()
                indicatorController.params.sort="id"
                indicatorController.params.order="desc"
                 indicatorController.params.q="share"
                
	       when:
               
                indicatorController.search
               
		then:
		println"response content ok: "+indicatorController.response.text
        
    }
}
