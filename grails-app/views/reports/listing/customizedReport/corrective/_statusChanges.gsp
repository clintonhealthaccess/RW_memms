<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.WorkOrderStatusChange" %>
<ul>
  <li>
    <label for="statusChanges"><g:message code="reports.statusChanges"/>:</label>
    <ul class="checkbox-list">
      <g:each in="${WorkOrderStatusChange.values()}" var="statusEnum">
        <li>
          <input name="statusChanges" type="checkbox" value="${statusEnum}"/>
          <label for="${statusEnum}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
        </li>
      </g:each>
    </ul>
  </li>
  <g:render template="/reports/listing/customizedReport/statusChangesPeriod"/>
  <li>
    <label for="warranty"><g:message code="reports.inventory.warranty.label"/>:</label>
    <input name="warranty" type="checkbox"/>
    <span><g:message code="reports.inventory.warranty"/></span>
  </li>
</ul>