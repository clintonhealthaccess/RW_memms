<div class="entity-form-container togglable">

	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'dsr.category.label')]"/>
		</h3>
		<g:locales/>
	</div>
	
	<g:form url="[controller:'dsrTargetCategory', action:'save', params:[targetURI:targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${category}" value="${category.names}" label="${message(code:'entity.name.label')}" field="names"/>
		<g:i18nTextarea name="descriptions" bean="${category}" value="${category.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions"/>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${category}" field="code"/>
		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${category}" field="order"/>
	
		<g:if test="${category?.id != null}">
			<input type="hidden" name="id" value="${category.id}"></input>
		</g:if>
		
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>