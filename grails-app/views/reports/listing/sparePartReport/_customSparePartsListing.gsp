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
			<g:if test="${reportTypeOptions.contains('quantityInStock')}">
				<th>Quantity in Stock</th>
			</g:if>

			%{-- <g:if test="${reportSubType == ReportSubType.STATUSCHANGES && reportTypeOptions.contains('statusChanges')}">
				// TODO SL AFTER RELEASE
			</g:if> --}%
			
			<g:if test="${reportTypeOptions.contains('status')}">
				<th>Status</th>
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
		<g:each in="${entities}" status="i" var="sparePart">
			<tr >
				<g:if test="${reportTypeOptions.contains('sparePartType')}">
					<td>${sparePart.type.names}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('locationOfStock')}">
					<td>
					<g:if test="${sparePart.dataLocation != null}">
					<g:message code="datalocation.label"/>: ${sparePart.dataLocation?.names}<br/>
					</g:if>
					<g:else>
					${sparePart.stockLocation?.name.toUpperCase()}
					</g:else>
					<g:if test="${!sparePart.room.equals("") && sparePart.room!=null}">
						<g:message code="spare.part.room.label"/>: ${sparePart.room}<br/>
					</g:if>
					<g:if test="${!sparePart.shelf.equals("") && sparePart.shelf!=null}">
						<g:message code="spare.part.shelf.label"/>: ${sparePart.shelf}<br/>
					</g:if>
					</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('quantityInStock')}">
					<td>${sparePart.inStockQuantity}</td>
				</g:if>

				%{-- <g:if test="${reportSubType == ReportSubType.STATUSCHANGES && reportTypeOptions.contains('statusChanges')}">
					// TODO SL AFTER RELEASE
				</g:if> --}%

				<g:if test="${reportTypeOptions.contains('status')}">
					<td>${message(code: sparePart.status?.messageCode+'.'+sparePart.status?.name)}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('manufacturer')}">
					<td>${sparePart.type.manufacturer?.contact?.contactName}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('warrantyPeriodRemaining')}">
					<td>%{-- TODO --}%</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('discontinuedDate')}">
					<td>${Utils.formatDate(sparePart.type.discontinuedDate)}</td>
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