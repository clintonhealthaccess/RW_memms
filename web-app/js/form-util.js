/**
 * data element Search
 */
function getDataElement(callback){
	$('.search-form button').bind('click', function(){$(this).submit(); return false;});
	$('.search-form').bind('submit', function() {
		var element = this;
		$.ajax({
			type: 'GET', data: $(element).serialize(), url: $(element).attr('action'), 
			success: function(data, textStatus){
				if (data.result == 'success') {
					var filtered = $(element).parent('div').find('.filtered');
					filtered.html(data.html);
					filtered.find('a.cluetip').cluetip(cluetipOptions);
					filtered.find('li').bind('mousedown', callback);
					filtered.find('li')
				}
			}
		});
		return false;
	});
}
 
/**
 * rich text content retrieve
 */
function getRichTextContent(){
	$('.rich-textarea-form').bind('click',function(){
		$('.toggle-entry textarea').each(function(){
			$(this).val($(this).prev('div').children().html())
		})
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

function removeMaintenanceProcess(baseUrl){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide()
	$('.delete-process').click(function(e){
		e.preventDefault();
		$(this).prevAll(".ajax-spinner").show();
		$(this).hide();
		$.ajax({
			type :'GET',dataType: 'json',data:{"process":$(this).attr("name")},url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-spinner").fadeOut("slow");
				$(e.target).fadeIn("slow");
				$(e.target).prevAll(".ajax-error").fadeOut("slow");
				$(e.target).parent("li").slideUp("slow").remove();
			}
		});
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-spinner").fadeOut("slow");
			$(this).fadeIn("slow");
			$(this).prevAll(".ajax-error").fadeIn("slow");
		});
		
	});
}

function addMaintenanceProcess(baseUrl,order,spinnerImgSrc,errorMsg){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide()
	$('.add-buttons').on("click",function(e){
		e.preventDefault();
		$(this).prevAll(".ajax-spinner").show();
		$(this).hide();
		$.ajax({
			type :'GET',
			dataType: 'json',
			data:{"order":order,"type":$(this).prevAll(".idle-field").attr('name'),"value":$(this).prevAll(".idle-field").attr('value')},
			url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-spinner").fadeOut("slow");
				$(e.target).fadeIn("slow");
				$(e.target).prevAll(".ajax-error").fadeOut("slow");
				$(e.target).prevAll(".idle-field").val("")	
				$(e.target).prevAll(".processes").append("<li>" +"<span class='ajax-error'>"+errorMsg+"</span>"+
								+"<img src='+spinnerImgSrc+' class='ajax-spinner'/>" +data.results[1].name
								+"<a href=\"#\" name="+data.results[1].id+" class=\"delete-process\">X</a></li>")	
			}
		});
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-spinner").fadeOut("slow");
			$(this).fadeIn("slow");
			$(this).prevAll(".ajax-error").fadeIn("slow");	
		});
	})
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
