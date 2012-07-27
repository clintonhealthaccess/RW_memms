<div class="left">
	<span class="dropdown js_dropdown">
		<a href="#" class="program js_dropdown-link nice-button with-highlight"> 
			<g:if test="${currentProgram != null}">
				<g:i18n field="${currentProgram.names}" />
			</g:if> 
			<g:else>
				<g:message code="filter.program.noselection.label" />
			</g:else>
		</a>		
		<div class="dropdown-list js_dropdown-list push-top-10">
			<g:if test="${programTree != null && !programTree.empty}">
				<ul>	
					<g:render template="/tags/filter/programTree"
						model="[				
						controller: controllerName, 
						action: actionName,
						current: currentProgram,
						program: programRoot,
						programTree: programTree,
						linkParams:linkParams]" />
				</ul>
			</g:if>
		</div>		
	</span>
</div>