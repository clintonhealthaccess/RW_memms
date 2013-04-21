<div class="dialog-form step-1" id='js-step-1'>
  <!-- Step 1 -->
  <p>ReportType: ${reportType}</p>
  <p>ReportSubType: ${reportSubType}</p>
  <h2>Select the type of customized report<span class="right">Step <b>1</b> of <b>4</b></span></h2>
  <g:formRemote name="formRemoteStep1" url="[action:'step2']" update="dialog-form" 
    onSuccess="customizedlisting_init();">
    <fieldset>
      <ul>
        <li>
          <!-- TODO -->
          <label for="report_type">Report Type</label>
          <select name="reportType" class="js-custom-report-type">
            <option value="0">Please select</option>
            <option value="inventory" ${reportType == 'inventory'?'selected':''}>
              <g:message code="header.navigation.inventory"/></option>
            <option value="corrective" ${reportType == 'corrective'?'selected':''}>
              <g:message code="header.navigation.corrective.maintenance"/></option>
            <option value="preventive" ${reportType == 'preventive'?'selected':''}>
              <g:message code="header.navigation.preventive.maintenance"/></option>
            <option value="spareParts" ${reportType == 'spareParts'?'selected':''}>
              <g:message code="header.navigation.sparePart"/></option>
          </select>
        </li>
        <li>
          <label for="report_type">Report Subtype</label>
          <select name="reportSubType" class="js-custom-report-subtype">
            <option value="0">Please select</option>
            <option data-report-type="inventory,spareParts" value="inventory">
              List of Inventory</option>
            <option data-report-type="corrective,preventive" value="workdOrders">
              List of Work Orders</option>
            <option data-report-type="inventory,corrective,preventive,spareParts" value="statusChanges">
              List of Status Changes</option>
            <option data-report-type="spareParts" value="useRate">
              Spare Part Use Rate</option>
            <option data-report-type="spareParts" value="stockOut">
              Forecast Stock Out</option>
          </select>
        </li>
      </ul>
    </fieldset>
    <a href="#" class="ui-widget-next right btn" id='js-next-step-1'>Next step</a>
  </g:formRemote>
  <!-- end step 1 -->
</div>