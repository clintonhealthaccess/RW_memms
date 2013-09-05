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
            colorAxis: {values:${indicatorItem.geoValues()},
            colors:${indicatorItem.geoColors()}
          });
        };
    </script>
    <div id="geo_chart_${indicatorItem.code}" style="width:800px; height:400px;"></div>
  </g:if>
</div>