package org.chai.memms.reports.dashboard
import static org.junit.Assert.*
import org.junit.*
import org.chai.memms.IntegrationTests
import java.util.HashMap
import org.chai.memms.reports.dashboard.DashboardInitializer
import org.chai.memms.reports.dashboard.Indicator
import org.chai.memms.reports.dashboard.IndicatorCategory

class IndicatorServiceSpec  extends IntegrationTests{
def indicatorService
   

    void "search Indicator"() {
                 setup:
		when:
                 def code=DashboardInitializer.CORRECTIVE_MAINTENANCE+"dontesttyy"
                 def indicatorCategory=IndicatorCategory.findByCode(code)
                 if(indicatorCategory==null)
                 indicatorCategory=new IndicatorCategory(code:code,name_en:"Corrective maintenance",redToYellowThreshold:60,yellowToGreenThreshold:80).save(failOnError: true, flush:true)
                 def indicator=Indicator.findByCode("SHARE_OPE_EQUIPMENTtestuniqutyy")
                 if(indicator==null)
                  indicator=new Indicator(category:indicatorCategory, code:"SHARE_OPE_EQUIPMENTtestuniqutyy", name_en:"Share of operational equipment",name_fr:"Share of operational equipment",description_en:"Share of operational equipment",description_fr:"Share of operational equipment", formula_en:"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})", formula_fr:"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})",unit:"%",redToYellowThreshold:0.8,yellowToGreenThreshold:0.9, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:DashboardInitializer.SHARE_OPERATIONAL_SIMPLE_SLD7, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:DashboardInitializer.SHARE_OPERATIONAL_GROUP_SLD7,sqlQuery:false, active:true).save(failOnError: true, flush:true)
                def params=new HashMap<String,String>()
                params.put("order","desc")
                params.put("sort","id")
		def result=indicatorService.searchIndicator("Share of operational equipment", params)
		then:
                assert result!=null
                println" This test passed wit search result :"+result
    }
}
