<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700&subset=latin' rel='stylesheet' type='text/css'>

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
    <div class="custom"><a id="customize-toggle" class="btn gray medium" href="#">Create customized report</a></div>

    <div id="dialog-form">
      <h2>Select the type of customized report<span class="right">Step <b>1</b> of <b>4</b></span></h2>
      
      <form>
      <fieldset>
        <label for="report_type">Report Type</label>
        <select><option>Please select</option></select>
        <label for="report_type">Report Subtype</label>
        <select><option>Please select</option></select>
      <!-- <label for="name">Name</label>
      <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
      <label for="email">Email</label>
      <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
      <label for="password">Password</label>
      <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" /> -->
      </fieldset>
      <a href="#" class="ui-widget-previous left btn gray">Go back</a>
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
        <!-- <li class="right"><a class="btn small gray" href="#">+</a></li> -->
      </ul>

      <div id="corrective">
        <div class="v_tabs_summary">
          <h2>Summary</h2>
          <hr />
          <ul>
            <li><a href="#">32.020<span>Some label</span></a></li>
            <li><a href="#">12.345<span>Some longer label</span></a></li>
            <li><a href="#">75.501<span>Some very long label</span></a></li>
            <li><a href="#">12.944<span>Some label</span></a></li>
            <li><a href="#">22.300<span>Some long label</span></a></li>
            <li><a href="#">11.478<span>Some very long label</span></a></li>
          </ul>
        </div>

        <div class="v_tabs_criteria">
          <!-- Load & initialize Tipsy -->
          <script src="/memms/static/js/jquery/tipsy/src/javascripts/jquery.tipsy.js" type="text/javascript" ></script>
          <script src="/memms/static/js/tipsy_init.js" type="text/javascript" ></script>
          <ul class="left">
            <li>
              <span>Report type:</span>
              <a href="#">Lorem Ipsum 1234</a>
            </li>
            <li>
              <span>Report subtype:</span>
              <a href="#">Dolor Sit Amet 1234</a>
            </li>
            <li>
              <span>Ordering:</span>
              <a href="#">by Location</a>
            </li>
            <li>
              <span>Filters:</span>
              <a href="#">There are <a href="#" id="showhide" class="tooltip" original-title="click to view them all">133</a> filters applied</a>
            </li>
          </ul>
        </div>

        <div class="filters main">
          <script src="/memms/static/js/form_init.js" type="text/javascript" ></script>
          <script src="/memms/static/js/dashboard/foldable.js" type="text/javascript" ></script>
          <script src="/memms/static/js/dashboard/list_tabs.js" type="text/javascript" ></script>

          <form class="filters-box" method="get" action="/memms/equipmentView/filter" style="display: none;">
            <ul class="applied-filters-list">
              <li>
                <h3>Filters category</h3>
                <ul class="applied-filters">
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                </ul>
              </li>
              <li>
                <h3>Filters category</h3>
                <ul class="applied-filters">
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                </ul>
              </li>
              <li>
                <h3>Filters category</h3>
                <ul class="applied-filters">
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                </ul>
              </li>
              <li>
                <h3>Filters category</h3>
                <ul class="applied-filters">
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                </ul>
              </li>
              <li>
                <h3>Filters category</h3>
                <ul class="applied-filters">
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                </ul>
              </li>
              <li>
                <h3>Filters category</h3>
                <ul class="applied-filters">
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                  <li>Lorem ipsum dolor</li>
                </ul>
              </li>

            </ul>
          </form>
        </div>

        <table class="items spaced ralign">
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
