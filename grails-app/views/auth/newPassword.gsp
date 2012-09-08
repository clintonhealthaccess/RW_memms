<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="new.password.title"/></title>
	</head>
	<body>
		<div class="heading1-bar">
		<h1><g:message code="new.password.header.label"/></h1>
		</div>
		<div class="main">
			<g:form action="setPassword" class="simple-list">
				<input type="hidden" name="targetURI" value="${targetURI}" />
	    		<input type="hidden" name="token" value="${token}" />
	    
				<div class="row">
					<label><g:message code="new.password.password.label"/></label>
					<input type="password" class="idle-field" name="password" value="" />
					<div ><g:renderErrors bean="${newPassword}" field="password" /></div>
				</div>
				<div class="row">
					<label ><g:message code="new.password.repeat.label"/></label>
					<input type="password" class="idle-field" name="repeat" value="" />
					<div ><g:renderErrors bean="${newPassword}" field="repeat" /></div>
				</div>
				<div class="buttons">
					<button type="submit">${message(code:'new.password.set.label')}</button>
				</div>			
			</g:form>
		</div>
	</body>
</html>
