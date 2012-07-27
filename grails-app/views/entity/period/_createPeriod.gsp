<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title">
			<g:message code="default.new.label" args="[message(code:'period.label')]"/>
		</h3>
		<g:locales/>
	</div>
	<g:form url="[controller:'period', action:'save', params:[targetURI: targetURI]]" useToken="true">
	
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${period}" field="code" />
		
		<div class="row">
			<label><g:message code="period.startdate.label" /></label>
			<g:datePicker name="startDate" value="${period.startDate}"  precision="day"  years="${Calendar.getInstance().get(Calendar.YEAR)+10..1990}"/>
		</div>
		<div class="row ${hasErrors(bean:period,field:'endDate','errors')}">
			<label><g:message code="period.enddate.label" /></label>
			<g:datePicker name="endDate" value="${period.endDate}" precision="day"  years="${Calendar.getInstance().get(Calendar.YEAR)+10..1990}"/>
			<div class="error-list"><g:renderErrors bean="${period}" field="endDate" /></div>
		</div>
		<g:if test="${period.id != null}">
			<input type="hidden" name="id" value="${period.id}"></input>
		</g:if>
		<br />
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
</div>