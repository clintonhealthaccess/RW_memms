<%@ page import="org.chai.memms.preventive.maintenance.PreventiveOrder.PreventiveOrderStatus" %>
<%@ page import="org.chai.memms.preventive.maintenance.PreventiveOrder.PreventionResponsible" %>
<ul>
  <li>
    %{-- TODO --}%
    <label for="statusChanges"><g:message code="reports.statusChanges"/>:</label>
    <g:each in="${PreventiveOrderStatus.values()}" var="statusEnum">
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
    %{-- TODO fix checkbox list styles !!! --}%
    <label for="doneByWho"><g:message code="reports.preventive.statusChanges.doneByWho"/>:</label>
    <g:each in="${PreventionResponsible.values() - PreventionResponsible.NONE}" var="statusEnum">
        <input name="doneByWho" type="checkbox" value="${statusEnum.key}"/>
        <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
    </g:each>
  </li>
</ul>