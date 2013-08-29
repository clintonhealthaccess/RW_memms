<div class="entity-list">
  <div class="heading1-bar"></div>
  <div id="list-grid" class="v-tabs">
    <ul id='top_tabs' class="v-tabs-nav left" >
      <g:each in="${categoryItems}" status="i" var="mapItem">
        <li>
          <span class="v-tabs-formula" style="background: ${mapItem.value.color}; z-index: 5000"> </span>
          <a id="${mapItem.key}">${mapItem.value.name}</a>
        </li>
      </g:each>
    </ul>
    <div class="v-tabs-content right">
      <ul class="v-tabs-filters">
        <li><input type="checkbox" id="checkbox-green" name="green" checked="checked"/><label>Green</label></li>
        <li><input type="checkbox" id="checkbox-yellow" name="yellow" checked="checked"/><label>Yellow</label></li>
        <li><input type="checkbox" id="checkbox-red" name="red" checked="checked"/><label>Red</label></li>
      </ul>
      <g:each in="${categoryItems}" status="categoryCount" var="mapItem">
        <g:set var="categoryCode" value="${mapItem.key}" />
        <g:set var="categoryItem" value="${mapItem.value}" />
        <div id="${categoryCode}" style="display: none">
          <ul class="v-tabs-list">
            <g:each in="${categoryItem.indicatorItems}" status="indicatorCount" var="indicatorItem">
              <li class="v-tabs-row v-tabs-row-${indicatorItem.color}">
                <p>
                  <a class="v-tabs-name v-tabs-fold-toggle">
                    <span class="v-tabs-switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span> ${indicatorItem.name}
                  </a>
                  <span class="tooltip v-tabs-formula" style="background: ${indicatorItem.color}" original-title="${indicatorItem.formula}">${indicatorItem.formula}</span>
              <g:if test="${indicatorItem.unit=='%'}">
                <span class="v-tabs-value"><g:formatNumber number="${indicatorItem.value}" format="0.00%"/></span>
              </g:if>
              <g:if test="${indicatorItem.unit!='%'}">
                <span class="v-tabs-value"><g:formatNumber number="${indicatorItem.value}" format="###,##0"/> ${indicatorItem.unit}</span>
              </g:if>
              </p>
              <div class="v-tabs-fold-container">
                <ul class="v-tabs-nested-nav">
                  <li><a id='historic_trend' class='active' href="#">Historic trend</a></li>
                  <li><a id='comparison' href="#">Comparison to other facilities</a></li>
                  <li><a id='geo_trend' href="#">Geographic trend</a></li>
                  <li><a id='info_facility' href="#">Information by ${indicatorItem.groupName}</a></li>
                </ul>
                <div id="historic_trend" class='toggled_tab'>
                  <g:if test="${indicatorItem.historicalTrendData()!=null}">
                    <script type="text/javascript">
                        google.load('visualization', '1', {'packages': ['corechart']});
                        google.setOnLoadCallback(drawVisualization);
                        function drawVisualization() {
                            data = new google.visualization.DataTable();
                            data.addColumn('string', 'Date');
                            data.addColumn('number', '${indicatorItem.name}');
                            data.addRows(${indicatorItem.historicalTrendData()});
                            formatter = new google.visualization.NumberFormat({pattern:'${indicatorItem.historicalTrendVAxisFormat()}'});
                            formatter.format(data,1);
                            chart = new google.visualization.LineChart(document.getElementById('historic_trend_chart_timeline_${indicatorItem.code}'));
                            chart.draw(data, {
                              tooltip: {isHtml: true},
                              legend: 'none',
                              pointSize: 12,
                              colors: [['${indicatorItem.color}']],
                              vAxis: {format:'${indicatorItem.historicalTrendVAxisFormat()}', title: '${indicatorItem.name}', gridlines:{color: 'lightgray' ${indicatorItem.historicalTrendLineCount()}}, minValue: 0 ${indicatorItem.historicalTrendMaxValue()}},
                              hAxis: {gridlines:{color: 'lightgray', count: ${indicatorItem.totalHistoryItems + 1}}, minValue: 0, maxValue: ${indicatorItem.totalHistoryItems}},
                              fontSize: 12,
                              width: 900,
                              height: 300});
                        }
                    </script>
                    <div id="historic_trend_chart_timeline_${indicatorItem.code}" style="width:800px; height:400px; overflow: auto;" ></div>
                  </g:if>
                </div>
                <div id="comparison">
                  <ul class="v-tabs-nested">
                    <g:if test="${indicatorItem.highestComparisonValueItems != null && !indicatorItem.highestComparisonValueItems.isEmpty()}">
                      <g:each in="${indicatorItem.highestComparisonValueItems}" var="compItem">
                        <li class="v-tabs-row">
                          <span class="v-tabs-name">${compItem.facility}</span>
                          <span class="v-tabs-formula" style="background: ${compItem.color}"  original-title="${compItem.color}">${compItem.color}</span>
                        <g:if test="${compItem.unit=='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="0%"/></span>
                        </g:if>
                        <g:if test="${compItem.unit!='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="###,##0"/> ${compItem.unit}</span>
                        </g:if>
                        </li>
                      </g:each>
                      <li class="v-tabs-row">
                        <span class="v-tabs-name"> ... </span>
                      </li>
                    </g:if>
                    <g:if test="${indicatorItem.higherComparisonValueItems != null && !indicatorItem.higherComparisonValueItems.isEmpty()}">
                      <g:each in="${indicatorItem.higherComparisonValueItems}" var="compItem">
                        <li class="v-tabs-row">
                          <span class="v-tabs-name">${compItem.facility}</span>
                          <span class="v-tabs-formula" style="background: ${compItem.color}"  original-title="${compItem.color}">${compItem.color}</span>
                        <g:if test="${compItem.unit=='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="0%"/></span>
                        </g:if>
                        <g:if test="${compItem.unit!='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="###,##0"/> ${compItem.unit}</span>
                        </g:if>
                        </li>
                      </g:each>
                    </g:if>
                    <li class="v-tabs-row" style="font-size:15px;font-weight:bold;color:#258CD5">
                      <span class="v-tabs-name">${indicatorItem.facilityName}</span>
                      <span class="v-tabs-formula" style="background: ${indicatorItem.color}"  original-title="${indicatorItem.color}">${indicatorItem.color}</span>
                    <g:if test="${indicatorItem.unit=='%'}">
                      <span class="v-tabs-value"><g:formatNumber number="${indicatorItem.value}" format="0%"/></span>
                    </g:if>
                    <g:if test="${indicatorItem.unit!='%'}">
                      <span class="v-tabs-value"><g:formatNumber number="${indicatorItem.value}" format="###,##0"/> ${indicatorItem.unit}</span>
                    </g:if>
                    </li>
                    <g:if test="${indicatorItem.lowerComparisonValueItems != null && !indicatorItem.lowerComparisonValueItems.isEmpty()}">
                      <g:each in="${indicatorItem.lowerComparisonValueItems}" var="compItem">
                        <li class="v-tabs-row">
                          <span class="v-tabs-name">${compItem.facility}</span>
                          <span class="v-tabs-formula" style="background: ${compItem.color}"  original-title="${compItem.color}">${compItem.color}</span>
                        <g:if test="${compItem.unit=='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="0%"/></span>
                        </g:if>
                        <g:if test="${compItem.unit!='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="###,##0"/> ${compItem.unit}</span>
                        </g:if>
                        </li>
                      </g:each>
                    </g:if>
                    <g:if test="${indicatorItem.lowestComparisonValueItems != null && !indicatorItem.lowestComparisonValueItems.isEmpty()}">
                      <li class="v-tabs-row">
                        <span class="v-tabs-name"> ... </span>
                      </li>
                      <g:each in="${indicatorItem.lowestComparisonValueItems}" var="compItem">
                        <li class="v-tabs-row">
                          <span class="v-tabs-name">${compItem.facility}</span>
                          <span class="v-tabs-formula" style="background: ${compItem.color}"  original-title="${compItem.color}">${compItem.color}</span>
                        <g:if test="${compItem.unit=='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="0%"/></span>
                        </g:if>
                        <g:if test="${compItem.unit!='%'}">
                          <span class="v-tabs-value"><g:formatNumber number="${compItem.value}" format="###,##0"/> ${compItem.unit}</span>
                        </g:if>
                        </li>
                      </g:each>
                    </g:if>
                  </ul>
                </div>
                <div id="geo_trend">
                  <g:if test="${(indicatorItem.geographicalValueItems != null) && !indicatorItem.geographicalValueItems.isEmpty()}">
                    <script type='text/javascript'>
                        google.load('visualization', '1', {'packages': ['geochart']});
                        google.setOnLoadCallback(drawMarkersMap);
                        function drawMarkersMap() {
                          var data = google.visualization.arrayToDataTable(${indicatorItem.geoData()});
                          var formatter = new google.visualization.NumberFormat({pattern:'${indicatorItem.geoChartValueFormat()}'});
                          formatter.format(data,3);
                          var chart = new google.visualization.GeoChart(document.getElementById('geo_chart_${indicatorItem.code}'));
                          chart.draw(data, {
                            region: 'RW',
                            resolution: 'provinces',
                            displayMode: 'markers',
                            colorAxis: {values:${indicatorItem.geoValues()},colors:${indicatorItem.geoColors()}}
                          });
                        };
                    </script>
                    <div id="geo_chart_${indicatorItem.code}" style="width:800px; height:400px;"></div>
                  </g:if>
                </div>
                <div id="info_facility" style="width:auto; height: 400px; overflow: auto;">
                  <ul class="v-tabs-nested">
                    <g:each in="${indicatorItem.valuesPerGroup}" var="itemsMap">
                      <g:set var="itemKey" value="${itemsMap.key}" />
                      <g:set var="itemValue" value="${itemsMap.value}" />
                      <li class="v-tabs-row">
                        <span class="v-tabs-name">${itemKey}</span>
                      <g:if test="${indicatorItem.unit=='%'}">
                        <span class="v-tabs-value"><g:formatNumber number="${itemValue}" format="0.00%"/> </span>
                      </g:if>
                      <g:if test="${indicatorItem.unit!='%'}">
                        <span class="v-tabs-value"><g:formatNumber number="${itemValue}" format="###,##0"/> ${indicatorItem.unit} </span>
                      </g:if>
                      </li>
                    </g:each>
                  </ul>
                </div>
              </div>
              </li>
            </g:each>
          </ul>
        </div>
      </g:each>
    </div>
  </div>
</div>