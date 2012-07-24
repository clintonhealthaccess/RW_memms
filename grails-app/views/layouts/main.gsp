<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>

<!DOCTYPE html>
<html>
<head>
	<title><g:layoutTitle /></title>

	<g:layoutHead />
</head>
<body>
	<div id="header">
		<shiro:user>
		<%= SecurityUtils.subject.principal%>
					<%
						def user = User.findByUuid(SecurityUtils.subject.principal, [cache: true])
					%>
					<li>
						<a>
							<%--<g:message code="header.navigation.myaccount"/> : ${user.firstname} ${user.lastname} --%>
						</a>
					</li>
				</shiro:user>
				<shiro:user>
					<li>
						<a ><g:message code="header.labels.logout"/></a>
					</li>
				</shiro:user>
		
	</div>

	<div id="content">
			<g:layoutBody />
	</div>

</body>
</html>