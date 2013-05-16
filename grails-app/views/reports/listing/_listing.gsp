<%@ page import="org.chai.memms.util.Utils.ReportType" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<div class="entity-list">

  <div class="heading1-bar">
    <h1>Detailed Reports</h1>
  </div>

  <!-- filter template -->
  <g:if test="${filterTemplate!=null}">
    <g:render template="/entity/${filterTemplate}" />
  </g:if>

  <!-- start listing template -->
  <div id ="list-grid" class="v-tabs">

    <!-- customized report template -->
    <g:render template="/reports/listing/customizedReport/createCustomizedReport" />

    <!-- report type tabs template -->
    <g:render template="/reports/listing/listingReportTypeTabs" />

    <!-- INVENTORY -->
    <g:if test="${reportType == ReportType.INVENTORY}">
      <div id='js-inventory' class="v-tabs-content right shown">
          <!-- equipment predefined reports template -->
          <g:render template="/reports/listing/equipmentReport/equipmentPredefinedReports" />
          <!-- TODO equipment saved reports template -->
          <div id='report-1' class="js-shown-report">
            <!-- equipment indicator summary template -->
            <g:render template ="/reports/listing/equipmentReport/equipmentIndicatorSummary" />
            <!-- listing filter summary template -->
            <g:render template ="/reports/listing/listingFilterSummary" />
            <!-- equipment report template -->
            <g:render template="/reports/listing/equipmentReport/equipmentListing" />
          </div>
      </div>
    </g:if>

    <!-- TODO CORRECTIVE -->
    <g:if test="${reportType == ReportType.CORRECTIVE}">
      <div id='js-corrective' class="v-tabs-content right shown">
        <!-- work order predefined reports template -->
          <g:render template="/reports/listing/workOrderReport/workOrderPredefinedReports" />
          <!-- TODO work order saved reports template -->
          <div id='report-1' class="js-shown-report">
            <!-- work order indicator summary template -->
            <g:render template ="/reports/listing/workOrderReport/workOrderIndicatorSummary" />
            <!-- listing filter summary template -->
            <g:render template ="/reports/listing/listingFilterSummary" />
            <!-- work order report template -->
            <g:render template="/reports/listing/workOrderReport/workOrderListing" />
          </div>
      </div>
    </g:if>

    <!-- TODO PREVENTIVE -->
    <g:if test="${reportType == ReportType.PREVENTIVE}">
      <div id='js-preventive' class="v-tabs-content right shown">
        <!-- preventive order predefined reports template -->
          <g:render template="/reports/listing/preventiveOrderReport/preventiveOrderPredefinedReports" />
          <!-- TODO preventive order saved reports template -->
          <div id='report-1' class="js-shown-report">
            <!-- preventive order indicator summary template -->
            <g:render template ="/reports/listing/preventiveOrderReport/preventiveOrderIndicatorSummary" />
            <!-- listing filter summary template -->
            <g:render template ="/reports/listing/listingFilterSummary" />
            <!-- preventive order report template -->
            <g:render template="/reports/listing/preventiveOrderReport/preventiveOrderListing" />
          </div>
      </div>
    </g:if>

    <!-- TODO SPARE PARTS -->
    <g:if test="${reportType == ReportType.SPAREPARTS}">
      <div id='js-spare-parts' class="v-tabs-content right shown">
        %{-- TODO --}%
      </div>
    </g:if>

  </div>
  <!-- end listing template -->
</div>
