<div id='js-chosen-report' class='v-tabs-chosen-report'>
  <a href='#' class='active'>${selectedReport}</a>
</div>
<a class='v-tabs-subnav-scroll-left' href='#' id='js-scroll-left'><</a>
<a class='v-tabs-subnav-scroll-right' href='#' id='js-scroll-right'>></a>
<div class='v-tabs-subnav-wrapper slide-wrapper' id='#js-slider-wrapper'>
	<ul class="v-tabs-subnav slide">
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalWorkOrdersListing')}" id="report-1">
				<g:message code="default.all.work.order.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'lastMonthWorkOrders')}" id="report-2">
				<g:message code="default.work.order.last.month.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'workOrdersEscalatedToMMC')}" id="report-3">
				<g:message code="default.work.order.escalated.to.mmc.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'lastYearClosedWorkOrders')}" id="report-4">
				<g:message code="default.work.order.closed.last.year.label"/>
			</a>
		</li>
			%{-- TODO saved reports --}%
	</ul>
</div>