<div class="data-search-column">
	<g:form name="search-data-form" class="search-form" url="${formUrl}">
		<div class="row">
			<label for="searchText"><g:message code="default.button.search.label"/>: </label>
	    	<input name="searchText" class="idle-field"></input>
	    </div>
		<div class="row">
			<button type="submit"><g:message code="default.button.search.label"/></button>
			<div class="clear"></div>
		</div>
	</g:form>
	
    <ul class="filtered idle-field" id="data"></ul>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		getDataElement(function(event){
			if ($('.in-edition').size() == 1) {
				var edition = $('.in-edition')[0]
				$(edition).replaceSelection('$'+$(this).data('code'));
			}
		});
		$('${element}')
		.bind('click keypress focus',
			function(){
				$(this).addClass('in-edition');
			}
		)
		.bind('blur',
			function(){
				$(this).removeClass('in-edition');
			}
		);
	});
</script>