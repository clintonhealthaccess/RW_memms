<div class="entity-list">
	<div>
		<div class="heading1-bar">
	     	<h1><g:message code="default.list.label" args="[entityName]" /></h1>
	     	<g:if test="${!search}">
		     	<span class="right">
		     	<shiro:hasPermission permission="${controllerName}:create">
					<g:if test="${!addTemplate}">
		  				<a href="${createLinkWithTargetURI(controller: controllerName, action:'create')+(request.queryString==null?'':'&'+request.queryString)}" class="next medium left push-r">
		  					<g:message code="default.new.label" args="[entityName]"/>
		  				</a>
		  			</g:if>
		  			<g:else>
		  				<g:render template="${addTemplate}"/>
		  			</g:else>
		  			
		  			</shiro:hasPermission>
		  			
		  			<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('importer')}">
	  				&nbsp;
		  				<g:if test="${entityClass != null}">
			  				<a href="${createLinkWithTargetURI(controller: 'entityImporter', action:'importer', params:[entityClass: entityClass.name])+(request.queryString==null?'':'&'+request.queryString)}" class="next medium gray left import">
			  					<g:message code="default.import.label" />
			  				</a>
		  				</g:if>
	  				</g:if>
		  			<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('export')}">
		  			&nbsp;
			  			<a href="${createLinkWithTargetURI(controller: controllerName, action:'exporter')+(request.queryString==null?'':'&'+request.queryString)}" class="next medium gray left export">
		  					<g:message code="default.export.label" />
		  				</a>
	  				</g:if>

	  				<g:if test="${grailsApplication.getArtefactByLogicalPropertyName('Controller', controllerName).hasProperty('search')}">
        				<g:searchBox action="search"/>
        			</g:if>
		     	</span>
	     	</g:if>
		</div>
		
		<!-- Filter starts here-->
		<div class="filters main">
		  <h2>Filter inventory<a href="#" class="right"><img src="${resource(dir:'images/icons',file:'icon_close_flash.png')}" alt="Section"/></a></h2>
		  <g:form url="[controller:'equipment', action:'filter', params:[targetURI: targetURI]]" useToken="false" class="filters-box">
				<ul>
					<%--<li><g:selectFromList name="dataLocation"
							label="${message(code:'inventory.datalocation.label')}" bean="${}"
							field="dataLocation" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'location', action:'getAjaxData', params:[class: 'DataLocation'])}"
							from="${dataLocations}" value="${dataLocations.collect{it.id}}"
							values="${dataLocations.collect{it.names}}" /></li>
							
					--%><li><g:selectFromList name="equipmentType"
							label="${message(code:'equipment.type.label')}" bean="${}"
							field="type" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'EquipmentType', action:'getAjaxData', params:[class: 'EquipmentType'])}"
							from="${equipmentType}" value="${equipmentType.collect{it.id}}"
							values="${equipmentType.collect{it.names}}" /></li>
							
					<li><g:selectFromList name="manufacturer"
							label="${message(code:'provider.manufacture.label')}" bean="${}"
							field="MANUFACTURE" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'Provider', action:'getAjaxData', params:[class: 'Provider',type:'MANUFACTURE'])}"
							
							from="${dataLocations}" value="${manufacturers.collect{it.id}}"
							values="${manufacturers.collect{it.contact.contactName}}" /></li>
							
					<li><g:selectFromList name="supplier"
							label="${message(code:'provider.supplier.label')}" bean="${}"
							field="SUPPLIER" optionKey="id" multiple="false"
							ajaxLink="${createLink(controller:'Provider', action:'getAjaxData', params:[class: 'Provider',type:'SUPPLIER'])}"
							
							from="${suppliers}" value="${suppliers.collect{it.contact.contactName}}"
							values="${suppliers.collect{it.contact.contactName}}" /></li>
					<li>
					<li><label>Obsolete</label> <select>
							<option>Please select</option>
							<option>Selection</option>
					</select></li>
					<li><label>Status</label> <select>
							<option>Please select</option>
							<option>Selection</option>
					</select></li>
					<label>Donated</label>
					<select>
						<option>Please select</option>
						<option>Selection</option>
					</select>
					</li>
				</ul>
				<button type="submit">Filter</button>
				<input type="hidden" name="location" value="${dataLocation.id}"/>
		  </g:form>
		</div>
		
		<h2 class="filter-results">Showing filtered list of equipment which contain search term "Scanner"</h2>
		<!-- Filter ends here -->

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
