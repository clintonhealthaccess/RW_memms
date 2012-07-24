<%@page import="org.chai.kevin.util.Utils"%>

<ul class="push-20 tab-navigation horizontal">
	<g:each in="${periods}" var="period" status="periodIndex">
		<li>
			<a href="${data==null?'#':createLink(controller:'data', action:'dataElementValueList', params:[data:data.id, period:period.id])}" class="listing-link ${!period.equals(selectedPeriod)?'':'selected'}">
				${Utils.formatDate(period.startDate)} to ${Utils.formatDate(period.endDate)}
			</a>
		</li>
	</g:each>
</ul>