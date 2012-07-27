<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="ajax" />
        <g:set var="entityName" value="${message(code: 'dashboard.explanation.label')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    
    <body>

		<div class="box">
			<div><g:i18n field="${rawDataElement.names}"/></div>
			<div class="row"><g:message code="entity.type.label"/>: <span class="type"><g:toHtml value="${rawDataElement.type.getDisplayedValue(2, null)}"/></span></div>
			<div><g:i18n field="${rawDataElement.descriptions}"/></div>
			<div class="clear"></div>
		</div>
		
		<g:if test="${surveyElements.size()!=0}">
			<table class="listing">
				<thead>
					<tr>
						<th><g:message code="period.label"/></th>
						<th><g:message code="survey.label"/></th>
						<th><g:message code="survey.question.label"/></th>
						<th><g:message code="rawdataelement.surveyelement.location.applicable.label"/></th>
					</tr>
				</thead>
				<tbod>
					<g:each in="${surveyElements}" status="i" var="surveyElement"> 
						<g:set var="question" value="${surveyElement.key.surveyQuestion}" /> 
						<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
							<td>${question.section.program.survey.period.startDate} &harr; ${question.section.program.survey.period.endDate}</td>
							<td>${i18n(field:question.section.program.survey.names)}</td>
							<td><g:stripHtml field="${i18n(field: question.names)}" chars="100"/></a></td>
							<td>${surveyElement.value}</td>
						</tr>
					</g:each>
				</tbod>
			</table>
		</g:if>
		<g:else>
			<g:message code="rawdataelement.surveyelement.notassociated"/>
		</g:else>

		<table class="listing">
			<thead>
				<tr>
					<th><g:message code="period.label"/></th>
					<th><g:message code="dataelement.values.number"/></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${periodValues}" status="i" var="periodValue"> 
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td>${periodValue.key.startDate} &harr; ${periodValue.key.endDate}</td>
						<td>${periodValue.value}</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		
		<g:if test="${!referencingData.isEmpty()}">
			<g:render template="/entity/data/referencingDataList" model="[referencingData: referencingData]"/>
		</g:if>
		
	</body>
</html>

