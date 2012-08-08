<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="register.title"/></title>
	</head>
	<body>
		<h3><g:message code="register.header.label"/></h3>
			<g:form action="sendRegistration">
	    
			<table>
				<tbody>
					<tr><td>
						<label><g:message code="register.firstname.label"/></label></td>
					<td>
						<input type="text" name="firstname" value="${register?.firstname}" />
						<g:renderErrors bean="${register}" field="firstname" />
					</td></tr>
					
					<tr><td>
						<label><g:message code="register.lastname.label"/></label></td>
					<td>
						<input type="text" name="lastname" value="${register?.lastname}" />
						<g:renderErrors bean="${register}" field="lastname" />
					</td></tr>
				
					<tr><td>
						<label><g:message code="register.organisation.label"/></label></td>
					<td>
						<input type="text" name="organisation" value="${register?.organisation}" />
						<g:renderErrors bean="${register}" field="organisation" />
					</td></tr>
					
					<tr><td>
						<label><g:message code="register.email.label"/></label></td>
					<td>
						<input type="text" name="email" value="${register?.email}" />
						<g:renderErrors bean="${register}" field="email" />
					</td></tr>
					
					<tr><td>
						<label><g:message code="register.phonenumber.label"/></label></td>
					<td>
						<input type="text" name="phoneNumber" value="${register?.phoneNumber}" />
						<g:renderErrors bean="${register}" field="phoneNumber" />
					</td></tr>
					
					<tr>
						<td><label><g:message code="register.language.label"/></label></td>
						<td>
							<g:select name="defaultLanguage" from="${languages}" value="${register?.defaultLanguage}" optionValue="displayLanguage"/>
							<g:renderErrors bean="${register}" field="defaultLanguage" />
						</td>
					</tr>
					
					<tr><td>
						<label><g:message code="register.password.label"/></label></td>
					<td>
						<input type="password" name="password" value="" />
						<g:renderErrors bean="${register}" field="password" />
					</td></tr>
					<tr><td>
						<label><g:message code="register.repeat.label"/></label></td>
					<td>
						<input type="password" name="repeat" value="" />
						<g:renderErrors bean="${register}" field="repeat" />
					</td></tr>
					
					<tr><td></td><td>
						<button type="submit">${message(code:'register.register.label')}</button>
						<div><g:message code="register.info.text"/></div>
					</td></tr>
				</tbody>
			</table>
		</g:form>
	</body>
</html>
