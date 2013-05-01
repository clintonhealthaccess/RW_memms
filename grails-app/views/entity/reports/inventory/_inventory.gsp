<ul>
  <li>
    <g:selectFromList name="dataLocations" label="Facility" field="dataLocations" 
      optionKey="id" multiple="true" value="${dataLocations*.id}" 
      ajaxLink="${createLink(controller: 'dataLocation', action:'getAjaxData')}" 
      values="${dataLocations.collect{it.names+' '+it.id+' ['+it.location.names+']'}}" />
    <!-- TODO -->
    <input name="allDataLocations" type='checkbox' class='js-select-all'><span>Select all</span>
  </li>
  <li>
    <g:selectFromList name="departments" label="Department" field="departments" 
      optionKey="id" multiple="true" value="${departments*.id}" 
      ajaxLink="${createLink(controller: 'department', action:'getAjaxData')}"
      values="${departments.collect{it.names+' ['+it.id+']'}}" />
    <!-- TODO -->
    <input name="allDepartments" type='checkbox' class='js-select-all'><span>Select all</span>
  </li>
  <li>
    <g:selectFromList name="equipmentTypes" label="${message(code:'equipment.type.label')}"
      field="type" optionKey="id" multiple="true" value="${equipmentTypes*.id}"
      ajaxLink="${createLink(controller:'EquipmentType', action:'getAjaxData', params:[observation:'USEDINMEMMS'])}"
      values="${equipmentTypes.collect{it.names + ' ['+ it.code +']'}}"/>
    <input type='checkbox' class='js-select-all'><span>Select all</span>
  </li>
  <li>
    <label for="report_type">Cost:</label>
    <input class="js-date-picker date-picker idle-field" /> <span class="dash">-</span> <input class="js-date-picker date-picker idle-field" />
  </li>
</ul>
<g:if test="${reportSubType == 'inventory'}">
  <g:render template="/entity/reports/inventory/inventory_inventory"/>
</g:if>
<g:if test="${reportSubType == 'statuschanges'}">
  <g:render template="/entity/reports/inventory/inventory_statuschanges"/>
</g:if>