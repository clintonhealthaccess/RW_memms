<%@page import="org.chai.kevin.util.Utils"%>
<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label" /></th>
			<th><g:message code="period.startdate.label" /></th>
			<th><g:message code="period.enddate.label" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="period">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a class="edit-link" href="${createLinkWithTargetURI(controller:'period', action:'edit', params:[id: period.id])}">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a class="delete-link" href="${createLinkWithTargetURI(controller:'period', action:'delete', params:[id: period.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>${period.code}</td>
				<td>${Utils.formatDate(period.startDate)}</td>
				<td>${Utils.formatDate(period.endDate)}</td>
			</tr>
		</g:each>
	</tbody>
</table>
