<div id="form-aside-'${provider.id}">
<h5>${message(code: type?.messageCode+'.'+type?.name)} ${message(code:"details.label")}</h5>
<ul class="half">
<li><span class="label">${message(code:"contact.name.label")}:</span><span class="text">${provider?.contact?.contactName}</span></li>
<li><span class="label">${message(code:"contact.email.label")}:</span><span class="text">${provider?.contact?.email}</span></li>
</ul>
<ul class="half">
<li><span class="label">${message(code:"contact.phone.label")}</span><span class="text">${provider?.contact?.phone}</span></li>
<li><span class="label">${message(code:"contact.pobox.label")}:</span><span class="text">${provider?.contact?.poBox}</span></li>
<li><span class="label">'+message(code:"contact.street.label")+'</span><span class="text">${provider?.contact?.street}</span></li>
<li><span class="label">'+message(code:"contact.city.label")+'</span><span class="text">${provider?.contact?.city}</span></li>
<li><span class="label">'+message(code:"contact.country.label")+'</span><span class="text">${provider?.contact?.country}</span></li>
</ul></div>
