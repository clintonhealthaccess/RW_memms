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

import org.chai.memms.reports.dashboard.LocationReport;
import org.chai.memms.reports.dashboard.Indicator;
import org.chai.memms.reports.dashboard.IndicatorValue;
import org.chai.memms.reports.dashboard.MemmsReport;
import org.chai.memms.reports.dashboard.GroupIndicatorValue;
import org.chai.location.CalculationLocation
import org.chai.location.DataLocationType
import org.chai.location.DataLocation
import org.chai.location.Location
import org.chai.location.LocationLevel
import java.util.*
import org.chai.memms.security.User
import org.apache.shiro.SecurityUtils
import java.util.Collections;
import org.joda.time.format.DateTimeFormat
/**
 * @author Antoine Nzeyi, Donatien Masengesho, Pivot Access Ltd
 *
 */
class IndicatorItem {

    IndicatorComputationService indicatorComputationService
    String locationNames
    String categoryCode
    Date computedAt
    String code
    String names
    String groupNames
    String formulas
    Double value
    String unit
    String color

    List<HistoricalValueItem> historicalValueItems
    List<ComparisonValueItem> highestComparisonValueItems
    List<ComparisonValueItem> higherComparisonValueItems
    List<ComparisonValueItem> lowerComparisonValueItems
    List<ComparisonValueItem> lowestComparisonValueItems
    List<GeographicalValueItem> geographicalValueItems
    Map<String, Double> valuesPerGroup
    Integer totalHistoryItems

    public IndicatorItem(IndicatorValue indicatorValue) {
        this.categoryCode = indicatorValue.indicator.category.code
        this.computedAt = indicatorValue.computedAt
        this.code = indicatorValue.indicator.code
        this.names = indicatorValue.indicator.names
        this.formulas = indicatorValue.indicator.formulas
        this.value = indicatorValue.computedValue
        this.unit = indicatorValue.indicator.unit
        this.groupNames = indicatorValue.indicator.groupNames
        this.totalHistoryItems = indicatorValue.indicator.historyItems
        this.locationNames = indicatorValue.locationReport.location.names

        Double red = indicatorValue.indicator.redToYellowThreshold
        Double green =  indicatorValue.indicator.yellowToGreenThreshold
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

        this.historicalValueItems = new ArrayList<HistoricalValueItem>()
        this.geographicalValueItems = new ArrayList<GeographicalValueItem>()
        this.highestComparisonValueItems= new ArrayList<ComparisonValueItem>()
        this.higherComparisonValueItems= new ArrayList<ComparisonValueItem>()
        this.lowerComparisonValueItems= new ArrayList<ComparisonValueItem>()
        this.lowestComparisonValueItems= new ArrayList<ComparisonValueItem>()
        this.valuesPerGroup=new HashMap<String,Double>()

        // add historical values
        // TODO is this necessary?
        // def historicalValueItem = new HistoricalValueItem(indicatorValue)
        // this.historicalValueItems.add(historicalValueItem)
        this.historicalValueItems.addAll(getHistoricValueItems(indicatorValue))
        if (log.isDebugEnabled()) log.debug("getHistoricalValueItems historical value items=" + historicalValueItems + ", size="+historicalValueItems.size());

        // add geographical values
        this.geographicalValueItems.add(new GeographicalValueItem(indicatorValue))
        for(IndicatorValue indV : getGeographicalValueItems(indicatorValue)) {
            this.geographicalValueItems.add(new GeographicalValueItem(indV))
        }
        if (log.isDebugEnabled()) log.debug("getGeographicalValueItems geographical value items=" + geographicalValueItems + ", size="+geographicalValueItems.size());

        // add comparison value items
        if(indicatorValue.locationReport.location instanceof Location){
            if(indicatorValue.locationReport.location.parent != null){
                getComparisonValueItems(indicatorValue)
            }
        }
        else{
            getComparisonValueItems(indicatorValue)
        }


        // add group value items
        for(GroupIndicatorValue grV:indicatorValue.groupIndicatorValues){
            this.valuesPerGroup.put(grV.names,grV.value)
        }
    }
    /**
     *Get historical values for this indicators = for different location reports
     */
    public def getHistoricValueItems(IndicatorValue indicatorValue) {
        def historicalValueItems = new ArrayList<HistoricalValueItem>();

        if(indicatorValue != null) {
            if (log.isDebugEnabled()) log.debug("getHistoricalValueItems period " + indicatorValue.indicator.historicalPeriod);

            def locationReports = null
            if(indicatorValue.indicator.historicalPeriod.equals(Indicator.HistoricalPeriod.MONTHLY)){
                locationReports = LocationReport.findAll("from LocationReport as locationReport  where locationReport.location.id='"+indicatorValue.locationReport.location.id+"' order by locationReport.eventDate desc limit "+indicatorValue.indicator.historyItems+"")
            } 
            else if(indicatorValue.indicator.historicalPeriod.equals(Indicator.HistoricalPeriod.QUARTERLY)){
                locationReports = LocationReport.findAll("from LocationReport as locationReport where month(locationReport.eventDate) in (3,6,9,12) and locationReport.location.id='"+indicatorValue.locationReport.location.id+"' order by locationReport.eventDate desc limit "+indicatorValue.indicator.historyItems+"")
            } 
            else if(indicatorValue.indicator.historicalPeriod.equals(Indicator.HistoricalPeriod.YEARLY)) {
                locationReports = LocationReport.findAll("from LocationReport as locationReport where month(locationReport.eventDate) = 12 and locationReport.location.id='"+indicatorValue.locationReport.location.id+"' order by locationReport.eventDate desc limit "+indicatorValue.indicator.historyItems+"")
            }

            def indicatorValues = IndicatorValue.findAllByLocationReportInListAndIndicator(locationReports,indicatorValue.indicator)
            indicatorValues.each{ indV ->
                def historicalValueItem = new HistoricalValueItem(indV)
                historicalValueItems.add(historicalValueItem)
            }
            if (log.isDebugEnabled()) log.debug("getHistoricalValueItems historical value items =" + historicalValueItems + ", size="+historicalValueItems.size());
            return historicalValueItems
        }
        return null
    }

