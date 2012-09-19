<div class="checkbox">
  <img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
  <input type="checkbox" name="${name}" value="${value}"  id="${id}" class="list-check-box" ${(checked)?:'checked="checked"'}/>
  <span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
</div>