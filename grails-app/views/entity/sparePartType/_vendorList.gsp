
<table class="items sub-items">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.code.label"/></th>
			<th><g:message code="entity.name.label"/></th>
			<th><g:message code="entity.type.label"/></th>
			<th><g:message code="contact.email.label"/></th>
			<th><g:message code="contact.phone.label"/></th>
			<th><g:message code="contact.pobox.label"/></th>
			<th><g:message code="contact.city.label"/></th>
			<th><g:message code="contact.country.label"/></th>
			<th><g:message code="contact.address.descriptions.label"/></th>
		</tr>
	</thead>
	<g:render template="/entity/provider/listBody" model="[entities: entities]"/>
</table>
