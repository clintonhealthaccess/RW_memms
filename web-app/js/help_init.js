function hideQuestionHelp(el) {
	el.parents('.js_help').slideUp(500, function () {
		el.parents('.js_help-container').prev().css({'display': 'inline-block'});
	})
}

function showQuestionHelp(el) {
	el.next().find('.js_help').slideDown(500, function () {
		el.hide();
	})
}

$(document).delegate('.js_hide-help', 'click', function(){
	hideQuestionHelp($(this));
	return false;
});

$(document).delegate('.js_show-help', 'click', function(){
	showQuestionHelp($(this));
    return false;
});
