<%@ page import="org.chai.memms.util.Utils" %>
<div id="form-aside-${field}-${equipment.id}" class="${(cssClass)?:cssClass}">
	<h5><g:message code="equipment.details.label" /></h5>
	<ul class="half">
		<li>
			<span class="label"><g:message code="equipment.serial.number.label" /></span>
			<span class="text">${equipment.serialNumber}</span>
		</li>
		<li>
			<span class="label"><g:message code="equipment.type.label" /></span>
			<span class="text">${equipment.type.names}</span>
		</li>
	</ul>
	
</div>
