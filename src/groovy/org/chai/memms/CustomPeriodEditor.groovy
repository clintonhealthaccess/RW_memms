/** 
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.grails.web.binding.StructuredPropertyEditor;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;


/**
 * @author Jean Kahigiso M.
 *
 */
class CustomPeriodEditor extends PropertyEditorSupport implements StructuredPropertyEditor{
	
	@Override
	public List getOptionalFields() {
		List optionalFilds =  new ArrayList();
		optionalFilds.add("months")
		optionalFilds.add("years")
		return optionalFilds;
	}

	@Override
	public List getRequiredFields() {
		return new ArrayList();
	}
	
	@Override
	public Object assemble(Class type, Map fieldValues) throws IllegalArgumentException {
		Integer months
		Integer years
		
		if(log.isDebugEnabled()) log.debug("period fieldValues to be bind "+fieldValues)
		
		String monthField = (String) fieldValues.get("months");
		String yearField =  (String) fieldValues.get("years");
		
		if(monthField && !monthField.isEmpty()){
			try{ 
				months = Integer.parseInt(monthField) 
				} catch (Exception nfe){
				if(log.isDebugEnabled()) log.debug("exception while parsing integer: period:months",nfe)
				throw new IllegalArgumentException("Months has to be a number",nfe)
			}
		}
		
		if(yearField && !yearField.isEmpty()){
			try{
				years = Integer.parseInt(yearField)
			} catch (Exception nfe){
				if(log.isDebugEnabled()) log.debug("exception while parsing integer: period:years",nfe)
				throw new IllegalArgumentException("Years has to be a number",nfe)
			}
		}
		
		if(years==null && months==null ) {
			return null
		}
		if(log.isDebugEnabled()) log.debug("Years and Months before creating period Years: "+years+" Months: "+months)
		Period period = new Period(years,months);
		if(log.isDebugEnabled()) log.debug("Created period: "+period)
		return period
	}
}
