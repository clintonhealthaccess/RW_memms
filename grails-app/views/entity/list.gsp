<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: code)}" />
		<g:set var="entityClass" value="${entityClass}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<r:require modules="chosen,form,tipsy,cluetip"/>
	</head>
	<body>
		<g:render template="/templates/genericList" model="[entityName: entityName,entityClass: entityClass,template: '/entity/'+template,filterTemplate:filterTemplate]"/>
		<script type="text/javascript">
			$(document).ready(function() {
				listGridAjaxInit()
			});
		</script>
	</body>
</html>