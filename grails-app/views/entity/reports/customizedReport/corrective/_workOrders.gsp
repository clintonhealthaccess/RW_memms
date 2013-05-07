<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<ul>
  <g:set var="statusValues" value="${OrderStatus.values()}"/>
  <g:render template="/entity/reports/customizedReport/workOrderStatusAndPeriod" model="[statusValues:statusValues]" />
  <li>
    %{-- TODO fix checkbox styles !!! --}%
    <label for="warranty"><g:message code="reports.inventory.warranty"/>:</label>
    <input name="warranty" type="checkbox"/>
  </li>
</ul>