<span class="right">
	<shiro:hasPermission permission="preventiveOrder:create">
		<a href="${createLinkWithTargetURI(controller:'preventiveOrder', action:'create', params:['location.id': location?.id,'dataLocation.id': dataLocation?.id])}" class="next medium left push-r"> 
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search"  controller="preventiveOrderView"/>
</span>
	