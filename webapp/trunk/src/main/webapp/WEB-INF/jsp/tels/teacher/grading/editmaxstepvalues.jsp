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
</script>

<div id="centeredDiv">

<%@ include file="headerteachergrading.jsp"%>

<%@ include file="L2grading_byvalue.jsp"%>

<div id="overviewHeaderGrading">Edit Maximum Values for Graded Steps</div>

<div id="gradeStepSelectionArea">

<div>
<c:set var="eCurnitmap" value="${curnitmap.ECurnitmap}" />

    <div id="gradeStepSelectedProject">${eCurnitmap.project.title}</div>

	<div id="selectAnotherLink"><a href="projectPickerGrading.html?gradeByType=step">Select Another Project</a></div>
	
	<div id="editValuesInstructions">
		<ul>
			<li>To change the maximum possible value for a step, enter a value (between 0-1000) in the <em>New Maximum Score</em> column.  Click the SAVE button when you're done making changes.</li>
			<li>Any Step with a score value of zero (0) will be automatically excluded when you're grading. This is a handy way to reduce your visual clutter when grading by Step or by Team.</li>
			<li>Each gradable step has just one score value.  If you're using a Note step with multiple sub-parts, 
			you can indicate the value of each Part to students by embedding a statement within the individual question prompts.		
			Ex: "(5 pts for Part 1)"
			<li>Note that Challenge Question values can't be edited here.  To change CQ values you must customize a project using the Authoring Tool.</li>
		</ul>
	</div>

<form:form method="post" action="editmaxstepvalues.html?runId=${run.id}" commandName="curnitmap" id="curnitmapform" >  				
	
	<table id="editValuesTable">
		<tr>
			<th>activity</th>
			<th>step</th>
			<th>current <br>maximum score</th>
			<th>NEW <br>maximum score</th>
		</tr>

	 <c:forEach var="someAct" varStatus="varAct" items="${eCurnitmap.project.activity}">
			<c:forEach var="someStep" varStatus="varStep" items="${someAct.step}">
				<c:if test="${someStep.type == 'Note' || someStep.type == 'Student Assessment'}">
				    <tr>
				    	<td>Activity ${someAct.number+1}: ${someAct.title}</td>
				        <td>Step ${someStep.number+1}: ${someStep.title} (${someStep.type})</td>
				        <td class="scoreStyle">${someStep.possibleScore}</td>
				        <td class="scoreStyle"><form:input id="newScoreBox" path="ECurnitmap.project.activity[${varAct.index}].step[${varStep.index}].possibleScore" /></td>
				    </tr>
				 </c:if>
			</c:forEach>
      </c:forEach>
      
      		<tr>
      			<td></td>
      			<td></td>
      			<td style="text-align:center;font-weight:bold;"><c:out value="${totalPossibleScore}" /></td>
      			<td></td>
      		</tr>
	</table>
	<div id="editValueSaveButton">
	  <input type="submit" value="Save New Maximum Scores" />
	</div>
</form:form>

</div>

<div>      <!--End of gradeStepSelectionArea -->

</div>      <!--End of Centered Div-->

</div>
</div>
</body>

</html>
