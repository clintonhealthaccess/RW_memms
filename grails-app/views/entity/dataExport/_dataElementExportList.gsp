<%@page import="org.chai.kevin.util.Utils"%>

<table class="listing">
	<thead>
		<tr>
			<th/>
			<th><g:message code="entity.description.label"/></th>
			<th><g:message code="exporter.locations"/></th>
			<th><g:message code="exporter.periods"/></th>
			<th><g:message code="exporter.location.types"/></th>
			<th><g:message code="exporter.dataelement.label"/></th>
			<th><g:message code="exporter.create.on"/></th>
			<th><g:message code="entity.list.manage.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="export">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
            		<ul class="horizontal">
		           		<li>
		           			<a class="edit-link" href="${createLinkWithTargetURI(controller:'dataElementExport', action:'edit', params:[id: export.id])}"><g:message code="default.link.edit.label" /></a>
						</li>
		           		<li>
		           			<a class="delete-link" href="${createLinkWithTargetURI(controller:'dataElementExport', action:'delete', params:[id:export.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');"><g:message code="default.link.delete.label" /></a>
						</li>
	           		</ul>
				</td>
				<td><g:i18n field="${export.descriptions}"/></td>
				<td>
					<g:each in="${export.locations}" status="l" var="location">
						<g:i18n field="${location.parent?.names}"/> - <g:i18n field="${location.names}"/>
						${(l < export.locations.size()-1)? ',' : ''}
					</g:each>
				</td>
  				<td>
	  				<g:each in="${export.periods}" status="p" var="period">
						[${Utils.formatDate(period.startDate)} - ${Utils.formatDate(period.endDate)}]
						${(p < export.periods.size()-1)? ',' : ''}
					</g:each>
  				</td>
  				<td>
  					${export.typeCodeString}
  				</td>
  				<td>
	  				<g:each in="${export.dataElements}" status="d" var="dataElement">
	  				 	<g:i18n field="${dataElement.names}"/>
						${(d < export.dataElements.size()-1)? ',' : ''}
					</g:each>

  				</td>
  				<td>${Utils.formatDateWithTime(export.date)}</td>
  				<td>
	  				<div class="js_dropdown dropdown"> 
						<a class="js_dropdown-link with-highlight" href="#"><g:message code="entity.list.manage.label"/></a>
						<div class="dropdown-list js_dropdown-list">
							<ul>
								<li>
	  								<a href="${createLinkWithTargetURI(controller:'dataElementExport', action:'export', params:['export.id': export.id, method: method])}"><g:message code="exporter.download.label" /></a>
	  							</li>
	  							<li>
	  								<a href="${createLinkWithTargetURI(controller:'dataElementExport', action:'clone', params:['export.id': export.id, method: method])}"><g:message code="exporter.clone.label" /></a>
	  							</li>
	  						</ul>
  						</div>
 					</div>
  				</td>
			</tr>
		</g:each>
	</tbody>
</table>
