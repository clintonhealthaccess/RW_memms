<table class="items spaced">
	<thead>
		<th><g:message code="datalocation.label" /></th>
		<th><g:message code="spare.part.sparePart.count" /></th>
		<th></th>
		<th></th>
	</thead>
	<tbody>
		<g:each in="${spareParts}" var="sparePart">
			<tr>
				<td>
					${sparePart.dataLocation.names}
				</td>
				<td>
					${sparePart.sparePartCount}
				</td>
				<td>
					<shiro:hasPermission permission="sparePart:create">
						<a href="${createLinkWithTargetURI(controller: 'sparePart', action:'create', params:['dataLocation.id': sparePart.dataLocation.id])}"><g:message code="sparePart.add.sparePart.label" /></a>
					</shiro:hasPermission>
				</td>
				<td>
					<shiro:hasPermission permission="sparePart:list">
						<a href="${createLink(controller: 'sparePartView', action: 'list', params:['dataLocation.id': sparePart.dataLocation.id] )}"><g:message code="sparePart.manage.sparePart.label" /></a>
					</shiro:hasPermission>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<div class="paginateButtons">
<g:paginate total="${entityCount}" params="${params}" action="${actionName}"/>
</div>
