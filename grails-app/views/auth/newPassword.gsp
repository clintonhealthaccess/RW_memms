<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="new.password.title"/></title>
	</head>
	<body>
		<h3><g:message code="new.password.header.label"/></h3>
		<div class="form-box">
			<g:form action="setPassword">
				<input type="hidden" name="targetURI" value="${targetURI}" />
	    		<input type="hidden" name="token" value="${token}" />
	    
				<table >
					<tbody>
						<tr>
							<td>
								<label><g:message code="new.password.password.label"/></label>
								<input type="password" name="password" value="" />
								<div ><g:renderErrors bean="${newPassword}" field="password" /></div>
							</td>
						</tr>
						<tr>
							<td>
								<label ><g:message code="new.password.repeat.label"/></label>
								<input type="password" name="repeat" value="" />
								<div ><g:renderErrors bean="${newPassword}" field="repeat" /></div>
							</td>
						</tr>
						
						<tr><td><button type="submit">${message(code:'new.password.set.label')}</button></td></tr>
					</tbody>
				</table>
			</g:form>
		</div>
	</body>
</html>
