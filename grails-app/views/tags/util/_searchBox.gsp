<div>
	<g:form class="search-form" url="[controller: controller, action: action]" method="GET">
		<g:each in="${hiddenParams}" var="entry">
			<input type="hidden" name="${entry.key}" value="${entry.value}"/>
		</g:each>
		<label for="q"><g:message code="entity.search.label" args="[entityName]"/>: </label>
		<input name="q" value="${params.q}"></input>
		<button type="submit" class="medium"><g:message code="default.button.search.label"/></button>
	</g:form>
</div>