    public void getComparisonValueItems(IndicatorValue currentValue){
        List<IndicatorValue> invVs=new ArrayList<IndicatorValue>()
        if(currentValue!=null) {
            def  simmilarFacilitiesOrLocations=getSimmilarFacilitiesOrlocations(currentValue)
            def locationReports=LocationReport.findAllByMemmsReportAndLocationInList(currentValue.locationReport.memmsReport,simmilarFacilitiesOrLocations)
            invVs.addAll(IndicatorValue.findAllByLocationReportInListAndIndicator(locationReports,currentValue.indicator))
            sortComparisonValues(currentValue,invVs)
        }
    }

    public void sortComparisonValues(IndicatorValue currentValue,List<IndicatorValue> invVs){
        Collections.sort(invVs);
        if(invVs.size() > 0) {
            List<IndicatorValue> listOfHighest=new ArrayList<IndicatorValue>()
            List<IndicatorValue> listOfLowest=new ArrayList<IndicatorValue>()
            Double red = currentValue.indicator.redToYellowThreshold
            Double green =  currentValue.indicator.yellowToGreenThreshold
            if(red < green) {
                for(IndicatorValue indicatorValue:invVs){
                    if(indicatorValue.computedValue <= currentValue.computedValue && indicatorValue.id!=currentValue.id){
                        listOfLowest.add(indicatorValue)
                    } else if(indicatorValue.computedValue >= currentValue.computedValue && indicatorValue.id!=currentValue.id){
                        listOfHighest.add(indicatorValue)
                    } else if(indicatorValue.id == currentValue.id) {
                        for(def i = 1 ; i <= 2 ; i++) {
                            if(invVs.indexOf(indicatorValue)+i<invVs.size())
                            this.higherComparisonValueItems.add(new ComparisonValueItem(invVs.get(invVs.indexOf(indicatorValue)+i)))
                            if(invVs.indexOf(indicatorValue)-i>=0)
                            this.lowerComparisonValueItems.add(new ComparisonValueItem(invVs.get(invVs.indexOf(indicatorValue)-i)))
                        }
                    }
                }
            } else {
                for(IndicatorValue indicatorValue:invVs){
                    if(indicatorValue.computedValue<=currentValue.computedValue  && indicatorValue.id!=currentValue.id){
                        listOfHighest.add(indicatorValue)
                    }else if(indicatorValue.computedValue >= currentValue.computedValue  && indicatorValue.id!=currentValue.id){
                        listOfLowest.add(indicatorValue)
                    }else if(indicatorValue.id == currentValue.id){
                        for(def i=1 ; i<=2 ; i++){
                            if(invVs.indexOf(indicatorValue)+i<invVs.size())
                            this.lowerComparisonValueItems.add(new ComparisonValueItem(invVs.get(invVs.indexOf(indicatorValue)+i)))
                            if(invVs.indexOf(indicatorValue)-i>=0)
                            this.higherComparisonValueItems.add(new ComparisonValueItem(invVs.get(invVs.indexOf(indicatorValue)-i)))
                        }
                    }
                }
            }
            def lowestCounter=0
            def highestcounter=0
            for(IndicatorValue indicatorValue: listOfLowest.reverse()){
                if(lowestCounter < 3)
                this.lowestComparisonValueItems.add(new ComparisonValueItem(indicatorValue))
                lowestCounter++
            }
            for(IndicatorValue indicatorValue:listOfHighest.reverse()){
                if(highestcounter < 3)
                this.highestComparisonValueItems.add(new ComparisonValueItem(indicatorValue))
                highestcounter++
            }
        }
        this.higherComparisonValueItems.reverse()
    }

