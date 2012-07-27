<g:each in="${data}" status="i" var="item">
	<li data-code="${item.id}" id="data-element-${item.id}" class="${(i % 2) == 0 ? 'odd' : 'even'}">
		<a class="no-link cluetip" onclick="return false;" title="${i18n(field:item.names)}"
			href="${createLink(controller: 'data', action:'getDescription', params:[id: item.id])}"
			rel="${createLink(controller: 'data', action:'getDescription', params:[id: item.id])}">
			<g:i18n field="${item.names}"/> [${item.code}] [${item.class.simpleName}]
		</a>
		<span>[${item.id}]</span>
	</li>
</g:each>

