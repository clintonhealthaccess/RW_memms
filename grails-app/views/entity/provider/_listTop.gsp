<span class="right">
	<shiro:hasPermission permission="provider:create">
		<a href="${createLinkWithTargetURI(controller:'provider', action:'create')}" class="next medium left push-r">
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search"  controller="provider"/>
</span>
	