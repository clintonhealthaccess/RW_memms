package org.chai.memms

import java.beans.PropertyEditorSupport

import org.codehaus.groovy.grails.web.binding.StructuredPropertyEditor;

public class YearMonthPropertyEditor extends PropertyEditorSupport implements StructuredPropertyEditor{
	
	Integer years
	Integer months
	
	public List getRequiredFields() {
		//TODO do we have any 
	  }
	 
	  public List getOptionalFields() {
		List optionalFields = new ArrayList();
		optionalFields.add("years");
		optionalFields.add("months");
		return optionalFields;
	  }
	 
	  public Object assemble(Class type, Map fieldValues) throws IllegalArgumentException {
		if (fieldValues.containsKey("years")) {
		  years = (Integer) fieldValues.get("years")
		}
		
		if (fieldValues.containsKey("months")) {
			months = (Integer) fieldValues.get("months")
		 }
		return (years * 12 ) + months
	  }
}
