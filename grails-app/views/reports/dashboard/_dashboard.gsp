<div class="entity-list">

  <!-- dashboard title -->
  <div class="heading1-bar">
    <h1>
      <g:message code="default.dashboard.report.label" args="[message(code:'default.dashboard.report.label')]" />
    </h1>
  </div>

  <!-- start dashboard template -->
  <div id="list-grid" class="v-tabs">
    
    <!-- report type tabs template -->
    <g:render template="/reports/dashboard/dashboardReportTypeTabs" />

    <div class="v-tabs-content right">
      <!-- filter template -->
      <g:if test="${filterTemplate!=null}">
        <g:render template="${filterTemplate}" />
      </g:if>

      <!-- threshold filter -->
      <ul class="v-tabs-filters">
        <li><input type="checkbox" id="checkbox-green" name="green" checked="checked"/><label>Green</label></li>
        <li><input type="checkbox" id="checkbox-yellow" name="yellow" checked="checked"/><label>Yellow</label></li>
        <li><input type="checkbox" id="checkbox-red" name="red" checked="checked"/><label>Red</label></li>
      </ul>

      <div>
        <ul class="v-tabs-list">
          <g:each in="${categoryItem.indicatorItems}" status="indicatorCount" var="indicatorItem">
            <li class="v-tabs-row v-tabs-row-${indicatorItem.color}">
              <p>
                <a class="v-tabs-name v-tabs-fold-toggle">
                  <span class="v-tabs-switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>${indicatorItem.names}
                </a>
                <span class="tooltip v-tabs-formula" style="background: ${indicatorItem.color}" original-title="${indicatorItem.formulas}">
                  ${indicatorItem.formulas}
                </span>
                <g:if test="${indicatorItem.unit=='%'}">
                  <span class="v-tabs-value"><g:formatNumber number="${indicatorItem.value}" format="0.00%"/></span>
                </g:if>
                <g:if test="${indicatorItem.unit!='%'}">
                  <span class="v-tabs-value"><g:formatNumber number="${indicatorItem.value}" format="###,##0"/>${indicatorItem.unit}</span>
                </g:if>
            </p>
            <div class="v-tabs-fold-container">
             
              <!-- chart type tabs template -->
              <g:render template="/reports/dashboard/dashboardChartTypeTabs" model="[indicatorItem:indicatorItem]" />

              <!-- historic trend -->
              <g:render template="/reports/dashboard/historicTrend" model="[indicatorItem:indicatorItem]" />
              
              <!-- comparison to other facilities -->
              <g:render template="/reports/dashboard/comparisonChart" model="[indicatorItem:indicatorItem]" />

              <!-- geographic trend -->
              <g:render template="/reports/dashboard/geographicTrend" model="[indicatorItem:indicatorItem]" />

              <!-- info by x -->
              <g:render template="/reports/dashboard/infoByChart" model="[indicatorItem:indicatorItem]" />
            </div>
            </li>
          </g:each>
        </ul>
      </div>
    </div>
  </div>
</div>