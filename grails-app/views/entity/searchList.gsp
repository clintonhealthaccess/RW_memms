<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: code)}" />
		<g:set var="entityClass" value="${entityClass}" />
		<title><g:message code="entity.search.label" args="[entityName]" /></title>
		<r:require modules="chosen,form,tipsy,cluetip"/>
	</head>
	<body>
		<div class="entity-list">
			<div>
				<div class="heading1-bar">
					<h1><g:message code="${partialCodeName}.general.search.list.label" args="[entityName]" /></h1>
				</div>
					<g:render template="/entity/${searchTemplate}" />	
				<div id ="list-grid" class="main table">
					<div class="spinner-container">
						<img src="${resource(dir:'images',file:'list-spinner.gif')}" class="ajax-big-spinner"/>
					</div>
					<div class="list-template ${(searchCmd)?:'hide-template'}">
						<g:render template="/entity/${template}" />
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				listGridAjaxInit()
				$(".hide-template").hide();
			});
		</script>
	</body>
</html>





