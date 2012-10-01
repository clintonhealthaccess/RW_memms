<div class="process process-${processType}">
   	<label><g:message code="${label}"/> :</label>
   	<g:render template="/templates/processList" model="['processes':processes,'type':processType]" /> 
   	<input type="text" name="${processType}" class="idle-field" value="" />
     <span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
   	<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
	<a href="#" class="add-process-button"><g:message code="default.add.label"  args="${['']}"/></a>
</div>