<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="datalocation.type.label"/></th>
			<th><g:message code="datalocation.location.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="location">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a class="edit-link" href="${createLinkWithTargetURI(controller:'dataLocation', action:'edit', params:[id: location.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a class="delete-link" href="${createLinkWithTargetURI(controller:'dataLocation', action:'delete', params:[id: location.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>${location.code}</td>
				<td>
					<g:i18n field="${location.names}"/>
				</td>
				<td>
					<g:i18n field="${location.type.names}"/>
				</td>
				<td>
					<g:i18n field="${location.location.names}"/>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>