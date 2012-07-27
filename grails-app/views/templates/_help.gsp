<g:ifText field="${content}">
	<p class="show-help js_show-help moved"><a href="#"><g:message code="help.tips.show"/></a></p>
	<div class="help-container js_help-container">
		<div class="help js_help push-20">
			<a class="hide-help js_hide-help" href="#"><g:message code="help.tips.hide"/></a>
			${content}
		</div>
	</div>
</g:ifText>