<%@ page import="org.chai.kevin.cost.CostTarget.CostType" %>

<div class="entity-form-container togglable">

	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'costrampup.label')]"/>
		</h3>
		<g:locales/>
	</div>
	
	<g:form url="[controller:'costRampUp', action:'save', params:[targetURI:targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${rampUp}" value="${rampUp.names}" label="${message(code:'entity.name.label')}" field="names"/>
		<g:i18nTextarea name="descriptions" bean="${rampUp}" value="${rampUp.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions"/>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${rampUp}" field="code"/>
		
		<g:if test="${rampUp != null}">
			<input type="hidden" name="id" value="${rampUp.id}"></input>
		</g:if>
		
		<g:each in="${years}" var="year">
			<div class="row ${rampUp?.years!=null?hasErrors(bean:rampUp?.years[year],field:'value','errors'):''}">
				<label for="years[${year}].value">Year ${year}</label>
				<input name="years[${year}].value" value="${rampUp?.years!=null?fieldValue(bean:rampUp?.years[year],field:'value'):''}"></input>
				<div class="error-list"><g:if test="${rampUp?.years != null}"><g:renderErrors bean="${rampUp?.years[year]}" field="value" /></g:if></div>
			</div>
			<input type="hidden" name="years[${year}].year" value="${year}"/>
			<g:if test="${rampUp?.years != null && rampUp?.years[year] != null}">
				<input type="hidden" name="years[${year}].id" value="${rampUp?.years[year].id}"/>
			</g:if>
		</g:each>
		
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>&nbsp;&nbsp;
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
    </g:form>
	<div class="clear"></div>
</div>

