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
  		<li><a href="${createLink(controller: 'Equipment', action:'summaryPage')}">Inventory</a></li>
  		<li><a href="#">Corrective Maintenance</a></li>
  		<li><a href="#">Preventive Maintenance</a></li>
  		<li><a href="#">Reports</a></li>
  	</ul>
  </div>
</body>
</html>