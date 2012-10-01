<ul class="process-list-${type}">
	<g:each in="${processes.sort{a,b -> (a.id < b.id) ? -1 : 1}}" status="i" var="process">
	 	<li>
		 	${process.name} 
		 	<span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
			<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
		 	<a href="#" name="${process.id}" class="delete-process">X</a>
	 	</li>
 	</g:each>
</ul>
   