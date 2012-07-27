<%@page import="org.chai.kevin.util.Utils"%>

<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'normalizeddataelement.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<div class="forms-container">
		<div class="data-field-column">
			<g:form url="[controller:'normalizedDataElement', action:'save', params: [targetURI: targetURI]]" useToken="true">
				<g:i18nInput name="names" bean="${normalizedDataElement}" value="${normalizedDataElement.names}" label="${message(code:'entity.name.label')}" field="names"/>
				<g:i18nTextarea name="descriptions" bean="${normalizedDataElement}" value="${normalizedDataElement.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions"/>
				<g:input name="code" label="${message(code:'entity.code.label')}" bean="${normalizedDataElement}" field="code"/>
				
				<g:render template="/templates/typeEditor" model="[bean: normalizedDataElement, name: 'type.jsonValue']"/>
				
				<div class="row ${hasErrors(bean:normalizedDataElement, field:'expressionMap', 'errors')}">
					<label><g:message code="normalizeddataelement.expressionmap.label"/></label>
					<div class="push-10">
						<g:each in="${periods}" var="period" status="i">
							<div>
								<a class="${i==0?'no-link':''} expression-period-link" href="#" 
									onclick="$('.js_expression-period').hide();$('#js_expression-period-${period.id}').show();$('.expression-period-link').removeClass('no-link');$(this).addClass('no-link');return false;">
									${Utils.formatDate(period.startDate)} to ${Utils.formatDate(period.endDate)}
								</a>
							</div>
						</g:each>
					</div>
					<g:each in="${periods}" var="period" status="i">
						<div class="js_expression-period ${i!=0?'hidden':''}" id="js_expression-period-${period.id}">
							<g:each in="${types}" var="group">
								<label for="expressionMap[${period.id}][${group.code}]">${group.code}</label> 
								<textarea name="expressionMap[${period.id}][${group.code}]" rows="4">${normalizedDataElement.getExpression(period, group.code)}</textarea>
							</g:each>
						</div>
					</g:each>
					<div class="error-list"><g:renderErrors bean="${normalizedDataElement}" field="expressionMap" /></div>
				</div>
				
				<g:if test="${normalizedDataElement.id != null}">
					<input type="hidden" name="id" value="${normalizedDataElement.id}"></input>
				</g:if>
				
				<div class="row">
					<button type="submit"><g:message code="default.button.save.label"/></button>
					<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
				</div>
			</g:form>
		</div>
		<g:render template="/templates/searchDataElement" model="[element: '#create-data-element textarea', formUrl: [controller:'data', action:'getData', params:[class:'RawDataElement']]]"/>
	</div>
</div>
