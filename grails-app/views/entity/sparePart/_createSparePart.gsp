<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.spare.part.SparePartStatus.Status" %>
<%@ page import="org.chai.memms.spare.part.SparePart.PurchasedBy" %>
<%@ page import="org.chai.memms.spare.part.SparePart.Donor" %>
<div  class="entity-form-container togglable">
  <div class="heading1-bar">
		<h1>
			<g:if test="${sparePart.id != null}">
				<g:message code="default.edit.label" args="[message(code:'sparePart.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'sparePart.label')]" />
			</g:else>
		</h1>
		<g:locales/>
	</div>
	
	<div class="main">
  	<g:form url="[controller:'sparePart', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  	
  	  <div class="form-section">
      	<fieldset class="form-content">
        	<h4 class="section-title">
            <span class="question-default">
              <img src="${resource(dir:'images/icons',file:'star_small.png')}">
            </span>
            <g:message code="sparePart.section.basic.information.label" default="Basic Information"/>
          </h4>  			  		
      		<div class="row">
    			  <input type="hidden" name="dataLocation.id" value="${sparePart.dataLocation.id}" />
    			  <label><g:message code="datalocation.label"/>:</label> ${sparePart.dataLocation.names}
    		  </div>				
        	<g:selectFromList name="type.id" label="${message(code:'sparePart.type.label')}" bean="${sparePart}" field="type" optionKey="id" multiple="false"
    			ajaxLink="${createLink(controller:'sparePartType', action:'getAjaxData', params: [observation:'USEDINMEMMS'])}"
    			from="${types}" value="${sparePart?.type?.id}" values="${types.collect{it.names}}" />
      		<g:inputYearMonth name="expectedLifeTime" field="expectedLifeTime" years="${sparePart.expectedLifeTime?.years}" months="${sparePart.expectedLifeTime?.months}" label='entity.expectedLifeTime.label' bean="${sparePart}"/>
      		<g:input name="serialNumber" label="${message(code:'sparePart.serial.number.label')}" bean="${sparePart}" field="serialNumber"/>
      		<g:input name="model" label="${message(code:'sparePart.model.label')}" bean="${sparePart}" field="model"/>
      		<g:i18nTextarea name="descriptions" bean="${sparePart}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
      		<g:selectFromList name="department.id" label="${message(code:'department.label')}" bean="${sparePart}" field="department" optionKey="id" multiple="false"
    			ajaxLink="${createLink(controller:'department', action:'getAjaxData')}"
    			from="${departments}" value="${sparePart?.department?.id}" values="${departments.collect{it.names}}" />
      		<g:input name="room" label="${message(code:'sparePart.room.label')}" bean="${sparePart}" field="room"/>
      	</fieldset>
      	
     		<div id="form-aside-type" class="form-aside">
      	  <g:if test="${sparePart?.type != null}">
      	 	  <g:render template="/templates/typeFormSide" model="['type':sparePart?.type,'cssClass':'current','field':'type' ]" />
          </g:if>
        </div>
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}" alt="Section"/>
          </span>
          <g:message code="sparePart.section.status.information.label" default="Status Information"/> 
        </h4>
      	<g:if test="${sparePart.id == null}">
      			<g:selectFromEnum name="status" bean="${cmd}" values="${Status.values()}" field="status" label="${message(code:'sparePart.status.label')}"/>
      			<g:input name="dateOfEvent" dateClass="date-picker" label="${message(code:'sparePart.status.date.of.event.label')}" bean="${cmd}" field="dateOfEvent"/>
      			<g:inputBox name="obsolete"  label="${message(code:'sparePart.obsolete.label')}" bean="${sparePart}" field="obsolete" value="${sparePart.obsolete}" checked="${(sparePart.obsolete)? true:false}"/>
      	</g:if>
      	<g:if test="${sparePart?.status!=null}">
      	 	<g:inputBox name="obsolete"  label="${message(code:'sparePart.obsolete.label')}" bean="${sparePart}" field="obsolete" value="${sparePart.obsolete}" checked="${(sparePart.obsolete)? true:false}"/>
	    	<table class="items">
	    		<tr>
	    			<th></th>
	    			<th>${message(code:'sparePart.status.label')}</th>
	    			<th>${message(code:'sparePart.status.date.of.event.label')}</th>
	    			<th>${message(code:'sparePart.status.recordedon.label')}</th>
	    			<th>${message(code:'sparePart.status.current.label')}</th>
	    		</tr>
	    		<g:each in="${sparePart.status.sort{a,b -> (a.dateCreated > b.dateCreated) ? -1 : 1}}" status="i" var="status">
		    		<g:if test="${i+1<numberOfStatusToDisplay}">
			    		<tr>
			    			<td>
				    		<ul>
								<li>
									<a href="${createLinkWithTargetURI(controller:'sparePartStatus', action:'delete', params:[id: status.id,'sparePart': sparePart?.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
								</li>
							</ul>
			    			</td>
			    			<td>${message(code: status?.status?.messageCode+'.'+status?.status?.name)}</td>
			    			<td>${Utils.formatDate(status?.dateOfEvent)}</td>
			    			<td>${Utils.formatDateWithTime(status?.dateCreated)}</td>
			    			<td>${(status==sparePart.timeBasedStatus)? '\u2713':''}</td>
			    		</tr>
		    		</g:if>
	    		</g:each>
	    	</table>
	    	<br />
	  	    	<a href="${createLinkWithTargetURI(controller:'sparePartStatus', action:'create', params:['sparePart.id': sparePart?.id])}" class="next medium gray">
	  	    		<g:message code="sparePart.change.status.label" default="Change Status"/>
	  	    	</a>
	  	    	<a href="${createLinkWithTargetURI(controller:'sparePartStatus', action:'list', params:['sparePart.id': sparePart?.id])}">
	  	    		<g:message code="sparePart.see.all.status.label" default="See all status"/>
	  	    	</a>
  	    	<br />
     		</g:if>
     	</fieldset>
      </div>   
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}">

          </span>
          <g:message code="sparePart.section.manufacturer.information.label" default="Manufacturer Information"/>
        </h4>
      	<g:selectFromList name="manufacturer.id" label="${message(code:'provider.type.manufacturer')}" bean="${sparePart}" field="manufacturer" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'provider', action:'getAjaxData', params: [type:'MANUFACTURER'])}"
  			from="${manufacturers}" value="${sparePart?.manufacturer?.id}" values="${manufacturers.collect{it.contact?.contactName}}" />	
  			<g:inputDate name="manufactureDate"  precision="month" label="${message(code:'sparePart.manufacture.date.label')}" value="${sparePart?.manufactureDate}" bean="${sparePart}" field="manufactureDate"/>
     	</fieldset>
    	  <div id="form-aside-manufacturer" class="form-aside">
	    	  <g:if test="${sparePart?.manufacturer != null}">
	    	 	 <g:render template="/templates/providerFormSide" model="['provider':sparePart?.manufacturer,'type':sparePart?.manufacturer?.type,'label':'provider.manufacturer.details','cssClass':'current','field':'manufacturer' ]" />
	          </g:if>
       	</div>
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
           <img src="${resource(dir:'images/icons',file:'star_small.png')}">
          </span>
          <g:message code="sparePart.section.supplier.information.label" default="Supplier Information"/>
        </h4>
      	<g:selectFromList name="supplier.id" label="${message(code:'provider.type.supplier')}" bean="${sparePart}" field="supplier" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'provider', action:'getAjaxData', params: [type:'SUPPLIER'])}"
  			from="${suppliers}" value="${sparePart?.supplier?.id}" values="${suppliers.collect{it.contact?.contactName}}" />		
  			<g:input name="purchaseDate" dateClass="date-picker" label="${message(code:'sparePart.purchase.date.label')}" bean="${sparePart}" field="purchaseDate"/>
    		<g:selectFromEnum name="purchaser" bean="${sparePart}" values="${PurchasedBy.values()}" field="purchaser" label="${message(code:'sparePart.purchaser.label')}"/>
    		<div class="donor-information">
	    		<g:selectFromEnum name="donor" bean="${sparePart}" values="${Donor.values()}" field="donor" label="${message(code:'sparePart.donor.label')}"/>
	    		<g:input name="donorName"  label="${message(code:'sparePart.donor.name.label')}" bean="${sparePart}" field="donorName"/>
    		</div>
    		<g:currency costName="purchaseCost" id="purchase-cost" costLabel="${message(code:'sparePart.purchase.cost.label')}" bean="${sparePart}" costField="purchaseCost"  currencyName="currency" values="${currencies}" currencyField="currency" currencyLabel="${message(code:'sparePart.currency.label')}"/>
     	</fieldset>
     	 <div id="form-aside-supplier" class="form-aside">
     		<g:if test="${sparePart?.supplier != null}">
     	  		<g:render template="/templates/providerFormSide" model="['provider':sparePart?.supplier,'type':sparePart?.supplier?.type,'label':'provider.supplier.details','cssClass':'current','field':'supplier']" />
       		</g:if>
       	</div>
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}" alt="Section"/>
          </span>
          <g:message code="sparePart.section.warranty.information.label" default="Warranty Information"/>
        </h4>
        <g:inputBox name="warranty.sameAsSupplier"  label="${message(code:'sparePart.same.as.supplier.label')}" bean="${sparePart}" field="warranty.sameAsSupplier" checked="${(sparePart.warranty?.sameAsSupplier)? true:false}"/>
      	<g:input name="warranty.startDate" dateClass="date-picker" label="${message(code:'warranty.start.date.label')}" bean="${sparePart}" field="warranty.startDate"/>
    	<g:inputYearMonth name="warrantyPeriod" field="warrantyPeriod" years="${sparePart.warrantyPeriod?.years}" months="${sparePart.warrantyPeriod?.months}" bean="${sparePart}" label='sparePart.warranty.period.label'/>
      	<g:address  bean="${sparePart}" warranty="true" field="warranty.contact"/>
     	<g:i18nTextarea name="warranty.descriptions" bean="${sparePart}" label="${message(code:'warranty.descriptions.label')}" field="warranty.descriptions" height="150" width="300" maxHeight="150" />	 			
  		</fieldset> 
      </div>
      <div class="form-section">
      	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}" alt="Section"/>
          </span>
          <g:message code="sparePart.section.service.provider.information.label" default="Service Provider Information"/>
        </h4>
        <g:selectFromList name="serviceProvider.id" label="${message(code:'provider.type.serviceProvider')}" bean="${sparePart}" field="serviceProvider" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'provider', action:'getAjaxData', params: [type:'SERVICEPROVIDER'])}"
  			from="${serviceProviders}" value="${sparePart.serviceProvider?.id}" values="${serviceProviders.collect{it.contact?.contactName}}" />
  			<g:input name="serviceContractStartDate" dateClass="date-picker" label="${message(code:'sparePart.provider.startDate.label')}" bean="${sparePart}" field="serviceContractStartDate"/>
  			<g:inputYearMonth name="serviceContractPeriod" field="serviceContractPeriod" years="${sparePart.serviceContractPeriod?.years}" months="${sparePart.serviceContractPeriod?.months}" bean="${sparePart}" label='sparePart.provider.period.label'/>
  		</fieldset> 
  		<div id="form-aside-serviceProvider" class="form-aside">
	    	  <g:if test="${sparePart?.serviceProvider != null}">
	    	 	 <g:render template="/templates/providerFormSide" model="['provider':sparePart.serviceProvider,'type':sparePart.serviceProvider?.type,'label':'provider.service.provider.details','cssClass':'current','field':'serviceProvider' ]" />
	          </g:if>
       	</div>
      </div>
  		<g:if test="${sparePart.id != null}">
  			<input type="hidden" name="id" value="${sparePart.id}"/>
  		</g:if>
  		
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
  </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		numberOnlyField();
		getToHide("${message(code:'sparePart.purchase.cost.label')}","${message(code:'sparePart.estimated.cost.label')}");
		getDatePicker("${resource(dir:'images',file:'icon_calendar.png')}");
	});
</script>
