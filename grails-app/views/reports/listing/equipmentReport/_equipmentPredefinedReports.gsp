<script type='text/javascript'>
$(document).ready(function(){
    var items, scroller = $('.v-tabs-subnav');
    var width = 0;
    var item = 0;
    var scrolledWidth = 0;
    scroller.children().each(function(){
        width += $(this).outerWidth(true);
    });
    scroller.css('width', width);

    $(document).on('click', '.v-tabs-subnav-scroll-left', function(e){
      e.preventDefault();
      console.log('scrolling left')
      items = scroller.children();
      if(item > 0) {
        scrollRight(item);
        item--;
      }
    });

    $(document).on('click', '.v-tabs-subnav-scroll-right', function(e){
      e.preventDefault();
      console.log('scrolling right')
      items = scroller.children();
      if(item < (items.length-1)){
        scrollLeft(item);
        item++;
      }
    });

    function scrollLeft(item){
      var scrollWidth = 0
      items.each(function(idx){
        if(idx < 5){
          scrollWidth += $(items[idx]).outerWidth();
        }
      });
      scrolledWidth += scrollWidth;
      scroller.animate({'left' : (0 - scrolledWidth - 30) + 'px'}, 'linear');
    }

    function scrollRight(item){
      var scrollWidth = 0
      items.each(function(idx){
        if(idx < 5){
          scrollWidth += $(items[idx]).outerWidth();
        }
      });
      scrolledWidth -= scrollWidth;
      scroller.animate({'left' : (0 - scrolledWidth - 30) + 'px'}, 'linear');
    }
});
</script>
<div id='js-chosen-report' class='v-tabs-chosen-report'>
  <!-- Generate selected report name with Groovy -->
  <a href='#' class='active'>Obsolete Equipments</a>
</div>
<a class='v-tabs-subnav-scroll-left' href='#' id='js-scroll-left'><</a>
<a class='v-tabs-subnav-scroll-right' href='#' id='js-scroll-right'>></a>
<div class='v-tabs-subnav-wrapper' id='#js-slider-wrapper'>
  <ul class="v-tabs-subnav slide">
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'generalEquipmentsListing')}" id="report-1">
              <g:message code="default.all.equipments.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'obsoleteEquipments')}" id="report-2">
              <g:message code="default.obsolete.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'disposedEquipments')}" id="report-3">
              <g:message code="default.disposed.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'underMaintenanceEquipments')}" id="report-4">
              <g:message code="default.under.maintenance.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'inStockEquipments')}" id="report-5">
              <g:message code="default.in.stock.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'inStockEquipments')}" id="report-5">
              <g:message code="default.in.stock.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'inStockEquipments')}" id="report-5">
              <g:message code="default.in.stock.label" />
      </a>
    </li>
    <li>
      <a href="${createLinkWithTargetURI(controller: 'listing', action:'inStockEquipments')}" id="report-5">
              <g:message code="default.in.stock.label" />
      </a>
    </li>
  </ul>
</div>
