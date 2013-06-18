
<div class="v-tabs-dynav-wrap">
  <div id='js-chosen-report' class='v-tabs-chosen-report'>
    <a href='#' class='active'>${selectedReport}</a>
  </div>
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'generalEquipmentsListing')}" id="report-1" class="tooltip" title="asdasdasda asdasd asdas dasdasd asd asd asd asd">
          <g:message code="default.all.equipments.label" /> with an extended title
        </a>
        <span class='delete-node' id='js-delete-node'>X</span>
      </li>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'obsoleteEquipments')}" id="report-2">
          <g:message code="default.obsolete.label" />
        </a>
        <span class='delete-node' id='js-delete-node'>X</span>
      </li>
      <li>
        <a href="${createLinkWithTargetURI(controller: 'listing', action:'disposedEquipments')}" id="report-3">
          <g:message code="default.disposed.label" />
        </a>
        <span class='delete-node' id='js-delete-node'>X</span>
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
  <a class='v-tabs-dynav-scroll-left' href='#' id='js-scroll-left'></a>
</div>





<!-- 

<div class="v-tabs-box">
  <div id='js-chosen-report' class='v-tabs-chosen-report'>
    <a href='#' class='active'>${selectedReport}</a>
  </div>
  <a class='v-tabs-subnav-scroll-left' href='#' id='js-scroll-left'></a>
  
  <div class='v-tabs-subnav-wrapper slide-wrapper' id='js-slider-wrapper'>
    <ul class="v-tabs-subnav slide">
      
    </ul>
  </div>
  <a class='v-tabs-subnav-scroll-right' href='#' id='js-scroll-right'></a>
</div>

