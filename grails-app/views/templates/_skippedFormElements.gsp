<div class="row ${hasErrors(bean:skip, field:'skippedFormElements', 'errors')}">
	<label><g:message code="skiprule.skippedformelement.label"/>: </label>
			
	<!-- START SKIPPED ELEMENTS -->
	<g:each in="${skip.skippedFormElements}" var="entry">
		<div class="white-box">
			<g:set var="formElement" value="${entry.key}"/>
			<g:set var="prefixes" value="${entry.value}"/>
			
			<label for="skipped.element"><g:message code="skiprule.skippedformelement.label"/>:</label> 
			<select name="skipped.element" class="ajax-search-field skipped-form-elements-list">
				<option value="${formElement.id}" selected>
					${formElement.getLabel(languageService)}[${formElement.id}]
				</option>
			</select>
			<label for="skipped.prefix"><g:message code="skiprule.skippedformelement.prefixes.label"/>:</label>
			<input type="text" value="${prefixes}" name="skipped.prefix"/> 
			<a href="#" onclick="$(this).parent().remove();return false;"><g:message code="default.link.delete.label"/></a>
		</div>
	</g:each>
	<div class="white-box hidden">
		<label for=""><g:message code="skiprule.skippedformelement.label"/>:</label> 
		<select name="skipped.element" class="ajax-search-field skipped-form-elements-list">
			<option value="" selected></option>
		</select>
		<label for="skipped.prefix"><g:message code="skiprule.skippedformelement.prefixes.label"/>:</label>
		<input type="text" value="${prefixes}" name="skipped.prefix"/> 
		<a href="#" onclick="$(this).parent().remove();return false;"><g:message code="default.link.delete.label"/></a>
	</div>
	<a href="#" onclick="$(this).before($(this).prev().clone()); $(this).prev().prev().show(); return false;">
		<g:message code="skiprule.skippedformelement.add.label"/>
	</a>
	<!-- END SKIPPED ELEMENTS -->
	
	<div class="error-list"><g:renderErrors bean="${skip}" field="skippedFormElements" /></div>
</div>
<script type="text/javascript">
	$(document).ready(function() {		
		$(".skipped-form-elements-list").ajaxChosen({
			type : 'GET',
			dataType: 'json',
			url : "${createLink(controller:'formElement', action:'getAjaxData')}"
		}, function (data) {
			var terms = {};
			$.each(data.elements, function (i, val) {
				terms[val.key] = val.value;
			});
			return terms;
		});
	});
</script>