<div class="process process-preventive-actions half">
 	<label><g:message code="${label}"/> :</label>
 	<fieldset>
   	<g:render template="/templates/preventiveActionList" model="['type':type]" /> 
   	<input type="text" name="preventiveAction" class="idle-field" value="" />
    <a href="#" class="add-process-button next medium"><g:message code="default.add.label"  args="${['']}"/></a>
    <img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
    <span class="ajax-error alt"><g:message code="entity.error.updating.try.again"/></span>
  </fieldset>
</div>