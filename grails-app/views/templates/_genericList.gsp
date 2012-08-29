<div class="entity-list">
	<div>
		<div class="heading1-bar">
	     	<h1><g:message code="default.list.label" args="[entityName]" /></h1>
	     	<g:if test="${!search}">
		     	<span class="right">
		     		<shiro:hasPermission permission="${controllerName}:create">
						<g:if test="${!addTemplate}">
			  				<a href="${createLinkWithTargetURI(controller: controllerName, action:'create',params:params)+(request.queryString==null?'':'&'+request.queryString)}" class="next medium left push-r">
			  					<g:message code="default.new.label" args="[entityName]"/>
			  				</a>
			  			</g:if>
			  			<g:else>
			  				<g:render template="${addTemplate}"/>
			  			</g:else>
		  			</shiro:hasPermission>
		  			<shiro:hasPermission permission="${controllerName}:export">
			  			<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('export')}">
			  			&nbsp;
				  			<a href="${createLinkWithTargetURI(controller: controllerName, action:'exporter',params:params)+(request.queryString==null?'':'&'+request.queryString)}" class="next medium gray left import">
			  					<g:message code="default.export.label" />
			  				</a>
		  				</g:if>
	  				</shiro:hasPermission>
	  				<shiro:hasPermission permission="${controllerName}:importer">
		  				<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('importer')}">
		  				&nbsp;
			  				<g:if test="${entityClass != null}">
				  				<a href="${createLinkWithTargetURI(controller: 'entityImporter', action:'importer', params:params)+(request.queryString==null?'':'&'+request.queryString)}" class="next medium gray left export">
				  					<g:message code="default.import.label" />
				  				</a>
			  				</g:if>
		  				</g:if>
	  				</shiro:hasPermission>
	  				<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('search')}">
        				<g:searchBox action="search"/>
        			</g:if>
		     	</span>
	     	</g:if>
		</div>
		<shiro:hasPermission permission="${controllerName}:filter">
		<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('filter')}">
			<!-- Filter starts here-->
			<div class="filters main">
			  <h2>Filter inventory<a href="#" class="right"><img src="${resource(dir:'images/icons',file:'icon_close_flash.png')}"></a></h2>
			  <form class="filters-box">
			    <ul>
			      <li>
			        <label>Locations</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>Type</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>Manufacturer</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>Warranty</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>District</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>Status</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>Supplier</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			      <li>
			        <label>Donated</label>
			        <select>
			          <option>Please select</option>
			          <option>Selection</option>
			        </select>
			      </li>
			    </ul>
			    <button type="submit">Filter</button>
			  </form>
			</div>
			
			<h2 class="filter-results">Showing filtered list of equipment which contain search term "Scanner"</h2>
			<!-- Filter ends here -->
		</g:if>
		</shiro:hasPermission>
		<!-- Template goes here -->
		
		<div class="main table">
			<g:render template="${template}"/>
			<div class="paginateButtons">
  			<g:if test="${entityCount != null}">
  				<g:paginate total="${entityCount}" params="${params}" action="${actionName}"/>
  			</g:if>
  		</div>
		</div>
		
		<g:if test="${entities.empty}">
			<div class="main"><g:message code="entity.list.empty.label" args="[entityName]"/></div>
		</g:if>
		
		<!-- End of template -->
		
		
	</div>
</div>
