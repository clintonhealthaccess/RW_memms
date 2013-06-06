<div id='js-chosen-report' class='v-tabs-chosen-report'>
  <a href='#' class='active'>${selectedReport}</a>
</div>
<a class='v-tabs-subnav-scroll-left' href='#' id='js-scroll-left'><</a>
<a class='v-tabs-subnav-scroll-right' href='#' id='js-scroll-right'>></a>
<div class='v-tabs-subnav-wrapper slide-wrapper' id='#js-slider-wrapper'>
  <ul class="v-tabs-subnav slide">
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'generalEquipmentsListing')}" id="report-1">
              <g:message code="default.all.equipments.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'obsoleteEquipments')}" id="report-2">
              <g:message code="default.obsolete.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'disposedEquipments')}" id="report-3">
              <g:message code="default.disposed.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'underMaintenanceEquipments')}" id="report-4">
              <g:message code="default.under.maintenance.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'inStockEquipments')}" id="report-5">
              <g:message code="default.in.stock.label" />
      </a>
    </li>
    %{-- TODO saved reports --}%
  </ul>
</div>
