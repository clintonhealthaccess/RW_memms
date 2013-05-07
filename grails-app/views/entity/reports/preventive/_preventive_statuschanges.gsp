<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
    %{-- TODO fix checkbox list styles !!! --}%
    <label for="statusChanges">Status Changes:</label>
    <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
        <input name="statusChanges" type="checkbox" value="${statusEnum.key}"/>
        <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
    </g:each>
  </li>
  <li>
    <label for="statusChangesPeriod">Status Changes Period:</label>
    <input name="fromStatusChangesPeriod" class="js-date-picker date-picker idle-field" />
    <span class="dash">-</span>
    <input name="toStatusChangesPeriod" class="js-date-picker date-picker idle-field" />
  </li>
  <li>
    %{-- TODO "Status Changes Done By Who?" checkbox list --}%
  </li>
</ul>