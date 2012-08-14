<table>
	<thead>
		<tr>
			<th></th>
			<th><g:message code="equipment.model.label"/></th>
			<th><g:message code="equipment.serial.number.label"/></th>
			<th><g:message code="equipment.manufacture.label"/></th>
			<th><g:message code="equipment.supplier.label"/></th>
			<th><g:message code="datalocation.label"/></th>
			<th><g:message code="department.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="equipment">
			<tr >
				<td>
					<ul>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipment', action:'edit', params:[id: equipment.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipment', action:'delete', params:[id: equipment.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${equipment.model}
				</td>
				<td>
					${equipment.serialNumber}
				</td>
				<td>
					${equipment.manufacture.contact.contactName}
				</td>
				<td>
					${equipment.supplier.contact.contactName}
				</td>
				<td>
					${equipment.dataLocation.names}
				</td>
				<td>
					${equipment.department.names}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>