<%@ page import="org.chai.memms.util.Utils" %>
<table class="items">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="${i18nField(field: 'names')}" params="[q:q]" title="${message(code: 'entity.name.label')}" />
			<g:sortableColumn property="partNumber" params="[q:q]" title="${message(code: 'entity.part.number.label')}" />	
			<th><g:message code="entity.manufacturer.label"/></th>
			<th><g:message code="sparePartType.quantity.in.stock.label"/></th>	
			<th><g:message code="sparePartType.estimated.date.stockout.label"/></th>
			<g:sortableColumn property="discontinuedDate" params="[q:q]" title="${message(code: 'entity.discontinued.date.label')}" />
			<th/>
			<th/>
			<th/>
       </tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="type">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'sparePartType', action:'edit', params:[id: type.id])}" class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'sparePartType', action:'delete', params:[id: type.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
						</li>		
					</ul>
				</td>
				<td>
					${type.names}
				</td>
				<td>
					${type.partNumber}
				</td>
				<td>
					${type.manufacturer.contact.contactName}
				</td>
				<td>
					
				</td>
				<td>
				    <a href="${createLinkWithTargetURI(controller:'sparePartView', action:'list', params:[sparePartType: type.id,statusOfSparePart:"INSTOCK"])}">
					     ${type.inStockSpareParts.size()}
					</a>
				</td>
				<td>
					${Utils.formatDateWithTime(type?.discontinuedDate)}
				</td>
				<td>
					<a href="${createLinkWithTargetURI(controller:'equipmentType', action:'list', params:[sparePartType: type.id])}">
						<g:message code="equipmentType.compatible.equipment.types.label" />
					</a>
				</td> 
				<td>
					<a href="${createLinkWithTargetURI(controller:'provider', action:'list', params:[sparePartType: type.id,type:"SUPPLIER"])}">
						<g:message code="provider.vendors.label" />
					</a>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<g:render template="/templates/pagination" />



	
		
