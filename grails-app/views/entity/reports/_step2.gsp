<div class="dialog-form step-2" id='js-step-2'>
  <!-- Step 2 -->
  <p>${dataLocations}</p>
  <h2>Apply filters<span class="right">Step <b>2</b> of <b>3</b></span></h2>
  <p>Type: ${reportType} > Subtype: ${reportSubType}</p>
  <g:formRemote name="formRemoteStep2Next" url="[action:'step3', params:step2Params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <g:if test="${reportType == 'inventory'}">
        <g:render template="/entity/reports/inventory/inventory"/>
      </g:if>
      <g:if test="${reportType == 'corrective'}">
        <g:render template="/entity/reports/corrective"/>
      </g:if>
      <g:if test="${reportType == 'preventive'}">
        <g:render template="/entity/reports/preventive"/>
      </g:if>
      <g:if test="${reportType == 'spareparts'}">
        <g:render template="/entity/reports/preventive"/>
      </g:if>
    </fieldset>
  </g:formRemote>
  <g:formRemote name="formRemoteStep2Prev" url="[action:'step1', params:params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
  </g:formRemote>
  <a href="#" class="ui-widget-previous left btn gray medium" id='js-prev-step-2'>Previous Step</a>
  <a href="#" class="ui-widget-next right btn" id='js-next-step-2'>Next step</a>
  <!-- end step 2 -->
</div>