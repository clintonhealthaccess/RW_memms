<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>
<table class="items">
	<thead>
		<tr>
			<th/>
			<th><g:message code="equipment.serial.number.label"/></th>
			<th><g:message code="equipment.type.label"/></th>
			<th><g:message code="work.order.status.label"/></th>
			<th><g:message code="work.order.criticality.label"/></th>
			<th><g:message code="work.order.description.label"/></th>
			<th><g:message code="work.order.openOn.label"/></th>
			<th><g:message code="work.order.closedOn.label"/></th>
			<th><g:message code="work.order.messages.label"/></th>
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
				<a rel="${createLinkWithTargetURI(controller:'workOrder', action:'getWorkOrderClueTipsAjaxData', params:[id: order.id])}" class="clueTip">
								${order.equipment.serialNumber}
							</a>
					
				</td>
				<td>
					${order.equipment.type.names}
				</td>
				<td>
					${order.status}
				</td>
				<td>
					${order.criticality}
				</td>
				<td>
					<g:stripHtml field="${order.description}" chars="30"/>
				</td>
				<td>
					${order.openOn}
				</td>
				<td>
					${order.closedOn}
				</td>
				<td>
					<a href="${createLinkWithTargetURI(controller:'#', action:'#', params:[id: order.id])}">${order.getUnReadNotificationsForUser(User.findByUuid(SecurityUtils.subject.principal, [cache: true])).size()}</a>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<script type="text/javascript">
	$(document).ready(function() {
		getDatePicker("${resource(dir:'images',file:'icon_calendar.png')}")
		$('a.clueTip').cluetip({
			  //cluetipClass: 'jtip', for formating the output
			  arrows: true,
			  dropShadow: false,
			  hoverIntent: false,
			  sticky: true,
			  mouseOutClose: true,
			  closePosition: 'title'
			});
	});
</script>
