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
package org.chai.memms.reports.hmis

import groovy.transform.EqualsAndHashCode
import org.chai.location.DataLocation
import i18nfields.I18nFields
/**
 * @author Eric Dusabe, Jean Kahigiso M.
 *
 */
@i18nfields.I18nFields
@EqualsAndHashCode(includes="periodCode")
class HmisReport {

	String periodCode
	String names
	Date dateCreated
	Date lastUpdated

	static hasMany = [hmisFacilityReports: HmisFacilityReport]
	static i18nFields = ["names"]

	static constraints = {

		periodCode nullable: false, blank: false,  unique: true
		names nullable: true, blank: true
		lastUpdated nullable: true, validator: {
			if(it != null) return (it <= new Date())
		}
	}

	static mapping = {
		table "memms_hmis_report"
		version false 
	}

	@Override
	public String toString() {
		return "HmisReport [Id: " + id + " periodCode: " + periodCode +"]";
	}



}