package org.chai.memms.reports.dashboard

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.chai.memms.IntegrationTests

import org.chai.memms.reports.dashboard.Indicator
import org.chai.location.CalculationLocation
import org.chai.location.DataLocation
import org.chai.location.Location
class IndicatorComputationServiceSpec  extends IntegrationTests{
    def indicatorComputationService
    
    //simple data computation terst
    
        def "compute SQL Qury Script"() {
		setup:
		when:
		   def result=indicatorComputationService.executeSQL("select sum(tmp2.max_eq)/tmp3.denominator as final_result from (select max(tmp1.counter) as max_eq from (select type_id, manufacturer_id, count(id) as counter FROM memms_equipment where current_status not in ('FORDISPOSAL','DISPOSED') and type_id is not null and manufacturer_id is not null and data_location_id is not null group by type_id, manufacturer_id) tmp1 group by tmp1.type_id) tmp2, (select sum(temp22.counter3) as denominator from (select e.type_id as typeid,count(e.id) as counter3 from memms_equipment e,memms_equipment_type et where e.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id is not null group by e.type_id,e.manufacturer_id) temp22) tmp3")
		then:
                   println" This test passed wit computaion result :"+result
                   assert result!=null
	}
   
    def "compute Script"(){
     
               setup:
		when:
		 def result=indicatorComputationService.computeScript("select sum(tmp2.max_eq)/tmp3.denominator as final_result from (select max(tmp1.counter) as max_eq from (select type_id, manufacturer_id, count(id) as counter FROM memms_equipment where current_status not in ('FORDISPOSAL','DISPOSED') and type_id is not null and manufacturer_id is not null and data_location_id is not null group by type_id, manufacturer_id) tmp1 group by tmp1.type_id) tmp2, (select sum(temp22.counter3) as denominator from (select e.type_id as typeid,count(e.id) as counter3 from memms_equipment e,memms_equipment_type et where e.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id is not null group by e.type_id,e.manufacturer_id) temp22) tmp3",true)
		then:
                 println" This test passed wit computaion  Result :"+result
                  assert result!=null
          
   }
    
    def "compute Script With DataLocation Condition"(){
       
                setup:
		when:
		  def result=indicatorComputationService.computeScriptWithDataLocationCondition("select sum(tmp2.max_eq)/tmp3.denominator as final_result from (select max(tmp1.counter) as max_eq from (select type_id, manufacturer_id, count(id) as counter FROM memms_equipment where current_status not in ('FORDISPOSAL','DISPOSED') and type_id is not null and manufacturer_id is not null and data_location_id is not null group by type_id, manufacturer_id) tmp1 group by tmp1.type_id) tmp2, (select sum(temp22.counter3) as denominator from (select e.type_id as typeid,count(e.id) as counter3 from memms_equipment e,memms_equipment_type et where e.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id is not null group by e.type_id,e.manufacturer_id) temp22) tmp3",true,"is not null")
		then:
                  println" This test passed wit computaion  Result :"+result
                 assert result!=null
        
    }
    
    
    
   def "compute Indicator For All Data Locations"() {
       
                setup:
		when:
                 def indicator=Indicator.findByCode("DEGREE_STD_EQUIPMENT")
                 def result= indicatorComputationService.computeIndicatorForAllDataLocations(indicator)
		then:
                 assert result!=null
                println" This test passed wit computaion  Result :"+result +" on this indicator :"+indicator
        
    }
    
 // Grouped data computation teast
    
