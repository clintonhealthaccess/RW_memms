<table class="nested push-top-10 ${table.cssClasses.join(' ')}">
	<thead>
		<tr>
			<th>${table.caption}</th>
			<g:each in="${table.columns}" var="column">
				<th>${column}</th>
			</g:each>
		</tr>
	</thead>
	<tbody>
		<g:each in="${table.tableLine.lines}" var="line">
			<g:render template="/tags/table/${line.getTemplate()}" model="[line: line, columns: table.columns, margin: 0, nullText: nullText]"/>
		</g:each>
		<g:if test="${table.displayTotal}">
			<tr class="total">
				<td><g:message code="planning.budget.table.total"/>:</td>
				<g:each in="${table.columns}" var="column" status="i">
					<g:set value="${table.tableLine.getValueForColumn(i)}" var="value"/>
					
					<td class="${table.tableLine.getValueForColumn(i).numberValue<0?'red':''}">
						<g:render template="/tags/table/value" model="[value: table.tableLine.getValueForColumn(i), type: table.tableLine.getTypeForColumn(i)]"/>
					</td>
				</g:each>
			</tr>
		</g:if>
	</tbody>
</table>