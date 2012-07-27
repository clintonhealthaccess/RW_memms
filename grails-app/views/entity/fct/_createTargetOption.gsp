<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">Fct Target Option</h3>
		<g:locales/>
		<div class="clear"></div>
	</div>
	<g:form url="[controller:'fctTargetOption', action:'save', params:[targetURI:targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${targetOption}" value="${targetOption.names}" label="${message(code:'entity.name.label')}" field="names"/>
		<g:i18nTextarea name="descriptions" bean="${targetOption}" value="${targetOption.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions"/>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${targetOption}" field="code"/>
		
		<g:selectFromList name="target.id" label="${message(code:'fct.targetoption.target.label')}" bean="${targetOption}" field="target" optionKey="id" multiple="false"
			from="${targets}" value="${targetOption.target?.id}" values="${targets.collect{i18n(field:it.names)}}" />

		<g:selectFromList name="sum.id" label="${message(code:'fct.targetoption.sum.label')}" bean="${targetOption}" field="sum" optionKey="id" multiple="false"
			ajaxLink="${createLink(controller:'data', action:'getAjaxData', params:[class:'Sum'])}"
			from="${sums}" value="${targetOption.sum?.id}" values="${sums.collect{i18n(field:it.names)+' ['+it.code+'] ['+it.class.simpleName+']'}}" />
		
		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${targetOption}" field="order"/>
		
		<g:if test="${targetOption != null}">
			<input type="hidden" name="id" value="${targetOption.id}"/>
		</g:if>
		<div class="row">
			<button type="submit">Save Target Option</button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label" default="Cancel"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>