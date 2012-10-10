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
			<th><g:message code="work.order.openOn.label"/></th>
			<th><g:message code="work.order.closedOn.label"/></th>
			<th><g:message code="work.order.description.label"/></th>
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
					${message(code: order.currentStatus?.messageCode+'.'+order.currentStatus?.name)}
				</td>
				<td>
					${order.criticality}
				</td>
				<td>
					${Utils.formatDateWithTime(order.openOn)}
				</td>
				<td>
					${Utils.formatDateWithTime(order.closedOn)}
				</td>
				<td>
	  				<button class="escalate next medium gray" id="${order.id}"><g:message code="work.order.escalate.issue.link.label"/></button>
	  				<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
				</td>
				<td>
					<a href="${createLinkWithTargetURI(controller:'notification', action:'list', params:[id: order.id, read:false])}">${order.getUnReadNotificationsForUser(User.findByUuid(SecurityUtils.subject.principal, [cache: true])).size()}</a>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<script type="text/javascript">
	$(document).ready(function() {
		escaletWorkOrder("${createLink(controller:'workOrder',action: 'escalate')}")
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
