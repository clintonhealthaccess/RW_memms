<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>
<table class="items">
	<thead>
		<tr>
			<g:if test="${reportTypeOptions.contains('sparePartType')}">
				<th>Spare Part Type</th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('locationOfStock')}">
				<th>Location of Stock</th>
			</g:if>
			
			<g:if test="${reportTypeOptions.contains('manufacturer')}">
				<g:sortableColumn property="manufacturer"  title="${message(code: 'provider.type.manufacturer')}" params="[q:q]" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('warrantyPeriodRemaining')}">
				<th>Warranty Period Remaining</th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('discontinuedDate')}">
				<th>Discontinued Date</th>
			</g:if>
			<g:if test="${reportSubType == ReportSubType.STOCKOUT && reportTypeOptions.contains('forecastedStockOut')}">
			    <th>Forecasted Stock Out</th>
			</g:if>
			<g:if test="${reportSubType == ReportSubType.USERATE && reportTypeOptions.contains('useRate')}">
			    <th>Use Rate</th>
			</g:if>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="sparePartType">
			<tr >
				
				<g:if test="${reportSubType == ReportSubType.STATUSCHANGES && reportTypeOptions.contains('statusChanges')}">
					%{-- TODO SL --}%
				</g:if>
				<g:if test="${reportTypeOptions.contains('manufacturer')}">
					<td>${sparePartType?.manufacturer?.contact?.contactName}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('warrantyPeriodRemaining')}">
					<td>%{-- TODO --}%</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('discontinuedDate')}">
					<td>${sparePartType?.discontinuedDate}</td>
				</g:if>
				<g:if test="${reportSubType == ReportSubType.STOCKOUT && reportTypeOptions.contains('forecastedStockOut')}">
				    <td>%{-- TODO --}%</td>
				</g:if>
				<g:if test="${reportSubType == ReportSubType.USERATE && reportTypeOptions.contains('useRate')}">
				    <td>%{-- TODO --}%</td>
				</g:if>
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" />