<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<ul>
  <li>
    %{-- TODO use corrective maintenance status changes object --}%
    %{-- TODO fix checkbox list styles !!! --}%
    <label for="statusChanges"><g:message code="reports.statusChanges"/>:</label>
    <g:each in="${OrderStatus.values() - OrderStatus.NONE}" var="statusEnum">
        <input name="statusChanges" type="checkbox" value="${statusEnum.key}"/>
        <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
    </g:each>
  </li>
  <li>
    <label for="statusChangesPeriod"><g:message code="reports.statusChangesPeriod"/>:</label>
    <input name="fromStatusChangesPeriod" class="js-date-picker date-picker idle-field" />
    <span class="dash">-</span>
    <input name="toStatusChangesPeriod" class="js-date-picker date-picker idle-field" />
  </li>
  <li>
    %{-- TODO fix checkbox styles !!! --}%
    <label for="warranty"><g:message code="reports.inventory.warranty"/>:</label>
    <input name="warranty" type="checkbox"/>
  </li>
</ul>