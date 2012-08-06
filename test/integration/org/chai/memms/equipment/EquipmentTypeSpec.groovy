package org.chai.memms.equipment

import org.chai.memms.IntegrationTests

class EquipmentTypeSpec extends IntegrationTests{

    def "can create and save an equipment type"() {
		
		when:
		def equipmentType = new EquipmentType(code:CODE(123),names:['en':"testName"], usedInMemms:true,observations:['en':"testObservations"])
		equipmentType.save(failOnError: true)
		then:
		EquipmentType.count() == 1
	}
	
	def "can't create and save an equipment type with a duplicate code"() {
		setup:
		newEquipmentType(CODE(123), ["en":"Accelerometers"], true,["en":"used in memms"])
		when:
		def equipmentType = new EquipmentType(code:CODE(123),names:['en':"testName"], usedInMemms:true,observations:['en':"testObservations"])
		equipmentType.save()
		then:
		EquipmentType.count() == 1
		equipmentType.errors.hasFieldErrors('code') == true
	}
}
