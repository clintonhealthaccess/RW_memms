<%@ page import="org.chai.memms.util.Utils" %>
<div id="form-aside-${field}-${equipment.id}" class="${(cssClass)?:cssClass}">
	<h5><g:message code="equipment.details.label" /></h5>
	<ul>
		<li>
			<span class="label"><g:message code="equipment.serial.number.label" /></span>
			<span class="text">${equipment.serialNumber}</span>
		</li>
		<li>
			<span class="label"><g:message code="datalocation.label"/>:</span>
			<span class="text">${equipment.dataLocation.names}</span>
			<span class="label"><g:message code="department.label"/>:</span>
			<span class="text">${equipment.department.names}</span>
			<span class="label"><g:message code="equipment.room.label"/>:</span>
			<span class="text"> ${equipment.room}</span>
		</li>
		<li>
			<h3>${message(code:"equipment.type.label")}</h3>
			<ul>
				<li>
					<span class="label"><g:message code="entity.code.label" /></span>
					<span class="text">${equipment.type?.code}</span>
				</li>
				<li>
					<span class="label"><g:message code="entity.name.label" /></span>
					<span class="text">${equipment.type?.names}</span>
				</li>
			</ul>
		</li>
		<li>
			<h3>${message(code:"provider.type.manufacturer")}</h3>
			<ul class="half">
				<li>
					<span class="label"><g:message code="entity.name.label"/>:</span>
					<span class="text">${equipment.manufacturer?.contact?.contactName}</span>
				</li>
				<li>
					<span class="label"><g:message code="contact.email.label"/>:</span>
					<span class="text">${equipment.manufacturer?.contact?.email}</span>
				</li>
				<li>
					<span class="label"><g:message code="contact.phone.label"/>:</span>
					<span class="text">${equipment.manufacturer?.contact?.phone}</span>
				</li>
			</ul>
		</li>
		<li>
			<h3>${message(code:"provider.type.supplier")}</h3>
			<ul class="half">
				<li>
					<span class="label"><g:message code="entity.name.label"/>:</span>
					<span class="text">${equipment.supplier?.contact?.contactName}</span>
				</li>
				<li>
					<span class="label"><g:message code="contact.email.label"/>:</span>
					<span class="text">${equipment.supplier?.contact?.email}</span>
				</li>
				<li>
					<span class="label"><g:message code="contact.phone.label"/>:</span>
					<span class="text">${equipment.supplier?.contact?.phone}</span>
				</li>
			</ul>
		</li>
		<li>
			<h3>${message(code:"equipment.status.current.status")}</h3>
			<ul class="half">
				<li>
					<span class="label"><g:message code="equipment.status.label"/>:</span>
					<span class="text">${message(code: equipment.currentState?.status?.messageCode+'.'+equipment.currentState?.status?.name)}</span>
				</li>
				<li>
					<span class="label"><g:message code="equipment.status.date.of.event.label"/>:</span>
					<span class="text">${Utils.formatDate(equipment.currentState?.dateOfEvent)}</span>
				</li>
			</ul>
		</li>
	
	</ul>
	
</div>
