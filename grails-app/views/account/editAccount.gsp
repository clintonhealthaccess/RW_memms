<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="myaccount.title"/></title>
	</head>
	<body>
		<h3><g:message code="myaccount.header.label"/></h3>
		<g:form action="saveAccount" >
			<input type="hidden" name="targetURI" value="${targetURI}" />
    
			<table >
				<tbody>
					<tr>
						<td><label><g:message code="myaccount.username.label"/></label></td>
						<td>
							<span class="bold">${user.username}</span> | 
							<span><g:message code="myaccount.username.info"/>
							<a href="${createLink(controller: 'home', action: 'contact')}"><g:message code="myaccount.username.contact.admin"/></a></span>
						</td>
					</tr>
					<tr>
						<td><label><g:message code="myaccount.firstname.label"/></label></td>
						<td>
							<input type="text" name="firstname" value="${user.firstname}" />
							<div class="error-list"><g:renderErrors bean="${user}" field="firstname" /></div>
						</td>
					</tr>
					<tr>
						<td><label><g:message code="myaccount.lastname.label"/></label></td>
						<td>
							<input type="text" name="lastname" value="${user.lastname}" />
							<div class="error-list"><g:renderErrors bean="${user}" field="lastname" /></div>
						</td>
					</tr>
					<tr>
						<td><label><g:message code="myaccount.phonenumber.label"/></label></td>
						<td>
							<input type="text" name="phoneNumber" value="${user.phoneNumber}" />
							<div class="error-list"><g:renderErrors bean="${user}" field="phoneNumber" /></div>
						</td>
					</tr>
					<tr>
						<td><label><g:message code="myaccount.organisation.label"/></label></td>
						<td>
							<input type="text" name="organisation" value="${user.organisation}" />
							<div class="error-list"><g:renderErrors bean="${user}" field="organisation" /></div>
						</td>
					</tr>
					<tr>
						<td><label><g:message code="myaccount.language.label"/></label></td>
						<td>
							<g:select name="defaultLanguage" from="${languages}" value="${user.defaultLanguage}" optionValue="displayLanguage"/>
							<div class="error-list"><g:renderErrors bean="${user}" field="defaultLanguage" /></div>
						</td>
					</tr>
					<tr>
						<td><label><g:message code="myaccount.password.label"/></label></td>
						<td>
							<a href="${createLink(controller: 'auth', action: 'newPassword')}"><g:message code="myaccount.changepassword.link"/></a>
						</td>
					</tr>
					
					<tr><td></td><td><button type="submit">${message(code:'myaccount.save.label')}</button></td></tr>
				</tbody>
			</table>
		</g:form>
	</body>
</html>
