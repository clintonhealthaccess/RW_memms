$(document).ready(function(){
    $('.v_tabs_fold_toggle').click(function(event) {
      if($(this).parent().parent().hasClass('selected')) {
        $(this).parent().parent().children('.v_tabs_fold_container').children().hide();
        $(this).parent().parent().removeClass('selected');
      } else {
        $(this).parent().parent().children('.v_tabs_fold_container').children().show();
        $(this).parent().parent().addClass('selected');
        $('div#comparison').hide();
        $('div#geo_trend').hide();
        $('div#info_facility').hide();
      }
      $(this).parent().parent().children('.v_tabs_fold_container').toggle(500);
    });
});
