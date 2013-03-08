<%@ page import="org.chai.memms.util.Utils" %>
<div id="form-aside-${field}-${sparePart.id}" class="${(cssClass)?:cssClass}">
	
	<ul>
		
		<li>
			<h6>${message(code:"spare.part.type.label")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="entity.code.label" /></span>
					<span class="text">${sparePart.type?.code}</span>
					<span class="label"><g:message code="entity.name.label" /></span>
					<span class="text">${sparePart.type?.names}</span>
				</li>
			</ul>
		</li>
		<li>
			<h6>${message(code:"spare.part.data.location.label")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="entity.name.label" /></span>				
					<span class="text">${sparePart.dataLocation?.code}</span>
					<span class="label"><g:message code="entity.type.label" /></span>
					<span class="text">${sparePart.dataLocation?.type?.code}</span>
					<span class="label"><g:message code="spare.part.location.label" /></span>
					<span class="text">${sparePart.dataLocation?.location?.code}</span>
				</li>
			</ul>
		</li>
		<li>
			<h6>${message(code:"spare.part.status.current.status")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="spare.part.status.label"/>:</span>
					<span class="text">${message(code: sparePart.statusOfSparePart?.messageCode+'.'+sparePart.statusOfSparePart?.name)}</span>
					<span class="label"><g:message code="spare.part.status.date.of.event.label"/>:</span>
					<span class="text">${Utils.formatDate(sparePart.timeBasedStatus?.dateOfEvent)}</span>
				</li>
			</ul>
		</li>
	
	</ul>
</div>
