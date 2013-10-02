<ul class="used-spare-part-list process-list-prevention ${(!spareParts.isEmpty()?'':'hide-list')}">
	<g:each in="${spareParts}" status="i" var="sparePart">
	 	<li>
	 	  %{-- <a href="#" name="${sparePart.sparePart.id}" data-id="${order.id}" class="delete-process delete-row">X</a> --}%
	 	  <span>${sparePart.sparePart.type?.names} --[<g:stripHtml field="${sparePart.sparePart?.descriptions}" chars="10"/>] -- [${sparePart.quantity}]</span>
	 	  <span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
		  <img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
		</li>
	</g:each>
</ul>

