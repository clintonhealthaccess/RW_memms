<div id="historic_trend" class='toggled_tab'>
  <g:if test="${indicatorItem.historicalTrendData()!=null}">
    <script type="text/javascript">
        google.load('visualization', '1', {'packages': ['corechart']});
        google.setOnLoadCallback(drawVisualization);
        function drawVisualization() {
            data = new google.visualization.DataTable();
            data.addColumn('string', 'Date');
            data.addColumn('number', '${indicatorItem.names}');
            data.addRows(${indicatorItem.historicalTrendData()});
            formatter = new google.visualization.NumberFormat({pattern:'${indicatorItem.historicalTrendVAxisFormat()}'});
            formatter.format(data,1);
            chart = new google.visualization.LineChart(document.getElementById('historic_trend_chart_timeline_${indicatorItem.code}'));
            chart.draw(data, {
              tooltip: {isHtml: true},
              legend: 'none',
              pointSize: 12,
              colors: [['${indicatorItem.color}']],
              vAxis: {format:'${indicatorItem.historicalTrendVAxisFormat()}', title: '${indicatorItem.names}', gridlines:{color: 'lightgray' ${indicatorItem.historicalTrendLineCount()}}, minValue: 0 ${indicatorItem.historicalTrendMaxValue()}},
              hAxis: {gridlines:{color: 'lightgray', count: ${indicatorItem.totalHistoryItems + 1}}, minValue: 0, maxValue: ${indicatorItem.totalHistoryItems}},
              fontSize: 12,
              width: 900,
              height: 300});
        }
    </script>
    <div id="historic_trend_chart_timeline_${indicatorItem.code}" style="width:800px; height:400px; overflow: auto;" ></div>
  </g:if>
</div>