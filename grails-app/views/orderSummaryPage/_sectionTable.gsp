<table class="items">
	<thead>
		<th><g:message code="datalocation.label" /></th>
		<th>${message(code:code)}</th>
		<th></th>
	</thead>
	<tbody>
		<g:if test="${maintenances != null && !maintenances.empty}">
			<g:each in="${maintenances}" var="maintenance">
				<tr>
					<td>
						${maintenance.dataLocation.names}
					</td>
					<td>
						${maintenance.orderCount}
					</td>
					<td>
						<shiro:hasPermission permission="order:list">
							<a href="${createLink(controller: controller, action: 'list', params:['dataLocation.id': maintenance.dataLocation.id] )}">
								<g:message code="entity.list.manage.label" />
							</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</g:each>
		</g:if>
		<g:else>
			<tr>
				<td><g:message code="entity.list.empty.label" args="[message(code:code)]"/></td>
			</tr>
		</g:else>
	</tbody>
</table>
<div class="paginateButtons">
	<g:paginate total="${entityCount}" params="${params}" action="${actionName}"/>
</div>

