$(document).ready(function(){
    $('.v_tabs_fold_toggle').click(function(event) {
      var toggle = $(this);
      var li = toggle.parent().parent();
      if(li.hasClass('selected')) {
        li.children('.v_tabs_fold_container').children().hide();
        li.removeClass('selected');
      } else {
        li.children('.v_tabs_fold_container').children().show();
        li.addClass('selected');
        li.find('div#comparison').hide();
        li.find('div#geo_trend').hide();
        li.find('div#info_facility').hide();
      }
      li.children('.v_tabs_fold_container').toggle(500);
    });
});
