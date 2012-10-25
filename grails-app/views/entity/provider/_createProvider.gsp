<%@ page import="org.chai.memms.inventory.Provider.Type" %>
<div class="entity-form-container togglable">
	<div class="heading1-bar">
	  <g:locales />
		<h1>
			<g:if test="${provider.id != null}">
				<g:message code="default.edit.label" args="[message(code:'provider.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'provider.label')]" />
			</g:else>
		</h1>
	</div>
	
	<div class="main">
		<g:form url="[controller:'provider', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
			<g:input name="code" label="${message(code:'entity.code.label')}" bean="${provider}" field="code"/>
			<g:selectFromEnum name="type" bean="${provider}" values="${Type.values()}" field="type" label="${message(code:'entity.type.label')}"/>
			<g:address  bean="${provider}" field="contact"/>
			<g:if test="${provider.id != null}">
				<input type="hidden" name="id" value="${provider.id}"></input>
			</g:if>
			<br/>
			<div class="buttons">
				<button type="submit"><g:message code="default.button.save.label"/></button>
				<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
			</div>
		</g:form>
	</div>
</div>
