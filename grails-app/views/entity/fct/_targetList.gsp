<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="fct.target.program.label"/></th>
			<th><g:message code="default.number.label" args="[message(code:'fct.targetoption.label')]"/></th>
			<th><g:message code="entity.order.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="target">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a class="edit-link" href="${createLinkWithTargetURI(controller:'fctTarget', action:'edit', params:[id: target.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a class="delete-link" href="${createLinkWithTargetURI(controller:'fctTarget', action:'delete', params:[id: target.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					<g:i18n field="${target.names}"/>
				</td>
				<td>${target.code}</td>
				<td>
					<g:i18n field="${target.program.names}"/>
				</td>
				<td>${target.targetOptions.size()}</td>
				<td>${target.order}</td>
			</tr>
		</g:each>
	</tbody>
</table>