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
	
	if(field=="supplier"){
		$("#form-aside-supplier .form-aside-hidden").remove();
		$("#form-aside-supplier").append(html);
		$(".form-aside-hidden").hide();
	}
		
	if(field=="manufacturer"){
		$("#form-aside-manufacturer .form-aside-hidden").remove();
		$("#form-aside-manufacturer").append(html);
		$(".form-aside-hidden").hide();
	}
	if(field=="type"){
		$("#form-aside-type .form-aside-hidden").remove();
		$("#form-aside-type").append(html);
		$(".form-aside-hidden").hide();
	}	
}
