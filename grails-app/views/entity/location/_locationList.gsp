<table class="items">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="code" params="[q:q]" title="${message(code: 'entity.code.label')}" />
			<g:sortableColumn property="${i18nField(field: 'names')}" params="[q:q]" title="${message(code: 'entity.name.label')}" />
			<g:sortableColumn property="coordinates" params="[q:q]" title="${message(code: 'location.coordinates.label')}" />
			<g:sortableColumn property="level" params="[q:q]" title="${message(code: 'location.level.label')}" />
			<g:sortableColumn property="parent" params="[q:q]" title="${message(code: 'location.parent.label')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="location">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'location', action:'edit', params:[id: location.id])}" class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'location', action:'delete', params:[id: location.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>${location.code}</td>
				<td>
					${location.names}
				</td>
				<td>
					${location.coordinates?'\u2713':''}
				</td>
				<td>
					${location.level.names}
				</td>
				<td>
					${location.parent?.names}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" model="[entities:entities, entityCount:entities.totalCount]" />