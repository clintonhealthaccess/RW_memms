<div class="entity-form-container togglable">
	
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'dashboard.target.label')]"/>
		</h3>
		<g:locales/>
	</div>

	<g:form url="[controller:'dashboardTarget', action:'save', params:[targetURI:targetURI]]" useToken="true">
		<g:i18nInput name="names" label="${message(code:'entity.name.label')}" bean="${entity}" value="${entity.names}" field="names"/>
		<g:i18nTextarea name="descriptions" label="${message(code:'entity.description.label')}" bean="${entity}" value="${entity.descriptions}" field="descriptions"/>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${entity}" field="code"/>
		
		<g:selectFromList name="program.id" label="${message(code:'dashboard.target.program.label')}" bean="${entity}" field="program" optionKey="id" multiple="false"
			from="${programs}" value="${entity.program?.id}" values="${programs.collect{i18n(field:it.names)}}" />
		
		<g:selectFromList name="calculation.id" label="${message(code:'dashboard.target.calculation.label')}" bean="${entity}" field="calculation" optionKey="id" multiple="false"
			ajaxLink="${createLink(controller:'data', action:'getAjaxData', params:[classes:['Sum', 'Aggregation']])}"
			from="${calculations}" value="${entity?.calculation?.id}" values="${calculations.collect{i18n(field:it.names)+' ['+it.code+'] ['+it.class.simpleName+']'}}" />
				
		<g:input name="weight" label="${message(code:'dashboard.target.weight.label')}" bean="${entity}" field="weight"/>
		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${entity}" field="order"/>
		
		<input type="hidden" name="id" value="${entity.id}"></input>
			
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>&nbsp;&nbsp;
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>
