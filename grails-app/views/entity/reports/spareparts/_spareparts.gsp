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
    <!-- TODO sparePartTypes -->
    <g:selectFromList name="sparePartTypes" label="Spare Part Type"
      field="type" optionKey="id" multiple="true" value="${sparePartTypes*.id}"
      ajaxLink="${createLink(controller:'SparePartType', action:'getAjaxData', params:[observation:'USEDINMEMMS'])}"
      values="${sparePartTypes.collect{it.names + ' ['+ it.code +']'}}"/>
    <g:checkBox name="allSparePartTypes" class='js-select-all'/>
    <span><g:message code="reports.selectAll"/></span>
  </li>
</ul>
<g:if test="${reportSubType == ReportSubType.INVENTORY}">
  <g:render template="/entity/reports/spareParts/spareparts_inventory"/>
</g:if>
<g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
  <g:render template="/entity/reports/spareParts/spareparts_statuschanges"/>
</g:if>
<g:if test="${reportSubType == ReportSubType.STOCKOUT}">
  <g:render template="/entity/reports/spareParts/spareparts_stockout"/>
</g:if>