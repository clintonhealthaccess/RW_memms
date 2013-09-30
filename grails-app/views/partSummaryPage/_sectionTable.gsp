<table class="items spaced">
	<thead>
		<th><g:message code="datalocation.label" /></th>
		<th><g:message code="part.sparePart.count" /></th>
		<th></th>
		<th></th>
	</thead>
	<tbody>
		<g:if test="${parts != null && !parts.empty}">
			<g:each in="${parts}" var="part">
				<tr>
					<td>
						${part.dataLocation.names}
					</td>
					<td>
						${part.sparePartCount}
					</td>
					<td>
						<shiro:hasPermission permission="sparePart:create">
							<a href="${createLinkWithTargetURI(controller: 'sparePart', action:'create', params:['dataLocation.id': part.dataLocation.id])}"><g:message code="part.add.sparePart.label" /></a>
						</shiro:hasPermission>
					</td>
					<td>
						<shiro:hasPermission permission="sparePart:list">
							<a href="${createLink(controller: 'sparePartView', action: 'list', params:['dataLocation.id': part.dataLocation.id] )}"><g:message code="part.manage.sparePart.label" /></a>
						</shiro:hasPermission>
					</td>
				</tr>
			</g:each>
		</g:if>
		<g:else>
			<tr>
				<td><g:message code="entity.list.empty.label" args="[message(code:'spare.part.label')]"/></td>
			</tr>
		</g:else>
	</tbody>
</table>
<div class="paginateButtons">
<g:paginate total="${entityCount}" params="${params}" action="${actionName}"/>
</div>
