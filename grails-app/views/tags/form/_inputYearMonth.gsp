<div class="row ${hasErrors(bean:bean,field:field,'errors')}">
	<label>${message(code:'entity.expectedLifeTime.label')}</label>
	<div>
	<label for="${year}">${labelYear}</label>
	<input type="text" class="idle-field ${dateClass}" name="${name +'_years'}" value="${fieldValue(bean:bean,field:name +'_years')}" ${active} ${readonly?'readonly="readonly"':''}></input>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${name +'_years'}" /></div>
	</div>
	<div>
	<label for="${month}">${labelMonth}</label>
	<input type="text" class="idle-field ${dateClass}" name="${name +'_months'}" value="${fieldValue(bean:bean,field:name +'_months')}" ${active} ${readonly?'readonly="readonly"':''}></input>
	<div class="error-list"><g:renderErrors bean="${bean}" field="${name +'_months'}" /></div>
	</div>
</div>
