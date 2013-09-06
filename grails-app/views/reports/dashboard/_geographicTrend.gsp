<div id="geo_trend">
  <g:if test="${(indicatorItem.geographicalValueItems != null) && !indicatorItem.geographicalValueItems.isEmpty()}">
    <script type='text/javascript'>
        google.load('visualization', '1', {'packages': ['geochart']});
        google.setOnLoadCallback(drawMarkersMap);

        function drawMarkersMap() {

          var data = google.visualization.arrayToDataTable(${indicatorItem.geoData()});

          var formatter = new google.visualization.NumberFormat({pattern:'0%'});
          formatter.format(data,3);

          var options = {
            region: 'RW',
            resolution: 'provinces',
            displayMode: 'markers',
            colorAxis: {values:${indicatorItem.geoValues()}},
            colors:${indicatorItem.geoColors()},
            enableRegionInteractivity: 'true' //If true, also enables region selection and firing of regionClick and select events on mouse click
          }

          var chart = new google.visualization.GeoChart(document.getElementById('geo_chart_${indicatorItem.code}'));

          // // register the 'select' event handler for when an object is clicked
          // google.visualization.events.addListener(chart, 'select', function () {
          //     // GeoChart selections return an array of objects with a row property; no column information
          //     var selection = chart.getSelection();
          //     alert('You clicked on row ' + selection[0].row + ' which corresponds to data point ' + data.getValue(selection[0].row, 0) + ': ' + data.getValue(selection[0].row, 1));
          // });

          chart.draw(data, options);

        };
    </script>
    <div id="geo_chart_${indicatorItem.code}" style="width:800px; height:400px;"></div>
  </g:if>
</div>