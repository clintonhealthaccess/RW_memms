<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="ajax" />
        <g:set var="entityName" value="${message(code: 'dashboard.explanation.label')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    
    <body>

		<ul class="box">
			<li>
				<div><g:i18n field="${normalizedDataElement.names}"/></div>
				<div class="row">
					<th><g:message code="entity.type.label"/></th>: <span class="type"><g:toHtml value="${normalizedDataElement.type.getDisplayedValue(2, null)}"/></span>
				</div>
				<div><g:i18n field="${normalizedDataElement.descriptions}"/></div>
				<div><th><g:message code="dataelement.values.number"/></th>: ${values}</div>
			</li>
			<li>
				<g:message code="normalizeddataelement.values.error.number"/>:
				<table> 
				<g:each in="${valuesWithError.entrySet()}" var="entry">
					<tr>
						<td><g:formatDate format="dd-MM-yyyy" date="${entry.key.startDate}"/></td>
						<td>${entry.value}</td>
					</tr>
				</g:each>
				</table>
			</li>
		</ul>
		
		<g:if test="${!referencingData.isEmpty()}">
			<g:render template="/entity/data/referencingDataList" model="[referencingData: referencingData]"/>
		</g:if>
		
	</body>
</html>