    /**
     *Gets geographical values for the current report
     **/
    public def getGeographicalValueItems(IndicatorValue indicatorValue){
        if(indicatorValue!=null){
            def geographicalLocations = getDataLocationsInLocation(indicatorValue.locationReport.location)
            def memmsReport = indicatorValue.locationReport.memmsReport
            if (log.isDebugEnabled()) log.debug("getGeographicalValueItems memmsReport=" + memmsReport);
            if (log.isDebugEnabled()) log.debug("getGeographicalValueItems locationReports=" + LocationReport.list());
            def locationReports=LocationReport.findAllByMemmsReportAndLocationInList(memmsReport,geographicalLocations)
            if (log.isDebugEnabled()) log.debug("getGeographicalValueItems location report items=" + locationReports + ", size="+locationReports.size());
            def indicatorValues = IndicatorValue.findAllByLocationReportInListAndIndicator(locationReports,indicatorValue.indicator)
            if (log.isDebugEnabled()) log.debug("getGeographicalValueItems indicator value items=" + indicatorValues + ", size="+indicatorValues.size());
            return indicatorValues
        }
        return null
    }

    /**
     *Gets  facilities or locations similar to the curent facility/location from this indicator value
     */
    public def getSimmilarFacilitiesOrlocations(IndicatorValue indicatorValue){
        def  simmilarFacilitiesOrLocations=null
        def locationIds=getLocationsIdWithUsers()
        if(indicatorValue!=null){
            if(indicatorValue.locationReport.location instanceof DataLocation){
                simmilarFacilitiesOrLocations=DataLocation.findAllByTypeAndIdInList(indicatorValue.locationReport.location.type,locationIds)
            } else if(indicatorValue.locationReport.location instanceof Location){
                simmilarFacilitiesOrLocations=Location.findAllByLevelAndIdInList(indicatorValue.locationReport.location.level,locationIds)
            }
        }
        return simmilarFacilitiesOrLocations
    }

    public def getDataLocationsInLocation(CalculationLocation location) {
        List<DataLocation> dataLocations = new ArrayList<DataLocation>()
        if (location instanceof Location) {
            // dataLocations = location.getDataLocations(LocationLevel.findAll(), null)
            // dataLocations.addAll(location.getChildren(null))
            dataLocations = location.collectDataLocations(null)
        } else if (location instanceof DataLocation) {
            dataLocations.add(location)
            dataLocations.addAll(location.manages)
        }
        if (log.isDebugEnabled()) log.debug("getDataLocationsInLocation data location items=" + dataLocations + ", size="+dataLocations.size());
        return dataLocations;
    }

    public def getLocationsIdWithUsers(){
        List<Long> locations=new ArrayList<Long>();
        for(User user:User.findAll()){
            if(user.location!=null)
            locations.add(user.location.id)
        }
        return locations
    }

    public def historicalTrendVAxisFormat() {
        if(unit == '%') {
            return '0%';
        }
        return '###,##0 ' + unit;
    }

    public def historicalTrendMaxValue() {
        if(unit == '%') {
            return ', maxValue: 1';
        }
        return '';
    }

    public def historicalTrendLineCount() {
        if(unit == '%') {
            return ', count: 5';
        }
        return ', count: -1';
    }

    public def historicalTrendData() {
        def ret = []
        def i = 0
        def fmt = DateTimeFormat.forPattern("MMM d, y")
        for(HistoricalValueItem h: historicalValueItems) {
            ret[i] = ["\""+fmt.print(h.computedAt.time)+"\"",h.value]
            i++
        }
        if(i == 0) {
            ret = null
        }
        return ret
    }

    public geoData() {
        def ret = [["\'Latitude\'", "\'Longitude\'", "\'Location\'", "\'"+names+"\'"]]
        def i = 1
        for(GeographicalValueItem geo: geographicalValueItems) {
            if((geo.latitude != null) && (geo.longitude != null) &&(geo.latitude != 0.0) && (geo.longitude != 0.0)) {
                ret[i] = geo.geoDataRow()
                i++;
            }
        }
        return ret
    }

    public geoValues() {
        Map<Double,String> map = new TreeMap<Double,String>()
        for(GeographicalValueItem geo: geographicalValueItems) {
            map.put(geo.value,geo.color)
        }
        def ret = []
        int i = 0
        for(Double val:map.keySet()) {
            ret[i++] = val
        }
        return ret
    }

    public geoColors() {
        Map<Double,String> map = new TreeMap<Double,String>()
        for(GeographicalValueItem geo: geographicalValueItems) {
            map.put(geo.value,geo.color)
        }
        def ret = []
        int i = 0
        for(Double val:map.keySet()) {
            ret[i++] = "\'"+map.get(val)+"\'"
        }
        return ret
    }

    public def geoChartValueFormat() {
        if (log.isDebugEnabled()) log.debug("geoChartValueFormat unit=" + unit);
        if(unit == '%') {
            return '0%';
        }
        return '###,##0 ' + unit;
    }
}