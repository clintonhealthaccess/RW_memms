<table class="items">
	<thead>
		<tr>
			<th><g:message code="notification.sender.label"/></th>
			<th><g:message code="notification.writtenOn.label"/></th>
			<th><g:message code="notification.content.label"/></th>
			<th/>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="notification">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					${notification?.sender?.firstname +" "+ notification?.sender?.lastname}
				</td>
				<td>
					${notification?.writtenOn}
				</td>
				<td><g:stripHtml field="${notification?.content}" chars="30"/></td>
				<td><a href="${createLinkWithTargetURI(controller:'notification', action:'read', params:[id: notification.id])}">
								<g:message code="notification.read.label" />
							</a></td>
			</tr>
		</g:each>
	</tbody>
</table>