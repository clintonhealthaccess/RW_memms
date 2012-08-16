<div class="row ${hasErrors(bean:bean,field:field,'errors')}">
	<label>${label}</label>
	<g:datePicker name="${name}" value="${value}"  precision="${precision}"   years="${Calendar.getInstance().get(Calendar.YEAR)+10..1980}"/>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}" /></div>
</div>