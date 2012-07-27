<g:if test="${fullText == smallText}">${fullText}</g:if>
<g:else>${smallText}<a href="#" onclick="return false;" title="${fullText}" class="tooltip">...</a></g:else>