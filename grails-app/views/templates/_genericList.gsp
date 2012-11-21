<div class="entity-list">
	<div>
		<!-- List Top Header Template goes here -->
		<div class="heading1-bar">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${listTop!=null}">
				<g:render template="/entity/${listTop}" />
			</g:if>
		</div>
		<!-- End of template -->
		
		<!-- Filter template if any goes here -->
		<g:if test="${filterTemplate!=null}">
			<g:render template="/entity/${filterTemplate}" />
		</g:if>
		<!-- End of template -->
		
		<!-- List Template goes here -->
		<div id ="list-grid" class="main table">
			<div class="spinner-container">
				<img src="${resource(dir:'images',file:'list-spinner.gif')}" class="ajax-big-spinner"/>
			</div>
			<div class="list-template">
				<g:render template="${template}" />
			</div>
		</div>
		<!-- End of template -->
	</div>
</div>
