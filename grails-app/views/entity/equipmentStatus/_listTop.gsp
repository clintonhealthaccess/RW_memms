<span class="right">
	<shiro:hasPermission permission="equipmentStatus:create">
		<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'create',params:params)}" class="next medium left push-r"> 
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
</span>


