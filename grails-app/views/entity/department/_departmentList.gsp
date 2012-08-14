<table>
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.names.label"/></th>
			<th><g:message code="entity.descriptions.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="department">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul>
						<li>
							<a href="${createLinkWithTargetURI(controller:'department', action:'edit', params:[id: department.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'department', action:'delete', params:[id: department.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${department.code}
				</td>
				<td>
					${department.names}
				</td>
				<td>
					${department.descriptions}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>