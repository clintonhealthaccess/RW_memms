<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="survey.label"/></th>
			<th><g:message code="entity.description.label"/></th>
			<th><g:message code="skiprule.expression.label"/></th>
			<th><g:message code="default.number.label" args="[message(code:'formelement.label')]"/></th>
			<th><g:message code="default.number.label" args="[message(code:'survey.question.label')]"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="skip">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
			    			<a class="edit-link" href="${createLinkWithTargetURI(controller:'surveySkipRule', action:'edit', params:[id: skip.id])}">
					    		<g:message code="default.link.edit.label" /> 
							</a>
						</li>
						<li>
			    			<a class="delete-link" href="${createLinkWithTargetURI(controller:'surveySkipRule', action:'delete', params:[id: skip.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
				        		<g:message code="default.link.delete.label" /> 
							</a>
						</li>
					</ul>
				</td>
				<td><g:i18n field="${skip.survey.names}" /></td>
				<td><g:i18n field="${skip.descriptions}" /></td>
				<td>${skip.expression}</td>
				<td> 
				   ${skip.skippedFormElements.size()}
			    </td>
				<td>
					${skip.skippedSurveyQuestions.size()}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
