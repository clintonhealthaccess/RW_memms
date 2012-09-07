<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>

<div class="heading1-bar">
	<h1>
		<g:message code="default.new.label" args="[message(code:'equipmentstatus.label')]"/>
	</h1>
	<g:locales/>
</div>

<div class="main">	
	<g:form url="[controller:'equipmentStatus', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
		<div class="row">
			<input type="hidden" name="equipment.id" value="${status.equipment.id}"/>
			<label><g:message code="equipment.serial.number.label"/>:</label>${status.equipment.serialNumber}
		</div>
		<g:selectFromEnum name="status" bean="${status}" values="${Status.values()}" field="status" label="${message(code:'equipment.status.label')}"/>
   		<g:inputDate name="dateOfEvent" precision="minute" default="none" dateFormatLabel = "${message(code:'entity.date.format.time.label')}" value="${status.dateOfEvent}" id="date-of-event" label="${message(code:'equipment.status.date.of.event.label')}" bean="${status}" field="dateOfEvent"/>
		<g:if test="${status.id != null}">
			<input type="hidden" name="id" value="${status.id}"></input>
		</g:if>
		<br/>
		<div class="buttons">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
</div>

