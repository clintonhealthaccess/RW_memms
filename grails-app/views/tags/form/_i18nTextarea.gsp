<div class="row ${hasErrors(bean:bean,field:field, 'errors')}">
	<g:each in="${locales}" var="locale" status="i">
		<div class="toggle-entry ${i!=0?'hidden':''}" data-toggle="${locale}">
			<label for="${name}.${locale}">${label} (${locale})</label>
			 <g:set var="fieldLang" value="${field+'_'+locale}"/>
			${names}
			<textarea type="${type}" class="idle-field" name="${name+'_'+locale}" rows="${rows}">${bean?."$fieldLang"}</textarea>	
		</div>
	</g:each>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}"/></div>
</div>