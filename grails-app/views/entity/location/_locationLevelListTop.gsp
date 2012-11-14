<div class="entity-list">
	<div>
		<div class="heading1-bar">
			<span class="right">
				<shiro:hasPermission permission="locationLevel:create">
					<a href="${createLinkWithTargetURI(controller:'locationLevel', action:'create')}" class="next medium left push-r">
						<g:message code="default.new.label"  args="[entityName]"  />
					</a>
				</shiro:hasPermission>
		    </span>
		</div>
	</div>
</div>