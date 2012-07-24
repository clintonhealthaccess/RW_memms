<div class="row ${hasErrors(bean:bean,field:'type','errors')}">
	<label for="type">${message(code:'expression.test.type.label')}</label>
	
	<div class="push-10 js_type-editor">
		<ul class="horizontal">
			<li>
				<a href="#">number</a>
				<span class="hidden">{"type":"number"}</span>
			</li>
			<li>
				<a href="#">bool</a>
				<span class="hidden">{"type":"bool"}</span>
			</li>
			<li>
				<a href="#">string</a>
				<span class="hidden">{"type":"string"}</span>
			</li>
			<li>
				<a href="#">text</a>
				<span class="hidden">{"type":"text"}</span>
			</li>
			<li>
				<a href="#">enum</a>
				<span class="hidden">{"type":"enum","enum_code":""}</span>
			</li>
			<li>
				<a href="#">list</a>
				<span class="hidden">{"type":"list","list_type":""}</span>
			</li>
			<li>
				<a href="#">map</a>
				<span class="hidden">{"type":"map","elements":{"name":"","element_type":""}}</span>
			</li>
		</ul>
	</div>
	
	<textarea class="input" type="text" name="${name}" rows="30" ${readonly?'readonly="readonly"':''} style="height:70px;">${bean?.type}</textarea>
	<div class="error-list"><g:renderErrors bean="${bean}" field="type" /></div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		 $('.js_type-editor a').bind('click', function() {
		 	$('textarea[name=${name.replaceAll('\\.','\\\\\\\\.')}]').replaceSelection($(this).next().html());
		 	return false;
		 });
	});
</script>