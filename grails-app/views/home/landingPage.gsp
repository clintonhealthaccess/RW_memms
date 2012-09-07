<%@ page import="org.apache.shiro.SecurityUtils" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title><g:message code="landingpage.title" /></title>
</head>
<body>
  <h2 class="login-heading"><g:message code="landingpage.welcome.label"/></h2>
	<div class="form-box wide">
  	<ul class="todo">
  		<li><a href="${createLink(controller: 'Equipment', action:'summaryPage')}"><g:message code="header.navigation.inventory"/></a></li>
  		<li><a href="#"><g:message code="header.navigation.corrective.maintenance"/></a></li>
  		<li><a href="#"><g:message code="header.navigation.preventive.maintenance"/></a></li>
  		<li><a href="#"><g:message code="header.navigation.reports"/></a></li>
  	</ul>
  </div>
</body>
</html>