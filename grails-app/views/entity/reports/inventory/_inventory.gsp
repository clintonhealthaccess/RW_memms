<%@ page import="org.chai.memms.util.Utils.ReportType" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<ul>
  <li>
    <g:selectFromList name="dataLocations" label="Facility" field="dataLocations" 
      optionKey="id" multiple="true" value="${dataLocations*.id}" 
      ajaxLink="${createLink(controller: 'dataLocation', action:'getAjaxData')}" 
      values="${dataLocations.collect{it.names+' '+it.id+' ['+it.location.names+']'}}" />
    <g:checkBox name="allDataLocations" class='js-select-all'/>
    <span><g:message code="reports.selectAll"/></span>
  </li>
  <li>
    <g:selectFromList name="departments" label="Department" field="departments" 
      optionKey="id" multiple="true" value="${departments*.id}" 
      ajaxLink="${createLink(controller: 'department', action:'getAjaxData')}"
      values="${departments.collect{it.names+' ['+it.id+']'}}" />
    <g:checkBox name="allDepartments" class='js-select-all'/>
    <span><g:message code="reports.selectAll"/></span>
  </li>
  <li>
    <g:selectFromList name="equipmentTypes" label="${message(code:'equipment.type.label')}"
      field="type" optionKey="id" multiple="true" value="${equipmentTypes*.id}"
      ajaxLink="${createLink(controller:'EquipmentType', action:'getAjaxData', params:[observation:'USEDINMEMMS'])}"
      values="${equipmentTypes.collect{it.names + ' ['+ it.code +']'}}"/>
    <g:checkBox name="allEquipmentTypes" class='js-select-all'/>
    <span><g:message code="reports.selectAll"/></span>
  </li>
  <li>
    %{-- TODO fix styles !!! --}%
    <label>Cost:</label>
    <input type="text" name="fromCost"/><span class="dash">-</span><input type="text" name="toCost"/>
    <label for="costCurrency">Currency:</label>
    <select name="costCurrency">
      <g:each in="${currencies}" var="currencyEnum">
        <option value="${currencyEnum.key}">
          ${message(code:'currency.'+currencyEnum.value)}
        </option>
      </g:each>
    </select>
  </li>
</ul>
<g:if test="${reportSubType == ReportSubType.INVENTORY}">
  <g:render template="/entity/reports/inventory/inventory_inventory"/>
</g:if>
<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
  <g:render template="/entity/reports/inventory/inventory_statuschanges"/>
</g:if>