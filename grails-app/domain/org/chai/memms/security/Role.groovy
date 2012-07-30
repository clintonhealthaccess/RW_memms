package org.chai.memms.security

import org.chai.memms.util.UtilsService

class Role {
    String name
	String permissionString
	
	def utilsService
	
    static hasMany = [ users: User ]
    static belongsTo = User

	def getPermissions() {
		return utilsService.split(permissionString, User.PERMISSION_DELIMITER)
	}
	
	def setPermissions(def permissions) {
		this.permissionString = utilsService.unsplit(permissions, User.PERMISSION_DELIMITER)
	}
	
	def addToPermissions(def permission) {
		def permissions = getPermissions()
		permissions << permission
		this.permissionString = utilsService.unsplit(permissions, User.PERMISSION_DELIMITER)
	}
	
    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
	static mapping = {
		table "memms_user_role"
		cache true
	}
	
	String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.is(obj))
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
