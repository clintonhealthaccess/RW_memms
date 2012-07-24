<div class="left">
	<span class="dropdown js_dropdown">
		<a class="time js_dropdown-link nice-button with-highlight" href="#">
			<g:dateFormat format="yyyy" date="${currentPeriod.startDate}"/>
		</a>
		<div class="dropdown-list js_dropdown-list push-top-10">
			<ul>
				<g:each in="${periods}" var="period">
					<% def periodLinkParams = new HashMap(linkParams) %>
					<% periodLinkParams << [period:period.id] %>
					<% linkParams = periodLinkParams %>
					<li>
						<a href="${createLinkByFilter(controller:controllerName, action:actionName, params:linkParams)}">
							<span><g:dateFormat format="yyyy" date="${period.startDate}" /></span> 
						</a>
					</li>
				</g:each>
			</ul>
		</div> 
	</span>
</div>