<ul class="filters-list third">
  <li>
    <g:selectFromList name="dataLocations" label="Facility" field="dataLocations" 
      optionKey="id" multiple="true" value="${dataLocations*.id}" 
      ajaxLink="${createLink(controller: 'dataLocation', action:'getAjaxData')}" 
      values="${dataLocations.collect{it.names+' '+it.id+' ['+it.location.names+']'}}" />
    <!-- TODO -->
    <input name="allDataLocations" type='checkbox' class='js-select-all'><span>Select all</span>
  </li>
</ul>
%{-- <ul class="filters-list third">
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
    <div class="half">
      <g:input name="costFrom" dateClass="date-picker" label="Cost" field="costFrom" value="${costFrom}"/>
    </div>
    <div class="half">
      <g:input name="costTo" dateClass="date-picker" label="Cost" field="costTo" value="${costTo}"/>
    </div>
  </li>
</ul>
<g:if test="${reportSubType == 'inventory'}">
  <g:render template="/entity/reports/inventory/inventory_inventory"/>
</g:if>
<g:if test="${reportSubType == 'statuschanges'}">
  <g:render template="/entity/reports/inventory/inventory_statuschanges"/>
</g:if> --}%