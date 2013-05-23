<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>
<table class="items">
	<thead>
		<tr>
			%{-- TODO --}%
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="order">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				%{-- TODO --}%
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" />