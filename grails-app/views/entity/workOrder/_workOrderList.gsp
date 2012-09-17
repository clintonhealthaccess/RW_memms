<%@ page import="org.chai.memms.util.Utils" %>
<table class="items">
	<thead>
		<tr>
			<th/>
			<th><g:message code="equipment.serial.number.label"/></th>
			<th><g:message code="equipment.type.label"/></th>
			<th><g:message code="entity.description.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="order">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'workOrder', action:'edit', params:[id: order.id])}" class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'workOrder', action:'delete', params:[id: order.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${order.equipment.serialNumber}
				</td>
				<td>
					${order.equipment.type.names}
				</td>
				<td>
					<g:stripHtml field="${order.description}" chars="30"/>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>