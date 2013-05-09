<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>

<li><g:checkBox name="spartPartOptions" value="sparePartType" disabled="disabled"/><span>Spare Part Type</span></li>
<li><g:checkBox name="spartPartOptions" value="locationOfStock" disabled="disabled"/><span>Location of Stock</span></li>
<li><g:checkBox name="spartPartOptions" value="quantityInStock" disabled="disabled"/><span>Quantity in Stock</span></li>

<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
  <li><g:checkBox name="inventoryOptions" value="statusChanges" disabled="disabled"/><span>Status Changes</span></li>
</g:if>

<g:if test="${[ReportSubType.INVENTORY,ReportSubType.STATUSCHANGES].contains(reportSubType)}">
    <li><g:checkBox name="spartPartOptions" value="status" checked="false" /><span>Status</span></li>
    <li><g:checkBox name="spartPartOptions" value="model" checked="false" /><span>Model</span></li>
    <li><g:checkBox name="spartPartOptions" value="manufacturer" checked="false" /><span>Manufacturer</span></li>
    <li><g:checkBox name="spartPartOptions" value="equipmentCode" checked="false" /><span>Equipment Code Associated With Spare Part</span></li>
    <li><g:checkBox name="spartPartOptions" value="cost" checked="false" /><span>Cost</span></li>
    <li><g:checkBox name="spartPartOptions" value="warrantyPeriodRemaining" checked="false" /><span>Warranty Period Remaining</span></li>
    <li><g:checkBox name="spartPartOptions" value="discontinuedDate" checked="false" /><span>Discontinued Date</span></li>
</g:if>

<g:if test="${reportSubType == ReportSubType.STOCKOUT}">
    <li><g:checkBox name="spartPartOptions" value="forecastedStockOut" disabled="disabled"/><span>Forecasted Stock Out</span></li>
</g:if>

<g:if test="${reportSubType == ReportSubType.USERATE}">
    <li><g:checkBox name="spartPartOptions" value="useRate" disabled="disabled"/><span>Use Rate</span></li>
</g:if>