<tbody>
	<g:each in="${entities}" status="i" var="provider">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td>
				<ul class="horizontal">
					<li>
						<a href="${createLinkWithTargetURI(controller:'provider', action:'edit', params:[id: provider.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<li>
						<a href="${createLinkWithTargetURI(controller:'provider', action:'delete', params:[id: provider.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>
					
				</ul>
			</td>
			<td>
				${provider.code}
			</td>
			<td>
				${provider.contact.contactName}
			</td>
			<td>
				${message(code: provider.type?.messageCode+'.'+provider.type?.name)}
			</td>
			<td>
				${provider.contact.email}
			</td>
			<td>
				${provider.contact.phone}
			</td>
			<td>
				${provider.contact.poBox}
			</td>
			<td>
				${provider.contact.city}
			</td>
			<td>
				${provider.contact.country}
			</td>
			<td>
				<g:stripHtml field="${provider.contact.addressDescriptions}" chars="30"/>dd
			</td>
		</tr>
	</g:each>
</tbody>
