<div  class="entity-form-container togglable">
    <div class="heading1-bar">
    		<h1>
      			<g:if test="${prevention.id != null}">
      				  <g:message code="default.edit.label" args="[message(code:'prevention.label')]" />
      			</g:if>
      			<g:else>
      				  <g:message code="default.new.label" args="[message(code:'prevention.label')]" />
      			</g:else>
    		</h1>
		    <g:locales/>
   </div>
	 <div class="main">
    	<g:form url="[controller:'prevention', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
      	  <div class="form-section">
            <fieldset class="form-content">
              <h4 class="section-title">
                  <span class="question-default">
                    <img src="${resource(dir:'images/icons',file:'star_small.png')}">
                  </span>
                  <g:message code="prevention.section.basic.information.label"/>
              </h4> 
              <div class="row">
                 <input type="hidden" name="id" value="${prevention.id}" />
              </div>
            <g:input name="eventDate" dateClass="date-picker" label="${message(code:'equipment.status.date.of.event.label')}" bean="${prevention}" field="eventDate"/>
            <g:inputHourMinute name="timeSpend" field="timeSpend" hours="${prevention.timeSpend?.hours}" minutes="${prevention.timeSpend?.minutes}" label='work.order.work.time.label' bean="${order}"/>
            <g:inputTimeDate name="scheduledOn" field="scheduledOn" date="${prevention.scheduledOn?.date}" time="${prevention.scheduledOn?.time}" label='preventive.starts.on.label' bean="${prevention}" dateClass="date-picker" timeClass="time-picker"/>
            <g:i18nTextarea name="descriptions" bean="${prevention}" label="${message(code:'entity.descriptions.label')}" field="descriptions" height="150" width="300" maxHeight="150" />
          </div>
          </fieldset>
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
    getToHide()
  });
</script>

