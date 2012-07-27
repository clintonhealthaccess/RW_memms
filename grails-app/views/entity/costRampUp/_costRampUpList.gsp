<!-- TODO fix this -->

<%@ page import="org.chai.kevin.cost.CostRampUp" %>
<table class="listing">
    <thead>
        <tr>
        	<th/>
            <g:sortableColumn property="name" title="${message(code: 'entity.name.label')}" />
            <th><g:message code="costrampup.years.label" /></th>
        </tr>
    </thead>
    <tbody>
    <g:each in="${entities}" status="i" var="costRampUp">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>
            	<ul class="horizontal">
	            	<li>
	            		<a class="edit-link" href="${createLinkWithTargetURI(action:'edit', id:costRampUp.id)}"><g:message code="default.link.edit.label" /></a>
	            	</li>
	            	<li>
	            		<a class="delete-link" href="${createLinkWithTargetURI(action:'delete', id:costRampUp.id)}"><g:message code="default.link.delete.label" /></a>
	            	</li>
            	</ul>
            </td>
            <td><g:i18n field="${costRampUp.names}" /></td>
            <td>${fieldValue(bean: costRampUp, field: "years")}</td>
        </tr>
    </g:each>
    </tbody>
</table>
