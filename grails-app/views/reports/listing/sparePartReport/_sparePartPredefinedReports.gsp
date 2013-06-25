<div class="v-tabs-dynav-wrap">
  <div id='js-chosen-report' class='v-tabs-chosen-report'>
    <a href='#' class='active'>${selectedReport}</a>
  </div>
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
	    <li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalSparePartsListing')}" id="report-1"
				class="tooltip" title="${message(code:'TODO All Spare Parts')}">
				TODO All Spare Parts
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'pendingOrderSparePartsListing')}" id="report-2"
				class="tooltip" title="${message(code:'TODO Pending Order Spare Parts')}">
				TODO Pending Order Spare Parts
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'pendingOrderSparePartsListing')}" id="report-2"
				class="tooltip" title="${message(code:'TODO Pending Order Spare Parts')}">
				TODO Pending Order Spare Parts
			</a>
			<span class='delete-node' id='js-delete-node'>X</span>
		</li>
      %{-- TODO saved reports --}%
    </ul>
  </div>
  <a class='v-tabs-dynav-scroll-left' href='#' id='js-scroll-left'></a>
</div>