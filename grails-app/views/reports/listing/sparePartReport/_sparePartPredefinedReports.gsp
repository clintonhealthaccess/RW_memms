<ul class="v-tabs-subnav">
	<li>
		<a class="${actionName == 'TODO'?'active':''}" 
			href="${createLinkWithTargetURI(controller: 'listing', action:'TODO')}" id="report-1">
			<g:message code="default.all.equipments.label" />
		</a></li>
	<li>
		<a class="${actionName == 'TODO'?'active':''}" 
		href="${createLinkWithTargetURI(controller: 'listing', action:'TODO')}" id="report-2">
			<g:message code="default.obsolete.label" />
		</a></li>
	<li>
		<a class="${actionName == 'TODO'?'active':''}" 
		href="${createLinkWithTargetURI(controller: 'listing', action:'TODO')}" id="report-3">
			<g:message code="default.disposed.label" />
		</a></li>
	<li>
		<a class="${actionName == 'TODO'?'active':''}" 
		href="${createLinkWithTargetURI(controller: 'listing', action:'TODO')}" id="report-4">
			<g:message code="default.under.maintenance.label" />
		</a></li>
	<li>
		<a class="${actionName == 'TODO'?'active':''}" 
		href="${createLinkWithTargetURI(controller: 'listing', action:'TODO')}" id="report-5">
			<g:message code="default.in.stock.label" />
		</a></li>
	<li>
		<a class="${actionName == 'TODO'?'active':''}" 
		href="${createLinkWithTargetURI(controller: 'listing', action:'TODO')}" id="report-6">
			<g:message code="default.under.waranty.label" />
		</a></li>
</ul>