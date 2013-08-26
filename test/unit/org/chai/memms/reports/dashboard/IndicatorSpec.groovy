
package org.chai.memms.reports.dashboard
import org.chai.memms.reports.dashboard.Indicator
import grails.test.mixin.TestFor
import grails.plugin.spock.UnitSpec;
import org.chai.memms.reports.dashboard.DashboardInitializer

/**
 *
 * @author donatien,Antoine
 */
@TestFor(Indicator)

class IndicatorSpec extends UnitSpec{
  
	  def "indicator is valid"() {
              
          // here the  indicator will be persisted
          setup:
          mockForConstraintsTests(Indicator)
          mockDomain(IndicatorCategory)
          when:
          def category=new IndicatorCategory(code:DashboardInitializer.CORRECTIVE_MAINTENANCE+"test",name_en:"Corrective maintenance",redToYellowThreshold:60,yellowToGreenThreshold:80).save()
          
         def validIndicator=new Indicator(category:category, code:"SHARE_OPE_EQUIPMENTtes", name_en:"Share of operational equipment",name_fr:"Share of operational equipment",description_en:"Share of operational equipment",description_fr:"Share of operational equipment", formula_en:"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})", formula_fr:"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})",unit:"%",redToYellowThreshold:0.8,yellowToGreenThreshold:0.9, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:DashboardInitializer.SHARE_OPERATIONAL_SIMPLE_SLD7, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:DashboardInitializer.SHARE_OPERATIONAL_GROUP_SLD7,sqlQuery:false, active:true).save(failOnError: true)
          validIndicator.validate()
          then:
           category!=null
           validIndicator!=null
          !validIndicator.errors.hasFieldErrors("name_en")
          !validIndicator.errors.hasFieldErrors("name_fr")
          !validIndicator.errors.hasFieldErrors("description_en")
          !validIndicator.errors.hasFieldErrors("description_fr")
          !validIndicator.errors.hasFieldErrors("formula_en")
          !validIndicator.errors.hasFieldErrors("formula_fr")
          !validIndicator.errors.hasFieldErrors("code")
          !validIndicator.errors.hasFieldErrors("groupQueryScript")
         
            assert validIndicator!=null
             
              // here the indicator will not be persisted
          when:
          def notTobeSavedIndicator
            try{
              def categoryNot=new IndicatorCategory(code:DashboardInitializer.CORRECTIVE_MAINTENANCE+"testnot",name_en:"Corrective maintenance",redToYellowThreshold:60,yellowToGreenThreshold:80).save(failOnError: true, flush:true)
              def notTobeSavedIndicatorInd=new Indicator(category:categoryNot, code:"SHARE_OPE_EQUIPMENTtes", name_en:"Share of operational equipment",name_fr:"Share of operational equipment",description_en:"Share of operational equipment",description_fr:"Share of operational equipment", formula_en:"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})", formula_fr:"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})",unit:"%",redToYellowThreshold:0.8,yellowToGreenThreshold:0.9, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:DashboardInitializer.SHARE_OPERATIONAL_SIMPLE_SLD7, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:DashboardInitializer.SHARE_OPERATIONAL_GROUP_SLD7,sqlQuery:false, active:true)
              notTobeSavedIndicator=notTobeSavedIndicatorInd.save()
          }catch(Exception ex){
          
        }
          then:   
          assert notTobeSavedIndicator==null
           
         
   }
   
    
     
   
   
    
}

