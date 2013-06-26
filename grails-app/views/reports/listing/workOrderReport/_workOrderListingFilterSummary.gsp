<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.WorkOrderStatusChange" %>
<div class="v-tabs-criteria">

  <g:if test="${customizedReportName != null && !customizedReportName.empty}">
    <h1>${customizedReportName}</h1>
  </g:if>
  <g:else>
    <h1>${selectedReport}</h1>
  </g:else>

  <ul class="left">

    <li>
      <span>Report Type:</span>
      <a href="#">${message(code:'reports.type.'+reportType?.reportType)}</a>
    </li>

    <li>
      <span>Report Subtype:</span>
      <a href="#">${message(code:'reports.subType.'+reportSubType?.reportSubType)}</a>
    </li>

    <li>
      <span>Report Total:</span>
      <a href="#"><g:message code="work.orders.label"/> = ${entities?.size()}</a>
    </li>

    <g:if test="${customizedReportName != null && !customizedReportName.empty}">
      <li>
        <span>Custom Report Filter Summary:</span>
        <a href="#"><g:message code="reports.dataLocation"/> = 
          ${customWorkOrderParams?.dataLocations?.size()}</a>,
        <a href="#"><g:message code="reports.department"/> = 
          ${customWorkOrderParams?.departments?.size()}</a>,
        <a href="#"><g:message code="reports.equipmentType"/> = 
          ${customWorkOrderParams?.equipmentTypes?.size()}</a>,
        <a href="#"><g:message code="reports.cost"/> = 
          ${customWorkOrderParams?.fromCost?:message(code:'reports.filters.none')} - 
          ${customWorkOrderParams?.toCost?:message(code:'reports.filters.none')}</a>,
        <a href="#"><g:message code="reports.currency"/> = 
          ${customWorkOrderParams?.costCurrency?:message(code:'reports.filters.none')}</a>,

        <g:if test="${reportSubType == ReportSubType.WORKORDERS}">
          <a href="#"><g:message code="reports.workOrderStatus"/> =   
            <g:if test="${customWorkOrderParams?.workOrderStatus == null || customWorkOrderParams?.workOrderStatus.empty}">
              ${message(code:'reports.filters.none')},
            </g:if>
            <g:else>
              <g:each in="${OrderStatus.values() - OrderStatus.NONE}" var="statusEnum">
                <g:if test="${customWorkOrderParams?.workOrderStatus?.contains(statusEnum)}">
                  ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
                </g:if>
              </g:each>
            </g:else>
          </a>
          <a href="#"><g:message code="reports.workOrderPeriod"/> =   
            ${customWorkOrderParams?.fromWorkOrderPeriod?:message(code:'reports.filters.none')} - 
            ${customWorkOrderParams?.toWorkOrderPeriod?:message(code:'reports.filters.none')}</a>,        
          
          <a href="#"><g:message code="reports.inventory.warranty.label"/> = 
            ${customWorkOrderParams?.warranty?'&radic;':message(code:'reports.filters.none')}</a>
        </g:if>

        <g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
          <a href="#"><g:message code="reports.statusChanges"/> = 
            <g:if test="${customWorkOrderParams?.statusChanges == null || customWorkOrderParams?.statusChanges.empty}">
              ${message(code:'reports.filters.none')},
            </g:if>
            <g:else>
              <g:each in="${WorkOrderStatusChange.values()}" var="statusEnum">
                <g:if test="${customWorkOrderParams?.statusChanges?.contains(statusEnum)}">
                  ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
                </g:if>
              </g:each>
            </g:else>
          </a>
          <a href="#"><g:message code="reports.statusChangesPeriod"/> =   
            ${customWorkOrderParams?.fromStatusChangesPeriod?:message(code:'reports.filters.none')} - 
            ${customWorkOrderParams?.toStatusChangesPeriod?:message(code:'reports.filters.none')}</a>,
          
          <a href="#"><g:message code="reports.inventory.warranty.label"/> = 
            ${customWorkOrderParams?.warranty?'&radic;':message(code:'reports.filters.none')}</a>
        </g:if>

      </li>
    </g:if>
%{-- TODO    
    <g:else>
      <li>
        <span>Report Filter Summary:</span>
        <a href="#">TODO</a>
      </li>
    </g:else> --}%
  </ul>
</div>