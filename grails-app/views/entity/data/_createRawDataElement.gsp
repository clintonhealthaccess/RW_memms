<%@ page import="org.chai.kevin.data.Type.ValueType" %>

<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'rawdataelement.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'rawDataElement', action:'save', params:[targetURI: targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${rawDataElement}" value="${rawDataElement.names}" label="${message(code:'entity.name.label')}" field="names" />
		<g:i18nTextarea name="descriptions" bean="${rawDataElement}" value="${rawDataElement.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions" height="150"  width="300" maxHeight="150" />
		
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${rawDataElement}" field="code" />
		
		<g:render template="/templates/typeEditor" model="[bean: rawDataElement, name: 'type.jsonValue']"/>

		<g:input name="info" label="${message(code:'rawdataelement.info.label')}" bean="${rawDataElement}" field="info"/>
		
		<g:if test="${rawDataElement.id != null}">
			<input type="hidden" name="id" value="${rawDataElement.id}"/>
		</g:if>
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
		
	</g:form>
	<div class="clear"></div>
</div>
