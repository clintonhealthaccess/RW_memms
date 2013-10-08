<span class="right">
	<g:form class="search-form search" url="[controller:'search', action: 'findEquipments']">
		<div class="row">
			<input type="checkbox" name="includeLocation" value=""/><label class="has-helper">Include locations</label>
		</div>
		<div class="row">
			<input type="text" name="term" value="" class="search-term"/>
			<button type="submit" class="medium equipment-search"><g:message code="default.button.search.label"/></button>
		</div>
		<div class="row location-container">
			<g:selectFromList name="locationIds" label="${message(code:'location.label')}" bean="${cmd}" field="locations" optionKey="id" multiple="true"
	  			ajaxLink="${createLink(controller:'location', action:'getAjaxData', params: [class: 'CalulculationLocation'])}"
	  			from="${locations}" value="${location?.id}" values="${locations.collect{it.names}}" />

		</div>
	</g:form>
</span>


<script type="text/javascript">
	$(document).ready(function() {
		search($("form.search-form").attr("action"))
	});
</script>





