<div class="v-tabs-dynav-wrap">
  <a class='v-tabs-dynav-scroll-right' href='#' id='js-scroll-right'></a>
  <div class="v-tabs-dynav" id='js-slider-wrapper'>
    <ul>
	    <li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'generalWorkOrdersListing')}" id="report-1"
				class="tooltip" title="${message(code:'default.all.work.order.label')}">
				<g:message code="default.all.work.order.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'lastMonthWorkOrders')}" id="report-2"
				class="tooltip" title="${message(code:'default.work.order.last.month.label')}">
				<g:message code="default.work.order.last.month.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'workOrdersEscalatedToMMC')}" id="report-3"
				class="tooltip" title="${message(code:'default.work.order.escalated.to.mmc.label')}">
				<g:message code="default.work.order.escalated.to.mmc.label"/>
			</a>
		</li>
		<li>
			<a href="${createLinkWithTargetURI(controller: 'listing', action:'lastYearClosedWorkOrders')}" id="report-4"
        class="tooltip" title="${message(code:'default.work.order.closed.last.year.label')}">
				<g:message code="default.work.order.closed.last.year.label"/>
			</a>
		</li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'delayedWorkOrders')}" id="report-5"
        class="tooltip" title="${message(code:'default.work.order.delayed.label')}">
        <g:message code="default.work.order.delayed.label"/>
      </a>
    </li>
		<g:each in="${savedReports}" var="savedReport" status="i">
        <li>
          <a href="${createLinkWithTargetURI(controller: 'listing', action:'savedCustomizedListing', params: [id: savedReport.id])}" id="report-${i+6}"
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