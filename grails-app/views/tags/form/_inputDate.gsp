<div class="row ${hasErrors(bean:bean,field:field,'errors')}">
	<label for="${name}">${label}</label>
	<input type="${type}" id="${id}" class="idle-field" name="${name}" value="${value}" ${active} ${readonly?'readonly="readonly"':''}></input>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}" /></div>
</div>