package org.chai.memms.security

class User {
	static String PERMISSION_DELIMITER = ";"
	
	String email
	String username
	String uuid
	String passwordHash = ''
	String permissionString = ''
	Boolean confirmed = false
	Boolean active = false
	String defaultLanguage
	String firstname, lastname, organisation, phoneNumber
	def utils
	
	static hasMany = [ roles: Role ]
	
	User() {
		roles = []
	}
	
	def getPermissions() {
		return Utils.split(permissionString, User.PERMISSION_DELIMITER)
	}
	
	def setPermissions(def permissions) {
		this.permissionString = utils.unsplit(permissions, User.PERMISSION_DELIMITER)
	}
	
	def addToPermissions(def permission) {
		def permissions = getPermissions()
		permissions << permission
		this.permissionString = utils.unsplit(permissions, User.PERMISSION_DELIMITER)
	}
	
	def removeFromPermissions(def permission) {
		def permissions = getPermissions()
		permissions.remove(permission)
		this.permissionString = utils.unsplit(permissions, User.PERMISSION_DELIMITER)
	}
	
	def setDefaultPermissions() {
		removeAllDefaultPermissions()
		addDefaultPermissions()
	}
	
	def setDefaultRoles() {
		removeAllDefaultRoles()
		addDefaultRoles()
	}
	
	private def addDefaultPermissions() {
		userType.defaultPermissions.each { permissionToAdd ->
			def permission = permissionToAdd.replaceAll('<id>', locationId+'')
			addToPermissions(permission)
		}
	}
	
	private def removeAllDefaultPermissions() {
		UserType.getAllPermissions().each { permissionToRemove ->
			def regexToCheck = permissionToRemove.replaceAll('\\*','\\\\*').replaceAll('<id>', "\\\\d*")
			permissions.each { permission ->
				if (permission.matches(regexToCheck)) {
					removeFromPermissions(permission)
				}
			}
		}
	}
	
	private def addDefaultRoles() {
		userType.defaultRoles.each { roleNameToAdd ->
			def roleToAdd = Role.findByName(roleNameToAdd)
			if (roleToAdd != null) roles.add(roleToAdd)
		}
	}
	
	private def removeAllDefaultRoles() {
		UserType.getAllRoles().each { roleNameToRemove ->
			def roleToRemove = Role.findByName(roleNameToRemove)
			if (roleToRemove != null) roles.remove(roleToRemove)
		}
	}
	
	def canActivate() {
		return confirmed == true && active == false
	}
	static constraints = {
		email(email:true, unique: true, nullable: true)
		username(nullable: false, blank: false, unique: true)
		uuid(nullable: false, blank: false, unique: true)
		firstname(nullable: false, blank: false)
		lastname(nullable: false, blank: false)
		phoneNumber(phoneNumber: true, nullable: false, blank: false)
		organisation(nullable: false, blank: false)
		defaultLanguage(nullable: true)		
	}
	
	static mapping = {
		cache true
	}
}
