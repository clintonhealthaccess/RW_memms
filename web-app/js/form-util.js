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
function updateEquipment(baseUrl,failMsg){
	$(".list-check-box-spinner").hide();
	$(".ajax-error").hide()
	$(".list-check-box").change(function(event){
		$(event.target).hide();
	    $(event.target).prev().show();
	    $(event.target).next().hide();
	    var state= event.target.checked
		$.ajax({
			type :'GET',
			dataType: 'json',
			data:{"equipment.id":event.target.id,"field":event.target.name},
			url: baseUrl,
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
