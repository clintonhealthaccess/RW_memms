<g:each in="${formElements}" status="i" var="formElement">
	<li data-code="${formElement.id}">
		<a	class="no-link cluetip" onclick="return false;" title="${i18n(field:formElement.dataElement.names)}"
			href="${createLink(controller:'formElement', action:'getDescription', params:[id: formElement.id])}"
			rel="${createLink(controller:'formElement', action:'getDescription', params:[id: formElement.id])}">
			
			${formElement.getLabel(languageService)} - <g:i18n field="${formElement.dataElement.names}"/>
		</a> 
		<span>[${formElement.id}]</span>
	</li>
</g:each>
<g:if test="${formElements.isEmpty()}">
	<g:message code="formelement.info.nomatch"/>
</g:if>

