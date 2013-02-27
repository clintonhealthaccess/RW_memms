<span class="right">
	<shiro:hasPermission permission="prevention:create">
		<a href="${createLinkWithTargetURI(controller:'prevention', action:'create', params:['order.id': order?.id])}"> 
			<g:message code="prevention.label"/>
		</a>
	</shiro:hasPermission>
	<g:searchBox action="search"  controller="prevention"/>
</span>
	