
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
  
	  def "indicator is valid and can be saved"() {
          setup:
          mockForConstraintsTests(Indicator)
          mockDomain(IndicatorCategory)
          when:
          def category=new IndicatorCategory(code:DashboardInitializer.CORRECTIVE_MAINTENANCE+"test",names:['en':"Corrective maintenance",'fr':"Corrective maintenance"],redToYellowThreshold:60,yellowToGreenThreshold:80).save()
          def validIndicator=new Indicator(
            category:category, 
            code:"SHARE_OPE_EQUIPMENTtes",
            names:['en':"Share of operational equipment",'fr':"Share of operational equipment"], 
            descriptions:['en':"Share of operational equipment",'fr':"Share of operational equipment"], 
            formulas:[
            'en':"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})", 
            'fr':"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})"
            ],
            unit:"%",redToYellowThreshold:0.8,
            yellowToGreenThreshold:0.9, 
            historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, 
            historyItems:8, queryScript:DashboardInitializer.SHARE_OPERATIONAL_SIMPLE_SLD7, 
            groupNames:['en':"Type of Equipment", 'fr':"Type of Equipment"],
            groupQueryScript:DashboardInitializer.SHARE_OPERATIONAL_GROUP_SLD7,
            sqlQuery:false, active:true
            
            ).save(failOnError: true)
         
          then:
           category!=null
           validIndicator!=null
          !validIndicator.errors.hasFieldErrors("names_en")
          !validIndicator.errors.hasFieldErrors("names_fr")
          !validIndicator.errors.hasFieldErrors("descriptions_en")
          !validIndicator.errors.hasFieldErrors("descriptions_fr")
          !validIndicator.errors.hasFieldErrors("formulas_en")
          !validIndicator.errors.hasFieldErrors("formulas_fr")
          !validIndicator.errors.hasFieldErrors("code")
          !validIndicator.errors.hasFieldErrors("groupQueryScript")
         assert  validIndicator.validate()
         assert validIndicator!=null
             
            
         
   }
     def "indicator without code  is invalid"() {
              
         
          setup:
          mockForConstraintsTests(Indicator)
          mockDomain(IndicatorCategory)
          when:
          def category=new IndicatorCategory(code:DashboardInitializer.CORRECTIVE_MAINTENANCE+"test",names:['en':"Corrective maintenance"],redToYellowThreshold:60,yellowToGreenThreshold:80).save()
          def invalidIndicator=new Indicator(category:category, 
            code:null, 
            names:['en':"Share of operational equipment",'fr':"Share of operational equipment"],
            descriptions:['en':"Share of operational equipment",'fr':"Share of operational equipment"], 
            formulas:[
            'en':"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})",
            'fr':"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})"
            ],
            unit:"%",
            redToYellowThreshold:0.8,yellowToGreenThreshold:0.9, 
            historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, 
            historyItems:8, queryScript:DashboardInitializer.SHARE_OPERATIONAL_SIMPLE_SLD7, 
            groupNames:['en':"Type of Equipment",'fr':"Type of Equipment"], 
            groupQueryScript:DashboardInitializer.SHARE_OPERATIONAL_GROUP_SLD7,
            sqlQuery:false, 
            active:true
            )
         
          then:
           category!=null
           
          !invalidIndicator.errors.hasFieldErrors("name_en")
          !invalidIndicator.errors.hasFieldErrors("name_fr")
          !invalidIndicator.errors.hasFieldErrors("description_en")
          !invalidIndicator.errors.hasFieldErrors("description_fr")
          !invalidIndicator.errors.hasFieldErrors("formula_en")
          !invalidIndicator.errors.hasFieldErrors("formula_fr")
          !invalidIndicator.errors.hasFieldErrors("groupQueryScript")
          
            assert !invalidIndicator.validate()
          
   }
   
   
    
}

