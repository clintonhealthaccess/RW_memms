<g:if test="${data!=null}">
	<div class="push-10">
		<g:message code="dataelement.label"/>: <g:i18n field="${data.names}"/> - ${data.code}
	</div>
</g:if>
<div class="main">
	<g:render template="/entity/value/periodTabs"/>
	<g:if test="${!entities.empty}">
		<table class="listing">
			<thead>
				<tr>
					<th><g:message code="dataelementvalue.location.label"/></th>
					<g:if test="${data != null}">
						<g:sortableColumn property="status" title="${message(code: 'dataelementvalue.status.label')}" params="[data: data.id, period: selectedPeriod.id, q: params.q]"/>
					</g:if>
					<g:else>
						<th><g:message code="dataelementvalue.status.label"/></th>
					</g:else>
					<th><g:message code="dataelementvalue.value.label"/></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${entities}" status="i" var="value"> 
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td><g:i18n field="${value.location.names}"/></td>
						<td>${value.status}</td>
						<td><g:adminValue value="${value.value}" type="${value.data.type}"/></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
</div>