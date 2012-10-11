<div id="form-aside-${field}-${provider.id}" class="${(cssClass)?:cssClass}">
	<h5>${message(code:label)}</h5>
	<ul class="half">
		<li><span class="label"><g:message code="entity.name.label"/>:</span><span class="text">${provider?.contact?.contactName}</span></li>
		<li><span class="label"><g:message code="contact.email.label"/>:</span><span class="text">${provider?.contact?.email}</span></li>
	</ul>
	<ul class="half">
		<li><span class="label"><g:message code="contact.address.label"/>:</span></li>
		<li><span class="label"><g:message code="contact.phone.label"/>:</span><span class="text">${provider?.contact?.phone}</span></li>
		<li><span class="label"><g:message code="contact.pobox.label"/>:</span><span class="text">${provider?.contact?.poBox}</span></li>
	</ul>
</div>
