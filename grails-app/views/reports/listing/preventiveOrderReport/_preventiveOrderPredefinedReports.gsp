<div id='js-chosen-report' class='v-tabs-chosen-report'>
  <a href='#' class='active'>${selectedReport}</a>
</div>
<a class='v-tabs-subnav-scroll-left' href='#' id='js-scroll-left'><</a>
<a class='v-tabs-subnav-scroll-right' href='#' id='js-scroll-right'>></a>
<div class='v-tabs-subnav-wrapper slide-wrapper' id='#js-slider-wrapper'>
	<ul class="v-tabs-subnav slide">
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalPreventiveOrdersListing')}" id="report-1">
				<g:message code="default.all.preventive.order.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'equipmentsWithPreventionPlan')}" id="report-1">
				<g:message code="default.equipments.with.prevention.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'preventionsDelayed')}" id="report-2">
				<g:message code="default.preventions.delayed.label"/>
			</a>
		</li>
		%{-- TODO saved reports --}%
	</ul>
</div>