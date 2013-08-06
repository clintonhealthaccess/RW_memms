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

import java.util.StringTokenizer;
import java.util.regex.Matcher
import java.util.regex.Pattern
/**
 * @author Antoine Nzeyi, Donatien Masengesho, Pivot Access Ltd
 *
 */
class GeographicalValueItem {

    public static final String splitter = "(?<=\\]),(?=\\[)";

    String dataLocation
    Double value
    String unit
    String color
    double longitude
    double latitude

    def geoDataRow() {
        return [latitude,longitude,"\'"+dataLocation+"\'",value]
    }

    public GeographicalValueItem(IndicatorValue iv){
        this.value = iv.computedValue
        this.unit = iv.indicator.unit
        this.dataLocation = iv.locationReport.location.names
        if(iv.locationReport.location.coordinates != null){
            String raw = iv.locationReport.location.coordinates;
            int alpha = raw.indexOf("[");
            int omega = raw.lastIndexOf("]");
            if((alpha >=0)&& (omega >=0)){
                while(raw.charAt(alpha+1) == '[') {
                    alpha++;
                }
                while(raw.charAt(omega-1) == ']') {
                    omega--;
                }
                String[] tokens = raw.substring(alpha,omega+1).split(splitter);
                double longs = 0.0;
                double lats = 0.0;
                long counter = 0;
                for(String token: tokens) {
                    try {
                        String[] nums = token.substring(1,token.length()-1).split(",");
                        longs += Double.parseDouble(nums[0].trim());
                        lats += Double.parseDouble(nums[1].trim());
                        counter++;
                    } catch(Exception ex) {
                    }
                }
                this.longitude = longs/counter;
                this.latitude = lats/counter;
            }
        }
        Double red = iv.indicator.redToYellowThreshold
        Double green =  iv.indicator.yellowToGreenThreshold
        if(red < green) {
            if(this.value < red) {
                this.color = "red"
            } else if(this.value < green) {
                this.color = "yellow"
            } else {
                this.color = "green"
            }
        } else {
            if(this.value > red) {
                this.color = "red"
            } else if(this.value > green) {
                this.color = "yellow"
            } else {
                this.color = "green"
            }
        }
    }

    @Override
    public String toString() {
        return "GeographicalValueItem[dataLocation:" +this.dataLocation + "value:"+this.value+" Latitude :"+this.latitude+"Longitude"+this.longitude+ "]"
    }
}