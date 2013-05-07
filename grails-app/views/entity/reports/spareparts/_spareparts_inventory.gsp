<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
    %{-- TODO fix checkbox list styles !!! --}%
    <label for="sparePartStatus">Spare Part Status:</label>
    <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
        <input name="sparePartStatus" type="checkbox" value="${statusEnum.key}"/>
        <label for="${statusEnum.key}">${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)}</label>
    </g:each>
  </li>
  <li>
    <label for="acquisitionPeriod">Spare Part Acquisition Period:</label>
    <input name="fromAcquisitionPeriod" class="js-date-picker date-picker idle-field" />
    <span class="dash">-</span>
    <input name="toAcquisitionPeriod" class="js-date-picker date-picker idle-field" />
    <g:checkBox name="noAcquisitionPeriod"/>
    <span>Include spare part without acquisition date</span>
  </li>
</ul>