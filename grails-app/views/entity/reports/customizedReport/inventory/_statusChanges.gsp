<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
      %{-- TODO --}%
      <label for="statusChanges"><g:message code="reports.statusChanges"/>:</label>
      <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
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
</ul>