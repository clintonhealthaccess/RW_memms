<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityClass" value="${entityClass}" />
		<title><g:message code="preventiveMaintenance.summary.title" /></title>
		<r:require modules="chosen, dropdown"/>
	</head>
	<body>
		<div>
			<div class="filter-bar">
				<g:locationFilter linkParams="${[order:'desc']}" selected="${currentLocation}" selectedTypes="${currentLocationTypes}" skipLevels="${locationSkipLevels}"/>
				<g:dataLocationTypeFilter linkParams="${params}" selected="${currentLocationTypes}"/>
			</div>			
			<div class="main table">
				<g:if test="${preventiveMaintenances == null}">
					<p class="nav-help"> <g:message code="${preventiveMaintenance.summary.selectlocation.text"/></p>
				</g:if>
				<g:else>
				  <br/>
					<h4 class="section-title alt">
            <span class="question-default">
              <img src="${resource(dir:'images/icons',file:'star_small.png')}">
            </span>
            <g:message code="location.label"/>: ${currentLocation.names}
          </h4>
					<g:render template="${template}"/>				
				</g:else>
				
			</div>
		</div>
	</body>
</html>