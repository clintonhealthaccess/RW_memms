<g:programFilter linkParams="${params}" selected="${currentProgram}"
	selectedTargetClass="${selectedTargetClass}" 
	exclude="${['dashboardEntity', 'dsrCategory', 'fctTarget']}" />
<g:locationFilter linkParams="${params}" selected="${currentLocation}" 
	selectedTypes="${currentLocationTypes}"
	skipLevels="${skipLevels}"/>
<g:periodFilter linkParams="${params}" selected="${currentPeriod}"/>
<g:dataLocationTypeFilter linkParams="${params}" selected="${currentLocationTypes}" />