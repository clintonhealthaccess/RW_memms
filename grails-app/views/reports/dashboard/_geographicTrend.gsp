<div>
  <g:if test="${indicatorItem.geographicalValueItems != null && !indicatorItem.geographicalValueItems.empty}">
    <script type="text/javascript">
        function drawGeoChart() {
            var data = google.visualization.arrayToDataTable(${indicatorItem.geoData()});
            var formatter = new google.visualization.NumberFormat({pattern:'${indicatorItem.geoValuesFormat()}'});
            formatter.format(data,3);
            var options = {
                region: 'RW',
                resolution: 'provinces',
                displayMode: 'markers',
                colorAxis: {values:${indicatorItem.geoValues()}},
                colors:${indicatorItem.geoColors()},
                sizeAxis: {minValue:${indicatorItem.geoTrendMinValue()}, maxValue:${indicatorItem.geoTrendMaxValue()}},
                // enableRegionInteractivity: 'true', // If true, also enables region selection and firing of regionClick and select events on mouse click
                tooltip:{showColorCode: true},
                width: 800,
                height: 300
            }
            var chart = new google.visualization.GeoChart(document.getElementById('geo_chart_${indicatorItem.code}'));
            chart.draw(data, options);
        };
    </script>
    <div id="geo_chart_${indicatorItem.code}" style="width:800px; height:400px;"></div>
  </g:if>
</div>