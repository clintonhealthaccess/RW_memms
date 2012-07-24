<h4 class="nice-title">
	<span class="nice-title-image">
		<img src="${resource(dir:'images/icons',file: file)}" />
	</span>
	${title}
	<g:if test="${program != null}">
	&nbsp;
		<g:render template="/templates/help_tooltip" 
			model="[names: i18n(field: program.names), descriptions: i18n(field: program.descriptions)]" />
	</g:if>
</h4>
