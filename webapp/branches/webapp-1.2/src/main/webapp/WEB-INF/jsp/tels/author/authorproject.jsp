<html>
<head>

<script type='text/javascript'>
var portalAuthorUrl = "${portalAuthorUrl}";
function loaded(){
	window.frames['authorfrm'].setPortalMode('${portalAuthorUrl}', '${curriculumBaseDir}', '${command}', '${projectId}');
};
</script>

</head>

<body style="margin:0;padding:0;" >
<iframe id="authorfrm" src="${vleAuthorUrl}" name="authorfrm" scrolling="auto" width="100%" height="100%" frameborder="0">
 [Iframes not enabled]
</iframe>
</body>
</html>