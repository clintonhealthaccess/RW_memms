<div class="entity-list">
	<div>
		<div class="heading1-bar">
	     	<g:message code="default.list.label" args="[entityName]" />
	     	<g:if test="${!search}">
		     	<span class="right">
					<g:if test="${!addTemplate}">
		  				<a <%-- href="${createLinkWithTargetURI(controller: controllerName, action:'create')+(request.queryString==null?'':'&'+request.queryString)}"--%> >
		  					<g:message code="default.new.label" args="[entityName]"/>
		  				</a>
		  			</g:if>
		  			<g:else>
		  				<g:render template="${addTemplate}"/>
		  			</g:else>
		  			&nbsp;
		  			<a href="${createLinkWithTargetURI(controller: controllerName, action:'exporter')+(request.queryString==null?'':'&'+request.queryString)}">
	  					<g:message code="default.export.label" />
	  				</a>
	  				&nbsp;
	  				<g:if test="${entityClass != null}">
		  				<a href="${createLinkWithTargetURI(controller: 'entityImporter', action:'importer', params:[entityClass: entityClass.name])+(request.queryString==null?'':'&'+request.queryString)}">
		  					<g:message code="default.import.label" />
		  				</a>
	  				</g:if>
		     	</span>
	     	</g:if>
		</div>
		
		<!-- Template goes here -->
		<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('search')}">
			<g:searchBox action="search"/>
		</g:if>
		
		<div class="main">
			<g:render template="${template}"/>
		</div>
		
		<g:if test="${entities.empty}">
			<div class="main"><g:message code="entity.list.empty.label" args="[entityName]"/></div>
		</g:if>
		
		<!-- End of template -->
		<div class="paginateButtons main">
			<g:if test="${entityCount != null}">
				<g:paginate total="${entityCount}" params="${params}" action="${actionName}"/>
			</g:if>
		</div>
		
	</div>
</div>
