<table>
	<thead>
		<tr>
			<th></th>			
			<th><g:message code="equipment.serial.number.label"/></th>
			<th><g:message code="equipment.type.label"/></th>
			<th><g:message code="equipment.model.label"/></th>
			<th><g:message code="provider.manufacture.label"/></th>
			<th><g:message code="provider.supplier.label"/></th>
			<th><g:message code="location.label"/></th>
			<th><g:message code="equipment.status.label"/></th>
			<th><g:message code="equipment.obsolete.label"/></th>
			<th><g:message code="equipment.donation.label"/></th>
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
					${equipment.serialNumber}
				</td>
				<td>
					${equipment.type.names}
				</td>
				<td>
					${equipment.model}
				</td>
				
				<td>
					${equipment.manufacture.contact.contactName}
				</td>
				<td>
					${equipment.supplier.contact.contactName}
				</td>
				<td>
					<g:message code="datalocation.label"/>: ${equipment.dataLocation.names}<br/>
					<g:message code="department.label"/>: ${equipment.department.names}<br/>
					<g:message code="equipment.room.label"/>: ${equipment.room}<br/>
				</td>
				<td>
					${equipment.getCurrentStatus()?.value?.name}
				</td>
				<td>
					${equipment.donation}
				</td>
				<td>
					${equipment.obsolete}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>