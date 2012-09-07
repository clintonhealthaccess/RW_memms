<table class="items spaced">

	<thead>
		<tr>
			<th></th>
			<g:sortableColumn property="serialNumber" defaultOrder="asc" title="${message(code: 'equipment.serial.number.label', default: 'Serial Number')}" params="[q:q,location:dataLocation.id]" />
			<g:sortableColumn property="type" defaultOrder="asc" title="${message(code: 'equipment.type.label', default: 'Type')}" params="[q:q,location:dataLocation.id]" />
			<g:sortableColumn property="model" defaultOrder="asc" title="${message(code: 'equipment.model.label', default: 'Model')}" params="[q:q,location:dataLocation.id]" />
			<g:sortableColumn property="manufacturer" defaultOrder="asc" title="${message(code: 'provider.manufacturer.label', default: 'manufacturer')}" params="[q:q,location:dataLocation.id]" />
			<g:sortableColumn property="supplier" defaultOrder="asc" title="${message(code: 'provider.supplier.label', default: 'Supplier')}" params="[q:q,location:dataLocation.id]" />
			<th><g:message code="location.label"/></th>
			<th><g:message code="equipment.status.label"/></th>
			<g:sortableColumn property="obsolete" defaultOrder="asc" title="${message(code: 'equipment.obsolete.label', default: 'Obsolete')}" params="[q:q,location:dataLocation.id]" />
			<g:sortableColumn property="donation" defaultOrder="asc" title="${message(code: 'equipment.donation.label', default: 'Donation')}" params="[q:q,location:dataLocation.id]" />
		</tr>
	</thead>

	<tbody>
		<g:each in="${entities}" status="i" var="equipment">
			<tr >
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipment', action:'edit', params:[id: equipment.id,location: equipment.dataLocation.id])}"  class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'equipment', action:'delete', params:[id: equipment.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button">
								<g:message code="default.link.delete.label" />
							</a>
						</li>
					</ul>
				</td>
				<td>${equipment.serialNumber}</td>
				<td>${equipment.type.names}</td>
				<td>${equipment.model}</td>
				<td>${equipment.manufacturer.contact.contactName}</td>
				<td>${equipment.supplier.contact.contactName}</td>
				<td>
					<g:message code="datalocation.label"/>: ${equipment.dataLocation.names}<br/>
					<g:message code="department.label"/>: ${equipment.department.names}<br/>
					<g:message code="equipment.room.label"/>: ${equipment.room}<br/>
				</td>
				<td>
					<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'create', params:[equipment: equipment?.id])}" title="Update equipment status" class="tooltip">
  	    				${message(code: equipment.getCurrentState()?.status?.messageCode+'.'+equipment.getCurrentState()?.status?.name)}
  	    			</a>
				</td>
				<td>
					<g:listCheckBox name="obsolete" id="${equipment.id}" checked="${(!equipment.obsolete)?:'checked'}"/>
				</td>
				<td>
					<g:listCheckBox name="donation" id="${equipment.id}" checked="${(!equipment.donation)?:'checked'}"/>
				</td>
			</tr>
		</g:each>
	</tbody>	
</table>
<script type="text/javascript">
	$(document).ready(function() {
		var url = "${createLink(controller:'equipment',action: 'updateDonationAndObsolete')}"
		updateEquipment(url);
	});
</script>
