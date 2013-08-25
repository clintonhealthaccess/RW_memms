package org.chai.memms.reports.dashboard
import static org.junit.Assert.*
import org.junit.*
import org.chai.memms.IntegrationTests
import java.util.HashMap


class IndicatorServiceSpec  extends IntegrationTests{
def indicatorService
   

    void "search Indicator"() {
                 setup:
		when:
                def params=new HashMap<String,String>()
                params.put("order","desc")
                params.put("sort","id")
		def result=indicatorService.searchIndicator("Share of operational equipment", params)
		then:
                assert result!=null
                println" This test passed wit search result :"+result
    }
}
