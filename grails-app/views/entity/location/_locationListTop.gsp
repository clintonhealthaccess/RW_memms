<span class="right">
	<shiro:hasPermission permission="location:create">
		<a href="${createLinkWithTargetURI(controller:'location', action:'create')}" class="next medium left push-r">
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search" controller="location"/>
</span>
		