$(document).ready(function(){
  $('.v_tabs_nested_nav').children('li').children('a').click(function(e) {
    var tabs = $(this).parents('div').children('.v_tabs_nested_nav').children('li').children('a');
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
});
