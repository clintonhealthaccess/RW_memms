<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
</head>
<body>

		<table class="items">
			<thead>
				<th><g:message code="datalocation.label" /></th>
				<th><g:message code="preventiveMaintenance.preventiveOrder.count" /></th>
				<th></th>
			</thead>
			<tbody>
				<g:each in="${preventiveMaintenances}" var="preventiveMaintenance">
					<tr>
						<td>
							${preventiveMaintenance.dataLocation.names}
						</td>
						<td>
							${preventiveMaintenance.orderCount}
						</td>
						<td>
							<shiro:hasPermission permission="preventiveOrder:list">
								<a href="${createLink(controller: 'preventiveOrderView', action: 'list', params:['dataLocation.id': preventiveMaintentce.dataLocation.id] )}"><g:message code="entity.manage.label" /></a>
							</shiro:hasPermission>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="paginateButtons">
		<g:paginate total="${entityCount}" params="${params}" action="${actionName}"/>
		 </div>

</body>
</html>
