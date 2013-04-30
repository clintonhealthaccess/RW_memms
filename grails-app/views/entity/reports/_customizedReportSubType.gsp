<label for="report_type">Report Subtype</label>
<select name="reportSubType" class="js-custom-report-subtype">
  <option value="0">Please select</option>
  <g:if test="${['inventory','spareparts'].contains(reportType)}">
    <option value="inventory" ${reportSubType == 'inventory'?'selected':''}>
      List of Inventory</option>
  </g:if>
  <g:if test="${['corrective','preventive'].contains(reportType)}">
    <option value="workdorders" ${reportSubType == 'workorders'?'selected':''}>
      List of Work Orders</option>
  </g:if>
  <g:if test="${['inventory','corrective','preventive','spareparts'].contains(reportType)}">
    <option value="statuschanges" ${reportSubType == 'statuschanges'?'selected':''}>
      List of Status Changes</option>
  </g:if>
  <g:if test="${['spareparts'].contains(reportType)}">
    <option value="userate" ${reportSubType == 'userate'?'selected':''}>
      Spare Part Use Rate</option>
  </g:if>
  <g:if test="${['spareparts'].contains(reportType)}">
    <option value="stockout" ${reportSubType == 'stockout'?'selected':''}>
      Forecast Stock Out</option>
  </g:if>
</select>