
/**
 * 
 * Date picker
 */
function getDatePicker(){
	$(function(){
		$(".datePicker").datepicker();
	});
}
/**
 * 
 * form-aside loader
 */
function getHtml(htmls,field){
	var html=""
	$.each(htmls, function (i, val) {
		html=html+" "+val.html
	});
	var attrId= "#form-aside-"+field
	$(attrId+" .form-aside-hidden").remove();
	$(attrId).append(html);
	$(".form-aside-hidden").hide();
}
/**
 * Edit donation and obsolete
 */
function updateEquipment(baseUrl){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide()
	$(".list-check-box").change(function(event){
		$(event.target).hide();
	    $(event.target).prev().show();
	    $(event.target).next().hide();
	    var state= event.target.checked
		$.ajax({
			type :'GET',dataType: 'json',data:{"equipment.id":event.target.id,"field":event.target.name},url: baseUrl,
			success: function(data) {
				$(event.target).prev().fadeOut("slow");
				$(event.target).fadeIn("slow");
			}
		});
		$(event.target).ajaxError(function(){
			if(state) $(event.target).attr('checked',false);
			else $(event.target).attr('checked',true);
			$(event.target).prev().fadeOut("slow");
			$(event.target).fadeIn("slow");
			$(event.target).next().show("slow");	
		
		});
	})
}

/**
 * Add maintenance process
 */
function addProcess(baseUrl,order,spinnerImgSrc,errorMsg){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide()
	$('.process').on("click",".add-process-button",function(e){
		e.preventDefault();
		$(this).hide();
		$(this).nextAll(".ajax-spinner").show();
		$.ajax({
			type :'GET',
			dataType: 'json',
			data:{"order.id":order,"type":$(this).prevAll(".idle-field").attr('name'),"value":$(this).prevAll(".idle-field").attr('value')},
			url:baseUrl,
			success: function(data) {
				$(e.target).nextAll(".ajax-error").hide();
				$(e.target).nextAll(".ajax-spinner").hide();
				$(e.target).prevAll(".idle-field").val("")
				$(e.target).fadeIn("slow");
				refreshList(data.results[1],'.process-list-'+data.results[2])
			}
		});
		$(this).ajaxError(function(){
			$(this).nextAll(".ajax-error").show();	
			$(this).nextAll(".ajax-spinner").hide();
			$(this).fadeIn("slow");
		});
	})
}

/**
 * Remove Maintenance Process
 */
function removeProcess(baseUrl){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide();
	$('.process').on("click","a.delete-process",function(e){
		e.preventDefault();
		$(this).hide();
		$(this).nextAll(".ajax-spinner").show();
		$.ajax({
			type :'GET',dataType: 'json',data:{"process.id":$(this).attr("name")},url:baseUrl,
			success: function(data) {
				$(e.target).nextAll(".ajax-error").hide();
				$(e.target).nextAll(".ajax-spinner").hide();
				$(e.target).fadeIn("slow");
				refreshList(data.results[1],'.process-list-'+data.results[2])
			}
		});
		
		$(this).ajaxError(function(){
			$(this).nextAll(".ajax-error").show();
			$(this).nextAll(".ajax-spinner").hide();	
			$(this).fadeIn("slow");
		});
		
	});
}

/**
 * Add Comment to a workOrder
 */
function addComment(baseUrl,order){
	$(".comment-section").on("click","#add-comment",function(e){
		e.preventDefault();
		$(this).hide();
		$(this).nextAll(".ajax-spinner").show();
		$.ajax({
			type :'GET',dataType: 'json',
			data:{"order.id":order,"content":$("#comment-content").val()},
			url:baseUrl,
			success: function(data) {
				$(e.target).nextAll(".ajax-error").hide();
				$(e.target).nextAll(".ajax-spinner").hide();
				$(e.target).fadeIn("slow");
				$("#comment-content").val("")
				refreshList(data.results[1],'.comment-list')
			}
		});
		$(this).ajaxError(function(){
			$(this).nextAll(".ajax-error").show();
			$(this).nextAll(".ajax-spinner").hide();	
			$(this).fadeIn("slow");
		});
	})
}

/**
 * Delete a comment from workOrder
 */
function removeComment(baseUrl){
	$(".comment-section").on("click","a.delete-comment",function(e){
		e.preventDefault();
		$(this).hide();
		$(this).prevAll(".ajax-spinner").show();
		$.ajax({
			type :'GET',dataType: 'json',data:{"comment.id":$(this).attr("id")},
			url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-error").hide();
				$(e.target).prevAll(".ajax-spinner").hide();
				$(e.target).fadeIn("slow");
				refreshList(data.results[1],'.comment-list')
			}
		});
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-error").show();
			$(this).prevAll(".ajax-spinner").hide();	
			$(this).fadeIn("slow");
		});
	})
}
/**
 * Replace (refresh) any list with the provided class and hide ajax-spinner
 */
function refreshList(html,cssClass){
	$(cssClass).slideUp().replaceWith(html).slideDown();
	$(".ajax-spinner").hide();
	$(".ajax-error").hide();	
}

/**
 * Hide set of fields if an option is selected (donation - supplier is same as warranty provider)
 */
function getToHide(donation,sameAsSupplier){
	if(donation=="true")
		$("#purchase-cost").addClass("hidden").hide()
	if(sameAsSupplier=="true")
		$("#address").addClass("hidden").hide()
		
	$(".add-equipment-form :input").change(function(event){
		var currentDiv = $(event.target).parents("div.row");
		if(currentDiv.nextAll("div.can-be-hidden").is(":visible"))
			currentDiv.nextAll("div.can-be-hidden").slideUp()
		else
			currentDiv.nextAll("div.can-be-hidden").slideDown()
	})
}
