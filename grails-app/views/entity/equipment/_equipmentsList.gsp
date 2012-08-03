<table>
	<thead>
		<tr>
			<th/>
			<th><g:message code="equipment.serial.number.label"/></th>
			<th><g:message code="equipment.manufacture.label"/></th>
			<th><g:message code="datalocation.label"/></th>
			<th><g:message code="department.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="model">
			<tr >
				<td>
					<ul>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipment', action:'edit', params:[id: model.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipment', action:'delete', params:[id: model.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${model.serialNumber}
				</td>
				<td>
					${model.manufacture.contactName}
				</td>
				<td>
					${model.dataLocation.names}
				</td>
				<td>
					${model.department.names}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>