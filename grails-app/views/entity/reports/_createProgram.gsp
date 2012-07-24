<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'reports.program.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'reportProgram', action:'save', params:[targetURI:targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${program}" value="${program.names}" label="${message(code:'entity.name.label')}" field="names"/>
		<g:i18nTextarea name="descriptions" bean="${program}" value="${program.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions"/>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${program}" field="code"/>
		
		<g:selectFromList name="parent.id" label="${message(code:'reports.program.parent.label')}" bean="${program}" field="parent" optionKey="id" multiple="false"
			from="${programs}" value="${program.parent?.id}" values="${programs.collect{i18n(field:it.names)}}" />
			
		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${program}" field="order"/>
		
		<g:if test="${program != null}">
			<input type="hidden" name="id" value="${program.id}"/>
		</g:if>
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>

