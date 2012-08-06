package org.chai.memms.equipment

import org.chai.memms.IntegrationTests

class DepartmentSpec extends IntegrationTests{

	def "can create and save a department"() {
		when:
		def department = new Department(code:CODE(123))
		setLocaleValueInMap(department,['en':"testName"],"Names")
		setLocaleValueInMap(department,['en':"testDescription"],"Descriptions")
		department.save(failOnError: true)
		then:
		Department.count() == 1
	}
	
	def "can't create and save a department without a code"() {
		when:
		def department = new Department()
		setLocaleValueInMap(department,['en':"testName"],"Names")
		setLocaleValueInMap(department,['en':"testDescription"],"Descriptions")
		department.save()
		then:
		department.errors.hasFieldErrors('code') == true
		Department.count() == 0
	}
	
	def "can't create and save a department with a duplicate code"() {
		setup:
		newDepartment(['en':'testName'],CODE(123),['en':"testDescription"])
		when:
		def department = new Department(code:CODE(123))
		setLocaleValueInMap(department,['en':"testName"],"Names")
		setLocaleValueInMap(department,['en':"testDescription"],"Descriptions")
		department.save()
		then:
		department.errors.hasFieldErrors('code') == true
		Department.count() == 1
	}
}
