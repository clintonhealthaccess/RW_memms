<tr>
	<td>
		<span style="margin-left: ${margin}px;">
			${line.displayName==null?nullText:line.displayName}
		</span>
	</td>
	<g:each in="${columns}" var="column" status="i">
		<td>
			<g:render template="/tags/table/value" model="[value: line.getValueForColumn(i), type: line.getTypeForColumn(i)]"/>
		</td>
	</g:each>
</tr>