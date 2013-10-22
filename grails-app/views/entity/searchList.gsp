<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<title><g:message code="equipment.search.label"/></title>
		<r:require modules="chosen,form,tipsy,cluetip"/>
	</head>
	<body>
		<h2 class="login-heading"><g:message code="header.navigation.find.equipment"/></h2>
		<div class="find-equipment form-box">

			<g:form url="[controller:'equipmentView', action:'findEquipments', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
				<table class="listing">
	  				<tbody>
	  					<tr>
	  					  <td>
	    						<input type="text" name="term" value="${cmd?.term}" />
	    						<div class="error-list"><g:renderErrors bean="${cmd}" field="term" /></div>
	    						<select name="locations"></select>
	    				  </td>
	    				  <td>
  					    	<br />
  						  	<button type="submit">${message(code:'register.register.label')}</button>
    					  </td>
						</tr>
					</tbody>
				</table>
			</g:form>
		</div>
		<div id ="list-grid" class="main table">
			<div class="spinner-container">
				<img src="${resource(dir:'images',file:'list-spinner.gif')}" class="ajax-big-spinner"/>
			</div>
			<div class="list-template">
				<g:if test="${equipments && !equipments.empty()}">
					<g:render template="/templates/foundEquipment" model="[entities: equipments]"/>
				</g:if>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				listGridAjaxInit()
			});
		</script>
	</body>
</html>





