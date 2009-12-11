<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>

<%@ include file="yahooUIStyles.jsp"%>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<title><spring:message code="run.list" /></title>

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<script language="JavaScript">
	function popup(URL, title) 
  	{window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=640,height=480,left = 320,top = 240');}
</script>

<script type="text/javascript">

	if(navigator.appName != "Microsoft Internet Explorer") {
		loadingImage = new Image();
		loadingImage.src = "/webapp/themes/tels/default/images/rel_interstitial_loading.gif";
	}
	
    YAHOO.namespace("example.container");

    function init() {

        if (!YAHOO.example.container.wait) {

            // Initialize the temporary Panel to display while waiting for external content to load

            YAHOO.example.container.wait = 
                    new YAHOO.widget.Panel("wait",  
                                                    { width: "240px", 
                                                      fixedcenter: true, 
                                                      close: false, 
                                                      draggable: false, 
                                                      zindex:4,
                                                      modal: true,
                                                      visible: false
                                                    } 
                                                );

            //YAHOO.example.container.wait.setHeader("Loading, please wait...");
            YAHOO.example.container.wait.setBody("<table><tr align='center'>Loading, please wait...</tr><tr align='center'><img src=/webapp/themes/tels/default/images/rel_interstitial_loading.gif /></tr><table>");
            YAHOO.example.container.wait.render(document.body);

        }

        // Define the callback object for Connection Manager that will set the body of our content area when the content has loaded



        var callback = {
            success : function(o) {
                //content.innerHTML = o.responseText;
                //content.style.visibility = "visible";
                YAHOO.example.container.wait.hide();
            },
            failure : function(o) {
                //content.innerHTML = o.responseText;
                //content.style.visibility = "visible";
                //content.innerHTML = "CONNECTION FAILED!";
                YAHOO.example.container.wait.hide();
            }
        }
    
        // Show the Panel
        YAHOO.example.container.wait.show();
        
        // Connect to our data source and load the data
        //var conn = YAHOO.util.Connect.asyncRequest("GET", "assets/somedata.php?r=" + new Date().getTime(), callback);
    }

    YAHOO.util.Event.on("studentScoreSummary", "click", init);
    
    
		(function() {
   		 var tabView = new YAHOO.widget.TabView('tabSystem');})();
 </script>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
</head>


<body class="yui-skin-sam">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="centeredDiv">

<%@ include file="../headerteachersub.jsp"%>

<div id="navigationSubHeader2">My Project Runs & Access Codes<span id="navigationSubHeader1">management</span></div>

<iframe src ="projectruntabs.html" width="100%" height="1000px" frameborder="0" scrolling="yes">
<p>Your browser does not support iframes.</p>
</iframe>

</div>




</body>
</html>


