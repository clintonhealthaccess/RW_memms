<span class="label"><g:message code="sparePart.code.label" />:</span>
<span class="text">${sparePart.type.code}</span><br/>
<span class="label"><g:message code="sparePart.serial.number.label" />:</span>
<span class="text">${sparePart.type.names}</span><br/>
<span class="label"><g:message code="location.label" />:</span>
<span class="text">${sparePart.location.names}</span><br/>
<span class="label"><g:message code="location.label" />:</span>
<span class="text">${sparePart.location.names}</span><br/>
<span class="label"><g:message code="sparePart.status.label" />:</span>
<span class="text">${message(code: sparePart.status?.messageCode+'.'+sparePart.status?.name)}</span>

<a href="${createLinkWithTargetURI(controller:'sparePart', action:'edit', params:[id: sparePart.id])}" >
	<g:message code="default.edit.label" args="[message(code:'sparePart.label')]" />
</a>