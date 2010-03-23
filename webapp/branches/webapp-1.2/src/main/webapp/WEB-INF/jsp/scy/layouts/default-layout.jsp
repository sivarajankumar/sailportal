<%@ include file="../common-taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

	<link rel="stylesheet" type="text/css"
		  href="http://yui.yahooapis.com/2.5.1/build/reset-fonts-grids/reset-fonts-grids.css"/>

	<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css"/>

	<link rel="shortcut icon" href="/webapp/themes/tels/default/images/favicon_panda.ico" />
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dijit/themes/tundra/tundra.css" />
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dijit/themes/tundra/layout/Dialog.css" />
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>

	<script type="text/javascript">
        function postForm(form){
            var xhrArgs = {
                    form: dojo.byId(form),
                    handleAs: "text",
                    load: function(data) {
                        dojo.byId("response").innerHTML = "Form posted.";
                    },
                    error: function(error) {
                        
                        dojo.byId("response").innerHTML = "Form not posted.";
                    }
                }
                //Call the asynchronous xhrPost
                dojo.byId("response").innerHTML = "Form being sent..."
                var deferred = dojo.xhrPost(xhrArgs);
            }

        

        function loadDialog(url, title){
            var theDialog =  new dijit.Dialog({
                title: title,
                style: "width:500px;height:300px;",
                href: url
            });

            theDialog.show();
        }
        
		var djConfig = {
			isDebug: true,
			parseOnLoad: true,
			xdWaitSeconds: 5,
			noFirebugLite: true,
			cacheBust: new Date()
		};
		google.load("dojo", "1.4.0");
        google.setOnLoadCallback(startDojo);
        function startDojo(){
            dojo.require("dojo.parser");
            dojo.require("dijit.form.CheckBox");
            dojo.require("dijit.Dialog");
        }

	</script>
	<title><tiles:insertAttribute name="title" defaultValue=""/></title>
	<tiles:insertAttribute name="extrahead" defaultValue=""/>
</head>
<body class="<tiles:insertAttribute name="bodyclass" defaultValue=""/> tundra">
<div id="doc4" class="yui-t7">
	<div id="hd" role="banner">
		<div class="logo"></div>
		<div class="options">
			<tiles:insertAttribute name="options"/>
		</div>
		<div class="menubar-wrapper">
			<div class="border-left">
				<div class="border-right">
					<ul class="menu">
						<tiles:insertAttribute name="menu"/>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="bd" role="main">
		<div class="yui-g main">
			<div class="border-left">
				<div class="border-right">
					<div style="padding: 0.3em 1em 0.3em 1em;">
						<div class="content rounded">
							<tiles:insertAttribute name="main"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="ft" role="contentinfo" class="footer">
		<div class="border-left">
			<div class="border-right">
				<tiles:insertAttribute name="footer"/>
			</div>
		</div>
		<div class="bottom">
			<div class="border-left">
				<div class="border-right"></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
