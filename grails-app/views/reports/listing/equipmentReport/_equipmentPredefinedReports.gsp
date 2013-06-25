<div class="v-tabs-dynav-wrap">
  <div id='js-chosen-report' class='v-tabs-chosen-report'>
    <a href='#' class='active'>${selectedReport}</a>
  </div>
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'generalEquipmentsListing')}" id="report-1" 
          class="tooltip" title="${message(code:'default.all.equipments.label')}">
          <g:message code="default.all.equipments.label" />
        </a>
      </li>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'obsoleteEquipments')}" id="report-2"
          class="tooltip" title="${message(code:'default.obsolete.label')}">
          <g:message code="default.obsolete.label" />
        </a>
      </li>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'disposedEquipments')}" id="report-3"
          class="tooltip" title="${message(code:'default.disposed.label')}">
          <g:message code="default.disposed.label" />
        </a>
      </li>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'underMaintenanceEquipments')}" id="report-4"
          class="tooltip" title="${message(code:'default.under.maintenance.label')}">
          <g:message code="default.under.maintenance.label" />
        </a>
      </li>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'inStockEquipments')}" id="report-5"
          class="tooltip" title="${message(code:'default.in.stock.label')}">
          <g:message code="default.in.stock.label" />
        </a>
      </li>
      <g:each in="${savedReports}" var="savedReport">
        <li>
          <a href="${createLinkWithTargetURI(controller: 'listing', action:'savedCustomizedListing', params: [id: savedReport.id])}"
            class="tooltip" title="${savedReport.reportName}">
            ${savedReport.reportName}
          </a>
          <span class='delete-node' id='js-delete-node' data-saved-report-id="${savedReport.id}">X</span>
        </li>
      </g:each>
    </ul>
  </div>
  <a class='v-tabs-dynav-scroll-left' href='#' id='js-scroll-left'></a>
</div>