   def "group Compute SQL Qury Script"() {
		setup:
		when:
		  def groupResult=indicatorComputationService.groupExecuteSQL("select tmp2.typeNames as typeName, tmp2.max_eq/tmp3.denominator as final_result from (select tmp1.namess as typeNames,max(tmp1.counter1) as max_eq from (select ee.type_id,et.names_en as namess, ee.manufacturer_id, count(ee.id) as counter1 FROM memms_equipment ee,memms_equipment_type et where ee.type_id=et.id and ee.type_id is not null and ee.manufacturer_id is not null and ee.current_status not in ('FORDISPOSAL','DISPOSED') and ee.data_location_id is not null group by ee.type_id, ee.manufacturer_id) tmp1 group by tmp1.type_id) tmp2,(select sum(temp2.counter3) as denominator from (select eq.type_id as typeid,count(eq.id) as counter3 from memms_equipment eq,memms_equipment_type et where eq.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id is not null group by eq.type_id,eq.manufacturer_id) temp2) tmp3")
		then:
                  println" This test passed wit computaion  Result :"+groupResult
                 assert groupResult!=null
                   
	}
        
    
    def "group Compute Script"(){
      
                setup:
		when:
		   def groupResult=indicatorComputationService.groupComputeScript("select tmp2.typeNames as typeName, tmp2.max_eq/tmp3.denominator as final_result from (select tmp1.namess as typeNames,max(tmp1.counter1) as max_eq from (select ee.type_id,et.names_en as namess, ee.manufacturer_id, count(ee.id) as counter1 FROM memms_equipment ee,memms_equipment_type et where ee.type_id=et.id and ee.type_id is not null and ee.manufacturer_id is not null and ee.current_status not in ('FORDISPOSAL','DISPOSED') and ee.data_location_id is not null group by ee.type_id, ee.manufacturer_id) tmp1 group by tmp1.type_id) tmp2,(select sum(temp2.counter3) as denominator from (select eq.type_id as typeid,count(eq.id) as counter3 from memms_equipment eq,memms_equipment_type et where eq.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id is not null group by eq.type_id,eq.manufacturer_id) temp2) tmp3",true)
		then:
                  println" This test passed wit computaion  Result :"+groupResult
                  assert groupResult!=null
          
    }
   
    def "group Compute Script With DataLocation Condition"(){
        
                setup:
		when:
		  def groupResult=indicatorComputationService.groupComputeScriptWithDataLocationCondition("select tmp2.typeNames as typeName, tmp2.max_eq/tmp3.denominator as final_result from (select tmp1.namess as typeNames,max(tmp1.counter1) as max_eq from (select ee.type_id,et.names_en as namess, ee.manufacturer_id, count(ee.id) as counter1 FROM memms_equipment ee,memms_equipment_type et where ee.type_id=et.id and ee.type_id is not null and ee.manufacturer_id is not null and ee.current_status not in ('FORDISPOSAL','DISPOSED') and ee.data_location_id is not null group by ee.type_id, ee.manufacturer_id) tmp1 group by tmp1.type_id) tmp2,(select sum(temp2.counter3) as denominator from (select eq.type_id as typeid,count(eq.id) as counter3 from memms_equipment eq,memms_equipment_type et where eq.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id is not null group by eq.type_id,eq.manufacturer_id) temp2) tmp3",true,"is not null")
		then:
                  println" This test passed wit computaion  Result :"+groupResult
                  assert groupResult!=null
        
    }
    
    
   
   def "group Compute Indicator For All Data Locations"() {
       
                setup:
		when:
                 def indicator=Indicator.findByCode("DEGREE_STD_EQUIPMENT")
                 def groupResult= indicatorComputationService.groupComputeIndicatorForAllDataLocations(indicator)
		then:
                 assert groupResult!=null
                 println" This test passed wit computaion  Result :"+groupResult +" on this indicator :"+indicator
       
   }
    
    //test for all most all method in this class in one method
    
    def "compute Indicator For Location"(){
        
                setup:
		when:
                 def indicator=Indicator.findByCode("DEGREE_STD_EQUIPMENT")
                 def groupResult= indicatorComputationService.computeIndicatorForLocation(indicator,null);
		then:
                 assert groupResult==0.0
                 println" This test passed wit computaion  Result :"+groupResult +" on this indicator :"+indicator
        
        
    }
	
}
