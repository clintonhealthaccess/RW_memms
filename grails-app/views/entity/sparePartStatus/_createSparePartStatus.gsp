<%@ page import="org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart" %>
<%@ page import="org.chai.memms.util.Utils" %>
<div  class="entity-form-container togglable">
<div class="heading1-bar">
	<h1>
		<g:message code="default.new.label" args="[message(code:'sparePart.statusOfSparePart.label')]"/>
	</h1>
	<g:locales/>
</div>

<div class="main">	
	<g:form url="[controller:'sparePartStatus', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
		<div class="row">
			<input type="hidden" name="sparePart.id" value="${statusOfSparePart.sparePart.id}"/>
			<label><g:message code="entity.code.label"/>:</label>${statusOfSparePart.sparePart.code}
		</div>
		<div class="row">
			<label><g:message code="sparePart.serial.number.label"/>:</label>${statusOfSparePart.sparePart.serialNumber}
		</div>
		<g:selectFromEnum name="statusOfSparePart" bean="${statusOfSparePart}" values="${StatusOfSparePart.values()}" field="statusOfSparePart" label="${message(code:'sparePart.statusOfSparePart.label')}"/>
		<g:input name="dateOfEvent" dateClass="date-picker" label="${message(code:'sparePart.statusOfSparePart.date.of.event.label')}" bean="${statusOfSparePart}" field="dateOfEvent"/>
    	<g:i18nTextarea name="reasons" bean="${statusOfSparePart}" label="${message(code:'sparePart.statusOfSparePart.reason')}" field="reasons" height="150" width="300" maxHeight="150" />
		
		<g:if test="${statusOfSparePart.id != null}">
			<input type="hidden" name="id" value="${statusOfSparePart.id}"></input>
		</g:if>
		<br/>
		<div class="buttons">
			<button type="submit"><g:message code="default.button.save.label"/></button>
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
	<g:if test="${statusOfSparePart.sparePart!=null}">
    	<table class="items">
    		<tr>
    			<th></th>
    			<th><g:message code="sparePart.statusOfSparePart.label"/></th>
    			<th><g:message code="sparePart.statusOfSparePart.date.of.event.label"/></th>
    			<th><g:message code="sparePart.statusOfSparePart.recordedon.label"/></th>
    			<th><g:message code="sparePart.statusOfSparePart.current.label"/></th>
    		</tr>
    		<g:each in="${sparePart.statusOfSparePart.sort{a,b -> (a.dateOfEvent > b.dateOfEvent) ? -1 : 1}}" statusOfSparePart="i" var="statusOfSparePart">
	    		<g:if test="${i+1<numberOfStatusToDisplay}">
		    		<tr>
		    			<td>
			    		<ul>
							<li>
								<a href="${createLinkWithTargetURI(controller:'sparePartStatus', action:'delete', params:[id: statusOfSparePart.id,'sparePart.id': sparePart?.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
							</li>
						</ul>
		    			</td>
		    			<td>${message(code: statusOfSparePart?.statusOfSparePart?.messageCode+'.'+statusOfSparePart?.statusOfSparePart?.name)}</td>
		    			<td>${Utils.formatDate(statusOfSparePart?.dateOfEvent)}</td>
		    			<td>${Utils.formatDateWithTime(statusOfSparePart?.dateCreated)}</td>
		    			<td>${(statusOfSparePart==sparePart.timeBasedStatus)? '\u2713':''}</td>
		    		</tr>
		    	</g:if>
    		</g:each>
    	</table>
    	<br/>
    	<a href="${createLinkWithTargetURI(controller:'sparePartStatus', action:'list', params:['sparePart.id': sparePart?.id])}">
  	    		<g:message code="sparePart.see.all.statusOfSparePart.label" default="See all statusOfSparePart"/>
  	    	</a>
   	</g:if>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {		
		getDatePicker("${resource(dir:'images',file:'icon_calendar.png')}")
	});
</script>
