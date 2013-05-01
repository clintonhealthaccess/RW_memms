<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
    <g:selectFromEnum name="equipmentStatus" values="${Status.values()}" field="equipmentStatus" label="Equipment Status" />
  </li>
  <li>
    <label for="report_type">Period of Acquisition:</label>
    <input class="js-date-picker date-picker idle-field" /> <span class="dash">-</span> <input class="js-date-picker date-picker idle-field" />
  </li>
  <li>
    <input name="obsolete" type='checkbox'><span><g:message code="equipment.obsolete.label" /></span>
  </li>
  <li>
    <input name="warranty" type='checkbox'><span>Under Warranty</span>
  </li>
</ul>