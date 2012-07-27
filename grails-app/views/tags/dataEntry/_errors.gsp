<ul>
	<g:each in="${errors}" var="error">
		<g:if test="${!error.accepted}">
			<g:if test="${error.displayed}">
				<li>
				${error.message}
				<g:if test="${error.rule.allowOutlier}">
					<g:message code="dataentry.validation.error.outlier.text"/>
					<a class="outlier-validation" href="#" data-rule="${error.rule.id}"><g:message code="dataentry.validation.error.outlier.link"/></a>.
					<input type="hidden" class="input" name="elements[${element.id}].value${error.suffix}[warning]" value=""/>
				</g:if>
				</li>
			</g:if>
		</g:if>
		<g:else>
			<input type="hidden" class="input" name="elements[${element.id}].value${error.suffix}[warning]" value="${error.rule.id}"/>
		</g:else>
	</g:each>
</ul>

