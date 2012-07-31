<%@ page import="org.apache.shiro.SecurityUtils" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title><g:message code="landingpage.title" /></title>
	
	<r:require modules="chosen,richeditor,fieldselection,cluetip,form,dropdown,datepicker,list"/>
</head>
<body>
	<h3 class="heading2-bar text-center"><g:message code="landingpage.welcome.label"/></h3>
	<%--<div class="main">
		<%
			if (SecurityUtils.subject.isPermitted('menu:reports') 
				&& SecurityUtils.subject.isPermitted('menu:survey')
				&& SecurityUtils.subject.isPermitted('menu:planning')) {
				size = "three"
			}
			else size = "two"
		%>
		<shiro:hasPermission permission="menu:reports">
			<g:render template="landingPageItem" model="[
				title: message(code: 'landingpage.reports.label'), image: 'reports.png', 
				class: size, link: createLink(controller: 'dashboard', action: 'view')]"/>
		</shiro:hasPermission>
		
		<shiro:hasPermission permission="menu:survey">
			<g:render template="landingPageItem" model="[
				title: message(code: 'landingpage.survey.label'), image: 'survey.png', 
				class: size, link: createLink(controller: 'editSurvey', action: 'view')]"/>
		</shiro:hasPermission>
		
		<shiro:hasPermission permission="menu:planning">
			<g:render template="landingPageItem" model="[
				title: message(code: 'landingpage.planning.label'), image: 'planning.png', 
				class: size, link: createLink(controller: 'editPlanning', action: 'view')]"/>
		</shiro:hasPermission>
		
		<g:if test="${false}">
			<!-- deactivated for now -->
			<shiro:hasPermission permission="menu:admin">
				<g:render template="landingPageItem" model="[
					title: message(code: 'landingpage.admin.label'), image: 'admin.png', 
					class: size, link: createLink(controller: 'admin', action: 'view')]"/>
			</shiro:hasPermission>
		</g:if>
	</div> --%>
	<ul>
		<li><g:link controller="User">Users Home</g:link></li>
	</ul>
</body>
</html>