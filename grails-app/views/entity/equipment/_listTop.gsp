<div class="entity-list">
	<div>
		<div class="heading1-bar">
			<span class="right">
				<shiro:hasPermission permission="equipment:create">
					<a href="${createLinkWithTargetURI(controller:'equipment', action:'create', params:['location.id': location?.id,'dataLocation.id': dataLocation?.id])}" class="next medium left push-r"> 
						<g:message code="default.new.label" args="[entityName]" />
					</a>
				</shiro:hasPermission>
				<a href="${createLinkWithTargetURI(controller: 'equipmentView', action:'export', params:['location.id': location?.id,'dataLocation.id': dataLocation?.id])}" class="next medium gray left export push-r">
					<g:message code="default.export.label" />
				</a>
				<g:searchBox action="search"  controller="equipmentView"/>
			</span>
		</div>
		<g:render template="${filterTemplate}" />
	</div>
</div>