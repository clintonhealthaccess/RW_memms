<div class="row can-be-hidden ${hasErrors(bean:bean,field:field,'errors')}" id="address">
	<g:input name="${(warranty)?'warranty.':''}contact.contactName" label="${message(code:'entity.name.label')}" bean="${bean}" field="${(warranty)?'warranty.':''}contact.contactName"/>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}"/></div>
   	<g:input name="${(warranty)?'warranty.':''}contact.email" label="${message(code:'contact.email.label')}" bean="${bean}" field="${(warranty)?'warranty.':''}contact.email"/>
   	<g:input name="${(warranty)?'warranty.':''}contact.phone" label="${message(code:'contact.phone.label')}" bean="${bean}" field="${(warranty)?'warranty.':''}contact.phone"/>
   	<g:input name="${(warranty)?'warranty.':''}contact.poBox" label="${message(code:'contact.pobox.label')}" bean="${bean}" field="${(warranty)?'warranty.':''}contact.poBox"/>
   	<g:input name="${(warranty)?'warranty.':''}contact.city" label="${message(code:'contact.city.label')}" bean="${bean}" field="${(warranty)?'warranty.':''}contact.city"/>
   	<g:input name="${(warranty)?'warranty.':''}contact.country" label="${message(code:'contact.country.label')}" bean="${bean}" field="${(warranty)?'warranty.':''}contact.country"/>
    <g:i18nTextarea name="${(warranty)?'warranty.':''}contact.addressDescriptions" bean="${bean}" label="${message(code:'contact.address.descriptions.label')}" field="${(warranty)?'warranty.':''}contact.addressDescriptions" height="150" width="300" maxHeight="150" />
</div>