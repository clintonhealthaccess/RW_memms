<div class="row ${hasErrors(bean:bean,field:field,'errors')}">
	<label for="${name}">${label}:</label>
	<input type="${type}" class="idle-field ${dateClass}" name="${name}" value="${fieldValue(bean:bean,field:field)}" ${active} ${readonly?'readonly="readonly"':''}></input>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}" /></div>
</div>