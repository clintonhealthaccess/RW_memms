<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="fct.targetoption.target.label"/></th>
			<th><g:message code="fct.targetoption.sum.label"/></th>
			<th><g:message code="entity.order.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="targetOption">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a class="edit-link" href="${createLinkWithTargetURI(controller:'fctTargetOption', action:'edit', params:[id: targetOption.id])}">
								<g:message code="default.link.edit.label" default="Edit" />
							</a>
						</li>
						<li>
							<a class="delete-link" href="${createLinkWithTargetURI(controller:'fctTargetOption', action:'delete', params:[id: targetOption.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message', default: 'Are you sure?')}');"><g:message code="default.link.delete.label" default="Delete" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					<g:i18n field="${targetOption.names}"/>
				</td>
				<td>${targetOption.code}</td>
				<td>
					<g:i18n field="${targetOption.target.names}"/>
				</td>
				<td>${targetOption.sum.code}</td>
				<td>${targetOption.order}</td>
			</tr>
		</g:each>
	</tbody>
</table>