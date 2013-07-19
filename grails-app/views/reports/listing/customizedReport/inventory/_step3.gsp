<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>

<ul class="onecol-checkboxes left">
	<li><g:checkBox name="inventoryOptions" value="obsolete" checked="false" /><span>${message(code:'listing.report.equipment.obsolete.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="supplier" checked="false" /><span>${message(code:'listing.report.equipment.supplier.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="purchaser" checked="false" /><span>${message(code:'listing.report.equipment.purchaser.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="acquisitionDate" checked="false" /><span>${message(code:'listing.report.equipment.aquisition.date.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="cost" checked="false" /><span>${message(code:'listing.report.equipment.aquisition.cost.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="warrantyProvider" checked="false" /><span>${message(code:'listing.report.equipment.warranty.provider.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="warrantyPeriodRemaining" checked="false" />
		<span>${message(code:'listing.report.equipment.warranty.period.remaining.label')}</span></li>
</ul>

<ul class="onecol-checkboxes right">
	<li><g:checkBox name="inventoryOptions" value="location" /><span>${message(code:'listing.report.equipment.location.label')} </span></li>
	<li><g:checkBox name="inventoryOptions" value="code" /><span>${message(code:'listing.report.equipment.code.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="serialNumber" /><span>${message(code:'listing.report.equipment.serial.number.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="equipmentType" /><span>${message(code:'listing.report.equipment.type.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="model" /><span>${message(code:'listing.report.equipment.model.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="manufacturer" /><span>${message(code:'listing.report.equipment.manufacturer.label')}</span></li>
	<li><g:checkBox name="inventoryOptions" value="currentStatus" /><span>${message(code:'listing.report.equipment.current.status.label')}</span></li>

	<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
		<li><g:checkBox name="inventoryOptions" value="statusChanges" /><span>${message(code:'listing.report.equipment.status.changes.label')}</span></li>
	</g:if>
	
	<li><g:checkBox name="inventoryOptions" value="currentValue" /><span>${message(code:'listing.report.equipment.current.value.label')}</span></li>
</ul>