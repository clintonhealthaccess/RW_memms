<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>
<%@ page import="org.chai.memms.equipment.Equipment.PurchasedBy" %>
<%@ page import="org.chai.memms.equipment.Equipment.Donor" %>
<div class="filters main">
		  <h2><g:message code="equipment.filter.label" /><a href="#" id="showhide" class="right"><g:message code="entity.show.hide.filter.label" /></a></h2>

			<g:hasErrors bean="${filterCmd}">
				<ul>
					<g:eachError var="err" bean="${filterCmd}">
						<h2><g:message error="${err}" /></h2>
					</g:eachError>
				</ul>
			</g:hasErrors>

			<g:form url="[controller:'equipmentView', action:'filter']" method="get" useToken="false" class="filters-box">
				<ul class="filters-list">
					<li><g:selectFromList name="equipmentType.id"
							label="${message(code:'equipment.type.label')}" bean="${filterCmd}"
							field="type" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'EquipmentType', action:'getAjaxData', params:[class: 'EquipmentType'])}"
							from="${filterCmd?.equipmentType}" value="${filterCmd?.equipmentType?.id}" 
							values="${filterCmd?.equipmentType.collect{it.names + ' ['+ it.code +']'}}"/></li>

					<li><g:selectFromList name="manufacturer.id"
							label="${message(code:'provider.type.manufacturer')}" bean="${filterCmd}"
							field="manufacturer" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'Provider', action:'getAjaxData', params:[class: 'Provider',type:'MANUFACTURER'])}"
							from="${filterCmd?.manufacturer}" value="${filterCmd?.manufacturer?.id}" 
							values="${filterCmd?.manufacturer.collect{it.contact.contactName + ' ['+ it.code +']'}}"/></li>

					<li><g:selectFromList name="supplier.id"
							label="${message(code:'provider.type.supplier')}" bean="${filterCmd}"
							field="supplier" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'Provider', action:'getAjaxData', params:[class: 'Provider',type:'SUPPLIER'])}"
							from="${filterCmd?.supplier}" value="${filterCmd?.supplier?.id}"
							values="${filterCmd?.supplier.collect{it.contact.contactName + ' ['+ it.code +']'}}"
							/></li>
					<li><g:selectFromList name="serviceProvider.id"
							label="${message(code:'provider.type.serviceProvider')}" bean="${filterCmd}"
							field="serviceProvider" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'Provider', action:'getAjaxData', params:[class: 'Provider',type:'SERVICEPROVIDER'])}"
							from="${filterCmd?.serviceProvider}" value="${filterCmd?.serviceProvider?.id}"
							values="${filterCmd?.serviceProvider.collect{it.contact.contactName + ' ['+ it.code +']'}}"
							/></li>
					<li>
						<label><g:message code="equipment.obsolete.label" /></label> 
						<select name="obsolete">
								<option value=""><g:message code="default.please.select" /></option>
								<option value="true" ${filterCmd?.obsolete?.equals("true")? 'selected' : ''} ><g:message code="default.boolean.true" /></option>
								<option value="false" ${filterCmd?.obsolete?.equals("false")? 'selected' : ''}><g:message code="default.boolean.false" /></option>
						</select>
					</li>
					<li><g:selectFromEnum name="status" values="${Status.values()}" field="status" label="${message(code:'equipment.status.label')}" /></li>
					<li><g:selectFromEnum name="purchaser" values="${PurchasedBy.values()}" field="purchaser" label="${message(code:'equipment.purchaser.label')}" /></li>
					<li><g:selectFromEnum name="donor" values="${Donor.values()}" field="donor" label="${message(code:'equipment.donor.label')}" /></li>
				</ul>
				<button type="submit">Filter</button>
				<input type="hidden" name="dataLocation.id" value="${dataLocation.id}"/>
		  </g:form>
		</div>
		<g:if test="${params?.q}">
		<h2 class="filter-results">Showing filtered list of equipment which contain search term ${params?.q}</h2>
		</g:if>
		