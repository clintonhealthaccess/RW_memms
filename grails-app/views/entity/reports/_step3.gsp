<div class="dialog-form step-3" id='js-step-3'>
  <!-- Step 3 -->
  <p>${dataLocations}</p>
  <h2>Choose the order<span class="right">Step <b>3</b> of <b>3</b></span></h2>
  <p>Type: ${reportType} > Subtype: ${reportSubType}</p>
  <g:form name="formRemoteStep3Next" url="[action:'listing', params: step3Params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <ul class="twocol-checkboxes">
        <g:if test="${reportType == 'inventory'}">
            <li><input type="checkbox"><span>Equipment Type Name</span></li>
            <li><input type="checkbox"><span>Equipment Type Code</span></li>
            <li><input type="checkbox"><span>Manufacturer Name</span></li>
            <li><input type="checkbox"><span>Model</span></li>
            <li><input type="checkbox"><span>Supplier Name</span></li>
            <li><input type="checkbox"><span>Location</span></li>
            <li><input type="checkbox"><span>Acquisition Date</span></li>
            <li><input type="checkbox"><span>Cost</span></li>
            <li><input type="checkbox"><span>Status</span></li>
            <li><input type="checkbox"><span>Obsolete</span></li>
            <li><input type="checkbox"><span>Warranty Provider Name</span></li>
            <li><input type="checkbox"><span>Warranty Period Remaining</span></li>
            <li><input type="checkbox"><span>Current Value</span></li>
        </g:if>
        <g:elseif test="${reportType == 'corrective'}">
            <li><input type="checkbox"><span>Equipment Type Name</span></li>
            <li><input type="checkbox"><span>Manufacturer Name</span></li>
            <li><input type="checkbox"><span>Equipment Model</span></li>
            <li><input type="checkbox"><span>Equipment Serial Number</span></li>
            <li><input type="checkbox"><span>Equipment Location</span></li>
            <li><input type="checkbox"><span>Work Order Status</span></li>
            <li><input type="checkbox"><span>Work Order Travel Time</span></li>
            <li><input type="checkbox"><span>Work Order Work Time</span></li>
            <li><input type="checkbox"><span>Work Order Estimated Cost</span></li>
            <li><input type="checkbox"><span>Department Requested Order</span></li>
            <li><input type="checkbox"><span>Reasons Equipment Failure</span></li>
            <li><input type="checkbox"><span>List Performed Actions</span></li>
            <li><input type="checkbox"><span>Description of Problem</span></li>
            <li><input type="checkbox"><span>Date of Event</span></li>
        </g:elseif>
        <g:elseif test="${reportType == 'preventive'}">
            <li><input type="checkbox"><span>Equipment Type Name</span></li>
            <li><input type="checkbox"><span>Manufacturer Name</span></li>
            <li><input type="checkbox"><span>Equipment Model</span></li>
            <li><input type="checkbox"><span>Equipment Serial Number</span></li>
            <li><input type="checkbox"><span>Equipment Location</span></li>
            <li><input type="checkbox"><span>Work Order Status</span></li>
            <li><input type="checkbox"><span>Schedule Next Interventions</span></li>
            <li><input type="checkbox"><span>% Interventions Done</span></li>
            <li><input type="checkbox"><span>Recurrence Period</span></li>
            <li><input type="checkbox"><span>Start Date</span></li>
            <li><input type="checkbox"><span>Responsible</span></li>
            <li><input type="checkbox"><span>Action Name</span></li>
            <li><input type="checkbox"><span>Action Description</span></li>
        </g:elseif>
        <g:elseif test="${reportType == 'spareparts'}">
          <g:if test="${['inventory','statuschanges'].contains(reportSubType)}">
            <li><input type="checkbox"><span>Spare Part Type</span></li>
            <li><input type="checkbox"><span>Status</span></li>
            <li><input type="checkbox"><span>Model</span></li>
            <li><input type="checkbox"><span>Manufacturer</span></li>
            <li><input type="checkbox"><span>Equipment Code Associated with Spare Part</span></li>
            <li><input type="checkbox"><span>Cost</span></li>
            <li><input type="checkbox"><span>Warranty Period Remaining</span></li>
            <li><input type="checkbox"><span>Discontinued Date</span></li>
          </g:if>
          <g:elseif test="${['userate','stockout'].contains(reportSubType)}">
            <li><input type="checkbox"><span>Quantity in Stock</span></li>
            <li><input type="checkbox"><span>Use Rate</span></li>
          </g:elseif>
        </g:elseif>
      </ul>
    </fieldset>
  </g:form>
  <g:formRemote name="formRemoteStep3Prev" url="[action:'step2', params:params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
  </g:formRemote>
  <a href="#" class="ui-widget-previous left btn gray medium" id='js-prev-step-3'>Previous Step</a>
  <a href="#" class="ui-widget-next right btn" id='js-next-step-3'>Next step</a>
  <!-- end step 3 -->
</div>