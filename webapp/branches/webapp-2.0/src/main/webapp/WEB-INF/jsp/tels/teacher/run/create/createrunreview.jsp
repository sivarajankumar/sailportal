<%@ include file="include.jsp"%>
<!--
  * Copyright (c) 2006 Encore Research Group, University of Toronto
  * 
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 2.1 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id: setupRun3.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%@ include file="../../grading/styles.jsp"%>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-four" /></title>

<!-- SuperFish drop-down menu from http://www.electrictoolbox.com/jquery-superfish-menus-plugin/  -->

<link rel="stylesheet" type="text/css" href="../../themes/tels/default/styles/teacher/superfish.css" media="screen">
<script type="text/javascript" src="../../javascript/tels/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="../../javascript/tels/superfish.js"></script>

<script type="text/javascript">
    
            // initialise plugins
            jQuery(function(){
                jQuery('ul.sf-menu').superfish();
            });

        	//preload image if browser is not IE because animated gif will just freeze if user is using IE
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
</script>

</head>
<body class=" yui-skin-sam">

<div id="centeredDiv">

<%@ include file="../../headerteacher.jsp"%> 

<div id="navigationSubHeader2">Project Run Setup<span id="navigationSubHeader1">projects</span></div> 

<h1 id="titleBarSetUpRun" class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1>

<div id="reviewRunBox">

	<div id="stepNumber"><spring:message code="teacher.run.setup.32"/><span class="blueText">&nbsp;&nbsp;Review the Project Content and Learning Goals</span></div>

	<h6 style="color:red;font-size:90%;margin:15px 0"><spring:message code="teacher.view-lesson-plan" htmlEscape="true" /></h6>

	<ol>
	<li><h5>Please <a href="#" onclick="javascript:alert('Lesson Plan not available yet')"><spring:message code="teacher.run.setup.35"/></a>
	&nbsp;<spring:message code="teacher.run.setup.36"/></h5></li>

	<li><h5>We highly recommend that you 
			<a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${projectId}"/></c:url>">
			preview the project</a> before running it. 
			Previewing a project allows you to walk through the learning experience from a student's perspective. </h5></li>
		
	<li><h5>First time carrying out a WISE4 Project Run?  Click the <em>HELP</em> button above for more information about running projects and using the WISE4 tools. 
This help area includes tips on setting up your classroom computers, having students register, managing student groups, grading student work, and more.</h5></li>
	</ol>

	<h5>To complete the creation of your Project Run click <em>DONE</em> below.</h5>
</div>

<form method="post" class="center">
<input type="submit" name="_target3" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" id="submit_form" name="_finish" value="<spring:message code="navigate.done" />" />
</form>

<div>

</body>
</html>