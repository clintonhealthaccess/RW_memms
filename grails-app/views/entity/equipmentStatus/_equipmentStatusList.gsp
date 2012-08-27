<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>
<table class="items">
	<thead>
		<tr>
			<th></th>
			<th><g:message code="equipment.status.label"/></th>
			<th><g:message code="equipment.status.date.of.event.label"/></th>
			<th><g:message code="equipment.status.recordedon.label"/></th>
			<th><g:message code="equipment.status.current.label"/></th>
		</tr>
	</thead>
	<tbody>		
   		<g:each in="${entities}" status="i" var="status">
    		<tr  class="${(i % 2) == 0 ? 'odd' : 'even'}">
    			<td>
	    			<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'edit', params:[id: status.id,equipment: status.equipment?.id])}" class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'delete', params:[id: status.id,equipment: status.equipment?.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button">
								<g:message code="default.link.delete.label" />
							</a>
						</li>
					</ul>
    			</td>
    			<td>${status.value}</td>
    			<td>${Utils.formatDate(status?.dateOfEvent)}</td>
    			<td>${Utils.formatDate(status?.statusChangeDate)}</td>
    			<td>${status.current}</td>
    		</tr>
   		</g:each>
	</tbody>
</table>