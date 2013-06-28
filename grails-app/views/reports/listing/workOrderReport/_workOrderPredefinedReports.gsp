<div class="v-tabs-dynav-wrap">
  <div id='js-chosen-report' class='v-tabs-chosen-report'>
    <a href='#' class='active'>${selectedReport}</a>
  </div>
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
	    <li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalWorkOrdersListing')}" id="report-1"
				class="tooltip" title="${message(code:'default.all.work.order.label')}">
				<g:message code="default.all.work.order.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'lastMonthWorkOrders')}" id="report-2"
				class="tooltip" title="${message(code:'default.work.order.last.month.label')}">
				<g:message code="default.work.order.last.month.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'workOrdersEscalatedToMMC')}" id="report-3"
				class="tooltip" title="${message(code:'default.work.order.escalated.to.mmc.label')}">
				<g:message code="default.work.order.escalated.to.mmc.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'workOrdersEscalatedToMMC')}" id="report-4"
				class="tooltip" title="${message(code:'default.work.order.escalated.to.mmc.label')}">
				<g:message code="default.work.order.escalated.to.mmc.label"/>
			</a>
			<span class='delete-node' id='js-delete-node'>X</span>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'lastYearClosedWorkOrders')}" id="report-4">
				<g:message code="default.work.order.last.year.label"/>
			</a>
		</li>
			%{-- TODO saved reports --}%
	</ul>

  </div>
  <a class='v-tabs-dynav-scroll-left' href='#' id='js-scroll-left'></a>
</div>