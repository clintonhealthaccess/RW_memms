<table class="items">
	<thead>
		<tr>
			<th><g:message code="notification.sender.label"/></th>
			<th><g:message code="notification.writtenOn.label"/></th>
			<th><g:message code="notification.content.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${notifications}" status="i" var="notification">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					${notification?.sender}
				</td>
				<td>
					${notification?.writtenOn}
				</td>
				<td><g:stripHtml field="${notification?.content}" chars="30"/></td>
			</tr>
		</g:each>
	</tbody>
</table>