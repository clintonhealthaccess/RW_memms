<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>

<ul class="onecol-checkboxes left">
	<li><g:checkBox name="correctiveOptions" value="location" /><span>${message(code:'listing.report.corrective.location.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="code" /><span>${message(code:'listing.report.corrective.code.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="serialNumber" /><span>${message(code:'listing.report.corrective.serial.number.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="equipmentType" /><span>${message(code:'listing.report.corrective.type.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="model" /><span>${message(code:'listing.report.corrective.model.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="manufacturer" /><span>${message(code:'listing.report.corrective.manufacturer.label')}</span></li>

	<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
		<li><g:checkBox name="correctiveOptions" value="statusChanges" /><span>${message(code:'listing.report.corrective.status.changes.label')}</span></li>
	</g:if>
	<li><g:checkBox name="correctiveOptions" value="currentStatus" /><span>${message(code:'listing.report.corrective.current.status.label')}</span></li>
</ul>

<ul class="onecol-checkboxes right">
	<li><g:checkBox name="correctiveOptions" value="travelTime" checked="false" /><span>${message(code:'listing.report.corrective.travel.time.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="workTime" checked="false" /><span>${message(code:'listing.report.corrective.work.time.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="estimatedCost" checked="false" /><span>${message(code:'listing.report.corrective.estimated.cost.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="departmentRequestedOrder" checked="false" />
		<span>${message(code:'listing.report.corrective.department.order.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="reasonsEquipmentFailure" checked="false" />
		<span>${message(code:'listing.report.corrective.failure.reason.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="listPerformedActions" checked="false" />
		<span>${message(code:'listing.report.corrective.performed.actions.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="description" checked="false" /><span>${message(code:'listing.report.corrective.problem.description.label')}</span></li>
	<li><g:checkBox name="correctiveOptions" value="dateOfEvent" checked="false" /><span>${message(code:'listing.report.corrective.event.date.label')}</span></li>
</ul>