<div class="entity-form-container togglable">
	<div class="entity-form-header">
		<h3 class="title"><g:message code="enum.label"/></h3>
		<g:locales/>
		<div class="clear"></div>
	</div>
	<g:form url="[controller:'enum', action:'save', params:[targetURI: targetURI]]" useToken="true">
		<g:i18nInput name="names" bean="${enumeration}" value="${enumeration?.names}" label="${message(code:'entity.name.label')}" field="names" />
		
		<g:i18nTextarea name="descriptions" bean="${enumeration}" value="${enumeration.descriptions}" label="${message(code:'entity.description.label')}" field="descriptions" height="150"  width="300" maxHeight="150" />
		
		<g:if test="${enumeration.id != null}">		
			<p class="red"><g:message code="enum.code.changed.warning"/></p>
		</g:if>
		<g:input name="code" label="${message(code:'entity.code.label')}" bean="${enumeration}" field="code" />
	
		<table>
			<g:each in="${enumeration.enumOptions}" status="i" var="option">
				<tr class="white-box">
					<td>
						<g:render template="/entity/data/enumOption" model="[option: option]" />
					</td>
				</tr>
			</g:each>
		</table>
			
		<g:if test="${enumeration.id != null}">
			<input type="hidden" name="id" value="${enumeration.id}"></input>
		</g:if>
		
		<div class="row">
			<button type="submit"><g:message code="default.button.save.label"/></button>&nbsp;&nbsp;
			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
		</div>
	</g:form>
	<div class="clear"></div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		getRichTextContent();
	});		
</script>