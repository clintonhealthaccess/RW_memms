<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<ul>
  <li>
    <g:selectFromEnum name="equipmentStatus" field="statusChanges" label="Status Changes"
      values="${Status.values()}" />
  </li>
  <li>
    <label for="report_type">Period of Status Changes:</label>
    <input class="js-date-picker date-picker idle-field" /> <span class="dash">-</span> <input class="js-date-picker date-picker idle-field" />
  </li>
</ul>