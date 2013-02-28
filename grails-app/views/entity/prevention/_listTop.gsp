<span class="right">
	<shiro:hasPermission permission="prevention:create">
		<a href="${createLinkWithTargetURI(controller:'prevention', action:'create', params:['order.id': order?.id])}" class="next medium left push-r"> 
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search"  controller="prevention"/>
</span>