<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>

<ul class="onecol-checkboxes left">
	<li><g:checkBox name="correctiveOptions" value="location" /><span>${message(code:'listing.report.cerrective.location.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="code" /><span>${message(code:'listing.report.cerrective.code.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="serialNumber" /><span>${message(code:'listing.report.cerrective.serial.number.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="equipmentType" /><span>${message(code:'listing.report.cerrective.type.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="model" /><span>${message(code:'listing.report.cerrective.model.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="manufacturer" /><span>${message(code:'listing.report.cerrective.manufacturer.label')}</span></li>

	<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
		<li><g:checkBox name="correctiveOptions" value="statusChanges" /><span>${message(code:'listing.report.cerrective.status.changes.label')}</span></li>
	</g:if>
	<li><g:checkBox name="correctiveOptions" value="currentStatus" /><span>${message(code:'listing.report.cerrective.current.status.label')}</span></li>
</ul>

<ul class="onecol-checkboxes right">
	<li><g:checkBox name="correctiveOptions" value="travelTime" checked="false" /><span>${message(code:'listing.report.cerrective.travel.time.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="workTime" checked="false" /><span>${message(code:'listing.report.cerrective.work.time.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="estimatedCost" checked="false" /><span>${message(code:'listing.report.cerrective.estimated.cost.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="departmentRequestedOrder" checked="false" />
		<span>${message(code:'listing.report.cerrective.department.order.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="reasonsEquipmentFailure" checked="false" />
		<span>${message(code:'listing.report.cerrective.failure.reason.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="listPerformedActions" checked="false" />
		<span>${message(code:'listing.report.cerrective.performed.actions.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="description" checked="false" /><span>${message(code:'listing.report.cerrective.problem.description.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="dateOfEvent" checked="false" /><span>${message(code:'listing.report.cerrective.event.date.label')}</span></li>
</ul>