<ul class="used-spare-part-list process-list-prevention ${(!usedSpareParts.isEmpty()?'':'hide-list')}">
	<g:each in="${usedSpareParts}" status="i" var="usedSparePart">
	 	<li>
	 	  <span>${usedSparePart.sparePartType?.names} -- [${usedSparePart.quantity}]</span>
	 	  <span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
		  <img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
		</li>
	</g:each>
</ul>

