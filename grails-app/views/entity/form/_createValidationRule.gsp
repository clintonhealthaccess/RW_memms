<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'formelement.validationrule.label')]"/>
		</h3>
		<g:locales />
	</div>
	<div class="forms-container"">
		<div class="data-field-column">
			<g:form url="[controller:'formValidationRule', action:'save', params:[targetURI: targetURI]]" useToken="true">

				<g:selectFromList name="formElement.id" label="${message(code:'formelement.label')}" field="formElement" optionKey="id" multiple="false"
					ajaxLink="${createLink(controller:'formElement', action:'getAjaxData')}" from="${formElements}"
					value="${validation.formElement?.id}" bean="${validation}"
					values="${formElements.collect {it.getLabel(languageService)+'['+it.id+']'}}" />
			
				<g:input name="prefix" label="${message(code:'formelement.validationrule.prefix.label')}" bean="${validation}" field="prefix"/>
		 		<g:i18nRichTextarea name="messages" bean="${validation}" value="${validation.messages}" label="Messages" field="messages" height="150"  width="400" maxHeight="100" />
		 		
		 		<g:selectFromList name="dependencies" label="${message(code:'formelement.validationrule.dependencies.label')}" field="dependencies" optionKey="id" multiple="true"
					ajaxLink="${createLink(controller:'formElement', action:'getAjaxData')}" from="${dependencies}" 
					value="${validation.dependencies*.id}" bean="${validation}"
					values="${dependencies.collect {it.getLabel(languageService)+'['+it.id+']'}}" />
			
				<div class="row">
					<label><g:message code="formelement.validationrule.allowoutlier.label" default="Allow Outlier"/></label>
					<g:checkBox name="allowOutlier" value="${validation.allowOutlier}" />
				</div>
				
				<g:textarea name="expression" label="Expression" bean="${validation}" field="expression" value="${validation.expression}" rows="5"/>
				
				<g:selectFromList name="typeCodes" label="${message(code:'entity.datalocationtype.label')}" bean="${validation}" field="typeCodeString" 
					from="${types}" value="${validation.typeCodes*.toString()}" values="${types.collect{i18n(field:it.names)}}" optionKey="code" multiple="true"/>
			
				<g:if test="${validation.id != null}">
					<input type="hidden" name="id" value="${validation.id}" />
				</g:if>
				<div class="row">
					<button type="submit" class="rich-textarea-form"><g:message code="default.button.save.label"/></button>
					<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
				</div>
			</g:form>
		</div>
		<g:render template="/templates/searchDataElement" model="[element: 'textarea[name="expression"]', formUrl: [controller:'formElement', action:'getHtmlData']]"/>
	</div>
</div>