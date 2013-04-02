<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script>
$(document).ready(function(){
  $( "#customize-toggle").click(function(e) {
      e.preventDefault();
    $( "#dialog-form" ).dialog();
  });
});
  
</script>

<div class="entity-list">
  <!-- List Top Header Template goes here -->
  <div class="heading1-bar">
    <h1>Detailed Reports</h1>

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
    <div class="custom"><a id="customize-toggle" class="btn gray medium" href="#">Customize reports</a></div>

    <div id="dialog-form" title="Create customized report">
      <!-- <div id="dialog-form-bg"></div> -->
      
      <form>
      <fieldset>
      <label for="name">Name</label>
      <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
      <label for="email">Email</label>
      <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
      <label for="password">Password</label>
      <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
      </fieldset>
      <a href="#" class="ui-widget-previous left">Go back</a>
      <a href="#" class="ui-widget-next right btn">Continue</a>
      </form>
    </div>

    <ul id='top_tabs' class="v_tabs_nav left">
      <li><a class="active" id="#corrective">Inventory</a></li>
      <li><a id="#preventive">Corrective Maintenance</a></li>
      <li><a id="#equipment">Preventive Maintenance</a></li>
      <li><a id="#spare_parts">Spare Parts</a></li>
    </ul>

    <div class="v_tabs_content right">
      <ul class="v_tabs_subnav">
        <li><a class="active" href="#">First predefined report</a></li>
        <li><a href="#">Second predefined report</a></li>
        <li><a href="#">Third predefined report</a></li>
        <li><a href="#">Fourth predefined report</a></li>
        <li><a href="#">Fifth predefined report</a></li>
        <li><a href="#">Sixth predefined report</a></li>
        <li class="right"><a class="btn small gray" href="#">+</a></li>
      </ul>

      <!-- <div class="filters main">
        <script src="/memms/static/js/form_init.js" type="text/javascript" ></script>
        <script src="/memms/static/js/dashboard/foldable.js" type="text/javascript" ></script>
        <script src="/memms/static/js/dashboard/list_tabs.js" type="text/javascript" ></script>

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
      </div> -->

      <div id="corrective">
        <ul class="v_tabs_overview">
          <li><a href="#">32.020<span>Some label</span></a></li>
          <li><a href="#">12.345<span>Some longer label</span></a></li>
          <li><a href="#">75.501<span>Some very long label</span></a></li>
          <li><a href="#">12.944<span>Some label</span></a></li>
          <li><a href="#">22.300<span>Some long label</span></a></li>
          <li><a href="#">11.478<span>Some very long label</span></a></li>
        </ul>
        <table class="items spaced">
          <thead>
            <tr>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
              <th>Column name</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
            </tr>
            <tr>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
            </tr>
            <tr>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
            </tr>
            <tr>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
            </tr>
            <tr>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
              <td>12.345</td>
            </tr>
          </tbody>
        </table>
        <ul class="paginate">
          <li><a href="#">Previous</a></li>
          <li><a href="#">1</a></li>
          <li><a class="active" href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#">6</a></li>
          <li><a href="#">Next</a></li>
        </ul>
      </div> <!-- end of Corrective Maintenance -->


      <div id="preventive">
        <ul class="v_tabs_list">
          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>
            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>

          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>
            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>
        </ul>
      </div> <!-- end of Preventive Maintenance -->


      <div id="equipment">
        <ul class="v_tabs_list">
          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>
            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>

          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>

            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>
          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>
            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>
        </ul>
      </div> <!-- end of Management Medical Equipment -->


      <div id="spare_parts">
        <ul class="v_tabs_list">
          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>

            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>

          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>

            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>
        </ul>
      </div> <!-- end of Spare parts -->


      <div id="monitoring">
        <ul class="v_tabs_list">
          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>

            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>

          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>

            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>

          <li class="v_tabs_row">
            <p>
              <a class="v_tabs_name v_tabs_fold_toggle">
                <span class="v_tabs_switch"><img src="${resource(dir:'images',file:'arrow.png')}"/></span>
                Lorem ipsum dolor sit amet
              </a>
              <span class="v_tabs_formula">a</span>
              <span class="v_tabs_value">53%</span>
            </p>

            <div class="v_tabs_fold_container">
              <ul class="v_tabs_nested_nav">
                <li><a id='historic_trend' class='active' href="#">Historic Trend</a></li>
                <li><a id='comparison' href="#">Comparison To Other Facilities</a></li>
                <li><a id='geo_trend' href="#">Geographic Trend</a></li>
                <li><a id='info_facility' href="#">Information By Facility</a></li>
              </ul>
              <div id="historic_trend" class='toggled_tab'>
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="comparison">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="geo_trend">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
              <div id="info_facility">
                <g:render template="/entity/reports/nested_tabs" />
              </div>
            </div>
          </li>
        </ul>
      </div> <!-- end of Monitoring MEMMS Use -->
    </div>
  </div>
  <!-- End of template -->
</div>
