
<div  class="entity-form-container togglable">
	<div class="heading1-bar">
		<g:locales/>
		<h1>
		  <g:if test="${type.id != null}">
				<g:message code="default.edit.label" args="[message(code:'spare.part.type.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'spare.part.type.label')]" />
			</g:else>
		</h1>		
	</div>	
	<div class="main">
  	<g:form url="[controller:'sparePartType', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
	
        <g:input name="code" label="${message(code:'entity.code.label')}" bean="${type}" field="code"/>    
  		<g:i18nInput name="names" label="${message(code:'entity.names.label')}" bean="${type}" field="names"/>
  		<g:input name="partNumber" label="${message(code:'entity.part.number.label')}" bean="${type}" field="partNumber"/> 		
  		
  		<g:selectFromList name="manufacturer.id" label="${message(code:'provider.type.manufacturer')}" bean="${type}" field="manufacturer" optionKey="id" multiple="false"
  		ajaxLink="${createLink(controller:'provider', action:'getAjaxData', params: [type:'MANUFACTURER'])}"
  		from="${manufacturers}" value="${type.manufacturer?.id}" values="${manufacturers.collect{it.contact?.contactName}}"/>		
  		
  		<g:i18nTextarea name="descriptions" bean="${type}" label="${message(code:'entity.description.label')}" field="descriptions" height="150" width="300" maxHeight="150"/>
  		<g:input name="quantityInStock" label="${message(code:'quantity.in.stock.label')}" bean="${type}" field="quantityInStock"/> 	
  		<g:input name="discontinuedDate" dateClass="date-picker" label="${message(code:'entity.discontinued.date.label')}" bean="${type}" field="discontinuedDate"/>
  		
  		<g:selectFromList name="compatibleEquipmenTypes.id" label="${message(code:'compatible.equipment.types.label')}" bean="${equipment}" field="type" optionKey="id" multiple="true"
    			ajaxLink="${createLink(controller:'equipment', action:'getAjaxData')}"
    			from="${compatibleEquipmentTypes}" value="${equipment.compatibleEquipmentTypes?.id}" values="${compatibleEquipmentTypes.collect{it.names}}"/>
    			
       <g:selectFromList name="vendors.id" label="${message(code:'vendors.label')}" bean="${provider}" field="type" optionKey="id" multiple="true"
    			ajaxLink="${createLink(controller:'provider', action:'getAjaxData')}"
    		    from="${vendors}" value="${provider.vendors?.id}" values="${vendors.collect{it.names}}"/>			
    			
  		<g:if test="${type.id != null}">
  			<input type="hidden" name="id" value="${type.id}"></input>
  		</g:if>
  		<br />
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
  </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {		
		getDatePicker("${resource(dir:'images',file:'icon_calendar.png')}")
	});
</script>
