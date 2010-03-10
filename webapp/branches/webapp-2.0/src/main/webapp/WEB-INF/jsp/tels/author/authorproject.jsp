<html>
<head>

<script type='text/javascript'><!--
var portalAuthorUrl = "${portalAuthorUrl}";

function notifyFatal(type,args,obj){
	window.location = '/webapp/errors/outsideerror.html?msg=' + encodeURIComponent(args[0]);
}

function loaded(){
	window.frames['authorfrm'].eventManager.subscribe('fatalError', notifyFatal);
	window.frames['authorfrm'].eventManager.fire('portalMode', ["${portalAuthorUrl}", "${curriculumBaseDir}", "${command}", "${projectId}"]);
};

function escapeQuotes(str){
	if(str){
		console.warn(str);
		console.log(escape(str));
	} else {
		return null;
	};
};
--></script>

</head>

<body style="margin:0;padding:0;" >
<iframe id="authorfrm" src="${vleAuthorUrl}" name="authorfrm" scrolling="auto" width="100%" height="100%" frameborder="0">
 [Iframes not enabled]
</iframe>
</body>
</html>