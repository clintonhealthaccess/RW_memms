<tr class="tree-sign js_foldable ${line.cssClasses.join(' ')}">
	<td class="${line.href==null?'js_foldable-toggle':''} ${line.openByDefault?'toggled':''}">
		<g:if test="${line.href != null}">
			<span class="js_foldable-toggle" style="margin-left: ${margin}px;"> <a href="#">&zwnj;</a> </span>
			<span>
				<a class="js_budget-section-link" href="${line.href}">
					${line.displayName==null?nullText:line.displayName}
				</a>
			</span>
		</g:if>
		<g:else>
			<span style="margin-left: ${margin}px;">
				${line.displayName==null?nullText:line.displayName}
			</span>
		</g:else>
	</td>
	<g:each in="${columns}" var="column" status="i">
		<td>
			<g:render template="/tags/table/value" model="[value: line.getValueForColumn(i), type: line.getTypeForColumn(i)]"/>
		</td>
	</g:each>
</tr>
<tr class="sub-tree js_foldable-container ${line.openByDefault?'':'hidden'}">
	<td colspan="7" class="bucket">
		<g:if test="${line.lines.empty}">
			<div class="empty-line italic" style="margin-left: ${margin+20}px"><g:message code="table.tag.aggregate.line.empty"/></div>
		</g:if>
		<g:else>
		<table>
			<g:each in="${line.lines}" var="tableLine">
				<g:render template="/tags/table/${tableLine.template}" model="[line: tableLine, margin: margin+20]"/> 
			</g:each>
		</table>
		</g:else>
	</td>
</tr>