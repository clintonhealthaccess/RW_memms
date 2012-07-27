<table class="listing">
	<thead>
		<tr>
			<th></th>
			<th><g:message code="entity.id.label"/></th>
			<th><g:message code="entity.type.label"/></th>
			<th><g:message code="entity.code.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${referencingData}" var="data" status="i">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<a class="edit-link" href="${createLinkWithTargetURI(controller:data.class.simpleName, action:'edit', params:[id: data.id])}">
						<g:message code="default.link.edit.label" />
					</a>
				</td>
				<td>${data.id}</td>
				<td>${data.class.simpleName}</td>
				<td>${data.code}</td>
			</tr>
		</g:each>
	</tbody>
</table>