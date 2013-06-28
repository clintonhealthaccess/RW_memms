<div class="v-tabs-dynav-wrap">
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
	    <li>
				<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalPreventiveOrdersListing')}" id="report-1"
					class="tooltip" title="${message(code:'default.all.preventive.order.label')}">
					<g:message code="default.all.preventive.order.label"/>
				</a>
			</li>
			<li>
				<a href="${createLinkWithTargetURI(controller: 'listing', action:'equipmentsWithPreventionPlan')}" id="report-2"
					class="tooltip" title="${message(code:'default.equipments.with.prevention.label')}">
					<g:message code="default.equipments.with.prevention.label"/>
				</a>
			</li>
			<li>
				<a href="${createLinkWithTargetURI(controller: 'listing', action:'preventionsDelayed')}" id="report-3"
					class="tooltip" title="${message(code:'default.preventions.delayed.label')}">
					<g:message code="default.preventions.delayed.label"/>
				</a>
			</li>
			<li>
				<a href="${createLinkWithTargetURI(controller: 'listing', action:'preventionsDelayed')}" id="report-4"
					class="tooltip" title="${message(code:'default.preventions.delayed.label')}">
					<g:message code="default.preventions.delayed.label"/>
				</a>
			</li>
			<g:each in="${savedReports}" var="savedReport" status="i">
				<li>
					<a href="${createLinkWithTargetURI(controller: 'listing', action:'savedCustomizedListing', params: [id: savedReport.id])}" id="report-${i+5}"
					class="tooltip" title="${savedReport.reportName}">
					${savedReport.reportName}
					</a>
					<span class='delete-node' data-id="${savedReport.id}">X</span>
				</li>
			</g:each>
    </ul>
  </div>
  <a class='v-tabs-dynav-scroll-left' href='#' id='js-scroll-left'></a>
</div>
<r:script>
$(document).ready(function(){
  $(".delete-node").click(function(e){
      if(confirm('Are you sure?')){
        var baseUrl = "${createLink(controller: 'listing', action:'deleteCustomizedListing')}"
        var savedReportId = $(this).data('id')
        var selectedReportId = ${selectedReport != null ? selectedReport.id : 0}
        var savedReport = $(this).parents('li')
        e.preventDefault();
        $.ajax({
          type :'GET',
          dataType: 'json',
          data:{"savedReportId":savedReportId, "selectedReportId":selectedReportId},
          url:baseUrl,
          success: function(results) {
            savedReport.remove();
          },
          error: function(request, status, error) {
            alert("Error! "+request+", "+status+", "+error);
          }
        });
      }
  });
});
</r:script>