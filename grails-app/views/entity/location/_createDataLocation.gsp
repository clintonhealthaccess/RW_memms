<div class="entity-form-container togglable">

	<div class="heading1-bar">
	  <g:locales/>
		<h1>
			<g:if test="${location.id != null}">
				<g:message code="default.edit.label"
					args="[message(code:'datalocation.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label"
					args="[message(code:'datalocation.label')]" />
			</g:else>
		</h1>
	</div>
	
	<div class="main">
  	<g:form url="[controller:'dataLocation', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  		<g:selectFromList name="type.id" label="${message(code:'datalocation.type.label')}" bean="${location}" field="type" 
  			from="${types}" value="${location.type?.id}" values="${types.collect{it.names}}" optionKey="id"/>
  			
  		<g:selectFromList name="location.id" label="${message(code:'location.label')}" bean="${location}" field="location" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'location', action:'getAjaxData', params: [class: 'Location'])}"
  			from="${locations}" value="${location.location?.id}" values="${locations.collect{it.names}}" />	
  			
  		<g:i18nInput name="names" label="${message(code:'entity.names.label')}" bean="${location}" field="names"/>
  		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${location}" field="code"/>		
  		<g:if test="${location.id != null}">
  			<label>${message(code:'dataLocation.managed.by.label')}</label>
  			<input name="" disabled class="idle-field" value="${location.managedBy?.names}">	
  			<input type="hidden" name="id" value="${location.id}"></input>
  		</g:if>		
  		<g:selectFromList name="managesIds" label="${message(code:'dataLocation.manages.label')}" field="manages" 
					optionKey="id" multiple="true" ajaxLink="${createLink(controller:'dataLocation', action:'getAjaxData')}" 
					from="${manages}" value="${location.manages*.id}" bean="${location}" 
					values="${manages.collect{it.names+' ['+it.location.names+']'}}" />
  		<br/>
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
	</div>
</div>
