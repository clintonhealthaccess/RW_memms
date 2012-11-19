<div class="entity-list">
	<div>
		<div class="heading1-bar">
			<span class="right">
				<shiro:hasPermission permission="dataLocationType:create">
					<a href="${createLinkWithTargetURI(controller:'dataLocationType', action:'create')}" class="next medium left push-r">
						<g:message code="default.new.label"  args="[entityName]"  />
					</a>
				</shiro:hasPermission>
		    </span>
		</div>
	</div>
</div>