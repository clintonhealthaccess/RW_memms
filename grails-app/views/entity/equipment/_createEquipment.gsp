<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.equipment.EquipmentStatus.Status" %>
<div  class="entity-form-container togglable">
  <div class="heading1-bar">
		<h1>
			<g:if test="${equipment.id != null}">
				<g:message code="default.edit.label" args="[message(code:'equipment.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'equipment.label')]" />
			</g:else>
		</h1>
		<g:locales/>
	</div>
	<div class="main">
  	<g:form url="[controller:'equipment', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  	  <div class="form-section">
    	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}">
          </span>
          <g:message code="equipment.section.basic.information.label" default="Basic Information"/>
        </h4>  			  		
  		<div class="row">
			<input type="hidden" name="dataLocation.id" value="${equipment.dataLocation.id}" />
			<label><g:message code="datalocation.label"/>:</label> ${equipment.dataLocation.names}
		</div>				
      	<g:selectFromList name="type.id" label="${message(code:'equipment.type.label')}" bean="${equipment}" field="type" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'equipmentType', action:'getAjaxData')}"
  			from="${types}" value="${equipment?.type?.id}" values="${types.collect{it.names}}" />
			
    		<g:input name="expectedLifeTime" label="${message(code:'equipment.expected.life.time.label')}" bean="${equipment}" field="expectedLifeTime"/>
      		<g:input name="serialNumber" label="${message(code:'equipment.serial.number.label')}" bean="${equipment}" field="serialNumber"/>
      		<g:input name="model" label="${message(code:'equipment.model.label')}" bean="${equipment}" field="model"/>
      		<g:i18nTextarea name="descriptions" bean="${equipment}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
		
      		<g:selectFromList name="department.id" label="${message(code:'department.label')}" bean="${equipment}" field="department" optionKey="id" multiple="false"
    			ajaxLink="${createLink(controller:'department', action:'getAjaxData')}"
    			from="${departments}" value="${equipment?.department?.id}" values="${departments.collect{it.names}}" />
			
      		<g:input name="room" label="${message(code:'equipment.room.label')}" bean="${equipment}" field="room"/>
    		<g:inputBox name="obsolete"  label="${message(code:'equipment.obsolete.label')}" bean="${equipment}" field="obsolete" value="${equipment.obsolete}" checked="${(equipment.obsolete)? true:false}"/>
    		<g:inputBox name="donation"  label="${message(code:'equipment.donation.label')}" bean="${equipment}" field="donation" value="${equipment.donation}" checked="${(equipment.donation)? true:false}"/>
    	</fieldset>	
    	<div class="form-aside">
       	  <h5>Equipment Type Details</h5>
       	  <ul class="half">
       	    <li>
       	      <span class="label">Code</span>
       	      <span class="text">${equipment?.type?.code}</span>
     	    </li>
     	    <li>
       	      <span class="label">Name</span>
       	      <span class="text">${equipment?.type?.names}</span>
       	    </li>
       	    </ul>
       	    <ul class="half">
       	     <li>
       	      <span class="label">Added On:</span>
       	      <span class="text">${Utils.formatDate(equipment?.type?.addedOn)}</span>
       	    </li>
       	    <li>
       	      <span class="label">Last Modification:</span>
       	      <span class="text">${Utils.formatDate(equipment?.type?.lastModifiedOn)}</span>
       	    </li>
       	  </ul>
       	</div>
      </div>   
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}">

          </span>
          <g:message code="equipment.section.manufacture.information.label" default="Manufacture Information"/>
        </h4>
      	<g:selectFromList name="manufacture.id" label="${message(code:'provider.manufacture.label')}" bean="${equipment}" field="manufacture" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'provider', action:'getAjaxData', params: [type:'MANUFACTURE'])}"
  			from="${manufactures}" value="${equipment?.manufacture?.id}" values="${manufactures.collect{it.contact.contactName}}" />	
  			<g:inputDate name="manufactureDate" precision="day"  value="${equipment.manufactureDate}" id="manufacture-date" label="${message(code:'equipment.manufacture.date.label')}" bean="${equipment}" field="manufactureDate"/>
     	</fieldset>
    	  <div class="form-aside">
       	  <h5>Manufacture Details</h5>
       	  <ul class="half">
       	    <li>
       	      <span class="label">Name:</span>
       	      <span class="text">${equipment?.manufacture?.contact?.contactName}</span>
       	    </li>
       	    <li>
       	      <span class="label">Email</span>
       	      <span class="text">${equipment?.manufacture?.contact?.email}</span>
       	    </li>
       	  </ul>
    	  <ul class="half">
       	    <li>
       	      <span class="label">Phone</span>
       	      <span class="text">${equipment?.manufacture?.contact?.phone}</span>
       	    </li>
       	    <li>
       	      <span class="label">PO. Box</span>
       	      <span class="text">${equipment?.manufacture?.contact?.poBox}</span>
       	    </li>
       	    <li>
       	      <span class="label">Street</span>
       	      <span class="text">${equipment?.manufacture?.contact?.street}</span>
     	    </li>
       	  </ul>
       	</div>
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
           <img src="${resource(dir:'images/icons',file:'star_small.png')}">
          </span>
          <g:message code="equipment.section.supplier.information.label" default="Supplier Information"/>
        </h4>
      	<g:selectFromList name="supplier.id" label="${message(code:'provider.supplier.label')}" bean="${equipment}" field="supplier" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'provider', action:'getAjaxData', params: [type:'SUPPLIER'])}"
  			from="${suppliers}" value="${equipment?.supplier?.id}" values="${suppliers.collect{it.contact.contactName}}" />		
    		<g:inputDate name="purchaseDate" precision="day" id="purchase-date" value="${equipment.purchaseDate}" label="${message(code:'equipment.purchase.date.label')}" bean="${equipment}" field="purchaseDate"/>
    		<g:input name="purchaseCost" label="${message(code:'equipment.purchase.cost.label')}" bean="${equipment}" field="purchaseCost"/>
     	</fieldset>
    	<div class="form-aside">
       	  <h5>Supplier Details</h5>
          <ul class="half">
       	    <li>
       	      <span class="label">Name:</span>
       	      <span class="text">${equipment?.supplier?.contact?.contactName}</span>
       	    </li>
       	    <li>
       	      <span class="label">Email</span>
       	      <span class="text">${equipment?.supplier?.contact?.email}</span>
       	    </li>
       	  </ul>
    	  <ul class="half">
    	  	<li><span class="label">Address</span></li>
       	    <li>
       	      <span class="label">Phone</span>
       	      <span class="text">${equipment?.supplier?.contact?.phone}</span>
       	    </li>
       	    <li>
       	      <span class="label">PO. Box</span>
       	      <span class="text">${equipment?.supplier?.contact?.poBox}</span>
       	    </li>
       	    <li>
       	      <span class="label">Street</span>
       	      <span class="text">${equipment?.supplier?.contact?.street}</span>
     	    </li>
       	  </ul>
       	</div>
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}" alt="Section"/>
          </span>
          <g:message code="equipment.section.status.information.label" default="Status Information"/> 
        </h4>
      	<g:if test="${equipment.id == null}">
     			<g:selectFromEnum name="status" bean="${equipment}" values="${Status.values()}" field="status" label="${message(code:'equipment.status.label')}"/>
     			<g:inputDate name="dateOfEvent" precision="day"  value="${equipment.status?.dateOfEvent}" id="date-of-event" label="${message(code:'equipment.status.date.of.event.label')}" bean="${equipment.status}" field="status.dateOfEvent"/>
      	</g:if>
      	<g:if test="${equipment?.status!=null}">
	    	<table class="items">
	    		<tr>
	    			<th></th>
	    			<th>${message(code:'equipment.status.label')}</th>
	    			<th>${message(code:'equipment.status.date.of.event.label')}</th>
	    			<th>${message(code:'equipment.status.recordedon.label')}</th>
	    			<th>${message(code:'equipment.status.current.label')}</th>
	    			
	    		</tr>
	    		<g:each in="${equipment.status.sort{a,b -> (a.current > b.current) ? -1 : 1}}" status="i" var="status">
		    		<g:if test="${i+1<numberOfStatusToDisplay}">
			    		<tr>
			    			<td>
				    		<ul>
								<li>
									<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'delete', params:[id: status.id,equipment: equipment?.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
								</li>
							</ul>
			    			</td>
			    			<td>${message(code: status?.status?.messageCode+'.'+status?.status?.name)}</td>
			    			<td>${Utils.formatDate(status?.dateOfEvent)}</td>
			    			<td>${Utils.formatDate(status?.statusChangeDate)}</td>
			    			<td>${(status.current)? '\u2713':'X'}</td>
			    		</tr>
		    		</g:if>
	    		</g:each>
	    	</table>
	    	<br />
  	    	<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'create', params:[equipment: equipment?.id])}" class="next medium gray">
  	    		<g:message code="equipment.change.status.label" default="Change Status"/>
  	    	</a>
  	    	<a href="${createLinkWithTargetURI(controller:'equipmentStatus', action:'list', params:[equipment: equipment?.id])}">
  	    		<g:message code="equipment.see.all.status.label" default="See all status"/>
  	    	</a>
     		</g:if>
     	</fieldset>
  	    <div class="form-aside">
       	  
       	</div>
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}" alt="Section"/>
          </span>
          <g:message code="equipment.section.warranty.information.label" default="Warranty Information"/>
        </h4>
      	<g:inputDate name="warranty.startDate" precision="day" id="start-date" value="${equipment?.warranty?.startDate}" label="${message(code:'warranty.start.date.label')}" bean="${equipment}" field="warranty.startDate"/>
      	<g:inputDate name="warranty.endDate" precision="day" id="end-date" value="${equipment?.warranty?.endDate}" label="${message(code:'warranty.end.date.label')}" bean="${equipment}" field="warranty.endDate"/>
      	<g:input name="warranty.contact.contactName" label="${message(code:'entity.name.label')}" bean="${equipment}" field="warranty.contact.contactName"/>
      	<g:input name="warranty.contact.email" label="${message(code:'contact.email.label')}" bean="${equipment}" field="warranty.contact.email"/>
      	<g:input name="warranty.contact.phone" label="${message(code:'contact.phone.label')}" bean="${equipment}" field="warranty.contact.phone"/>
      	<g:input name="warranty.contact.poBox" label="${message(code:'contact.pobox.label')}" bean="${equipment}" field="warranty.contact.poBox"/>
      	<g:input name="warranty.contact.city" label="${message(code:'contact.city.label')}" bean="${equipment}" field="warranty.contact.city"/>
      	<g:input name="warranty.contact.country" label="${message(code:'contact.country.label')}" bean="${equipment}" field="warranty.contact.country"/>
  	    <g:i18nTextarea name="warranty.contact.addressDescriptions" bean="${equipment}" label="${message(code:'contact.address.descriptions.label')}" field="warranty.contact.addressDescriptions" height="150" width="300" maxHeight="150" />
     	<g:i18nTextarea name="warranty.descriptions" bean="${equipment}" label="${message(code:'warranty.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />	 			
  		</fieldset> 
  		<div class="form-aside">
       	  
       	</div>
      </div>
  		<g:if test="${equipment.id != null}">
  			<input type="hidden" name="id" value="${equipment.id}"></input>
  		</g:if>
  		
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  		
  	</g:form>
  </div>
</div>