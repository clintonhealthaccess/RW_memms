<table class="items">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="code" title="${message(code: 'entity.code.label', default: 'Code')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<g:sortableColumn property="contact.contactName" title="${message(code: 'entity.name.label', default: 'Name')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<g:sortableColumn property="type" title="${message(code: 'entity.type.label', default: 'Type')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<g:sortableColumn property="contact.email" title="${message(code: 'contact.email.label', default: 'Email')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<th><g:message code="contact.phone.label"/></th>
			<th><g:message code="contact.pobox.label"/></th>
			<g:sortableColumn property="contact.street" title="${message(code: 'contact.street.label', default: 'Street')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<g:sortableColumn property="contact.city" title="${message(code: 'contact.city.label', default: 'City')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<g:sortableColumn property="contact.country" title="${message(code: 'contact.country.label', default: 'Country')}" params="[q:q,sparePartType:sparePartType?.id,type:type]" />
			<th><g:message code="contact.address.descriptions.label"/></th>
		</tr>
	</thead>
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
					${provider.contact.street}
				</td>
				<td>
					${provider.contact.city}
				</td>
				<td>
					${provider.contact.country}
				</td>
				<td>
					<g:stripHtml field="${provider.contact.addressDescriptions}" chars="30"/>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" />