$(document).ready(function(){

	// load listing stuff here
	// ...

	// load customized report jqueryui dialog box
	$( "#js-customize-toggle").click(function(e) {
	  e.preventDefault();
		$( "#dialog-form" ).dialog({ modal: true });
		$('.ui-dialog').resizable('destroy');
	});

	// load report list jqueryui horizontal scroll
	var items, scroller = $('#js-slider-wrapper ul');
	var width = 0;
	var item = 0;
	var scrolledWidth = 0;
	scroller.children().each(function(){
	    width += $(this).outerWidth(true);
	});
	scroller.css('width', width);
        hideRestOfElements();

	$(document).on('click', '#js-scroll-left', function(e){
	  e.preventDefault();
	  items = scroller.children();
	  if(item > 0) {
	    item -= 4;
	  }
          hideRestOfElements();
	});

	$(document).on('click', '#js-scroll-right', function(e){
	  e.preventDefault();
	  items = scroller.children();
	  if(item < (items.length - 3)){
	    item += 4;
	  }
          hideRestOfElements();
	});

        function hideRestOfElements(){
          var scrollWidth = 0;
          items = scroller.children('li');
          items.show();
          items.each(function(idx) {
            if(idx < item || idx > item + 3) {
              scrollWidth += $(items[idx]).outerWidth();
              $(items[idx]).hide();
            }
          });
        }

	function scrollLeft(item){
	  var scrollWidth = 0
	  items.each(function(idx){
            if(scrollWidth < 650 && idx < items.length -1){
              scrollWidth += $(items[idx]).outerWidth();
            }
	  });
	  scrolledWidth += scrollWidth;
	  scroller.animate({'left' : (0 - scrolledWidth) + 'px'}, 'linear');
	}

	function scrollRight(item){
	  var scrollWidth = 0
	  items.each(function(idx){
            if(scrollWidth < 650 && idx < items.length -1){
              scrollWidth += $(items[idx]).outerWidth();
            }
	  });
	  scrolledWidth -= scrollWidth;
	  scroller.animate({'left' : (0 - scrolledWidth) + 'px'}, 'linear');
	}

	//load report list delete node
	$("#js-delete-node").click(function(e){
		e.preventDefault();
		if(confirm("Are you sure?")){
			var savedReportId = $(this).data("saved-report-id");
			$(this).parents('li').remove();
			// TODO add ajax method in listing controller that deletes the saved report
		}
	});

});

// load customized listing init
$(function(){
  customizedlisting_init(1);
});

