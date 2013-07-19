<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>

<ul class="onecol-checkboxes left">
	<g:if test="${[ReportSubType.INVENTORY,ReportSubType.STATUSCHANGES].contains(reportSubType)}">
		<li><g:checkBox name="spartPartsOptions" value="model" checked="false" /><span>${message(code:'listing.report.spare.part.model.label')}</span></li>
		<li><g:checkBox name="spartPartsOptions" value="manufacturer" checked="false" /><span>${message(code:'listing.report.spare.part.manufacturer.label')}</span></li>
		<li><g:checkBox name="spartPartsOptions" value="cost" checked="false" /><span>${message(code:'listing.report.spare.part.cost.label')}</span></li>
		<li><g:checkBox name="spartPartsOptions" value="discontinuedDate" checked="false" /><span>${message(code:'listing.report.spare.part.discontinued.date.label')}</span></li>
	</g:if>
</ul>

<ul class="onecol-checkboxes right">
	<li><g:checkBox name="spartPartsOptions" value="sparePartType" /><span>${message(code:'listing.report.spare.part.type.label')}</span></li>
	<li><g:checkBox name="spartPartsOptions" value="locationOfStock" /><span>${message(code:'listing.report.spare.part.stock.location.label')}</span></li>
	<li><g:checkBox name="spartPartsOptions" value="quantityInStock" /><span>${message(code:'listing.report.spare.part.quantity.in.stock.label')}</span></li>
	<li><g:checkBox name="spartPartsOptions" value="currentStatus" /><span>${message(code:'listing.report.spare.part.status.label')}</span></li>
	<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
	  <li><g:checkBox name="sparePartsOptions" value="statusChanges" /><span>${message(code:'listing.report.spare.part.status.changes.label')}</span></li>
	</g:if>
	<g:if test="${reportSubType == ReportSubType.STOCKOUT}">
		<li><g:checkBox name="spartPartsOptions" value="forecastedStockOut" /><span>${message(code:'listing.report.spare.part.forecasted.stock.out.label')}</span></li>
	</g:if>

	<g:if test="${reportSubType == ReportSubType.USERATE}">
	    <li><g:checkBox name="spartPartsOptions" value="useRate" /><span>${message(code:'listing.report.spare.part.use.rate.label')}</span></li>
	</g:if>
</ul>
