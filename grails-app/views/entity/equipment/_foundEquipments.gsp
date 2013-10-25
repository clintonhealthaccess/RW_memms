<div class="list-template">
	<table class="items spaced">
		<thead>
			<tr>
				<th><g:message code="location.label"/></th>
				<th><g:message code="equipment.code.label"/></th>
				<th><g:message code="equipment.type.label"/></th>
				<th><g:message code="equipment.model.label"/></th>
				<th><g:message code="equipment.status.label"/></th>
				<th><g:message code="provider.type.manufacturer"/></th>
				<th><g:message code="equipment.obsolete.label"/></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${entities}" status="i" var="equipment">
				<tr >
					<td>
						<g:message code="datalocation.label"/>: ${equipment.dataLocation.names}<br/>
						<g:message code="department.label"/>: ${equipment.department.names}<br/>
						<g:message code="equipment.room.label"/>: ${equipment.room}<br/>
					</td>
					<td>${equipment.code}</td>
					<td>${equipment.type.names}</td>
					<td>${equipment.model}</td>
					<td>${message(code: equipment.currentStatus?.messageCode+'.'+equipment.currentStatus?.name)}</td>
					<td>${equipment.manufacturer?.contact?.contactName}</td>
					<td>${(!equipment.obsolete)?'&radic;':''}</td>
				</tr>
			</g:each>
		</tbody>	
	</table>
	<g:render template="/templates/pagination" model="[entities:entities, entityCount:entities.totalCount]" />
</div>