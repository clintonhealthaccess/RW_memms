<div class="entity-form-container togglable">
	
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'dashboard.program.label')]"/>
		</h3>
		<g:locales/>
	</div>

	<g:form url="[controller:'dashboardProgram', action:'save', params:[targetURI:targetURI]]" useToken="true">		

		<g:selectFromList name="program.id" label="${message(code:'dashboard.program.label')}" bean="${entity}" field="program" optionKey="id" multiple="false"
			from="${programs}" value="${entity.program?.id}" values="${programs.collect{i18n(field:it.names)}}" />
		
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${target}" field="code"/>
		<g:input name="weight" label="${message(code:'dashboard.program.weight.label')}" bean="${entity}" field="weight"/>
		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${entity}" field="order"/>
		
		<g:if test="${entity.id != null}">
			<input type="hidden" name="id" value="${entity.id}"></input>
		</g:if>
		
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>