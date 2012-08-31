package org.chai.memms.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum UserType {

	ADMIN("admin"), 
	SYSTEM("system"), 
	OTHER("other");
	
	String messageCode = "user.type";	
	String name;
	UserType(String name){ this.name=name; }
	public String getName(){
		return name;
	}
	
	protected Set<String> defaultPermissions;
	protected Set<String> defaultRoles;
	
	private UserType(Set<String> defaultRoles, String... defaultPermissions) {
		this.defaultRoles = defaultRoles;
		this.defaultPermissions = new HashSet<String>(Arrays.asList(defaultPermissions));
	}
	
	private UserType(String defaultRole, String... defaultPermissions) {
		this.defaultRoles = new HashSet<String>();
		this.defaultRoles.add(defaultRole);
		this.defaultPermissions = new HashSet<String>(Arrays.asList(defaultPermissions));
	}
	
	String getKey() { return name(); }
	
	public static Set<String> getAllPermissions() {
		Set<String> result = new HashSet<String>();
		for (UserType userType : UserType.values()) {
			result.addAll(userType.defaultPermissions);
		}
		return result;
	}
	
	public static Set<String> getAllRoles() {
		Set<String> result = new HashSet<String>();
		for (UserType userType : UserType.values()) {
			result.addAll(userType.defaultRoles);
		}
		return result;
	}
}
