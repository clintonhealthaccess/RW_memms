<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: code)}" />
	<title><g:message code="notification.read.label" /> Notification </title>
	<r:require modules="chosen,fieldselection,cluetip,form"/>
</head>
<body>
	<div  class="entity-form-container togglable">
	<div class="heading1-bar">
		<h1>
			<g:message code="notification.read.label"/> - Notification
		</h1>
	</div>
	
	<div class="main">
	Sent By: ${notification?.sender?.firstname +" "+ notification?.sender?.lastname}
	Content: ${notification?.content}
  	<div class="buttons">
  			<a href="${createLink(uri: targetURI)}"><button><g:message code="default.button.back.label"/></button></a>
  	</div>
  </div>
</div>
</body>
</html>