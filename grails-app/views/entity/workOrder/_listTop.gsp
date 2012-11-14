<div class="entity-list">
	<div>
		<div class="heading1-bar">
			<span class="right">
				<shiro:hasPermission permission="workOrder:create">
					<a href="${createLinkWithTargetURI(controller:'equipment', action:'create', params:['location.id': location?.id,'dataLocation.id': dataLocation?.id])}" class="next medium left push-r"> 
						<g:message code="default.new.label" args="[entityName]" />
					</a>
				</shiro:hasPermission>
				<g:searchBox action="search"  controller="workOrderView"/>
			</span>
		</div>
		<g:render template="${filterTemplate}" />
	</div>
</div>