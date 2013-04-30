<div class="dialog-form step-2" id='js-step-2'>
  <!-- Step 2 -->
  <p>${dataLocations}</p>
  <%
    step2Params = [:]
    step2Params.putAll params
    step2Params.remove 'dataLocations'
  %>
  <h2>Apply filters<span class="right">Step <b>2</b> of <b>3</b></span></h2>
  <p>Type: ${reportType} > Subtype: ${reportSubType}</p>
  <g:formRemote name="formRemoteStep2Next" url="[action:'step3', params:step2Params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <ul>
        <li>
          <g:selectFromList name="dataLocations" label="${message(code:'datalocation.label')}" field="dataLocations" 
            optionKey="id" multiple="true" ajaxLink="${createLink(action:'getAjaxData')}" 
            from="${dataLocationTree}" value="${dataLocations*.id}"
            values="${dataLocations.collect{it.names+' ['+it.location.names+']'+' ['+it.id+']'}}" />
          <input type='checkbox' class='js-select-all'><span>Select all</span>
        </li>
        %{-- <li>
          <label>Department</label>
          <select multiple style="width:384px;" class="chzn-select"><option>Please select</option><option>Please select</option><option>Please select</option><option>Please select</option><option>Please select</option></select>
        <input type='checkbox' class='js-select-all'><span>Select all</span>
        </li>
        <li>
          <label>Equipment type</label>
          <select style="width:384px;" class="chzn-select"><option>Please select</option><option>Please select</option><option>Please select</option><option>Please select</option><option>Please select</option></select>
      </li>
        <li>
          <label>Equipment status</label>
          <select style="width:384px;" class="chzn-select"><option>Please select</option><option>Please select</option><option>Please select</option><option>Please select</option><option>Please select</option></select>
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
        </li> --}%
      </ul>
    </fieldset>
  </g:formRemote>
  <g:formRemote name="formRemoteStep2Prev" url="[action:'step1', params:params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
  </g:formRemote>
  <a href="#" class="ui-widget-previous left btn gray medium" id='js-prev-step-2'>Previous Step</a>
  <a href="#" class="ui-widget-next right btn" id='js-next-step-2'>Next step</a>
  <!-- end step 2 -->
</div>