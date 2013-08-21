$(document).ready(function(){

  // load dashboard stuff here
  
  // filters show/hide
  $('#js-filters-toggle').click(function(e) {
    e.preventDefault();
    $('.filters-box').slideToggle();
  });
  $('#js-filters-close').click(function(e) {
    e.preventDefault();
    $('.filters-box').slideToggle();
  });

  $('.v-tabs-fold-toggle').click(function(event) {
    var toggle = $(this);
    var tabs = $('.v-tabs-nested-nav').children('li').children('a');
    var li = toggle.parent().parent();
    if(li.hasClass('selected')) {
      //li.children('.v-tabs-fold-container').children().hide();
      li.removeClass('selected');
      tabs.removeClass('active');
      li.find('div#historic_trend').hide().removeClass('toggled_tab');
      li.find('div#comparison').hide().removeClass('toggled_tab');
      li.find('div#geo_trend').hide().removeClass('toggled_tab');
      li.find('div#info_facility').hide().removeClass('toggled_tab');
    } else {
      //li.children('.v-tabs-fold-container').children().show();
      li.addClass('selected');
      tabs.removeClass('active');
      li.find('div#historic_trend').hide().removeClass('toggled_tab');
      li.find('div#comparison').hide().removeClass('toggled_tab');
      li.find('div#geo_trend').hide().removeClass('toggled_tab');
      li.find('div#info_facility').hide().removeClass('toggled_tab');
      li.find('a#historic_trend').addClass('active');
      li.find('div#historic_trend').addClass('toggled_tab');
      li.find('div#historic_trend').show();
    }
    li.children('.v-tabs-fold-container').toggle(500);
  });

  $('.v-tabs-nested-nav').children('li').children('a').click(function(e) {
    var tabs = $(this).parents('div').children('.v-tabs-nested-nav').children('li').children('a');
    var clicked_tab = $(this);
    var parent_div = $(this).parents('div');
    e.preventDefault();
    tabs.removeClass('active');
    clicked_tab.addClass('active');
    var id = $(this).attr('id');
    parent_div.children('.toggled_tab').hide().removeClass('toggled_tab');
    parent_div.children('div#' + id).addClass('toggled_tab');
    parent_div.children('div#' + id).show();
  });

  $('#top_tabs').children('li').children('a').click(function(e) {
    e.preventDefault();
    var clicked_tab = $(this);
    var tabs = $('#top_tabs');
    tabs.find('.active').removeClass('active');
    clicked_tab.addClass('active');

    var id = $(this).attr('id');
    var parent_div = clicked_tab.parents('div');

    $('div#corrective').hide();
    $('div#preventive').hide();
    $('div#equipment').hide();
    $('div#spare_parts').hide();
    $('div#monitoring').hide();

    $('div#' + id).show();
  });

  $('#checkbox-green').change(function() {
    if(this.checked) {
        $('.v-tabs-row-green').show();
    } else {
        $('.v-tabs-row-green').hide();
    }
   });

  $('#checkbox-yellow').change(function() {
    if(this.checked) {
        $('.v-tabs-row-yellow').show();
    } else {
        $('.v-tabs-row-yellow').hide();
    }
   });

  $('#checkbox-red').change(function() {
    if(this.checked) {
        $('.v-tabs-row-red').show();
    } else {
        $('.v-tabs-row-red').hide();
    }
   });

});