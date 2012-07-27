<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'dsr.target.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'dsrTarget', action:'save', params:[targetURI:targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${target}" value="${target.names}" label="${message(code:'entity.name.label')}" field="names"/>
		<g:i18nTextarea name="descriptions" bean="${target}" value="${target.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions"/>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${target}" field="code"/>
		<g:input name="format" label="${message(code:'dsr.target.format.label')}" bean="${target}" field="format"/>		   		
	
		<g:selectFromList name="program.id" label="${message(code:'dsr.target.program.label')}" bean="${target}" field="program" optionKey="id" multiple="false"
			from="${programs}" value="${target.program?.id}" values="${programs.collect{i18n(field:it.names)}}" />
			
		<g:selectFromList name="category.id" label="${message(code:'dsr.target.category.label')}" bean="${target}" field="category" optionKey="id" multiple="false"
			from="${categories}" value="${target.category?.id}" values="${categories.collect{i18n(field:it.names)}}" />
	
		<g:selectFromList name="data.id" label="${message(code:'dsr.target.calculationelement.label')}" bean="${target}" field="data" optionKey="id" multiple="false"
			ajaxLink="${createLink(controller:'data', action:'getAjaxData', params:[class:'Data'])}"
			from="${data}" value="${target.data?.id}" values="${data.collect{i18n(field:it.names)+' ['+it.code+'] ['+it.class.simpleName+']'}}" />
		
		<div class="row">
			<label>${message(code:'dsr.target.average.label')}</label>
			<g:checkBox class="checkbox" name="average" value="${target.average}" />
		</div>

		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${target}" field="order"/>				
		
		<g:if test="${target != null}">
			<input type="hidden" name="id" value="${target.id}"/>
		</g:if>
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>&nbsp;&nbsp;
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>