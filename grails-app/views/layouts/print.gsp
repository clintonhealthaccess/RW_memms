<!DOCTYPE html>
<html>
	<head>
		<title><g:layoutTitle /></title>
		<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
	
		<g:layoutHead />	
		<r:require module="print2"/>
		<r:layoutResources/>
	</head>
	<body class="body-print">
		<div id="content-print">
			<g:layoutBody />
			<div class=clear></div>
		</div>
	</body>
</html>
