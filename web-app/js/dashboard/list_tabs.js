$(document).ready(function(){
  $('.v_tabs_nested_nav').children('li').children('a').click(function(e) {
    e.preventDefault();
    $('.v_tabs_nested_nav').children('li').children('a').removeClass('active');
    $(this).addClass('active');
    var id = $(this).attr('id');
    $('.toggled_tab').hide().removeClass('toggled_tab');
    $('div#' + id).addClass('toggled_tab');
    $('div#' + id).show();
  });
});
