<div class="main">
  <table class="listing">
  	<thead>
  		<tr>
  			<th/>
  		    <g:sortableColumn property="id" title="${message(code: 'entity.id.label')}" />
  		    <g:sortableColumn property="code" title="${message(code: 'entity.code.label')}" />
  		    <th><g:message code="entity.type.label"/></th>
  			<th><g:message code="entity.name.label"/></th>
  			<th><g:message code="calculation.expression.label"/></th>
  			<th><g:message code="calculation.lastrefreshed.label"/></th>
  			<th><g:message code="calculation.lastvaluechanged.label"/></th>
  			<th><g:message code="entity.list.manage.label"/></th>
  		</tr>
  	</thead>
  	<tbody>
  		<g:each in="${entities}" status="i" var="calculation"> 
  			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
  				<td>
  					<ul class="horizontal">
  						<li>
  							<a class="edit-link" href="${createLinkWithTargetURI(controller:calculation.class.simpleName.toLowerCase(), action:'edit', params:[id: calculation.id])}">
  								<g:message code="default.link.edit.label" />
  							</a>
  						</li>
  						<li>
  							<a class="delete-link" href="${createLinkWithTargetURI(controller:calculation.class.simpleName.toLowerCase(), action:'delete', params:[id: calculation.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
  								<g:message code="default.link.delete.label" />
  							</a>
  						</li>
  					</ul>
  				</td>
  				<td>${calculation.id}</td>
  				<td>${calculation.code}</td>  				
  				<td><g:message code="${calculation.class.simpleName.toLowerCase()}.label"/></td>
  				<td data-data="${calculation.id}"><g:i18n field="${calculation.names}" /></td>  				
  				<td>${calculation.expression}</td>
  				<td><g:formatDate format="yyyy-MM-dd HH:mm" date="${calculation.refreshed}"/></td>
  				<td><g:formatDate format="yyyy-MM-dd HH:mm" date="${calculation.lastValueChanged}"/></td>
  				<td>
					<div class="js_dropdown dropdown"> 
						<a class="js_dropdown-link with-highlight" href="#"><g:message code="entity.list.manage.label"/></a>
						<div class="dropdown-list js_dropdown-list">
							<ul>
  								<li>
  									<a href="${createLinkWithTargetURI(controller:'data', action:'calculateValues', params:[data:calculation.id])}">
  										<g:message code="dataelement.calculatevalues.label"/>
  									</a>
  								</li>
  								<li>
  									<a href="${createLinkWithTargetURI(controller:'data', action:'deleteValues', params:[data:calculation.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');">
  										<g:message code="data.deletevalues.label"/>
  									</a>
  								</li>
  							</ul>
  						</div>
  					</div>
  				</td>
  			</tr>
  		</g:each>
  	</tbody>
  </table>
</div>