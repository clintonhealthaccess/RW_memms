<table class="items spaced">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="code"  title="${message(code: 'sparePart.code.label')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<g:sortableColumn property="type"  title="${message(code: 'sparePart.type.label')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<g:sortableColumn property="model"  title="${message(code: 'sparePart.model.label')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<th><g:message code="location.label"/></th>
			<g:sortableColumn property="currentStatus"  title="${message(code: 'sparePart.status.label')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<g:sortableColumn property="obsolete"  title="${message(code: 'sparePart.obsolete.label')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<g:sortableColumn property="manufacturer"  title="${message(code: 'provider.type.manufacturer')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<g:sortableColumn property="supplier"  title="${message(code: 'provider.type.supplier')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<g:sortableColumn property="purchaser"  title="${message(code: 'sparePart.purchaser.label')}" params="[q:q,'dataLocation.id':dataLocation?.id]" />
			<th><g:message code="work.order.label"/></th>
			<th><g:message code="preventive.order.label"/></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="sparePart">
			<tr >
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'sparePart', action:'edit', params:[id: sparePart.id])}"  class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'sparePart', action:'delete', params:[id: sparePart.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button">
								<g:message code="default.link.delete.label" />
							</a>
						</li>
					</ul>
				</td>
				<td>${sparePart.code}</td>
				<td>${sparePart.type.names}</td>
				<td>${sparePart.model}</td>
				<td>
					<g:message code="datalocation.label"/>: ${sparePart.dataLocation.names}<br/>
					<g:message code="department.label"/>: ${sparePart.department.names}<br/>
					<g:message code="sparePart.room.label"/>: ${sparePart.room}<br/>
				</td>
				<td>
					<a href="${createLinkWithTargetURI(controller:'sparePartStatus', action:'create', params:['sparePart.id': sparePart?.id])}" title="${message(code: 'tooltip.click.update.status')}" class="tooltip">
  	    				${message(code: sparePart.currentStatus?.messageCode+'.'+sparePart.currentStatus?.name)}
  	    			</a>
				</td>
				<td>
					<g:listCheckBox name="obsolete" id="${sparePart.id}" checked="${(!sparePart.obsolete)?:'checked'}"/>
				</td>
				<td>${sparePart.manufacturer.contact.contactName}</td>
				<td>${sparePart.supplier.contact.contactName}</td>
				
				
				<td>${message(code: sparePart.purchaser?.messageCode+'.'+sparePart.purchaser?.name)}</td>
				
				<td>
					<a href="${createLinkWithTargetURI(controller:'workOrderView', action:'list', params:['sparePart.id': sparePart?.id])}" title="${message(code: 'work.order.see.list.label')}" class="tooltip">
  	    				${sparePart.workOrders?.size()}
  	    			</a>
				</td>
				<td>
					<a href="${createLinkWithTargetURI(controller:'preventiveOrderView', action:'list', params:['sparePart.id': sparePart?.id])}" title="${message(code: 'preventive.order.see.list.label')}" class="tooltip">
  	    				${sparePart.preventiveOrders?.size()}
  	    			</a>
				</td>
			</tr>
		</g:each>
	</tbody>	
</table>
<g:render template="/templates/pagination" />
<script type="text/javascript">
	$(document).ready(function() {
		updateEquipment("${createLink(controller:'sparePartView',action: 'updateObsolete')}");
	});
</script>

