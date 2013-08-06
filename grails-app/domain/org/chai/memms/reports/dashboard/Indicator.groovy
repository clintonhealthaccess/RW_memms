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
package org.chai.memms.reports.dashboard
import groovy.transform.EqualsAndHashCode
import i18nfields.I18nFields
/**
 * @author Antoine Nzeyi, Donatien Masengesho, Pivot Access Ltd
 *
 */
@I18nFields
@EqualsAndHashCode(includes="code")
class Indicator {

    enum HistoricalPeriod {

        MONTHLY("monthly"),
        QUARTERLY("quarterly"),
        YEARLY("yearly")

        String messageCode = "locationReport.historicalPeriod"

        final String name
        HistoricalPeriod(String name) {
            this.name = name
        }
        String getKey() {
            return name()
        }
    }

    String code
    String name
    String description
    String formula
    String unit

    Double redToYellowThreshold
    Double yellowToGreenThreshold

    HistoricalPeriod historicalPeriod
    Integer historyItems

    String queryScript
    Boolean sqlQuery
    Boolean active
    String groupName
    String groupQueryScript

    static i18nFields = [
		"name",
		"description",
		"formula","groupName"
    ]
    static belongsTo = [category:IndicatorCategory]
    static hasMany = [values:IndicatorValue]

    static mapping = {
        table "memms_report_indicator"
        version false
        content type:"text"
        cache true
    }

    static constraints = {
        code (blank:false, nullable:false, unique:true)
        name (blank:false, nullable:false, size:3..250)
        description (blank:true, nullable:true, size:0..1000)
        formula (blank:false, nullable:false, size:0..1000)
        unit (blank:false, nullable:false)
        redToYellowThreshold (blank:false, nullable:false)
        yellowToGreenThreshold (blank:false, nullable:false)
        queryScript (blank:false, nullable:false,size:0..8000)
        sqlQuery (blank:false, nullable:false)
        active (blank:false, nullable:false)
        category (nullable: false)
        groupName (blank:false, nullable:true, unique:false)
        groupQueryScript (blank:false, nullable:true, size:0..8000)
    }

    @Override
    public String toString() {
        return "Indicator[#id = " + id + ", code = " + code + "]"
    }
}
