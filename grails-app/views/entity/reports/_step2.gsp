<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<div class="dialog-form step-2" id='js-step-2'>
  <!-- Step 2 -->
%{--   <p>ReportType: ${reportType}</p>
  <p>ReportSubType: ${reportSubType}</p> --}%
  <h2>Apply filters<span class="right">Step <b>2</b> of <b>4</b></span></h2>
  <p>Type: Inventory &gt; List of inventory</p>
  <g:formRemote name="formRemoteStep2" url="[action:'step3', params: params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <ul>
        <li>
          <g:selectFromList name="dataLocationIds" style="width:384px;" class="chzn-select" label="${message(code:'spare.part.dataLocation.label')}" bean="${equipment}" field="dataLocations" optionKey="id" multiple="true"
    			ajaxLink="${createLink(controller:'dataLocation', action:'getAjaxData', params: [class:'dataLocation'])}"
    			from="${dataLocations}" value="${equipment?.dataLocation?.id}" values="${dataLocations.collect{it.names}}" />
          <input type='checkbox' class='js-select-all'><span>Select all</span>
        </li>
        <li>
          <g:selectFromList name="departmentIds" style="width:384px;" class="chzn-select" label="${message(code:'department.label')}" bean="${equipment}" field="department" optionKey="id" multiple="true"
    			ajaxLink="${createLink(controller:'department', action:'getAjaxData')}"
    			from="${departments}" value="${equipment?.department?.id}" values="${departments.collect{it.names}}" />
        <input type='checkbox' class='js-select-all'><span>Select all</span>
        </li>
        <li>
          <g:selectFromList name="typeIds" style="width:384px;" class="chzn-select" label="${message(code:'equipment.type.label')}" bean="${equipment}" field="type" optionKey="id" multiple="true"
    			ajaxLink="${createLink(controller:'equipmentType', action:'getAjaxData', params: [observation:'USEDINMEMMS'])}"
    			from="${types}" value="${type?.id}" values="${types.collect{it.names}}" />
      </li>
        <li>
          <g:selectFromEnum name="equipmentStatus" bean="${equipment}" values="${Status.values()}" field="equipmentStatus" label="${message(code:'equipment.status.label')}" optionKey="id" multiple="true" />
        </li>
        <li>
          <input type="checkbox" class="no-margin"><span>Show only obsolete equipment</span>
        </li>
        <li>
          <label for="report_type">Acquisition period</label>
          <input class="js-date-picker date-picker idle-field" /> <span class="dash">-</span> <input class="js-date-picker date-picker idle-field" />
        </li>
        <li>
          <label for="report_type">Which cost</label>
          <input class="js-date-picker date-picker idle-field" /> <span class="dash">-</span> <input class="js-date-picker date-picker idle-field" />
          <input type="checkbox"><span>Include equipment without cost</span>
        </li>
        <li>
          <label>Warranty</label>
          <input type="checkbox"><span>Under warranty</span>
        </li>
      </ul>
    </fieldset>
    <g:remoteLink class="ui-widget-previous left btn gray medium" action="step1" 
      update="dialog-form" params="${params}" onSuccess="customizedlisting_init();">
      Previous Step</g:remoteLink>
    <a href="#" class="ui-widget-next right btn" id='js-next-step-2'>Next step</a>
  </g:formRemote>
  <!-- end step 2 -->
</div>