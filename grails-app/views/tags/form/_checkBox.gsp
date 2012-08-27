<div class="row ${hasErrors(bean:bean,field:field,'errors')}">
	<label for="${name}">${label}</label>
	<g:checkBox name="${name}" value="${value}" checked="${(checked)? true:false}" />
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}" /></div>
</div>