<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
      %{-- TODO fix styles !!! --}%
      <label for="statusChanges"><g:message code="reports.inventory.workOrders.statusChanges"/>:</label>
      <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
        <input name="statusChanges" type="checkbox" value="${statusEnum.key}"/>
        <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
      </g:each>
  </li>
  <li>
    <label for="report_type"><g:message code="reports.inventory.workOrders.statusChangesPeriod"/>:</label>
    <input name="fromStatusChanges" class="js-date-picker date-picker idle-field" />
    <span class="dash">-</span>
    <input name="toStatusChanges" class="js-date-picker date-picker idle-field" />
  </li>
</ul>