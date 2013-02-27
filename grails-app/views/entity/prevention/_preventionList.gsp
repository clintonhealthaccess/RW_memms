
<table class="items">
	<thead>
		<tr>
			<th/>
			<th><g:message code="prevention.label"/></th>
			<th><g:message code="prevention.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="order">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'prevention', action:'edit', 
							params:[id: prevention.id])}" class="edit-button"> 
							<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'prevention', action:'delete', 
							params:[id: prevention.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button">
							<g:message code="default.link.delete.label" />
							</a>
						</li>	
					</ul>
				</td>
				<td>
					${prevention}
				</td>
				<td>
					<g:stripHtml field="${prevention.descriptions}" chars="30"/>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" />
<script type="text/javascript">
	$(document).ready(function() {
		getDatePicker("${resource(dir:'images',file:'icon_calendar.png')}")
		showClutips()
	});
</script>
