<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>
<div>
	<div>
		<h3>
			<g:message code="default.new.label" args="[message(code:'equipment.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'equipment', action:'save', params:[targetURI: targetURI]]" useToken="true">
	
		<g:input name="serialNumber" label="${message(code:'entity.serialNumber.label')}" bean="${equipment}" field="serialNumber"/>
		<g:input name="purchaseCost" label="${message(code:'entity.purchaseCost.label')}" bean="${equipment}" field="purchaseCost"/>
		
		<g:i18nTextarea name="descriptions" bean="${equipment}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
		<g:i18nTextarea name="observations" bean="${equipment}" label="${message(code:'entity.observations.label')}" field="observations" height="150" width="300" maxHeight="150" />
		
		<g:input name="manufactureDate" label="${message(code:'entity.manufacturedate.label')}" bean="${equipment}" field="manufactureDate"/>
		<g:input name="purchaseDate" label="${message(code:'entity.purchasedate.label')}" bean="${equipment}" field="purchaseDate"/>
		
		<g:selectFromEnum name="status" bean="${equipment}" values="${Status.values()}" field="status" label="${message(code:'equipment.status.label')}"/>
		<g:selectFromEnum name="status" bean="${equipment}" values="${Status.values()}" field="status" label="${message(code:'equipment.status.label')}"/>
		
		<g:if test="${equipment.id != null}">
			<input type="hidden" name="id" value="${equipment.id}"></input>
		</g:if>
		<div>
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
</div>
