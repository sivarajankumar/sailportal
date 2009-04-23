<html>
<head>
<title>Loading Sample PAS Project to Virtual Learning Environment</title>

<script src="http://yui.yahooapis.com/3.0.0pr2/build/yui/yui-min.js" type="text/javascript"></script>



<script type="text/javascript" src="http://tels-group.soe.berkeley.edu:16080/sail-web/vle/js/common/loadxmldoc.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/2.6.0/build/utilities/utilities.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/2.6.0/build/container/container-min.js"></script>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.6.0/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.6.0/build/container/assets/skins/sam/container.css" />

<link rel="stylesheet" type="text/css" href="http://uccpdev.berkeley.edu:10080/uccp-assets/vle/css/niftycube.css" />
<link rel="stylesheet" type="text/css" href="http://uccpdev.berkeley.edu:10080/uccp-assets/vle/css/navigation.css" />
<link rel="stylesheet" type="text/css" href="css/sdmenu.css" />

<script type="text/javascript" src="http://uccpdev.berkeley.edu:10080/uccp-assets/vle/js/visibility/VisibilityLogic.js"></script>
<script type="text/javascript" src="http://uccpdev.berkeley.edu:10080/uccp-assets/vle/js/visibility/OnlyShowSelectedNodes.js"></script>

<!-- Source file -->
<script src="http://yui.yahooapis.com/2.6.0/build/connection/connection-min.js"></script>
<!-- Combo-handled YUI CSS files: -->
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.6.0/build/container/assets/skins/sam/container.css">
<!-- Combo-handled YUI JS files: -->
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.6.0/build/yahoo-dom-event/yahoo-dom-event.js&2.6.0/build/animation/animation-min.js&2.6.0/build/connection/connection-min.js&2.6.0/build/dragdrop/dragdrop-min.js&2.6.0/build/container/container-min.js"></script>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.7.0/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.7.0/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/yahoo-dom-event/yahoo-dom-event.js"></script>

<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/connection/connection-min.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/animation/animation-min.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/2.7.0/build/container/container-min.js"></script>


	<script type="text/javascript">
		function topiframeOnLoad() {
			//var xmlString = "${xmlString}";
			var runId = "${runId}";
			var workgroupId = "${workgroup.id}";
			var contentUrl = "${contentUrl}";
			var userInfoUrl = "${userInfoUrl}";
			var getDataUrl = "${getDataUrl}";
			var contentBaseUrl = "${contentBaseUrl}";
			//alert(userInfoUrl);
			//var userInfoUrl = "http://localhost:8080/webapp/student/vle/studentdata.html?runId=${runId}&getUserInfo=true";
			//var userInfoUrl = "vle.html?runId=${runId}&getUserInfo=true";
			//window.frames["topifrm"].loadFromString(xmlString, runId, workgroupId);
			window.frames["topifrm"].load(contentUrl, userInfoUrl, getDataUrl, contentBaseUrl);
		}
		
		function foo() {
			//alert('foo!');
		}
		function foo2() {
			//alert('foo222!');
		}		
	</script>



<script>
/*
 * This is an example callback object with success
 * and failure members defined inline.  The argument
 * property demonstrates how to pass multiple values
 * to the callback as an array.
 */
var callback =
{
  success: function(o) { 
	  //alert(o.responseText);
	  runManager.setRunManager(o.responseText); 
  },
  failure: function(o) {},
  argument: ["a", "b", "c"]
}

var sUrl = "vle.html?getRunManager=true&runId=${runId}";

function RunManager() {
	this.pollInterval = 10000;
	this.isPaused = false;
	this.visibleNodes = [];   // only nodes that the student can see
	this.message = "";     // message from teacher
}

