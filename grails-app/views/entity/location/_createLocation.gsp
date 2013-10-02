<div class="entity-form-container togglable">
	<div class="heading1-bar">
	  <g:locales/>
		<h1>
		<g:if test="${location.id != null}">
				<g:message code="default.edit.label" args="[message(code:'location.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'location.label')]" />
			</g:else>
		</h1>
	</div>
	
	<div class="main">
  	<g:form url="[controller:'location', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  		<g:i18nInput name="names" label="${message(code:'entity.names.label')}" bean="${location}" field="names"/>
  		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${location}" field="code"/>
  		<g:selectFromList name="level.id" label="${message(code:'location.level.label')}" bean="${location}" field="level" 
  			from="${levels}" value="${location.level?.id}" values="${levels.collect{it.names}}" optionKey="id"/>
  		<g:selectFromList name="parent.id" label="${message(code:'location.parent.label')}" bean="${location}" field="parent" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'location', action:'getAjaxData', params: [class: 'Location'])}"
  			from="${locations}" value="${location.parent?.id}" values="${locations.collect{it.names}}" />
      <g:textarea name="coordinates" label="${message(code:'location.coordinates.label')}, ${message(code:'location.coordinates.format.label')}" bean="${location}" field="coordinates" value="${location?.coordinates}" height="130" />
  		<g:if test="${location.id != null}">
  			<input type="hidden" name="id" value="${location.id}"></input>
  		</g:if>
  		<br/>
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
  </div>
</div>
