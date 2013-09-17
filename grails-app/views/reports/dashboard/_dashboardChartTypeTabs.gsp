<%@ page import="org.chai.memms.util.Utils.ReportChartType" %>
<ul class="v-tabs-nested-nav">
  <li><g:remoteLink data-chart-type="historic_trend" data-indicator-code="${indicatorItem.code}"
  			action="dashboardChart" update="historicTrend_${indicatorItem.code}" 
  			params="[valueId:indicatorItem.valueId, reportChartType:ReportChartType.HISTORIC]"
        onComplete="drawLineChart();hideSpinner('${indicatorItem.code}', 'historic_trend')">
  		<g:message code="reports.chartType.historic.trend"/></g:remoteLink></li>
  <li><g:remoteLink data-chart-type="comparison" data-indicator-code="${indicatorItem.code}"
  			action="dashboardChart" update="comparisonChart_${indicatorItem.code}" 
  			params="[valueId:indicatorItem.valueId, reportChartType:ReportChartType.COMPARISON]"
        onComplete="hideSpinner('${indicatorItem.code}', 'comparison')">
  		<g:message code="reports.chartType.comparison.facilities"/></g:remoteLink></li>
  <li><g:remoteLink data-chart-type="geo_trend" data-indicator-code="${indicatorItem.code}"
  			action="dashboardChart" update="geographicTrend_${indicatorItem.code}"
  			params="[valueId:indicatorItem.valueId, reportChartType:ReportChartType.GEOGRAPHIC]"
        onComplete="drawGeoChart();hideSpinner('${indicatorItem.code}', 'geo_trend')">
		<g:message code="reports.chartType.geographic.trend"/></g:remoteLink></li>
  <li><g:remoteLink data-chart-type="info_by" data-indicator-code="${indicatorItem.code}"
  			action="dashboardChart" update="infoByChart_${indicatorItem.code}" 
  			params="[valueId:indicatorItem.valueId, reportChartType:ReportChartType.INFOBY]"
        onComplete="hideSpinner('${indicatorItem.code}', 'info_by')">
  		<g:message code="reports.chartType.info.by" args="${[indicatorItem.groupNames]}"/></g:remoteLink></li>
</ul>