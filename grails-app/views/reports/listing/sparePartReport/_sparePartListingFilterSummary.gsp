<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.spare.part.SparePart.SparePartStatus" %>
<%@ page import="org.chai.memms.spare.part.SparePart.SparePartStatusChange" %>
<div class="v-tabs-criteria">

  %{-- customized but unsaved listing --}%
  <g:if test="${customizedReportName != null && !customizedReportName.empty}">
    <h1>${customizedReportName}</h1>
    <a class="btn right gray medium push-r-15 inline-save save-custom-report" href="${createLink(action:'saveCustomizedReport', params: params)}">Save</a>
  </g:if>
  %{-- customized saved listing --}%
  <g:elseif test="${savedReport != null}">
    <h1>${savedReport.reportName}</h1>
  </g:elseif>
  %{-- predefined listing --}%
  <g:else>
    <h1>${reportName}</h1>
  </g:else>

  <ul>
    <li>
       <span><g:message code="reports.type.label"/>:</span>
      <a href="#">${message(code:'reports.type.'+reportType?.reportType)}</a>
    </li>

    <li>
      <span><g:message code="reports.subType.label"/>:</span>
      <a href="#">${message(code:'reports.subType.'+reportSubType?.reportSubType)}</a>
    </li>
  </ul>
  
  <ul>
    <g:if test="${savedReport != null || (customizedReportName != null && !customizedReportName.empty)}">
      <li>
        <span>Report Filter Summary:</span>
        <a href="#"><g:message code="reports.dataLocation"/> = 
          ${dataLocations?.size()}</a>,
        <a href="#"><g:message code="reports.spareParts.showAtMmc.label" /> = 
            ${showAtMmc?'&radic;':message(code:'reports.filters.none')}</a>,
        <a href="#"><g:message code="reports.sparePartType"/> = 
          ${sparePartTypes?.size()}</a>,

        <g:if test="${reportSubType == ReportSubType.INVENTORY}">
          <a href="#"><g:message code="listing.report.spare.part.compatible.equipment.types"/> = 
            <g:if test="${equipmentTypes != null && !equipmentTypes.empty}">
              ${equipmentTypes?.collect{it.names}?.join(',')}
            </g:if>
            <g:else>
              ${message(code:'reports.filters.none')},
            </g:else>
            </a>,
          <a href="#"><g:message code="reports.spareParts.inventory.sparePartStatus"/> = 
            <g:if test="${sparePartStatus == null || sparePartStatus.empty}">
              ${message(code:'reports.filters.all')},
            </g:if>
            <g:else>
              <g:each in="${SparePartStatus.values() - SparePartStatus.NONE}" var="statusEnum">
                <g:if test="${sparePartStatus?.contains(statusEnum)}">
                  ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
                </g:if>
              </g:each>
            </g:else>
          </a>
          <a href="#"><g:message code="reports.spareParts.inventory.acquisitionPeriod"/> =   
            <g:if test="${fromAcquisitionPeriod != null || toAcquisitionPeriod != null}">
              ${Utils.formatDate(fromAcquisitionPeriod)?:message(code:'reports.filters.none')} - 
              ${Utils.formatDate(toAcquisitionPeriod)?:message(code:'reports.filters.none')}</a>,
            </g:if>
            <g:else>
               ${message(code:'reports.filters.all')},
            </g:else>
          <a href="#"><g:message code="reports.spareParts.inventory.noAcquisitionPeriod.label"/> = 
            ${noAcquisitionPeriod?'&radic;':message(code:'reports.filters.none')}</a>
        </g:if>

        <g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
          <a href="#"><g:message code="reports.statusChanges"/> = 
            <g:if test="${statusChanges == null || statusChanges.empty}">
              ${message(code:'reports.filters.all')},
            </g:if>
            <g:else>
              <g:each in="${SparePartStatusChange.values()}" var="statusEnum">
                <g:if test="${statusChanges?.contains(statusEnum)}">
                  ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
                </g:if>
              </g:each>
            </g:else>
          </a>
          <a href="#"><g:message code="reports.statusChangesPeriod"/> =   
            <g:if test="${fromStatusChangesPeriod != null || toStatusChangesPeriod != null}">
              ${Utils.formatDate(fromStatusChangesPeriod)?:message(code:'reports.filters.none')} - 
              ${Utils.formatDate(toStatusChangesPeriod)?:message(code:'reports.filters.none')}</a>
            </g:if>
            <g:else>
               ${message(code:'reports.filters.all')}
            </g:else>
          </a>
        </g:if>
        <g:if test="${reportSubType == ReportSubType.STOCKOUT}">
          <a href="#"><g:message code="reports.spareParts.stockOut" /> = 
            <g:if test="${stockOutMonths != null && !stockOutMonths.empty}">
              ${stockOutMonths+' '+message(code:'reports.spareParts.stockOut.months')}
            </g:if>
            <g:else>${message(code:'reports.filters.all')}</g:else>
          </a>
        </g:if>
      </li>
    </g:if>
  </ul>
</div>