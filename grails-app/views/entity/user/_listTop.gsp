<div class="entity-list">
	<div>
		<div class="heading1-bar">
			<span class="right">
				<shiro:hasPermission permission="user:create">
					<a href="${createLinkWithTargetURI(controller:'user', action:'create',params:params)}" class="next medium left push-r"> 
						<g:message code="default.new.label" args="[entityName]" />
					</a>
				</shiro:hasPermission>
				<g:searchBox action="search"  controller="user"/>
			</span>
		</div>
	</div>
</div>