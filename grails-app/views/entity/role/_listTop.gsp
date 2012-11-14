<div class="entity-list">
	<div>
		<div class="heading1-bar">
			<span class="right">
				<shiro:hasPermission permission="role:create">
					<a href="${createLinkWithTargetURI(controller:'role', action:'create')}" class="next medium left push-r">
						<g:message code="default.new.label" args="[entityName]" />
					</a>
				</shiro:hasPermission>
				<g:searchBox action="search"  controller="role"/>
		    </span>
		</div>
	</div>
</div>
