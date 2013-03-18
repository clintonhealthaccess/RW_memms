<div class="entity-list">
  <!-- List Top Header Template goes here -->
  <div class="heading1-bar">
    <h1>Detailed Listings</h1>

  </div>
  <!-- End of template -->
  
  <!-- Filter template if any goes here -->
  <g:if test="${filterTemplate!=null}">
    <g:render template="/entity/${filterTemplate}" />
  </g:if>
  <!-- End of template -->
  
  <!-- List Template goes here -->
  <div id ="list-grid" class="v_tabs">
	  <!-- <div class="spinner-container">
      <img src="${resource(dir:'images',file:'list-spinner.gif')}" class="ajax-big-spinner"/>
    </div> -->
    <ul class="v_tabs_nav left">
      <li><a href="#">First detailed report</a></li>
      <li><a class="active" href="#">Second detailed report</a></li>
      <li><a href="#">Third detailed report</a></li>
      <li><a href="#">Fourth detailed report</a></li>
      <li><a href="#">Fifth detailed report</a></li>
    </ul>

    <div class="v_tabs_content right">
      <a id="showhide" class="right" href="#">Show / Hide filters</a>
      <ul class="v_tabs_subnav">
        <li><a class="active" href="#">First predefined report</a></li>
        <li><a href="#">Second predefined report</a></li>
        <li><a href="#">Third predefined report</a></li>
        <li><a href="#">Fourth predefined report</a></li>
      </ul>

      <ul class="v_tabs_list">
        <li class="v_tabs_row">
          <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
          <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
          <span class="v_tabs_formula">a</span>
          <span class="v_tabs_value">53%</span>
        </li>
        <li class="v_tabs_row">
          <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
          <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
          <span class="v_tabs_formula">a</span>
          <span class="v_tabs_value">53%</span>
        </li>
        <li class="v_tabs_row">
          <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
          <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
          <span class="v_tabs_formula">a</span>
          <span class="v_tabs_value">53%</span>
        </li>

        <li class="v_tabs_row selected">
          <p>
            <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
            <a class="v_tabs_name">Lorem ipsum dolor sit amet</a>
            <span class="v_tabs_formula">a</span>
            <span class="v_tabs_value">53%</span>
          </p>
          <ul class="v_tabs_nested_nav">
            <li><a href="#">Historic Trend</a></li>
            <li><a href="#">Comparison To Other Facilities</a></li>
            <li><a href="#">Geographic Trend</a></li>
            <li><a class="active" href="#">Information By Facility</a></li>
          </ul>
          <ul class="v_tabs_nested">
            <li class="v_tabs_row">
              <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </li>
            <li class="v_tabs_row">
              <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </li>
            <li class="v_tabs_row">
              <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </li>
            <li class="v_tabs_row">
              <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </li>
            <li class="v_tabs_row">
              <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </li>
            <li class="v_tabs_row">
              <span class="v_tabs_name">Lorem ipsum dolor sit amet</span>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </li>
          </ul>
        </li>

      </ul>
    </div>
  </div>
  <!-- End of template -->
</div>