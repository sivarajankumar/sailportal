<html>
<head>
<title>Loading Sample PAS Project to Virtual Learning Environment</title>

	<script type="text/javascript">
		function scriptsLoaded(workgroupId) {
			var vleConfigUrl = "${vleConfigUrl}";
			window.frames["topifrm"].initializeVLEFromVLEConfig(vleConfigUrl);
		}
	</script>

<!--The following style nixes an annoying white margin border added by default to the YUI class element.-->
<style type="text/css">
.yui-skin-sam {	margin:0px;}
</style>


</head>
<body class="yui-skin-sam">
<div id="wait"></div> 
<iframe id="topifrm" src="${vleurl}" onload="topiframeOnLoad();" name="topifrm" scrolling="auto"
 width="100%" height="100%" frameborder="0">
 [Content for browsers that don't support iframes goes here.]
</iframe>

</body>
</html>