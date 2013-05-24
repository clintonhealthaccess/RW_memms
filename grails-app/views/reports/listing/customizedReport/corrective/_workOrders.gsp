<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<ul>
  <g:set var="statusValues" value="${OrderStatus.values()-OrderStatus.NONE}"/>
  <g:render template="/reports/listing/customizedReport/workOrderStatusAndPeriod" model="[statusValues:statusValues]" />
  <li>
    <label for="warranty"><g:message code="reports.inventory.warranty.label"/>:</label>
    <input name="warranty" type="checkbox"/>
    <span><g:message code="reports.inventory.warranty"/></span>
  </li>
</ul>