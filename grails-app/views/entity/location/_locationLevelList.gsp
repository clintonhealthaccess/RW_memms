<table>
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.order.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="locationLevel">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul>
						<li>
							<a class="edit-link" href="${createLinkWithTargetURI(controller:'locationLevel', action:'edit', params:[id: locationLevel.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'locationLevel', action:'delete', params:[id: locationLevel.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${locationLevel.code}
				</td>
				<td>
					${locationLevel.names}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>