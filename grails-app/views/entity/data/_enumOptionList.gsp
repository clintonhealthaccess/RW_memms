<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.description.label"/></th>
			<th><g:message code="enumoption.inactive.label"/></th>
			<th><g:message code="enumoption.value.label"/></th>
			<th><g:message code="entity.order.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="option"> 
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
				        	<a class="edit-link" href="${createLinkWithTargetURI(controller:'enumOption', action:'edit', params:[id: option.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a class="delete-link" href="${createLinkWithTargetURI(controller:'enumOption', action:'delete', params:[id: option.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
								<g:message code="default.link.delete.label" />
							</a>
						</li>
					</ul>
				</td>
				<td>${option.code}</td>
				<td><g:i18n field="${option.names}" /></td>
				<td><g:stripHtml field="${i18n(field: option.descriptions)}" chars="40" /></td>
				<td>${option.inactive?'\u2713':''}</td>
				<td>${option.value}</td>
				<td><g:i18n field="${option.order}" /></td>
			</tr>
		</g:each>
	</tbody>
</table>
