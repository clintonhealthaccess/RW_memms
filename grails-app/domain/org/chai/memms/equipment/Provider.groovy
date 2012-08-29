/**
 * 
 */
package org.chai.memms.equipment

import org.chai.memms.Contact;

import i18nfields.I18nFields
/**
 * @author JeanKahigiso
 *
 */
@i18nfields.I18nFields
public class Provider{
	
	enum Type{
		BOTH("both"),
		MANUFACTURE("manufacture"),
		SUPPLIER("supplier")
		
		String messageCode = "provider.type"
		final String name
		Type(String name){ this.name = name }
		String getKey() { return name() }
	}
	
	String code
	Type type
	Contact contact
	
	static embedded = ["contact"]
	static mappedBy = [manufactures: "manufacture",suppliers: "supplier"]
	static hasMany = [manufactures: Equipment, suppliers: Equipment]
   
	static constraints ={
		importFrom Contact
		code nullable: false, blank: false, unique: true
		type nullable: false
		//contact nullable: false 
	}
	static mapping = {
	    version false
	    table "memms_provider"
	}
	
	@Override
	public String toString() {
		return "Provider [code=" + code + ", type=" + type + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.is(obj))
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}	
   
}
