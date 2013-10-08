<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="entity.search.label" args="[entityName]"/></title>
		<r:require modules="chosen,form,tipsy,cluetip"/>
	</head>
	<body>
		<div class="entity-list">
			<div class="heading1-bar">
				<h1><g:message code="entity.search.label" args="[entityName]"/></h1>
				<div class="filters main projection search-box">
					<g:render template="${template}" model="[entity: entity]"/>
				</div>
			</div>
			<div id ="list-grid" class="main table">
				<div class="spinner-container">
					<img src="${resource(dir:'images',file:'list-spinner.gif')}" class="ajax-big-spinner"/>
				</div>
				<div class="list-template">
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				$(".location-container").hide();
				$(".list-template").hide()
				$( "input[type=checkbox]" ).on( "click", function(event){
					if($(this).is(":checked")) $(this).attr("checked", "checked");
					else  $(this).removeAttr("checked");
					$(".location-container").toggle("slow");
				} );
				listGridAjaxInit()
			});
		</script>
	</body>
</html>





