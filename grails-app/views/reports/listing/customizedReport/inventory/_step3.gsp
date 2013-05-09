<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>

<li><g:checkBox name="inventoryOptions" value="serialNumber" /><span>Serial Number</span></li>
<li><g:checkBox name="inventoryOptions" value="equipmentType" /><span>Equipment Type</span></li>

<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
	<li><g:checkBox name="inventoryOptions" value="statusChanges" /><span>Status Changes</span></li>
</g:if>

<li><g:checkBox name="inventoryOptions" value="manufacturerName" checked="false" /><span>Manufacturer Name</span></li>
<li><g:checkBox name="inventoryOptions" value="model" checked="false" /><span>Model</span></li>
<li><g:checkBox name="inventoryOptions" value="supplierName" checked="false" /><span>Supplier Name</span></li>
<li><g:checkBox name="inventoryOptions" value="location" checked="false" /><span>Location</span></li>
<li><g:checkBox name="inventoryOptions" value="acquisitionDate" checked="false" /><span>Acquisition Date</span></li>
<li><g:checkBox name="inventoryOptions" value="cost" checked="false" /><span>Acquisition Cost</span></li>
<li><g:checkBox name="inventoryOptions" value="status" checked="false" /><span>Status</span></li>
<li><g:checkBox name="inventoryOptions" value="obsolete" checked="false" /><span>Obsolete</span></li>
<li><g:checkBox name="inventoryOptions" value="warrantyProviderName" checked="false" /><span>Warranty Provider Name</span></li>
<li><g:checkBox name="inventoryOptions" value="warrantyPeriodRemaining" checked="false" /><span>Warranty Period Remaining</span></li>
<li><g:checkBox name="inventoryOptions" value="currentValue" checked="false" /><span>Current Value</span></li>