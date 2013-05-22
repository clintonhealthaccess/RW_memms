$(document).ready(function(){

  // load listing stuff here

  // load jqueryui dialog box
  $( "#js-customize-toggle").click(function(e) {
      e.preventDefault();
    $( "#dialog-form" ).dialog({ modal: true });
    $('.ui-dialog').resizable('destroy');
  });

});

// load jqueryui time picker
$(function() {
  $( ".js-date-picker" ).datepicker();
});

// load chosen
$(".chzn-select").chosen();

// load customized listing init
$(function(){
  customizedlisting_init();
});

