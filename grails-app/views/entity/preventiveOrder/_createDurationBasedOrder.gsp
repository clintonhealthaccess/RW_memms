<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>
<%@ page import="org.chai.memms.corrective.maintenance.PreventiveOrder.*" %>
<r:require modules="tipsy"/>
<div  class="entity-form-container togglable">
    <div class="heading1-bar">
		<h1>
			<g:if test="${order.id != null}">
				<g:message code="default.edit.label" args="[message(code:'preventive.order.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'preventive.order.label')]" />
			</g:else>
		</h1>
		<g:locales/>
	</div>
	<div class="main">
  	<g:form url="[controller:'preventiveOrder', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
  	  <div class="form-section">
    	<fieldset class="form-content">
      	<h4 class="section-title">
          <span class="question-default">
            <img src="${resource(dir:'images/icons',file:'star_small.png')}">
          </span>
          <g:message code="order.section.basic.information.label"/>
        </h4> 
      	<g:selectFromList name="equipment.id" readonly="${(closed)? true:false}" label="${message(code:'equipment.label')}" bean="${order}" field="equipment" optionKey="id" multiple="false"
  			ajaxLink="${createLink(controller:'equipmentView', action:'getAjaxData')}"
  			from="${equipments}" value="${order?.equipment?.id}" values="${equipments.collect{it.code}}" />	
  		<g:if test="${order.id != null}">
	  		<div class="row">
		  		 <label class="top"><g:message code="work.order.reported.by.label"/> :</label>
		  		 ${order.addedBy.names}  - ${Utils.formatDateWithTime(order?.openOn)}
	  		</div>
	  		<div class="row">
		  		 <label class="top"><g:message code="work.order.last.modified.by.label"/> :</label>
		  		 ${order.lastModifiedBy?.names} - ${Utils.formatDateWithTime(order?.lastModifiedOn)}
	  		</div>
  		</g:if>						
   		<g:textarea name="description" rows="12" width="380" label="${message(code:'entity.description.label')}" readonly="${(closed)? true:false}" bean="${order}" field="description" value="${order.description}"/>
   		<input type="text" name="type" value="${PreventiveOrderType.DURATIONBASED}"/>
   	   		
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
		numberOnlyField();
		getDatePicker("${resource(dir:'images',file:'icon_calendar.png')}");
	});
</script>

