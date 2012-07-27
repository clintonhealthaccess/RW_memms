<g:if test="${value == null}">
	<a href="#" class="tooltip" title="${message(code: 'planning.budget.error.tooltip')}" onclick="return false;">?</a>
</g:if>
<g:else>
	<g:value value="${value}" type="${type}" format="#,###" zero="-"/>
</g:else>