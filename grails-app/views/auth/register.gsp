<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="register.title"/></title>
	</head>
	<body>
		<h3 class="heading2-bar text-center"><g:message code="register.header.label"/></h3>
			<g:form action="sendRegistration" class="nice-form">
	    
			<table class="listing">
				<tbody>
					<tr><td>
						<label><g:message code="register.firstname.label"/></label></td>
					<td>
						<input type="text" name="firstname" value="${register?.firstname}" />
						<div class="error-list"><g:renderErrors bean="${register}" field="firstname" /></div>
					</td></tr>
					
					<tr><td>
						<label><g:message code="register.lastname.label"/></label></td>
					<td>
						<input type="text" name="lastname" value="${register?.lastname}" />
						<div class="error-list"><g:renderErrors bean="${register}" field="lastname" /></div>
					</td></tr>
				
					<tr><td>
						<label><g:message code="register.organisation.label"/></label></td>
					<td>
						<input type="text" name="organisation" value="${register?.organisation}" />
						<div class="error-list"><g:renderErrors bean="${register}" field="organisation" /></div>
					</td></tr>
					
					<tr><td>
						<label><g:message code="register.email.label"/></label></td>
					<td>
						<input type="text" name="email" value="${register?.email}" />
						<div class="error-list"><g:renderErrors bean="${register}" field="email" /></div>
					</td></tr>
					
					<tr><td>
						<label><g:message code="register.phonenumber.label"/></label></td>
					<td>
						<input type="text" name="phoneNumber" value="${register?.phoneNumber}" />
						<div class="error-list"><g:renderErrors bean="${register}" field="phoneNumber" /></div>
					</td></tr>
					
					<tr>
						<td><label><g:message code="register.language.label"/></label></td>
						<td>
							<g:select name="defaultLanguage" from="${languages}" value="${register?.defaultLanguage}" optionValue="displayLanguage"/>
							<div class="error-list"><g:renderErrors bean="${register}" field="defaultLanguage" /></div>
						</td>
					</tr>
					
					<tr><td>
						<label><g:message code="register.password.label"/></label></td>
					<td>
						<input type="password" name="password" value="" />
						<div class="error-list"><g:renderErrors bean="${register}" field="password" /></div>
					</td></tr>
					<tr><td>
						<label><g:message code="register.repeat.label"/></label></td>
					<td>
						<input type="password" name="repeat" value="" />
						<div class="error-list"><g:renderErrors bean="${register}" field="repeat" /></div>
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
