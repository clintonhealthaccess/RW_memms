<div class="left">	
	<span class="dropdown js_dropdown">
		<div>
			<a class="level js_dropdown-link nice-button with-highlight" href="#">
				<g:i18n field="${currentLevel?.names}"/>
			</a>
		</div>
		<div class="hidden dropdown-list js_dropdown-list">
			<ul>
				<g:each in="${levels}" var="level">
					<% def levelLinkParams = new HashMap(linkParams) %>
					<% levelLinkParams << [level:level.id+""] %>
					<% linkParams = levelLinkParams %>
					<li>
						<a href="${createLinkByFilter(controller:controllerName, action:actionName, params:linkParams)}">
							<span><${level.names}</span> 
						</a>
					</li>
				</g:each>
			</ul>
		</div> 
	</span>
</div>