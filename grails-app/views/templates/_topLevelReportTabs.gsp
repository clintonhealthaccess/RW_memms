<ul class="horizontal tab-navigation">
	
	<li><a ${controllerName == 'dashboard' ? 'class="selected"':''}
		href="${createLink(controller:'dashboard', action:actionName, params:linkParams)}">
		<g:message code="header.navigation.reports.dashboard"/></a>
	</li>
	<li><a ${controllerName == 'dsr' ? 'class="selected"':''}
		href="${createLink(controller:'dsr', action:actionName, params:linkParams)}">
		<g:message code="header.navigation.reports.dsr"/></a>
	</li>
	<li><a ${controllerName == 'fct' ? 'class="selected"':''}
		href="${createLink(controller:'fct', action:actionName, params:linkParams)}">
		<g:message code="header.navigation.reports.fct"/></a>
	</li>	 		
</ul>