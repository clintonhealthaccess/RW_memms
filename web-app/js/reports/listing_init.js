$(document).ready(function(){

  // load listing stuff here
  // ...

  // load jqueryui dialog box

  $( "#js-customize-toggle").click(function(e) {
    e.preventDefault();
    $( "#dialog-form" ).dialog({ modal: true });
    $('.ui-dialog').resizable('destroy');
  });

  // load jqueryui horizontal scroll
  var items, scroller = $('.v-tabs-subnav');
  var width = 0;
  var item = 0;
  var scrolledWidth = 0;
  scroller.children().each(function(){
    width += $(this).outerWidth(true);
  });
  scroller.css('width', width + 20);

  $(document).on('click', '#js-scroll-left', function(e){
    e.preventDefault();
    items = scroller.children();
    if(item > 0) {
      scrollRight(item);
      item -= 5;
    }
  });

  $(document).on('click', '#js-scroll-right', function(e){
    e.preventDefault();
    items = scroller.children();
    if(item < (items.length-6)){
      scrollLeft(item);
      item += 5;
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
    scroller.animate({'left' : (0 - scrolledWidth) + 'px'}, 'linear');
  }

  function scrollRight(item){
    var scrollWidth = 0
      items.each(function(idx){
        if(idx < 5){
          scrollWidth += $(items[idx]).outerWidth();
        }
      });
    scrolledWidth -= scrollWidth;
    scroller.animate({'left' : (0 - scrolledWidth) + 'px'}, 'linear');
  }

});

// load customized listing init
$(function(){
  customizedlisting_init(1);
});

