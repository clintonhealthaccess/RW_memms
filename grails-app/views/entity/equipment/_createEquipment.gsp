<%@ page import="org.chai.memms.util.Utils" %>
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
			from="${departments}" value="${equipment?.department?.id}" values="${departments.collect{it.names}}" />
	</fieldset>	
	<fieldset>
    	<legend>Status Information:</legend>
    	<g:if test="${equipment?.status?.size() > 0}">
	    	<g:each in="${equipment?.status}" status="i" var="status">
		    	<table>
		    		<tr>
		    			<td>${status.value}</td>
		    			<td>${Utils.formatDate(status.statusChangeDate)}</td>
		    			<td>${status.current}</td>
		    		</tr>
		    	</table>
	    	</g:each>
    		<a href="#" id="pop" ><g:message code="change.equipment.status.label" /></a>
   		</g:if>
	   	<g:else>
	   		<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'edit', params:[equipment: equipment.id])}" ><g:message code="add.equipment.status.label" /></a>
	   	</g:else>
   	</fieldset>
	<fieldset>
    	<legend>Warranty Information:</legend>	
		<g:i18nTextarea name="warranty.descriptions" bean="${equipment.warranty}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
			<fieldset>
		    	<legend>Contact Information:</legend>
		    	<g:input name="warranty.contact.contactName" label="${message(code:'entity.name.label')}" bean="${equipment?.warranty?.contact}" field="contactName"/>
		    	<g:input name="warranty.contact.email" label="${message(code:'entity.email.label')}" bean="${equipment?.warranty?.contact}" field="email"/>
		    	<g:input name="warranty.contact.phone" label="${message(code:'entity.phone.label')}" bean="${equipment?.warranty?.contact}" field="phone"/>
		    	<g:input name="warranty.contact.poBox" label="${message(code:'entity.address.label')}" bean="${equipment?.warranty?.contact}" field="poBox"/>
			    <g:i18nTextarea name="warranty.contact.addressDescriptions" bean="${equipment?.warranty?.contact}" label="${message(code:'entity.address.descriptions.label')}" field="addressDescriptions" height="150" width="300" maxHeight="150" />  	
	   		</fieldset>
	</fieldset>
	
	<fieldset>
    	<legend>Supply Information:</legend>
    	<g:input name="supplier.contactName" label="${message(code:'entity.name.label')}" bean="${equipment?.supplier}" field="contactName"/>
    	<g:input name="supplier.email" label="${message(code:'entity.email.label')}" bean="${equipment?.supplier}" field="email"/>
    	<g:input name="supplier.phone" label="${message(code:'entity.phone.label')}" bean="${equipment?.supplier}" field="phone"/>
    	<g:input name="supplier.poBox" label="${message(code:'entity.address.label')}" bean="${equipment?.supplier}" field="poBox"/>
	    <g:i18nTextarea name="supplier.addressDescriptions" bean="${equipment?.supplier}" label="${message(code:'entity.descriptions.label')}" field="addressDescriptions" height="150" width="300" maxHeight="150" />  	
   	</fieldset>
   	
   	<fieldset>
    	<legend>Manufacture Information:</legend>
    	<g:input name="manufacture.contactName" label="${message(code:'entity.name.label')}" bean="${equipment?.manufacture}" field="contactName"/>
    	<g:input name="manufacture.email" label="${message(code:'entity.email.label')}" bean="${equipment?.manufacture}" field="email"/>
    	<g:input name="manufacture.phone" label="${message(code:'entity.phone.label')}" bean="${equipment?.manufacture}" field="phone"/>
    	<g:input name="manufacture.poBox" label="${message(code:'entity.address.label')}" bean="${equipment?.manufacture}" field="poBox"/>
    	<g:input name="manufacture.street" label="${message(code:'entity.address.label')}" bean="${equipment?.manufacture}" field="street"/>
	    <g:i18nTextarea name="manufacture.addressDescriptions" bean="${equipment?.manufacture}" label="${message(code:'entity.descriptions.label')}" field="addressDescriptions" height="150" width="300" maxHeight="150" />	
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
