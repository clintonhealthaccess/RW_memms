<table class="items sub-items">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.description.label"/></th>
			<th><g:message code="entity.observation.label"/></th>
			<th><g:message code="equipmentType.expectedLifeTime.label"/></th>
			<th><g:message code="equipment.type.added.on.label"/></th>
			<th><g:message code="equipment.type.last.modified.on.label"/></th>
		</tr>
	</thead>
	<g:render template="/entity/equipmentType/listBody" model="[entities: entities]"/>
</table>