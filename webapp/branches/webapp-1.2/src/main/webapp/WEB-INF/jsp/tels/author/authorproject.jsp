<html>
<head>

<script type='text/javascript'>
function loaded(){
	window.frames['authorfrm'].setPortalMode('${portalAuthorUrl}', '${command}', '${projectId}');
};
</script>

</head>

<body>
<iframe id="authorfrm" src="${vleAuthorUrl}" onload="loaded();" name="authorfrm" scrolling="auto" width="100%" height="100%" frameborder="0">
 [Iframes not enabled]
</iframe>
</body>
</html>