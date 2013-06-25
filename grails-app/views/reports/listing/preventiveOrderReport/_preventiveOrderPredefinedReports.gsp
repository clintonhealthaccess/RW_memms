<div class="v-tabs-dynav-wrap">
  <div id='js-chosen-report' class='v-tabs-chosen-report'>
    <a href='#' class='active'>${selectedReport}</a>
  </div>
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
	    <li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalPreventiveOrdersListing')}" id="report-1"
				class="tooltip" title="${message(code:'default.all.preventive.order.label')}">
				<g:message code="default.all.preventive.order.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'equipmentsWithPreventionPlan')}" id="report-2"
				class="tooltip" title="${message(code:'default.equipments.with.prevention.label')}">
				<g:message code="default.equipments.with.prevention.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'preventionsDelayed')}" id="report-3"
				class="tooltip" title="${message(code:'default.preventions.delayed.label')}">
				<g:message code="default.preventions.delayed.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'preventionsDelayed')}" id="report-4"
				class="tooltip" title="${message(code:'default.preventions.delayed.label')}">
				<g:message code="default.preventions.delayed.label"/>
			</a>
			<span class='delete-node' id='js-delete-node'>X</span>
		</li>
      %{-- TODO saved reports --}%
    </ul>
  </div>
  <a class='v-tabs-dynav-scroll-left' href='#' id='js-scroll-left'></a>
</div>