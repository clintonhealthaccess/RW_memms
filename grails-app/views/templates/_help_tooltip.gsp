<g:set var="helpDescriptions" value="${descriptions != null && !descriptions.empty ? descriptions : names}" />
<img class="report-help tooltip" src="${resource(dir:'images',file:'/icons/info_tooltip.png')}" 
alt="Help" href="#" onclick="return false;" original-title="${helpDescriptions}" />