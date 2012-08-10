<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>
<div>
	<div>
		<h3>
			<g:message code="default.new.label" args="[message(code:'equipment.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'equipment', action:'save', params:[targetURI: targetURI]]" useToken="true">
	<fieldset>
    	<legend>Basic Information:</legend>	
		<g:input name="serialNumber" label="${message(code:'entity.serialNumber.label')}" bean="${equipment}" field="serialNumber"/>
		<g:input name="purchaseCost" label="${message(code:'entity.purchaseCost.label')}" bean="${equipment}" field="purchaseCost"/>
		
		<g:i18nTextarea name="descriptions" bean="${equipment}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
		<g:i18nTextarea name="observations" bean="${equipment}" label="${message(code:'entity.observations.label')}" field="observations" height="150" width="300" maxHeight="150" />
		
		<g:input name="manufactureDate" label="${message(code:'entity.manufacturedate.label')}" bean="${equipment}" field="manufactureDate"/>
		<g:input name="purchaseDate" label="${message(code:'entity.purchasedate.label')}" bean="${equipment}" field="purchaseDate"/>
		
		
		<g:selectFromList name="department.id" label="${message(code:'entity.department.label')}" bean="${equipment}" field="department" optionKey="id" multiple="false"
			ajaxLink="${createLink(controller:'department', action:'getAjaxData', params: [class: 'Department'])}"
			from="${departments}" value="${equipment.department.id}" values="${departments.collect{it.names}}" />
		
	</fieldset>	
	
	<fieldset>
    	<legend>Supply Information:</legend>
    	<g:input name="supplier.contactName" label="${message(code:'entity.name.label')}" bean="${equipment.supplier}" field="contactName"/>
    	<g:input name="supplier.email" label="${message(code:'entity.email.label')}" bean="${equipment.supplier}" field="email"/>
    	<g:input name="supplier.phone" label="${message(code:'entity.phone.label')}" bean="${equipment.supplier}" field="phone"/>
    	<g:input name="supplier.poBox" label="${message(code:'entity.address.label')}" bean="${equipment.supplier}" field="poBox"/>
	    <g:i18nTextarea name="supplier.addressDescriptions" bean="${equipment.supplier}" label="${message(code:'entity.descriptions.label')}" field="addressDescriptions" height="150" width="300" maxHeight="150" />  	
   	</fieldset>
   	
   	<fieldset>
    	<legend>Manufacture Information:</legend>
    	<g:input name="manufacture.contactName" label="${message(code:'entity.name.label')}" bean="${equipment.manufacture}" field="contactName"/>
    	<g:input name="manufacture.email" label="${message(code:'entity.email.label')}" bean="${equipment.manufacture}" field="email"/>
    	<g:input name="manufacture.phone" label="${message(code:'entity.phone.label')}" bean="${equipment.manufacture}" field="phone"/>
    	<g:input name="manufacture.poBox" label="${message(code:'entity.address.label')}" bean="${equipment.manufacture}" field="poBox"/>
    	<g:input name="manufacture.street" label="${message(code:'entity.address.label')}" bean="${equipment.manufacture}" field="street"/>
	    <g:i18nTextarea name="manufacture.addressDescriptions" bean="${equipment.manufacture}" label="${message(code:'entity.descriptions.label')}" field="addressDescriptions" height="150" width="300" maxHeight="150" />	
   	</fieldset>
   
		<g:if test="${equipment.id != null}">
			<input type="hidden" name="id" value="${equipment.id}"></input>
		</g:if>
		<div>
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
</div>
