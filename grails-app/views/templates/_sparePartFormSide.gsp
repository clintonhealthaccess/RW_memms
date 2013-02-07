<%@ page import="org.chai.memms.util.Utils" %>
<div id="form-aside-${field}-${sparePart.id}" class="${(cssClass)?:cssClass}">
	<a href="${createLinkWithTargetURI(controller:'sparePart', action:'edit', params:[id: sparePart.id])}" class="next small gray right pulled">
		<g:message code="default.edit.label" args="[message(code:'sparePart.label')]" />
	</a>
	<h5><g:message code="sparePart.details.label" /></h5>
	
	<ul>
		<li>
			<h6>${message(code:"sparePart.section.basic.information.label")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="sparePart.code.label" /></span>
					<span class="text">${sparePart.code}</span>
					<span class="label"><g:message code="sparePart.serial.number.label" /></span>
					<span class="text">${sparePart.serialNumber}</span>
					<span class="label"><g:message code="sparePart.model.label" /></span>
					<span class="text">${sparePart.model}</span>
					<span class="label"><g:message code="datalocation.label"/>:</span>
					<span class="text">${sparePart.dataLocation.names}</span>
					
					<span class="label"><g:message code="entity.in.system.since.label"/>:</span>
					<span class="text"> ${Utils.formatDate(sparePart.dateCreated)}</span>
				</li>
			</ul>
		</li>
		<li>
			<h6>${message(code:"sparePart.type.label")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="entity.code.label" /></span>
					<span class="text">${sparePart.type?.code}</span>
					<span class="label"><g:message code="entity.name.label" /></span>
					<span class="text">${sparePart.type?.names}</span>
				</li>
			</ul>
		</li>
		<li>
			<h6>${message(code:"provider.type.manufacturer")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="entity.name.label"/>:</span>
					<span class="text">${sparePart.manufacturer?.contact?.contactName}</span>
					<span class="label"><g:message code="contact.email.label"/>:</span>
					<span class="text">${sparePart.manufacturer?.contact?.email}</span>
					<span class="label"><g:message code="contact.phone.label"/>:</span>
					<span class="text">${sparePart.manufacturer?.contact?.phone}</span>
				</li>
			</ul>
		</li>
		<li>
			<h6>${message(code:"provider.type.supplier")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="entity.name.label"/>:</span>
					<span class="text">${sparePart.supplier?.contact?.contactName}</span>
					<span class="label"><g:message code="contact.email.label"/>:</span>
					<span class="text">${sparePart.supplier?.contact?.email}</span>
					<span class="label"><g:message code="contact.phone.label"/>:</span>
					<span class="text">${sparePart.supplier?.contact?.phone}</span>
				</li>
			</ul>
		</li>
		<li>
			<h6>${message(code:"sparePart.status.current.status")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="sparePart.status.label"/>:</span>
					<span class="text">${message(code: sparePart.statusOfSparePart?.messageCode+'.'+sparePart.statusOfSparePart?.name)}</span>
					<span class="label"><g:message code="sparePart.status.date.of.event.label"/>:</span>
					<span class="text">${Utils.formatDate(sparePart.timeBasedStatus?.dateOfEvent)}</span>
				</li>
			</ul>
		</li>
	
	</ul>
</div>
