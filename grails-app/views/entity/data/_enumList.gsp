<table class="listing">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="code" params="[q:q]" title="${message(code: 'entity.code.label')}" />
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.description.label"/></th>
			<th><g:message code="default.number.label" args="[message(code:'enumoption.label')]"/></th>
			<th><g:message code="entity.list.manage.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="enumation"> 
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a class="edit-link" href="${createLinkWithTargetURI(controller:'enum', action:'edit', params:[id: enumation.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a class="delete-link" href="${createLinkWithTargetURI(controller:'enum', action:'delete', params:[id: enumation.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
								<g:message code="default.link.delete.label" />
							</a>
						</li>
					</ul>
				</td>
				<td>${enumation.code}</td>				
				<td><g:i18n field="${enumation.names}" /></td>
				<td><g:i18n field="${enumation.descriptions}" /></td>
				<td>${enumation.enumOptions.size()}</td>
				<td>
				<div class="js_dropdown dropdown"> 
					<a class="js_dropdown-link with-highlight" href="#">Manage</a>
					<div class="js_dropdown-list dropdown-list">
						<ul>
							<li>
								<a href="${createLink(controller:'enumOption', action:'list', params:['enume.id': enumation.id])}">
									<g:message code="default.list.label" args="[message(code:'enum.enumoption.label')]" />
								</a>
							</li>
						</ul>
					</div>
				</div> 		
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
