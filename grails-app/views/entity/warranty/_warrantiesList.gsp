<table>
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="equipment.serial.number.label"/></th>
			<th><g:message code="model.label"/></th>
			<th><g:message code="warranty.date.start.label"/></th>
			<th><g:message code="warranty.date.end.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="model">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul>
						<li>
							<a href="${createLinkWithTargetURI(controller:'department', action:'edit', params:[id: model.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'department', action:'delete', params:[id: model.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${model.code}
				</td>
				<td>
					${model.equipment.serialNumber}
				</td>
				<td>
					${model.equipment.model.names}
				</td>
				<td>
					${model.startDate}
				</td>
				<td>
					${model.endDate}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>