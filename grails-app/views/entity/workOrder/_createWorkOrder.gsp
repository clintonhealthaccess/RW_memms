<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.maintenance.WorkOrder.OrderStatus" %>
<%@ page import="org.chai.memms.maintenance.WorkOrder.Criticality" %>
<%@ page import="java.util.Date" %>
<div  class="entity-form-container togglable">
  <div class="heading1-bar">
		<h1>
			<g:if test="${order.id != null}">
				<g:message code="default.edit.label" args="[message(code:'work.order.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'work.order.label')]" />
			</g:else>
		</h1>
	</div>
	<div class="main">
  	<g:form url="[controller:'workOrder', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  	  <div class="form-section">
    	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}">
          </span>
          <g:message code="equipment.section.basic.information.label" default="Basic Information"/>
        </h4> 
      	<g:selectFromList name="equipment.id" label="${message(code:'equipment.label')}" bean="${order}" field="equipment" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'equipment', action:'getAjaxData')}"
  			from="${equipments}" value="${order?.equipment?.id}" values="${equipments.collect{it.serialNumber}}" />	
  		<g:if test="${order.id != null}">
  		<div class="row">
	  		 <label><g:message code="work.order.reported.by.label"/> :</label>
	  		 ${order.addedBy.firstname} ${order.addedBy.lastname}  - ${order.openOn}
  		</div>
  		<div class="row">
	  		 <label><g:message code="work.order.last.modified.by.label"/> :</label>
	  		 ${order.lastModifiedBy?.firstname} ${order.lastModifiedBy?.lastname}  - ${order.lastModifiedOn}
  		</div>
  		</g:if>						
   		<g:textarea name="description" rows="12" width="380" label="${message(code:'entity.description.label')}" bean="${order}" field="description" value="${order.description}"/>
   		<g:selectFromEnum name="criticality" bean="${order}" values="${Criticality.values()}" field="criticality" label="${message(code:'work.order.criticality.label')}"/>
   		<g:if test="${order.id != null}">
   			<g:selectFromEnum name="status" bean="${order}" values="${OrderStatus.values()}" field="status" label="${message(code:'work.order.status.label')}"/>
   		</g:if>
    	</fieldset>	
   		<div id="form-aside-equipment" class="form-aside">
    	  <g:if test="${order.id != null}">
    	 	 <g:render template="/templates/equipmentFormSide" model="['equipment':order.equipment,'cssClass':'current','field':'equipment' ]" />
          </g:if>
      	</div>
      </div> 
      <g:if test="${order.id != null}">
	      <div class="form-section">
	    	<fieldset class="form-content">
	      	<h4 class="section-title">
	          <span class="question-default">
	            <img src="${resource(dir:'images/icons',file:'star_small.png')}">
	          </span>
	          <g:message code="equipment.section.maintenance.information.label" default="Maintenance Information"/>
	        </h4>
        	<div class="row">
		        <div class="process-action-perfomed">
		        	<label><g:message code="work.order.performed.action.label"/> :${order.performedActions.size()}</label>
		        	<ul class="maintenance-processes">
			        	<g:each in="${order.performedActions}" status="i" var="action">
				        	<li process-id="${action.id}" type="action">${action.name} <a href="#" class="delete-process">x</a></li>
				        </g:each>
		        	</ul>
		        	<input type="text" name="action-perfomed" class="idle-field" value="" />
 			        <span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
		        	<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
  					<a href="#" class="add-buttons"><g:message code="default.add.label"  args="${['']}"/></a>
		        </div>
		        
		        <div class="process-materials-used">
		        	<label><g:message code="work.order.materials.used.label"/> :${order.materialsUsed.size()}</label>
		        	<ul class="maintenance-processes">
		        		<g:each in="${order.materialsUsed}" status="i" var="material">
				        	<li process-id="${material.id}" type="material" >${material.name} <a href="#" class="delete-process">x</a></li>
				        </g:each>
		        	</ul>
		        	<input type="text" name="materials-used" class="idle-field" value="" />
		        	<span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
		        	<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
  					<a href="#" class="add-buttons"><g:message code="default.add.label"  args="${['']}"/></a>
		        </div>
        	</div>
	         <g:currency costName="estimatedCost" id="estimated-cost" costLabel="${message(code:'work.order.estimated.cost.label')}" bean="${equipment}" costField="estimatedCost"  currencyName="currency" values="${currencies}" currencyField="currency" currencyLabel="${message(code:'work.order.currency.label')}"/>
	        </fieldset>
	       </div>
      </g:if>
		<g:if test="${order.id != null}">
			<input type="hidden" name="id" value="${order.id}"/>
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
		$(".ajax-spinner").hide();
		$(".ajax-error").hide()
		$('.add-buttons').click(function(e){
			e.preventDefault();
			var inputField = $(e.target).prevAll("img.ajax-spinner");
			alert(inputField.attr('name'));
			$(inputField).show();
			$(e.target).hide();
			$.ajax({
				type :'GET',
				dataType: 'json',
				data:{"order":"${order.id}","type":$(inputField).attr('name'),"value":$(inputField).attr('value')},
				url:"${createLink(controller:'workOrder',action: 'addProcess')}",
				success: function(data) {
					$(e.target).prevAll("img.ajax-spinner").hide();
					$(e.target).show("slow");
				}
			});
			$(this).ajaxError(function(){
				$(e.target).prevAll("img.ajax-spinner").fadeOut("slow");
				$(e.target).fadeIn("slow");
				$(e.target).next().show("slow");	
			});
		})
	});
</script>

