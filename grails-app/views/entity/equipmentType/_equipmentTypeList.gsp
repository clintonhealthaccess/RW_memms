<table>
	<thead>
		<tr>
			<th/>
			<th><g:message code="equipmentType.code.label"/></th>
			<th><g:message code="equipmentType.name.label"/></th>
			<th><g:message code="equipmentType.usedInMemms.label"/></th>
			<th><g:message code="equipmentType.observations.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="model">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'edit', params:[id: model.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'delete', params:[id: model.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${model.code}
				</td>
				<td>
					${model.names}
				</td>
				<td>
					${model.usedInMemms}
				</td>
				<td>
					${model.observations}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>