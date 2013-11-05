<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<table class="items">
	<thead>
		<tr>
			<th/>
			<th><g:message code="equipment.label"/></th>
			<th><g:message code="entity.status.label"/></th>
			<th><g:message code="work.order.criticality.label"/></th>
			<th><g:message code="order.open.on.label"/></th>
			<th><g:message code="work.order.description.label"/></th>
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
					</ul>
				</td>
				<td>
					<a rel="${createLinkWithTargetURI(controller:'equipmentView', action:'getEquipmentClueTipsAjaxData', params:['equipment.id': order.equipment.id])}" class="clueTip">
						${order.equipment.code}
					</a>
					
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
					<g:stripHtml field="${order.description}" chars="30"/>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
