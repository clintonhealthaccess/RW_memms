<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="myaccount.title"/></title>
	</head>
	<body>
	  <div class="heading1-bar">
		  <h1><g:message code="myaccount.header.label"/></h1>
		</div>
		
		<div class="main">
		  <g:form action="saveAccount" class="simple-list">
  			<input type="hidden" name="targetURI" value="${targetURI}" />
  			<div class="row">
  			  <label><g:message code="myaccount.username.label"/></label>
					<span class="bold">${user.username}</span> | 
					<span><g:message code="myaccount.username.info"/>
					<a href="${createLink(controller: 'home', action: 'contact')}"><g:message code="myaccount.username.contact.admin"/></a></span>
        </div>
        <div class="row">
  			  <label><g:message code="myaccount.firstname.label"/></label>
					<input type="text" name="firstname" value="${user.firstname}" />
					<div class="error-list"><g:renderErrors bean="${user}" field="firstname" /></div>
				</div>
				<div class="row">
  				<label><g:message code="myaccount.lastname.label"/></label>
					<input type="text" name="lastname" value="${user.lastname}" />
					<div class="error-list"><g:renderErrors bean="${user}" field="lastname" /></div>
        </div>
        <div class="row">
					<label><g:message code="myaccount.phonenumber.label"/></label>
					<input type="text" name="phoneNumber" value="${user.phoneNumber}" />
					<div class="error-list"><g:renderErrors bean="${user}" field="phoneNumber" /></div>
				</div>
				<div class="row">
  				<label><g:message code="myaccount.organisation.label"/></label>
					<input type="text" name="organisation" value="${user.organisation}" />
					<div class="error-list"><g:renderErrors bean="${user}" field="organisation" /></div>
				</div>
				<div class="row">
  			  <label><g:message code="myaccount.language.label"/></label>
					<g:select name="defaultLanguage" from="${languages}" value="${user.defaultLanguage}" optionValue="displayLanguage"/>
					<div class="error-list"><g:renderErrors bean="${user}" field="defaultLanguage" /></div>
				</div>
				<div class="row">
  				<label><g:message code="myaccount.password.label"/></label>
					<a href="${createLink(controller: 'auth', action: 'newPassword')}"><g:message code="myaccount.changepassword.link"/></a>
				</div>
				<div class="buttons">
    			<button type="submit">${message(code:'myaccount.save.label')}</button>
    		</div>
  		</g:form>
  	</div>
	</body>
</html>
