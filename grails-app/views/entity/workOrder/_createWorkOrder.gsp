<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.maintenance.WorkOrder.OrderStatus" %>
<%@ page import="org.chai.memms.maintenance.WorkOrder.Criticality" %>
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
      	<g:selectFromList name="equipment.id" readonly="${(closed)? true:false}" label="${message(code:'equipment.label')}" bean="${order}" field="equipment" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'equipment', action:'getAjaxData')}"
  			from="${equipments}" value="${order?.equipment?.id}" values="${equipments.collect{it.serialNumber}}" />	
  		<g:if test="${order.id != null}">
	  		<div class="row">
		  		 <label><g:message code="work.order.reported.by.label"/> :</label>
		  		 ${order.addedBy.firstname} ${order.addedBy.lastname}  - ${Utils.formatDateWithTime(order?.openOn)}
	  		</div>
	  		<div class="row">
		  		 <label><g:message code="work.order.last.modified.by.label"/> :</label>
		  		 ${order.lastModifiedBy?.firstname} ${order.lastModifiedBy?.lastname} - ${Utils.formatDateWithTime(order?.lastModifiedOn)}
	  		</div>
  		</g:if>						
   		<g:textarea name="description" rows="12" width="380" label="${message(code:'entity.description.label')}" readonly="${(closed)? true:false}" bean="${order}" field="description" value="${order.description}"/>
   		<g:selectFromEnum name="criticality" bean="${order}" values="${Criticality.values()}" field="criticality" readonly="${(closed)? true:false}" label="${message(code:'work.order.criticality.label')}"/>
   		<g:if test="${order.id != null}">
   			<g:selectFromEnum name="status" bean="${order}" values="${OrderStatus.values()}" field="status" label="${message(code:'work.order.status.label')}"/>
   		</g:if>
    	</fieldset>	
   		<div id="form-aside-equipment" class="form-aside">
    	  <g:if test="${order.id != null || order?.equipment!=null}">
    	 	 <g:render template="/templates/equipmentFormSide" model="['equipment':order?.equipment,'cssClass':'current','field':'equipment' ]" />
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
        	<div class="row maintenance-process">
        		<g:render template="/templates/processes" model="['processes':order.actions,'processType':'action','label':'work.order.performed.action.label','readonly':closed]" /> 
        		<g:render template="/templates/processes" model="['processes':order.materials,'processType':'material','label':'work.order.materials.used.label','readonly':closed]" /> 
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
  
  <g:if test="${order.id != null}">
    <div class="heading1-bar">
  		<h1><g:message code="work.order.comments.label"/></h1>
  	</div>
    <div class="main">
  	
	  	<div class="comment-section">
	  		<div class="comment-field">
		  		<label><g:message code="work.order.comment.label"/></label>
		  		<input type="hidden" name="order" value="${order.id}"/>
		  		<textarea name="content" class="idle-field" id="comment-content" rows="2" cols="90"></textarea>
	  		</div>
	  		<div class="comment-button">
	  			<button class="medium" id="add-comment"><g:message code="work.order.comment.label"/></button>
	  			<span class="ajax-error"><g:message code="entity.error.updating.try.again"/></span>
	  			<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
	  		</div> 
	  		 <g:render template="/templates/comments" model="['order':order,'closed':closed]" /> 
	  	</div>
    </div>
  </g:if>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		addProcess("${createLink(controller:'workOrder',action: 'addProcess')}","${order.id}")
		removeProcess("${createLink(controller:'workOrder',action: 'removeProcess')}")
		addComment("${createLink(controller:'workOrder',action: 'addComment')}","${order.id}")
		removeComment("${createLink(controller:'workOrder',action: 'removeComment')}")
	});
</script>

