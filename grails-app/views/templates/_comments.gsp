<%@ page import="org.chai.memms.util.Utils" %>
<ul class="comment-list">
	<g:each in="${order.comments.sort{a,b -> (a.writtenOn < b.writtenOn) ? 1 : -1}}" status="i" var="comment">
		<li>
			<div class="delete-comment-option">
				<img src="${resource(dir:'images',file:'spinner.gif')}" class="ajax-spinner"/>
				<a href="#" class="delete-comment" id="${comment.id}">X</a>
			</div>
			<div class="comment-written-by">${comment.writtenBy.firstname} ${comment.writtenBy.lastname}</div>
			<div class ="comment-written-on">${Utils.formatDateWithTime(comment?.writtenOn)}</div>
			<div class="comment-content">${comment.content}</div>
		</li>
	</g:each>
</ul>