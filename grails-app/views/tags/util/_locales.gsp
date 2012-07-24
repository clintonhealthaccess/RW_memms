<div class="locales">
	<g:each in="${locales}" var="locale" status="i">
		<a href="#" class="toggle-link ${i==0?'no-link':''}" data-toggle="${locale}">
			${locale}
		</a>
	</g:each>
	<a href="#" id="js_show-all-language" class="toggle-link ${i==0?'no-link':''}" data-toggle="${locale}" >
		<g:message code="entity.locales.all"/>
	</a>
</div>