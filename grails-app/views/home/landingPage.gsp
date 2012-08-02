<%@ page import="org.apache.shiro.SecurityUtils" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title><g:message code="landingpage.title" /></title>
	
	<r:require modules="chosen,richeditor,fieldselection,cluetip,form,dropdown,datepicker,list"/>
</head>
<body>
	<h3><g:message code="landingpage.welcome.label"/></h3>
	
	<ul>
		<li><g:link controller="User">Users Home</g:link></li>
	</ul>
</body>
</html>