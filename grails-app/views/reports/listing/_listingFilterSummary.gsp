<%@ page import="org.chai.memms.util.Utils.ReportType" %>
<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<div class="v-tabs-criteria">
  <ul class="left">

    <li>
      <span><g:message code="reports.type.label"/></span>
      <a href="#">${message(code:'reports.type.'+reportType?.reportType)}</a>
    </li>

    <li>
      <span><g:message code="reports.subType.label"/></span>
      <a href="#">${message(code:'reports.subType.'+reportSubType?.reportSubType)}</a>
    </li>

    <li>
      <span><g:message code="reports.total.label"/></span>
      <g:if test="${reportType == ReportType.INVENTORY}">
          <a href="#"><g:message code="inventory.equipment.count"/> = ${entities?.size()}</a>
      </g:if>
      <g:if test="${reportType == ReportType.CORRECTIVE}">
        <a href="#"><g:message code="inventory.equipment.count"/> = ${entities?.size()}</a>
      </g:if>
      <g:if test="${reportType == ReportType.PREVENTIVE}">
        <a href="#"><g:message code="inventory.equipment.count"/> = ${entities?.size()}</a>
      </g:if>
      <g:if test="${reportType == ReportType.SPAREPARTS}">
        <a href="#">TODO Spare Parts = ${entities?.size()}</a>
      </g:if>
    </li>

    <g:if test="${customizedReportName != null && !customizedReportName.empty}">
      <li>
        <span><g:message code="reports.name.label"/></span>
        <a href="#">${customizedReportName}</a>
      </li>
    </g:if>
    <g:else>
      <li>
        <span><g:message code="reports.name.label"/></span>
        <a href="#">
          <g:if test="${reportType == ReportType.INVENTORY}">
            <g:if test="${actionName == 'generalEquipmentsListing'}">
              <g:message code="default.all.equipments.label" />
            </g:if>
            <g:if test="${actionName == 'obsoleteEquipments'}">
              <g:message code="default.obsolete.label" />
            </g:if>
            <g:if test="${actionName == 'disposedEquipments'}">
              <g:message code="default.disposed.label" />
            </g:if>
            <g:if test="${actionName == 'underMaintenanceEquipments'}">
             <g:message code="default.under.maintenance.label" />
            </g:if>
            <g:if test="${actionName == 'inStockEquipments'}">
              <g:message code="default.in.stock.label" />
            </g:if>
            <g:if test="${actionName == 'underWarrantyEquipments'}">
              <g:message code="default.under.waranty.label" />
            </g:if>
          </g:if>
          <g:if test="${reportType == ReportType.CORRECTIVE}">
            <g:if test="${actionName == 'generalWorkOrdersListing'}">
              TODO All Work Orders
            </g:if>
            <g:if test="${actionName == 'lastMonthWorkOrders'}">
              TODO Last Month Work Orders
            </g:if>
            <g:if test="${actionName == 'workOrdersEscalatedToMMC'}">
              TODO Work Orders Escalated to MMC
            </g:if>
          </g:if>
          <g:if test="${reportType == ReportType.PREVENTIVE}">
            <g:if test="${actionName == 'generalPreventiveOrdersListing'}">
              TODO All Preventions
            </g:if>
            <g:if test="${actionName == 'equipmentsWithPreventionPlan'}">
              TODO Equipments with Prevention Plan
            </g:if>
            <g:if test="${actionName == 'preventionsDelayed'}">
              TODO Preventions Delayed
            </g:if>
          </g:if>
           <g:if test="${reportType == ReportType.SPAREPARTS}">
            TODO
          </g:if>
        </a>
      </li>
    </g:else>

    <g:if test="${customizedReportName != null && !customizedReportName.empty}">
      <li>
        <span>Custom Report Filter Summary:</span>
        <g:if test="${reportType == ReportType.INVENTORY}">
          <a href="#"><g:message code="reports.dataLocation"/> = 
            ${customEquipmentParams?.dataLocations?.size()}</a>,
          <a href="#"><g:message code="reports.department"/> = 
            ${customEquipmentParams?.departments?.size()}</a>,
          <a href="#"><g:message code="reports.equipmentType"/> = 
            ${customEquipmentParams?.equipmentTypes?.size()}</a>,
          <a href="#"><g:message code="reports.cost"/> = 
            ${customEquipmentParams?.fromCost?:message(code:'reports.filters.none')} - 
            ${customEquipmentParams?.toCost?:message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.currency"/> = 
            ${customEquipmentParams?.costCurrency?:message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.inventory.inventory.acquisitionPeriod"/> =   
            ${customEquipmentParams?.fromAcquisitionPeriod?:message(code:'reports.filters.none')} - 
            ${customEquipmentParams?.toAcquisitionPeriod?:message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.inventory.inventory.noAcquisitionPeriod.label"/> = 
            ${customEquipmentParams?.noAcquisitionPeriod?'&radic;':message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.inventory.inventory.equipmentStatus"/> = 
            <g:if test="${customEquipmentParams?.equipmentStatus == null || customEquipmentParams?.equipmentStatus.empty}">
              ${message(code:'reports.filters.none')},
            </g:if>
            <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
              <g:if test="${customEquipmentParams?.equipmentStatus?.contains(statusEnum)}">
                ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
              </g:if>
            </g:each>
          </a>
          <a href="#"><g:message code="reports.inventory.inventory.obsolete.label" /> = 
            ${customEquipmentParams?.obsolete?'&radic;':message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.inventory.warranty.label"/> = 
            ${customEquipmentParams?.warranty?'&radic;':message(code:'reports.filters.none')}</a>
        </g:if>
        <g:if test="${reportType == ReportType.CORRECTIVE}">
          <a href="#">TODO</a>
        </g:if>
        <g:if test="${reportType == ReportType.PREVENTIVE}">
          <a href="#">TODO</a>
        </g:if>
        <g:if test="${reportType == ReportType.SPAREPARTS}">
          <a href="#">TODO</a>
        </g:if>
      </li>
    </g:if>
    <g:else>
      <li>
        <span>Report Filter Summary:</span>
        <g:if test="${reportType == ReportType.INVENTORY}">
          <a href="#">TODO</a>
        </g:if>
        <g:if test="${reportType == ReportType.CORRECTIVE}">
          <a href="#">TODO</a>
        </g:if>
        <g:if test="${reportType == ReportType.PREVENTIVE}">
          <a href="#">TODO</a>
        </g:if>
        <g:if test="${reportType == ReportType.SPAREPARTS}">
          <a href="#">TODO</a>
        </g:if>
      </li>
    </g:else>
  </ul>
</div>