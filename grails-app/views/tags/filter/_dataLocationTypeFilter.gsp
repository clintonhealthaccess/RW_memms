<div>
	<%
		newLinkParams = [:]
		newLinkParams.putAll(linkParams)
		newLinkParams.remove('dataLocationTypes')
	%>
	<g:form name="report-filters" method="post" url="[controller:controllerName, action:actionName, params: newLinkParams]">
			
		<span class="js_dropdown dropdown">
			<a class="datalocation js_dropdown-link nice-button with-highlight" href="#">
				<g:message code="filter.datalocationtype.label"/>
			</a>
			<g:if test="${dataLocationTypes != null && !dataLocationTypes.isEmpty()}">
				<div class="js_dropdown-list dropdown-list push-top-10" id="js_location-type-filter">
					<ul>
						<li>
							<a id="js_checkall" class="nice-button2" href="#"><g:message code="filter.datalocationtype.checkall"/></a>
							<a id="js_uncheckall" class="nice-button2" href="#" ><g:message code="filter.datalocationtype.uncheckall"/></a>
							<g:each in="${dataLocationTypes}" var="type">
								<li>								
									<input class="js_data-location-type-checkbox js_dropdown-ignore" name="dataLocationTypes" type="checkbox" value="${type.id}" 
									${currentLocationTypes != null && !currentLocationTypes.empty && currentLocationTypes.contains(type)?'checked="checked"':''}/>
									<label for="${type.id}">${type.names}</label>								
								</li>
							</g:each>	        
						<li>
						<button id="js_data-location-type-submit" type="submit"><g:message code="filter.datalocationtype.filter"/></button></li>
					</ul>
				</div>
			</g:if>
		    <g:else>
				<span class="italic"><g:message code="filter.datalocationtype.empty.label"/></span>
			</g:else>	
	    </span>
	    <r:script>
	    $(document).ready(function() {
	    	$('#js_data-location-type-submit').bind('click', function() {
	    		var minLocationTypes = 0;
	    		$('.js_data-location-type-checkbox').each(function() { 
	    			if(this.checked)
	    				minLocationTypes++;
	    		});
	    		if(minLocationTypes < 1){
	    			alert('${message(code:'filter.datalocationtype.minimum')}');
	    			return false;
	    		}		    	
			});
	    });    
	    </r:script>
	</g:form>
</div>