RunManager.prototype.setRunManager = function(xmlRunManagerTxt) {
	var xmlDoc = loadXMLDocFromString(xmlRunManagerTxt);
	var isPaused = xmlDoc.getElementsByTagName('isPaused')[0].firstChild.nodeValue;
	if (isPaused == "true") {
		this.isPaused = true;
	} else {
		this.isPaused = false;
	}

    // get message from teacher
    var messageElement = xmlDoc.getElementsByTagName('message')[0];
    //alert(messageElement.textContent);
    var messagePart;
    this.message = "";
    for (var i=0; i < messageElement.childNodes.length; i++) {
        if (messageElement.childNodes[i].nodeType == 1) {
            alert("1: " + messageElement.childNodes[i] + " , " + messageElement.childNodes[i].nodeName  + " , " + messageElement.childNodes[i].xml);
        	this.message += messageElement.childNodes[i].innerHTML;
        } else {
        //alert(messageElement.childNodes[i] + "," + messageElement.childNodes[i].nodeValue + messageElement.childNodes[i].nodeName);
    	this.message += messageElement.childNodes[i].nodeValue;
        }
    }
    
	
	var newArray = [];
	var visibleNodeElements = xmlDoc.getElementsByTagName('visiblenode');
	for (var i=0; i < visibleNodeElements.length; i++) {
		newArray.push(visibleNodeElements[i].firstChild.nodeValue);
	}

	// see if the newly-retrieved array of visible nodes are same as the ones
	// that was retrieved right before it.  If so, there's no need to update the
	// navigation panel. If not, update the nav panel and the node that the student sees.
	if (!this.visibleNodes.compare(newArray)) {
		this.visibleNodes = [];
		for (var i=0; i < visibleNodeElements.length; i++) {
			this.visibleNodes.push(visibleNodeElements[i].firstChild.nodeValue);
		}
		  if (runManager.visibleNodes.length > 0) {
		  		window.frames["topifrm"].vle.visibilityLogic = new VisibilityLogic(new OnlyShowSelectedNodes(window.frames["topifrm"].vle.project.rootNode, runManager.visibleNodes)); 
		  		window.frames["topifrm"].vle.navigationPanel.render();
		  		if (window.frames["topifrm"].vle.getCurrentNode().id != runManager.visibleNodes[0]) {
		  			window.frames["topifrm"].vle.renderNode(runManager.visibleNodes[0]);
		  		}
		  }
	} 

	if (runManager.isPaused) { 
		  lockscreen(); 
		  if (this.message != "") {
	          YAHOO.example.container.wait.setBody("<table><tr align='center'>Teacher has locked your screen. Please talk to your teacher.</tr><tr align='center'></tr><table><b>Your teacher says:</b><br/>" + this.message);		  
		  }
	} else {
		  unlockscreen();
	} 
	
}

Array.prototype.compare = function(testArr) {
    if (this.length != testArr.length) return false;
    for (var i = 0; i < testArr.length; i++) {
        if (this[i].compare) { 
            if (!this[i].compare(testArr[i])) return false;
        }
        if (this[i] !== testArr[i]) return false;
    }
    return true;
}

function poll() {
	var transaction = YAHOO.util.Connect.asyncRequest('GET', sUrl, callback, null);
}

var runManager = new RunManager();
//setInterval("poll();", runManager.pollInterval);
</script>

<script type="text/javascript">
	
	//preload image if browser is not IE because animated gif will just freeze if user is using IE
	if(navigator.appName != "Microsoft Internet Explorer") {
		loadingImage = new Image();
		loadingImage.src = "/webapp/themes/tels/default/images/rel_interstitial_loading.gif";
	}
	
    function lockscreen() {
    	YAHOO.namespace("example.container");

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

            YAHOO.example.container.wait.setHeader("Locked Screen");
            YAHOO.example.container.wait.setBody("<table><tr align='center'>Teacher has locked your screen. Please talk to your teacher.</tr><tr align='center'></tr><table>");
            YAHOO.example.container.wait.render(document.body);

        }
    	// Show the Panel
        YAHOO.example.container.wait.show();
        YAHOO.example.container.wait.cfg.setProperty("visible", true);
        
    }

    function unlockscreen() {
    	YAHOO.namespace("example.container");

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

            YAHOO.example.container.wait.setHeader("Loading, please wait...");
            YAHOO.example.container.wait.setBody("<table><tr align='center'>Teacher has locked your screen. Please talk to your teacher.</tr><tr align='center'></tr><table>");
            YAHOO.example.container.wait.render(document.body);

        }
    	YAHOO.example.container.wait.hide();
    }

</script>



</head>
<body class=" yui-skin-sam">
<div id="wait"></div> 
<iframe id="topifrm" src="${vleurl}" onload="topiframeOnLoad();" name="topifrm" scrolling="auto"
 width="100%" height="100%" frameborder="0">
 [Content for browsers that don't support iframes goes here.]
</iframe>

</body>
</html>