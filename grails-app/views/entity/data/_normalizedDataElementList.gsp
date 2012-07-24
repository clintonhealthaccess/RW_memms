<div class="main">
  <table class="listing">
  	<thead>
  		<tr>
  			<th/>
  			<th><g:message code="entity.id.label"/></th>
  			<th><g:message code="entity.code.label"/></th>
  			<th><g:message code="entity.name.label"/></th>
  			<th><g:message code="entity.type.label"/></th>
  			<th><g:message code="normalizeddataelement.lastrefreshed.label"/></th>
  			<th><g:message code="normalizeddataelement.lastvaluechanged.label"/></th>
  			<th><g:message code="entity.list.manage.label"/></th>
  		</tr>
  	</thead>
  	<tbody>
  		<g:each in="${entities}" status="i" var="normalizedDataElement">
  			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
  				<td>
  	            	<ul class="horizontal">
  			           	<li>
  			           		<a class="edit-link" href="${createLinkWithTargetURI(action:'edit', id: normalizedDataElement.id)}"><g:message code="default.link.edit.label" /></a>
  						</li>
  			           	<li>
  			           		<a class="delete-link" href="${createLinkWithTargetURI(controller:'normalizedDataElement', action:'delete', params:[id:normalizedDataElement.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
  						</li>
  		           	</ul>
  				</td>
  				<td>${normalizedDataElement.id}</td>
  				<td>${normalizedDataElement.code}</td>  				
  				<td class="data-element-explainer" data-data="${normalizedDataElement.id}">
  					<a href="${createLink(controller:'normalizedDataElement', action:'getExplainer', params:[id: normalizedDataElement.id])}"><g:i18n field="${normalizedDataElement.names}"/></a>
  				</td>
  				<td><g:toHtml value="${normalizedDataElement.type.getDisplayedValue(2, 2)}"/></td>
  				<td><g:formatDate format="yyyy-MM-dd HH:mm" date="${normalizedDataElement.refreshed}"/></td>
  				<td><g:formatDate format="yyyy-MM-dd HH:mm" date="${normalizedDataElement.lastValueChanged}"/></td>
  				<td>
					<div class="js_dropdown dropdown"> 
						<a class="js_dropdown-link with-highlight" href="#"><g:message code="entity.list.manage.label"/></a>
						<div class="dropdown-list js_dropdown-list">
							<ul>
								<li>
  									<a href="${createLink(controller:'data', action:'dataElementValueList', params:[data:normalizedDataElement.id])}">
  										<g:message code="dataelement.viewvalues.label"/>
  									</a>
  								</li>
  								<li>
  									<a href="${createLinkWithTargetURI(controller:'data', action:'calculateValues', params:[data:normalizedDataElement.id])}">
  										<g:message code="dataelement.calculatevalues.label"/>
  									</a>
  								</li>
  								<li>
  									<a href="${createLinkWithTargetURI(controller:'data', action:'deleteValues', params:[data:normalizedDataElement.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
  										<g:message code="data.deletevalues.label"/>
  									</a>
  								</li>
  							</ul>
  						</div>
  					</div>
  				</td>
  			</tr>
  			<tr class="explanation-row">
  				<td colspan="8">
  					<div class="explanation-cell" id="explanation-${normalizedDataElement.id}"></div>
  				</td>
  			</tr>
  		</g:each>
  	</tbody>
  </table>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('.data-element-explainer').bind('click', function() {
			var normalizedDataElement = $(this).data('data');
			explanationClick(this, normalizedDataElement, function(){});
			return false;
		});
	});
</script>