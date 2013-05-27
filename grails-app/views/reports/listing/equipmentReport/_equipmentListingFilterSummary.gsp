<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<%@ page import="org.chai.memms.inventory.EquipmentStatus.Status" %>
<%@ page import="org.chai.memms.inventory.EquipmentStatus.EquipmentStatusChange" %>
<div class="v-tabs-criteria">
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
        <a href="#"><g:message code="inventory.equipment.count"/> = ${entities?.size()}</a>
    </li>

    <g:if test="${customizedReportName != null && !customizedReportName.empty}">
      <li>
        <span>Custom Report Name:</span>
        <a href="#">${customizedReportName}</a>
      </li>
    </g:if>
    <g:else>
      <li>
        <span>Report Name:</span>
        <a href="#">
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
        </a>
      </li>
    </g:else>

    <g:if test="${customizedReportName != null && !customizedReportName.empty}">
      <li>
        <span>Custom Report Filter Summary:</span>
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

        <g:if test="${reportSubType == ReportSubType.INVENTORY}">
          <a href="#"><g:message code="reports.inventory.inventory.equipmentStatus"/> = 
            <g:if test="${customEquipmentParams?.equipmentStatus == null || customEquipmentParams?.equipmentStatus.empty}">
              ${message(code:'reports.filters.none')},
            </g:if>
            <g:else>
              <g:each in="${Status.values() - Status.NONE}" var="statusEnum">
                <g:if test="${customEquipmentParams?.equipmentStatus?.contains(statusEnum)}">
                  ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
                </g:if>
              </g:each>
            </g:else>
          </a>
          
          <a href="#"><g:message code="reports.inventory.inventory.acquisitionPeriod"/> =   
            ${customEquipmentParams?.fromAcquisitionPeriod?:message(code:'reports.filters.none')} - 
            ${customEquipmentParams?.toAcquisitionPeriod?:message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.inventory.inventory.noAcquisitionPeriod.label"/> = 
            ${customEquipmentParams?.noAcquisitionPeriod?'&radic;':message(code:'reports.filters.none')}</a>,
          
          <a href="#"><g:message code="reports.inventory.inventory.obsolete.label" /> = 
            ${customEquipmentParams?.obsolete?'&radic;':message(code:'reports.filters.none')}</a>,
          <a href="#"><g:message code="reports.inventory.warranty.label"/> = 
            ${customEquipmentParams?.warranty?'&radic;':message(code:'reports.filters.none')}</a>
        </g:if>

        <g:if test="${reportSubType == ReportSubType.STATUSCHANGES}">
          <a href="#"><g:message code="reports.statusChanges"/> = 
            <g:if test="${customEquipmentParams?.statusChanges == null || customEquipmentParams?.statusChanges.empty}">
              ${message(code:'reports.filters.none')},
            </g:if>
            <g:else>
              <g:each in="${EquipmentStatusChange.values()}" var="statusEnum">
                <g:if test="${customEquipmentParams?.statusChanges?.contains(statusEnum)}">
                  ${message(code: statusEnum?.messageCode+'.'+statusEnum?.name)},
                </g:if>
              </g:each>
            </g:else>
          </a>
          <a href="#"><g:message code="reports.statusChangesPeriod"/> =   
            ${customEquipmentParams?.fromStatusChangesPeriod?:message(code:'reports.filters.none')} - 
            ${customEquipmentParams?.toStatusChangesPeriod?:message(code:'reports.filters.none')}</a>  
        </g:if>

      </li>
    </g:if>
    <g:else>
      <li>
        <span>Report Filter Summary:</span>
        <a href="#">TODO</a>
      </li>
    </g:else>
  </ul>
</div>