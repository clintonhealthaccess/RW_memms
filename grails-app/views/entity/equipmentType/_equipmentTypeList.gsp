<%@ page import="org.chai.memms.util.Utils" %>
<table>
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.description.label"/></th>
			<th><g:message code="entity.observation.label"/></th>
			<th><g:message code="type.addon.label"/></th>
			<th><g:message code="type.lastmodifiedon.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="type">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'edit', params:[id: type.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'delete', params:[id: type.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${type.code}
				</td>
				<td>
					${type.names}
				</td>
				<td>
					${type.descriptions}
				</td>
				<td>
					<g:message code="${type.observation.messageCode}.${type.observation.name}"/>
				</td>
				<td>
					${Utils.formatDateWithTime(type?.addedOn)}
				</td>
				<td>
					${Utils.formatDateWithTime(type?.lastModifiedOn)}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>