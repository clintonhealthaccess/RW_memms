<%@ page import="org.chai.memms.security.User" %>
<%@ page import="org.chai.memms.security.User.UserType" %>

<div class="filters main">
		  <h2><g:message code="user.filter.label" /><a href="#" id="showhide" class="right"><g:message code="entity.show.hide.filter.label" /></a></h2>

			<g:hasErrors bean="${filterCmd}">
				<ul>
					<g:eachError var="err" bean="${filterCmd}">
						<h2><g:message error="${err}" /></h2>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form url="[controller:'user', action:'filter']" method="get" useToken="false" class="filters-box">
				<ul class="filters-list">

                    <li><g:selectFromEnum name="userType" values="${UserType.values()}" field="userType" label="${message(code:'user.type.label')}" /></li>  
                       
					<li><g:selectFromList name="roles.id"
							label="${message(code:'roles.label')}" bean="${filterCmd}" field="roles"
							from="${filterCmd?.roles}" value="${filterCmd?.roles?.id}" 
							values="${filterCmd?.roles.collect{it.names + ' ['+ it.code +']'}}"/></li>

				   <li><g:selectFromList name="location.id"
							label="${message(code:'location.label')}" bean="${filterCmd}" field="location"
							from="${filterCmd?.location}" value="${filterCmd?.location?.id}" 
							values="${filterCmd?.location.collect{it.names + ' ['+ it.code +']'}}"/></li>					

					<li>
						<label><g:message code="user.active.label" /></label> 
						<select name="active">
								<option value=""><g:message code="default.please.select" /></option>
								<option value="true" ${filterCmd?.active?.equals("true")? 'selected' : ''} ><g:message code="active.boolean.true" /></option>
								<option value="false" ${filterCmd?.active?.equals("false")? 'selected' : ''}><g:message code="active.boolean.false" /></option>
						</select></li>
					
					<li>
						<label><g:message code="user.confirmed.label" /></label> 
						<select name="confirmed">
								<option value=""><g:message code="default.please.select" /></option>
								<option value="true" ${filterCmd?.confirmed?.equals("true")? 'selected' : ''} ><g:message code="confirmed.boolean.true" /></option>
								<option value="false" ${filterCmd?.confirmed?.equals("false")? 'selected' : ''}><g:message code="confirmed.boolean.false" /></option>
						</select></li>
					
				</ul>
				<button type="submit"><g:message code="entity.filter.label" /></button>
				<a href="#" class="clear-form"><g:message code="default.link.clear.form.label"/></a>
		  </g:form>
</div>
<g:if test="${params?.q}">
	<h2 class="filter-results">
		<g:message code="entity.filter.message.label" args="${[message(code: 'user.label'),params?.q]}" />
	</h2>
</g:if>
		