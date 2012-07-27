<div class="float-right">
	<span> 
		<a class="edit-link" href="${createLinkWithTargetURI(controller:'enumOption', action:'edit', params:[id: option.id])}">
			<g:message code="default.link.edit.label" />
		</a>&nbsp; 
		<a class="delete-link" href="${createLinkWithTargetURI(controller:'enumOption', action:'delete', params:[id: option.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
			<g:message code="default.link.delete.label" />
		</a>
	</span>
</div>
<div>
	<span class="bold"><g:message code="entity.name.label"/></span> 
	<span>${i18n(field: option.names)}</span>
</div>
<div>
	<span class="bold"><g:message code="enumoption.value.label"/></span> 
	<span>${option.value}</span>
</div>
