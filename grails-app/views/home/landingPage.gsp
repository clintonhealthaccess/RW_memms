<%@ page import="org.apache.shiro.SecurityUtils" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title><g:message code="landingpage.title" /></title>
</head>
<body>
	<h3><g:message code="landingpage.welcome.label"/></h3>
	
	<ul>
		<li><a href="${createLink(controller: 'User', action:'list')}">Users</a></li>
		<li><a href="${createLink(controller: 'Equipment', action:'list')}">Equipments</a></li>
		<li><a href="${createLink(controller: 'Department', action:'list')}">Departments</a></li>
		<li><a href="${createLink(controller: 'EquipmentModel', action:'list')}">Equipment Models</a></li>
		<li><a href="${createLink(controller: 'EquipmentType', action:'list')}">Equipment Types</a></li>
		<li><a href="${createLink(controller: 'EquipmentStatus', action:'list')}">Equipment Status</a></li>
		<li><a href="${createLink(controller: 'Warranty', action:'list')}">Warranties</a></li>
		<li><a href="${createLink(controller: 'Location', action:'list')}">Locations</a></li>
		<li><a href="${createLink(controller: 'DataLocation', action:'list')}">Data Locations</a></li>
		<li><a href="${createLink(controller: 'LocationLevel', action:'list')}">Location Levels</a></li>
		<li><a href="${createLink(controller: 'DataLocationType', action:'list')}">Data Location Types</a></li>
	</ul>
</body>
</html>