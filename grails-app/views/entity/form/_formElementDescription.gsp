<div class="row">
	<span class="type"><g:message code="dataelement.label" default="Data Element"/>:</span> 
	<g:i18n field="${formElement.dataElement.names}"/>
</div>

<g:render template="/entity/data/dataDescription" model="${[data: formElement.dataElement]}"/>


