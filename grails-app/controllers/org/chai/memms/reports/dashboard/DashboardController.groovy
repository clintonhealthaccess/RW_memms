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

import java.util.*
import org.chai.memms.security.User
import org.apache.shiro.SecurityUtils
import org.chai.memms.AbstractController
import org.chai.location.Location
import org.chai.memms.inventory.EquipmentType
import java.lang.reflect.*;

/**
 * @author Antoine Nzeyi, Donatien Masengesho, Pivot Access Ltd
 *
 */
class DashboardController extends AbstractController {

    def dashboardService
    def indicatorComputationService
 
    def indicators = {
      indicatorComputationService.computeCurrentReport()
      
        LocationReport report = getUserReport()
        
        List<IndicatorItem> indicatorItems = new ArrayList<IndicatorItem>()
        Map<String, CategoryItem> categoryItems = new LinkedHashMap<String, CategoryItem>()
        if(report != null) {
            for(IndicatorValue i : IndicatorValue.findAllByLocationReport(report)) {
                indicatorItems.add(new IndicatorItem(i))
            }
            for(IndicatorCategory category : IndicatorCategory.findAll([sort: "id", order: "asc"])){
                CategoryItem categoryItem = new CategoryItem(category, indicatorItems)
                if (categoryItem.indicatorItems.size() > 0){
                    categoryItems.put(category.code, categoryItem)
                }
            }
        }
        def model = [categoryItems: categoryItems]
        render(view:"/reports/reports", model:model << [
                template:"/reports/dashboard/dashboard"
            ])
    }
       
    def getUserReport() {
        MemmsReport memmsReport = dashboardService.getCurrentMemmsReport()
        if(memmsReport == null) {
            return null
        }
        User user =  User.findByUuid(SecurityUtils.subject.principal, [cache: true])
        if(user == null) {
            return null
        }
        return LocationReport.findByMemmsReportAndLocation(memmsReport, user.location)
    }
}