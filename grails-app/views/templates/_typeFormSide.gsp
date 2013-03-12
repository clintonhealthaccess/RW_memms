<%@ page import="org.chai.memms.util.Utils" %>
<div id="form-aside-${field}-${type.id}" class="${(cssClass)?:cssClass}">
	<h5><g:message code="spare.part.type.details.label" /></h5>
	<ul class="half">
		<li>
			<span class="label"><g:message code="entity.code.label" /></span>
			<span class="text">${type?.code}</span>
		</li>
		<li>
			<span class="label"><g:message code="entity.name.label" /></span>
			<span class="text">${type?.names}</span>
		</li>
	</ul>
	<ul class="half">
		<li>
			<span class="label"><g:message code="spare.part.type.added.on.label" /></span>
			<span class="text">${Utils.formatDate(type?.dateCreated)}</span>
		</li>
		<li>
			<span class="label"><g:message code="spare.part.type.last.modified.on.label" /></span>
			<span class="text">${Utils.formatDate(type?.lastUpdated)}</span>
		</li>
	</ul>
	<ul>
		<li>
			<h6>${message(code:"spare.part.type.manufacturer.label")}</h6>
			<ul>
				<li>
					<span class="label"><g:message code="spare.part.type.manufacturer.code.label"/>:</span>
					<span class="text">${type?.manufacturer?.code}</span>
					<span class="label"><g:message code="spare.part.type.manufacturer.type.label"/>:</span>
					<span class="text">${type?.manufacturer?.type}</span>
				</li>
			</ul>
		</li>
	</ul>
</div>
