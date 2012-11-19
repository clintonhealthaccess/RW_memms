<span class="right">
	<shiro:hasPermission permission="dataLocation:create">
		<a href="${createLinkWithTargetURI(controller:'dataLocation', action:'create')}" class="next medium left push-r">
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search" controller="dataLocation"/>
</span>
	