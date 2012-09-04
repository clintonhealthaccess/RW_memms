<div class="entity-form-container togglable">
	<div class="heading1-bar">
		<h1 class="asd">
			<g:if test="${department.id != null}">
				<g:message code="default.edit.label" args="[message(code:'department.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'department.label')]" />
			</g:else>
		</h1>
		<g:locales />
	</div>
  
  <div class="main">
  	<g:form url="[controller:'department', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
	
  		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${department}" field="code"/>
		
  		<g:i18nInput name="names" label="${message(code:'entity.names.label')}" bean="${department}" field="names"/>
		
  		<g:i18nTextarea name="descriptions" bean="${department}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
		
  		<g:if test="${department.id != null}">
  			<input type="hidden" name="id" value="${department.id}"></input>
  		</g:if>
  		<br/>
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
  </div>
</div>
