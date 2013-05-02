<%@ page import="org.chai.memms.util.Utils.ReportType" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<div class="dialog-form step-3" id='js-step-3'>
  <!-- Step 3 -->
  <h2><g:message code="reports.select.optional.information"/><span class="right"><g:message code="reports.step" args="['3','3']"/></span></h2>
  <p>${message(code:'reports.type.label')}: <b>${message(code:'header.navigation.'+reportType.reportType)}</b> > ${message(code:'reports.subType.label')}: <b>${message(code:'reports.subType.'+reportSubType.reportSubType)}</b></p>
  <g:form name="formRemoteStep3Next" url="[action:'listing', params: step3Params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <ul class="twocol-checkboxes">
        <g:if test="${reportType == ReportType.INVENTORY}">
            <li><g:checkBox name="inventoryOptions" value="equipmentTypeName"/><span>Equipment Type Name</span></li>
            <li><g:checkBox name="inventoryOptions" value="equipmentTypeCode"/><span>Equipment Type Code</span></li>
            <li><g:checkBox name="inventoryOptions" value="manufacturerName"/><span>Manufacturer Name</span></li>
            <li><g:checkBox name="inventoryOptions" value="model"/><span>Model</span></li>
            <li><g:checkBox name="inventoryOptions" value="supplierName"/><span>Supplier Name</span></li>
            <li><g:checkBox name="inventoryOptions" value="equipmentLocation"/><span>Equipment Location</span></li>
            <li><g:checkBox name="inventoryOptions" value="acquisitionDate"/><span>Acquisition Date</span></li>
            <li><g:checkBox name="inventoryOptions" value="cost"/><span>Cost</span></li>
            <li><g:checkBox name="inventoryOptions" value="status"/><span>Status</span></li>
            <li><g:checkBox name="inventoryOptions" value="obsolete"/><span>Obsolete</span></li>
            <li><g:checkBox name="inventoryOptions" value="warrantyProviderName"/><span>Warranty Provider Name</span></li>
            <li><g:checkBox name="inventoryOptions" value="warrantyPeriodRemaining"/><span>Warranty Period Remaining</span></li>
            <li><g:checkBox name="inventoryOptions" value="currentValue"/><span>Current Value</span></li>
        </g:if>
%{-- TODO       
        <g:if test="${reportType == ReportType.CORRECTIVE}">
          <li><g:checkBox name="correctiveOptions" value="equipmentTypeName"/><span>Equipment Type Name</span></li>
          <li><g:checkBox name="correctiveOptions" value="manufacturerName"/><span>Manufacturer Name</span></li>
          <li><g:checkbox name="correctiveOptions" value="equipmentModel"/><span>Equipment Model</span></li>
          <li><g:checkbox name="correctiveOptions" value="equipmentSerialNumber"/><span>Equipment Serial Number</span></li>
          <li><g:checkbox name="correctiveOptions" value="equipmentLocation"/><span>Equipment Location</span></li>
          <li><g:checkbox name="correctiveOptions" value="workOrderStatus"/><span>Work Order Status</span></li>
          <li><g:checkbox name="correctiveOptions" value="workOrderTravelTime"/><span>Work Order Travel Time</span></li>
          <li><g:checkbox name="correctiveOptions" value="workOrderWorkTime"/><span>Work Order Work Time</span></li>
          <li><g:checkbox name="correctiveOptions" value="workOrderEstimatedCost"/><span>Work Order Estimated Cost</span></li>
          <li><g:checkbox name="correctiveOptions" value="departmentRequestedOrder"/><span>Department Requested Order</span></li>
          <li><g:checkbox name="correctiveOptions" value="reasonsEquipmentFailure"/><span>Reasons Equipment Failure</span></li>
          <li><g:checkbox name="correctiveOptions" value="listPerformedActions"/><span>List Performed Actions</span></li>
          <li><g:checkbox name="correctiveOptions" value="descriptionOfProblem"/><span>Description of Problem</span></li>
          <li><g:checkbox name="correctiveOptions" value="dateOfEvent"/><span>Date of Event</span></li>
        </g:if>
        <g:if test="${reportType == ReportType.PREVENTIVE}">
          <li><input type="checkbox" name="preventiveOptions"><span>Equipment Type Name</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Manufacturer Name</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Equipment Model</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Equipment Serial Number</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Equipment Location</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Work Order Status</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Schedule Next Interventions</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>% Interventions Done</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Recurrence Period</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Start Date</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Responsible</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Action Name</span></li>
          <li><input type="checkbox" name="preventiveOptions"><span>Action Description</span></li>
        </g:if>
        <g:if test="${reportType == ReportType.SPAREPARTS}">
          <g:if test="${[ReportSubType.INVENTORY,ReportSubType.STATUSCHANGES].contains(reportSubType)}">
            <li><input type="checkbox" name="spartPartOptions"><span>Spare Part Type</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Status</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Model</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Manufacturer</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Equipment Code</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Cost</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Warranty Period Remaining</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Discontinued Date</span></li>
          </g:if>
          <g:if test="${[ReportSubType.USERATE,ReportSubType.STOCKOUT].contains(reportSubType)}">
            <li><input type="checkbox" name="spartPartOptions"><span>Quantity in Stock</span></li>
            <li><input type="checkbox" name="spartPartOptions"><span>Use Rate</span></li>
          </g:if>
        </g:if> --}%
      </ul>
    </fieldset>
  </g:form>
  <g:formRemote name="formRemoteStep3Prev" url="[action:'step2', params:params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
  </g:formRemote>
  <a href="#" class="ui-widget-previous left btn gray medium" id='js-prev-step-3'><g:message code="reports.step.previous"/></a>
  <a href="#" class="ui-widget-next right btn" id='js-next-step-3'><g:message code="reports.step.submit"/></a>
  <!-- end step 3 -->
</div>