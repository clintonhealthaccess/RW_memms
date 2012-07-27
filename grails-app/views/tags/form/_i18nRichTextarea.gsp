<g:set var="random" value="${org.apache.commons.lang.math.RandomUtils.nextInt()}"/>
<script type="text/javascript">
	var maxH= '${maxheight}';
	var iconsP ='${resource(dir: "images", file: "nicEditorIcons.gif")}';
	var buttonL=['save','bold','italic','underline','ol','ul','indent','outdent','image','link','unlink','subscript','superscript','strikethrough','hr','html'];
</script>
<div class="row ${hasErrors(bean:bean,field:field, 'errors')}">
	<g:each in="${locales}" var="locale" status="i">
		<script type="text/javascript">
			$(document).ready(function() {
				var myNicEditor = new nicEditor({
					maxHeight: maxH,
					iconsPath : iconsP,
					buttonList : buttonL
				});
				myNicEditor.panelInstance('area-${random}-${locale}');
			});
		</script> 
		<div class="toggle-entry ${i!=0?'hidden':''}" data-toggle="${locale}">
			<label for="${name}.${locale}">${label} (${locale})</label>		
			<textarea id="area-${random}-${locale}" type="${type}" class="idle-field" name="${name}.${locale}" style="width: ${width}px; height: ${height}px;" class="rich-text-area">${value?.get(locale)}</textarea>
		</div>
	</g:each>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${field}"/></div>
</div>