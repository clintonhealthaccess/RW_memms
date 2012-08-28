<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>
<div>
	<div>
		<h3>
			<g:message code="default.new.label" args="[message(code:'equipmentstatus.label')]"/>
		</h3>
		<g:locales/>
	</div>
	
	<g:form url="[controller:'equipmentStatus', action:'save', params:[targetURI: targetURI]]" useToken="true">
		<div class="row">
			<input type="hidden" name="equipment.id" value="${status.equipment.id}"/>
			<label><g:message code="entity.equipment.label"/>:</label>${status.equipment.serialNumber}
		</div>
		<g:selectFromEnum name="status" bean="${equipmentStatus}" values="${Status.values()}" field="status" label="${message(code:'equipment.status.label')}"/>
   		<g:inputDate name="dateOfEvent" precision="day"  value="${status.dateOfEvent}" id="date-of-event" label="${message(code:'entity.date.of.event.label')}" bean="${equipmentStatus}" field="dateOfEvent"/>
		<g:checkBox name="current"  label="${message(code:'equipment.status.current.label')}" value="${status.current}" bean="${equipment}" field="current"/>
		<g:if test="${status.id != null}">
			<input type="hidden" name="id" value="${status.id}"></input>
		</g:if>
		<div>
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
</div>

