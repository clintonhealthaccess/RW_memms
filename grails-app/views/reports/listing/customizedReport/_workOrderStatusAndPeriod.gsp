<li>
	<label for="workOrderStatus"><g:message code="reports.workOrderStatus"/>:</label>
	<ul class="checkbox-list">
		<g:each in="${statusValues}" var="statusEnum">
			<li>
			    <input name="workOrderStatus" type="checkbox" value="${statusEnum.key}"/>
			    <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
			</li>
		</g:each>
	</ul>
</li>
<li>
	<label for="workOrderPeriod"><g:message code="reports.workOrderPeriod"/>:</label>
	<input name="fromWorkOrderPeriod" class="js-date-picker date-picker idle-field" />
	<span class="dash">-</span>
	<input name="toWorkOrderPeriod" class="js-date-picker date-picker idle-field" />
</li>