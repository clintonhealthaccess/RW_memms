<%@ page import="org.chai.memms.util.Utils.ReportType" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<div class="dialog-form step-2" id='js-step-2'>
  <!-- Step 2 -->
  <h2><g:message code="reports.apply.filters"/><span class="right"><g:message code="reports.step" args="['2','3']"/></span></h2>
  <p>${message(code:'reports.type.label')}: <b>${message(code:'header.navigation.'+reportType?.reportType)}</b> > ${message(code:'reports.subType.label')}: <b>${message(code:'reports.subType.'+reportSubType?.reportSubType)}</b></p>
  <g:formRemote name="formRemoteStep2Next" url="[action:'step3', params:step2Params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
    <fieldset>
      <g:if test="${reportType == ReportType.INVENTORY}">
        <g:render template="/entity/reports/inventory/inventory"/>
      </g:if>
      <g:if test="${reportType == ReportType.CORRECTIVE}">
        <g:render template="/entity/reports/corrective"/>
      </g:if>
      <g:if test="${reportType == ReportType.PREVENTIVE}">
        <g:render template="/entity/reports/preventive"/>
      </g:if>
      <g:if test="${reportType == ReportType.SPAREPARTS}">
        <g:render template="/entity/reports/preventive"/>
      </g:if>
    </fieldset>
  </g:formRemote>
  <g:formRemote name="formRemoteStep2Prev" url="[action:'step1', params:params]" update="dialog-form"
    onSuccess="customizedlisting_init();">
  </g:formRemote>
  <a href="#" class="ui-widget-previous left btn gray medium" id='js-prev-step-2'><g:message code="reports.step.previous"/></a>
  <a href="#" class="ui-widget-next right btn" id='js-next-step-2'><g:message code="reports.step.next"/></a>
  <!-- end step 2 -->
</div>