<%@ page import="org.chai.memms.util.Utils" %>
<tbody>
	<g:each in="${entities}" status="i" var="type">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td>
				<ul class="horizontal">
					<li>
						<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'edit', params:[id: type.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<li>
						<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'delete', params:[id: type.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
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
				${type.expectedLifeTime?.numberOfMonths}
			</td>
			<td>
				${Utils.formatDateWithTime(type?.dateCreated)}
			</td>
			<td>
				${Utils.formatDateWithTime(type?.lastUpdated)}
			</td>
			<td>
				<g:if test="${!type.preventiveActions || type.preventiveActions.size()==0}">
					<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'edit', params:[id: type.id])}" class="next gray medium"> 
							<g:message code="preventive.add.preventive.action.label" />
					</a>
				</g:if>
			</td>
		</tr>
	</g:each>
</tbody>
