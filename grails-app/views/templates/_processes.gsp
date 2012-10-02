<div class="process process-${processType}">
 	<label><g:message code="${label}"/> :</label>
 	<fieldset>
   	<g:render template="/templates/processList" model="['processes':processes,'type':processType]" /> 
   	<input type="text" name="${processType}" class="idle-field" value="" />
    <a href="#" class="add-process-button next medium"><g:message code="default.add.label"  args="${['']}"/></a>
    <span class="ajax-error alt"><g:message code="entity.error.updating.try.again"/></span>
   	<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
  </fieldset>
</div>