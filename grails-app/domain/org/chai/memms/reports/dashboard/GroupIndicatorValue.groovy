/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.chai.memms.reports.dashboard

/**
 *
 * @author donatien
 */
class GroupIndicatorValue {
    Date generatedAt
    String name
    Double value

    static belongsTo = [indicatorValue:IndicatorValue]

    static mapping ={
        table "memms_report_group_indicator_value"
        version false
        content type:"text"
    }
}