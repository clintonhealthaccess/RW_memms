<ul class="process-list-action ${(!type.preventiveActions.isEmpty()?'':'hide-list')}">
	<g:each in="${type.preventiveActions.sort{a,b -> (a.id < b.id) ? -1 : 1}}" status="i" var="action">
	 	<li>
	 	  <a href="#" name="${action.id}" class="delete-process delete-row">X</a>
	 	  <span><g:stripHtml field="${action?.description}" chars="50"/></span>
	 	  <span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
		  <img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
		</li>
	</g:each>
</ul>