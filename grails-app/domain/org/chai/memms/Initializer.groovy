package org.chai.memms


import org.apache.shiro.crypto.hash.Sha256Hash
import org.chai.memms.security.Role
import org.chai.memms.security.User
import org.chai.memms.security.UserType

public class Initializer {


	static def createUsers() {
		def reportAllReadonly = new Role(name: "report-all-readonly")
		reportAllReadonly.addToPermissions("menu:reports")
		reportAllReadonly.addToPermissions("dashboard:*")
		reportAllReadonly.addToPermissions("dsr:*")
		reportAllReadonly.addToPermissions("maps:*")
		reportAllReadonly.addToPermissions("cost:*")
		reportAllReadonly.addToPermissions("fct:*")
		reportAllReadonly.save()

		def surveyAllReadonly = new Role(name: "survey-all-readonly")
		surveyAllReadonly.addToPermissions("menu:survey")
		surveyAllReadonly.addToPermissions("summary:*")
		surveyAllReadonly.addToPermissions("editSurvey:view")
		surveyAllReadonly.addToPermissions("editSurvey:summaryPage")
		surveyAllReadonly.addToPermissions("editSurvey:sectionTable")
		surveyAllReadonly.addToPermissions("editSurvey:programTable")
		surveyAllReadonly.addToPermissions("editSurvey:surveyPage")
		surveyAllReadonly.addToPermissions("editSurvey:programPage")
		surveyAllReadonly.addToPermissions("editSurvey:sectionPage")
		surveyAllReadonly.addToPermissions("editSurvey:print")
		surveyAllReadonly.save()

		def user = new User(userType: UserType.OTHER,code:"dhsst", username: "dhsst", firstname: "Dhsst", lastname: "Dhsst", email:'dhsst@dhsst.org', passwordHash: new Sha256Hash("dhsst").toHex(), active: true, confirmed: true, uuid:'dhsst_uuid', defaultLanguage:'fr', phoneNumber: '+250 11 111 11 11', organisation:'org')
		user.addToRoles(reportAllReadonly)
		user.addToRoles(surveyAllReadonly)
		// access to site
		user.save(failOnError: true)

		def admin = new User(userType: UserType.OTHER, code:"admin", firstname: "Super", lastname: "Admin", username: "admin", email:'admin@dhsst.org', passwordHash: new Sha256Hash("admin").toHex(), active: true, confirmed: true, uuid:'admin_uuid', phoneNumber: '+250 11 111 11 11', organisation:'org')
		admin.addToPermissions("*")
		admin.save(failOnError: true)

		def butaro = new User(userType: UserType.SYSTEM, code:"butaro",username: "butaro", firstname: "butaro", lastname: "butaro", locationId: 12.3, passwordHash: new Sha256Hash("123").toHex(), active: true, confirmed: true, uuid: 'butaro_uuid', phoneNumber: '+250 11 111 11 11', organisation:'org')
		butaro.addToPermissions("editSurvey:view")
		butaro.addToPermissions("editSurvey:*:")
		butaro.addToPermissions("menu:survey")
		butaro.addToPermissions("menu:reports")
		butaro.addToPermissions("home:*")
		butaro.save(failOnError: true)
		
		def kivuye = new User(userType: UserType.PERSON, code:"kivuye",username: "kivuye", firstname: "kivuye", lastname: "kivuye", locationId: 12.3, passwordHash: new Sha256Hash("123").toHex(), active: true, confirmed: true, uuid: 'kivuye_uuid', phoneNumber: '+250 11 111 11 11', organisation:'org')
		kivuye.addToPermissions("editPlanning:view")
		kivuye.addToPermissions("editPlanning:*:")
		kivuye.addToPermissions("menu:planning")
		kivuye.addToPermissions("menu:reports")
		kivuye.addToPermissions("home:*")
		kivuye.save(failOnError: true)
	}
}
