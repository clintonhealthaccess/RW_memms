<div class="entity-list">
	<div>
		<!-- List Top Header Template goes here -->
		<g:render template="${'/entity/'+listTop}" />
		<!-- List Template goes here -->
		<div class="main table">
			<g:render template="${template}" />
			<g:render template="/templates/pagination" />
		</div>
		<!-- End of template -->
		
	</div>
</div>
