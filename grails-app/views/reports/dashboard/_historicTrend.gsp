<div>
  <g:if test="${indicatorItem.historicalValueItems != null && !indicatorItem.historicalValueItems.empty}">
    <script type="text/javascript">
      function drawLineChart() {
          data = new google.visualization.DataTable();
          data.addColumn('string', 'Date');
          data.addColumn('number', '${indicatorItem.names}');
          data.addRows(${indicatorItem.historicalTrendData()});
          formatter = new google.visualization.NumberFormat({pattern:'${indicatorItem.historicalTrendVAxisFormat()}'});
          formatter.format(data,1);
          var options = {
            tooltip: {textStyle: {fontName:'Arial', fontSize:12}, showColorCode:true},
            legend: 'none',
            pointSize: 12,
            colors: [['${indicatorItem.color}']],
            vAxis:{
                    format:'${indicatorItem.historicalTrendVAxisFormat()}', 
                    title:'${indicatorItem.names}', 
                    gridlines:{color:'lightgray' ${indicatorItem.historicalTrendLineCount()}}, 
                    minValue:0 ${indicatorItem.historicalTrendMaxValue()}
                  },
            hAxis:{
                    gridlines:{color: 'lightgray', count:${indicatorItem.totalHistoryItems + 1}}, 
                    minValue: 0, 
                    maxValue: ${indicatorItem.totalHistoryItems}
                  },
            fontSize: 12,
            width: 800,
            height: 300
          }
          var chart = new google.visualization.LineChart(document.getElementById('historic_chart_${indicatorItem.code}'));
          chart.draw(data, options);
      }
    </script>
    <div id="historic_chart_${indicatorItem.code}"></div>
  </g:if>
</div>