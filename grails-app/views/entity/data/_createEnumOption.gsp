<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'enumoption.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'enumOption', action:'save', params:[targetURI: targetURI]]" useToken="true">
		<input type="hidden" name="enume.id" value="${option.enume.id}"/>
		
		<g:i18nTextarea name="names" bean="${option}" value="${option.names}" label="${message(code:'entity.name.label')}" field="names" height="100"  width="300" maxHeight="100" />
		<g:i18nTextarea name="descriptions" bean="${option}" value="${option.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions" height="150"  width="300" maxHeight="150" />
		<g:i18nInput name="order" label="${message(code:'entity.order.label')}" bean="${option}" value="${option.order}" field="order"/>
		
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${option}" field="code" />
		
		<g:input name="value" label="${message(code:'enumoption.value.label')}" bean="${option}" field="value"/>
		
		<div class="row">
			<label><g:message code="enumoption.inactive.label"/></label>
			<g:checkBox name="inactive" value="${option.inactive}" />
		</div>
			
		<g:if test="${option.id != null}">
			<input type="hidden" name="id" value="${option.id}"></input>
		</g:if>
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>
