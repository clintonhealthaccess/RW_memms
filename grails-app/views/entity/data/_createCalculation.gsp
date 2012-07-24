<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:calculation.class.simpleName.toLowerCase()+'.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<div class="forms-container">
		<div class="data-field-column">
			<g:form url="[controller:calculation.class.simpleName.toLowerCase(), action:'save', params:[targetURI: targetURI]]" useToken="true">
				<g:i18nInput name="names" bean="${calculation}" value="${calculation.names}" label="${message(code:'entity.name.label')}" field="names" />
				<g:i18nTextarea name="descriptions" bean="${calculation}" value="${calculation.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
				
				<g:input name="code" label="${message(code:'entity.code.label')}" bean="${calculation}" field="code" />
				<g:textarea name="expression" label="${message(code:'calculation.expression.label')}" bean="${calculation}" field="expression" value="${calculation.expression}" rows="5"/>
				
				<g:if test="${calculation.id != null}">
					<input type="hidden" name="id" value="${calculation.id}"/>
				</g:if>
				<div class="row">
					<button type="submit"><g:message code="default.button.save.label"/></button>
					<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
				</div>
			</g:form>
		</div>
		<g:render template="/templates/searchDataElement" model="[element: 'textarea[name="expression"]', formUrl: [controller:'data', action:'getData', params:[class:'DataElement']]]"/>
	</div>
</div>
