<div class="dialog-form step-3" id='js-step-3'>
  <!-- Step 3 -->
  <p>${dataLocations}</p>
  <%
    step3Params = [:]
    step3Params.putAll params
  %>
  <h2>Choose the order<span class="right">Step <b>3</b> of <b>3</b></span></h2>
  <p>Type: ${reportType} > Subtype: ${reportSubType}</p>
  <g:form name="formRemoteStep3Next" url="[action:'listing', params: step3Params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <ul class="twocol-checkboxes">
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
        <li><input type="checkbox"><span>Lorem ipsum dolor sit amet</span></li>
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