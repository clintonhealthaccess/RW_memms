<%@ page import java.net.URLEncoder %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <title><g:message code="default.report.label" /></title>
  	<r:require module="reports"/>

    <g:googleWebFontsApi />
    <g:googleAjaxApi />

</head>
  <body>
    <g:render template="${template}"/>
  </body>
</html>
