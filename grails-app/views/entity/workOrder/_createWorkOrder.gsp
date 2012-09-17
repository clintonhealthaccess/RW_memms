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
		<g:locales/>
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
	  		 <label>Reporter by: </label>
	  		 ${order.addedBy.firstname} ${order.addedBy.lastname}  - ${order.openOn}
  		</div>
  		<div class="row">
	  		 <label>Last Modified by: </label>
	  		 ${order.lastModifiedBy?.firstname} ${order.lastModifiedBy?.lastname}  - ${order.lastModifiedOn}
  		</div>
  		</g:if>						
   		<g:textarea name="description" rows="12" label="${message(code:'entity.description.label')}" bean="${order}" field="description" value="${order.description}"/>
   		<g:selectFromEnum name="criticality" bean="${order}" values="${Criticality.values()}" field="criticality" label="${message(code:'wordorder.criticality.label')}"/>
    	</fieldset>	
   		<div id="form-aside-equipment" class="form-aside">
    	  <g:if test="${order.id != null}">
    	 	 <g:render template="/templates/equipmentFormSide" model="['equipment':order.equipment,'cssClass':'current','field':'equipment' ]" />
          </g:if>
      	</div>
      </div> 
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

