package org.chai.memms.inventory

import org.chai.memms.inventory.EquipmentType.Observation;
import org.chai.memms.inventory.EquipmentTypeService;

import grails.plugin.spock.UnitSpec;


class EquipmentTypeServiceUtilSpec extends UnitSpec {

	def "test enum matcher"() {
		
		expect:
		EquipmentTypeService.getEnumeMatcher("retired") == [Observation.RETIRED]
		EquipmentTypeService.getEnumeMatcher("used") == [Observation.USEDINMEMMS]
		EquipmentTypeService.getEnumeMatcher("detailed") == [Observation.TOODETAILED]
		EquipmentTypeService.getEnumeMatcher("") == []
		
	}
	
}
