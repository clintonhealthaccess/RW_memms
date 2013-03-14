<div class="entity-list">
  <!-- List Top Header Template goes here -->
  <div class="heading1-bar">
    <h1>Reports Dashboard</h1>

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
      <li><a class="active" href="#">Corrective Maintenance</a></li>
      <li><a href="#">Preventive Maintenance</a></li>
      <li><a href="#">Management Medical Equipment</a></li>
      <li><a href="#">Management Of Spare Parts</a></li>
      <li><a href="#">Monitoring Of MEMMS Use</a></li>
    </ul>
    <div class="v_tabs_content right">
    	<a id="showhide" class="right" href="#">Show / Hide filters</a>
      <ul class="v_tabs_filters">
        <li><input type="checkbox" /><label>option 1</label></li>
        <li><input type="checkbox" /><label>option 2</label></li>
        <li><input type="checkbox" /><label>option 3</label></li>
      </ul>

      <div class="filters main">
        <script src="/memms/static/js/form_init.js" type="text/javascript" ></script>

        <form class="filters-box" method="get" action="/memms/equipmentView/filter" style="display: none;">
          <ul class="filters-list">

            <li>
              <div class="row ">
                <label for="equipmentType.id">Equipment Type :</label>
                <select name="obsolete">
                  <option value="">Please select</option>
                  <option value="true">obsolete</option>
                  <option value="false">not obsolete</option>
                </select>
              </div>
            </li>

            <li>
              <div class="row ">
                <label for="manufacturer.id">Manufacturer :</label>
                <select name="obsolete">
                  <option value="">Please select</option>
                  <option value="true">obsolete</option>
                  <option value="false">not obsolete</option>
                </select>
              </div>
            </li>

            <li>
              <div class="row ">
                <label for="supplier.id">Supplier :</label>
                <select name="obsolete">
                  <option value="">Please select</option>
                  <option value="true">obsolete</option>
                  <option value="false">not obsolete</option>
                </select>
              </div>
            </li>

            <li>
              <div class="row ">    
                <label for="serviceProvider.id">Service Provider :</label>
                <select name="obsolete">
                  <option value="">Please select</option>
                  <option value="true">obsolete</option>
                  <option value="false">not obsolete</option>
                </select>
              </div>
            </li>

            <li>
              <label>Obsolete</label> 
              <select name="obsolete">
                <option value="">Please select</option>
                <option value="true">obsolete</option>
                <option value="false">not obsolete</option>
              </select>
            </li>

            <li>
              <div class="row ">
                <label for="status">Status :</label>
                <select name="status">
                  <option value="NONE">Please select</option>
                  <option value="OPERATIONAL">Operational</option>
                  <option value="PARTIALLYOPERATIONAL">Partially Operational</option>
                  <option value="INSTOCK">In Stock</option>
                  <option value="UNDERMAINTENANCE">Under Maintenance</option>
                  <option value="FORDISPOSAL">For Disposal</option>
                  <option value="DISPOSED">Disposed</option>
                </select>
                <div class="error-list"></div>
              </div>
            </li>
            
            <li>
              <div class="row ">
                <label for="purchaser">Purchaser :</label>
                <select name="purchaser">
                  <option value="NONE">Please select</option>
                  <option value="BYMOH">Ministry of Health</option>
                  <option value="BYFACILITY">Facility</option>
                  <option value="BYDONOR">Donor</option>
                </select>
                <div class="error-list"></div>
              </div>
            </li>
            
            <li>
              <div class="row ">
                <label for="donor">Donor :</label>
                <select name="donor">
                  <option value="NONE">Please select</option>
                  <option value="MOHPARTNER">MoH Partner</option>
                  <option value="OTHERNGO">Other NGO</option>
                  <option value="INDIVIDUAL">Individual</option>
                  <option value="OTHERS">Others</option>
                </select>
                <div class="error-list"></div>
              </div>
            </li>

          </ul>
          <input type="hidden" value="13" name="dataLocation.id">
          <button type="submit">Filter</button>
          <a class="clear-form" href="#">Clear</a>
        </form>
      </div>

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
      </ul>
    </div>
  </div>
  <!-- End of template -->
</div>