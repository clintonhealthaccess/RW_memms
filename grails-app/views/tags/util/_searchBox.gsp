<g:form class="search-form" url="[controller: controller, action: action]" method="GET">
	<input type="text" name="q" value="${params.q}"/>
	<input type="hidden" name="dataLocation" value="${dataLocation?.id}"/>
	<input type="hidden" name="equipment" value="${equipment?.id}"/>
	<button type="submit" class="medium"><g:message code="default.button.search.label"/></button>
</g:form>