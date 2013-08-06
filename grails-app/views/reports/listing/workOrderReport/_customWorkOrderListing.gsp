<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>
<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<table class="items">
	<thead>
		<tr>			
			<g:if test="${reportTypeOptions.contains('location')}">
				<g:sortableColumn property="dataLocation"  title="${message(code: 'location.label')}" params="${params}" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('code')}">
				<th><g:message code="default.equipment.code.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('serialNumber')}">
				<th><g:message code="default.equipment.serial.number.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('equipmentType')}">
				<th><g:message code="default.equipment.type.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('model')}">
				<g:sortableColumn property="model"  title="${message(code: 'equipment.model.label')}" params="${params}" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('manufacturer')}">
				<g:sortableColumn property="manufacturer"  title="${message(code: 'provider.type.manufacturer')}" params="${params}" />
			</g:if>

			<g:if test="${reportSubType == ReportSubType.STATUSCHANGES && reportTypeOptions.contains('statusChanges')}">
				<g:sortableColumn property="statusChanges"  title="Status Changes" params="${params}" />
			</g:if>

			<g:if test="${reportTypeOptions.contains('currentStatus')}">
				<g:sortableColumn property="currentStatus" title="${message(code: 'entity.status.label')}" params="${params}" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('travelTime')}">
				<th><g:message code="listing.report.corrective.travel.time.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('workTime')}">
				<th><g:message code="listing.report.corrective.work.time.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('estimatedCost')}">
				<th><g:message code="listing.report.corrective.estimated.cost.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('departmentRequestedOrder')}">
				<th><g:message code="listing.report.corrective.department.order.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('reasonsEquipmentFailure')}">
				<th><g:message code="listing.report.corrective.failure.reason.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('listPerformedActions')}">
				<th><g:message code="listing.report.corrective.performed.actions.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('description')}">
				<th><g:message code="listing.report.corrective.problem.description.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('dateOfEvent')}">
				<th><g:message code="listing.report.corrective.event.date.label"/></th>
			</g:if>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="order">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<g:if test="${reportTypeOptions.contains('location')}">
					<td>${order.equipment.dataLocation.names}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('code')}">
					<td>${order?.equipment?.code}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('serialNumber')}">
					<td>${order.equipment.serialNumber}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('equipmentType')}">
					<td>${order.equipment.type.names}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('model')}">
					<td>${order.equipment.model}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('manufacturer')}">
					<td>${order.equipment.manufacturer?.contact?.contactName}</td>
				</g:if>

				<g:if test="${reportSubType == ReportSubType.STATUSCHANGES && reportTypeOptions.contains('statusChanges')}">
					%{--
					<g:set var="statusChangesEnum" value="${order.getWorkOrderTimeBasedStatusChange(customWorkOrderParams.statusChanges)}"/>
					--}%
					<td>${message(code: statusChangesEnum?.messageCode+'.'+statusChangesEnum?.name)}</td>
				</g:if>

				<g:if test="${reportTypeOptions.contains('currentStatus')}">
					<td>${message(code: order.currentStatus?.messageCode+'.'+order.currentStatus?.name)}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('travelTime')}">
					<td>
					<g:if test="${order.travelTime?.numberOfMinutes!=null}">
						${order.travelTime?.numberOfMinutes} Minutes
					</g:if>
					<g:else>
					
					</g:else>
					</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('workTime')}">
					<td>${order.workTime?.numberOfMinutes} Minutes</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('estimatedCost')}">
					<td>${order.estimatedCost}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('departmentRequestedOrder')}">
					<td>${order.equipment?.department?.code}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('reasonsEquipmentFailure')}">
					<td>${order.failureReasonDetails}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('listPerformedActions')}">
					%{-- TODO AR corrective process property AFTER RELEASE --}%
					<td>${order.actions?.collect{(it.name)}}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('description')}">
					<td><g:stripHtml field="${order.description}" chars="30"/></td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('dateOfEvent')}">
				<td>${Utils.formatDateWithTime(order.dateCreated)}</td>
				</g:if>
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" />