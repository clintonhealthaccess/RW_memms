<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
    %{-- TODO fix checkbox list styles !!! --}%
    <label for="workOrderStatus">Work Order Status:</label>
    <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
        <input name="workOrderStatus" type="checkbox" value="${statusEnum.key}"/>
        <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
    </g:each>
  </li>
  <li>
    <label for="workOrderPeriod">Work Order Period:</label>
    <input name="fromWorkOrderPeriod" class="js-date-picker date-picker idle-field" />
    <span class="dash">-</span>
    <input name="toWorkOrderPeriod" class="js-date-picker date-picker idle-field" />
  </li>
  <li>
    %{-- TODO "Who is in Charge?" checkbox list --}%
  </li>
</ul>