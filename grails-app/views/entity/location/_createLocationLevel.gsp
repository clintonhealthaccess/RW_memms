<div  class="entity-form-container togglable">

	<div class="heading1-bar">
	  <g:locales/>
		<h1>
  		<g:if test="${locationLevel.id != null}">
				<g:message code="default.edit.label" args="[message(code:'location.level.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'location.level.label')]" />
			</g:else>
		</h1>
	</div>

	<div class="main">
  	<g:form url="[controller:'locationLevel', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  	
  		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${locationLevel}" field="code"/>
  		<g:input name="order" label="${message(code:'entity.order.label')}" bean="${locationLevel}" field="order"/>
  		<g:i18nInput name="names" label="${message(code:'entity.names.label')}" bean="${locationLevel}" field="names"/>
		
  		<g:if test="${locationLevel.id != null}">
  			<input type="hidden" name="id" value="${locationLevel.id}"></input>
  		</g:if>
  		<br/>
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
  </div>
</div>
