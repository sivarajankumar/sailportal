<%@ include file="include.jsp"%>
<!-- 
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />  
 
<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../.././javascript/tels/prototype.js"></script>
<script type="text/javascript" src="../.././javascript/tels/effects.js"></script>

<%@ include file="./styles.jsp"%>

<link href="../../<spring:theme code="yui-fonts-min-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="yui-container-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teachergradingstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

</head>

<body class="yui-skin-sam">

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

	<c:forEach var="someAct" varStatus="varAct" items="${curnitMap.project.activity}">
			<c:forEach var="someStep" varStatus="varStep" items="${someAct.step}">
				YAHOO.util.Event.on("gradeAct${someAct.number}Step${someStep.number}", "click", init);
			</c:forEach>
	</c:forEach>


	function topiframeOnLoad() {
	//var xmlString = "${xmlString}";
	var runId = "${runId}";
	var workgroupId = "${workgroup.id}";
	var contentUrl = "${contentUrl}";
	var userInfoUrl = "${userInfoUrl}";
	var getDataUrl = "${getDataUrl}";
	var contentBaseUrl = "${contentBaseUrl}";
	var getAnnotationsUrl = "${getAnnotationsUrl}";
	var postAnnotationsUrl = "${postAnnotationsUrl}";

	var getFlagsUrl = "${getFlagsUrl}";
	var postFlagsUrl = "${postFlagsUrl}";
	
	//alert(userInfoUrl);
	//var userInfoUrl = "http://localhost:8080/webapp/student/vle/studentdata.html?runId=${runId}&getUserInfo=true";
	//var userInfoUrl = "vle.html?runId=${runId}&getUserInfo=true";
	//window.frames["topifrm"].loadFromString(xmlString, runId, workgroupId);
	window.frames["topifrm"].load(contentUrl, userInfoUrl, getDataUrl, contentBaseUrl, getAnnotationsUrl, postAnnotationsUrl, runId, getFlagsUrl, postFlagsUrl);
}
	
	function foo() {
		//alert('foo!');
	}
	function foo2() {
		//alert('foo222!');
	}		
</script>

<div id="centeredDiv">

<%@ include file="headerteachergrading.jsp"%>

<%@ include file="L2grading_bystep.jsp"%>

<div id="overviewHeaderGrading"><spring:message code="teacher.gradebystep.1"/></div>

<div id="gradeStepSelectionArea">

<!--  BEGIN: for projects that have curnitmap -->
<c:if test="${fn:length(curnitMap) > 0}">
<div>
	<div id="gradeStepSelectedProject">${curnitMap.project.title}</div>
	<table>
	<tr>
        <td><div id="selectAnotherLink"><a href="projectPickerGrading.html?gradeByType=step"><spring:message code="teacher.gradebystep.2"/></a></div><td>
        <td><div id="gradeStepInstructions"><spring:message code="teacher.gradebystep.3"/></div></td>
	</tr>
	</table>

	 <c:forEach var="someAct" varStatus="varAct" items="${curnitMap.project.activity}">
		<div id="stepTitle">Activity ${someAct.number+1}: ${someAct.title}</div>  
		<ul id="stepSelectionList"> 
			<c:forEach var="someStep" varStatus="varStep" items="${someAct.step}">
				<c:if test="${someStep.type == 'Note'}"><li><a href="gradingtool.html?runId=${runId}&podUUID=${someStep.podUUID}&tab=0" id="gradeAct${someAct.number}Step${someStep.number}">Step  ${someStep.number+1}: ${someStep.title}</a> <span id="stepTypeStyle"> (${someStep.type})</span> <span id="stepGradingNotification"> X <spring:message code="teacher.gradebystep.4"/></span></li></c:if>
				<c:if test="${someStep.type == 'Student Assessment'}"><li><a href="gradingtool.html?runId=${runId}&podUUID=${someStep.podUUID}&tab=0" id="gradeAct${someAct.number}Step${someStep.number}">Step  ${someStep.number+1}: ${someStep.title}</a></li></c:if>
			</c:forEach>
		</ul>
    </c:forEach>

</div>
</c:if>
<!--  END: for projects that have curnitmap -->

<!--  BEGIN: for LD-inspired Projects that don't have curnitmap -->
<c:if test="${fn:length(gradeByStepUrl) > 0}">
		<div>
		<div id="gradeStepSelectedProject">${curnitMap.project.title}</div>
		<table>
				<tr>
						<td>
						<div id="selectAnotherLink"><a href="projectPickerGrading.html?gradeByType=step"><spring:message
								code="teacher.gradebystep.2" /></a></div>
						</td>
						<td>
						<div id="gradeStepInstructions"><spring:message code="teacher.gradebystep.3" /></div>
						</td>
				</tr>
		</table>

		<iframe id="topifrm" src="${gradeByStepUrl}" onload="topiframeOnLoad();" name="topifrm" scrolling="auto" width="100%"
				height="100%" frameborder="0"> [Content for browsers that don't support iframes goes here.] </iframe>

		<h3>gradebystepurl: ${gradeByStepUrl}</h3>
		<h3>contentUrl: ${contentUrl}</h3>
		</div>
</c:if>
<!--  END: for LD-inspired Projects that don't have curnitmap -->


<div>      <!--End of gradeStepSelectionArea -->

</div>      <!--End of Centered Div-->

</div>
</div>
</body>

</html>
