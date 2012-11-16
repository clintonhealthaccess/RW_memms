<span class="right">
	<shiro:hasPermission permission="department:create">
		<a href="${createLinkWithTargetURI(controller:'department', action:'create')}" class="next medium left push-r">
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search"  controller="department"/>
</span>
