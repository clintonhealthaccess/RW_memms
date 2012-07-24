<html>
	<head>
	</head>
	<body>
	<h2>A list of users</h2>
	<p>There are <%= users.count%> users</p>
	<ul>
		<g:each var="user" in="${users}">
		    <li>Username: ${user.username}</li>
		    <li>Persmissions: ${user.permissionString}</li>
		</g:each>
		</ul>
	</body>
</html>