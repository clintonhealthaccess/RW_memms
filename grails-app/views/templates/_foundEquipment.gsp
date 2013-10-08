<table class="items spaced">
	<thead>
		<tr>
			<th><g:message code="location.label"/></th>
			<g:sortableColumn property="code"  title="${message(code: 'equipment.code.label')}" params="[q:q]" />
			<g:sortableColumn property="type"  title="${message(code: 'equipment.type.label')}" params="[q:q]" />
			<g:sortableColumn property="model"  title="${message(code: 'equipment.model.label')}" params="[q:q]" />
			<g:sortableColumn property="currentStatus"  title="${message(code: 'equipment.status.label')}" params="[q:q]" />
			<g:sortableColumn property="obsolete"  title="${message(code: 'equipment.obsolete.label')}" params="[q:q]" />
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
				<td>${(!equipment.obsolete)?:'X'}</td>
			</tr>
		</g:each>
	</tbody>	
</table>
<g:render template="/templates/pagination" model="[entities:entities, entityCount:entities.totalCount]" />
