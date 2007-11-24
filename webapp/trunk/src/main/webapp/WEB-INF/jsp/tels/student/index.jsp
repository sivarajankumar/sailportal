<%@ include file="../include.jsp"%>
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

<!-- $Id$ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../<spring:theme code="stylesheet"/>" media="screen"
	rel="stylesheet" type="text/css" />
<title><spring:message code="application.title" /></title>
<%@ include file="styles.jsp"%>
<style>
.yui-panel-container select {
              _visibility: inherit;
}
</style>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
<script language="JavaScript">

function popup(URL) {
  window.open(URL, 'Select Team', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=300,height=300,left = 570,top = 300');
}
</script>
<script>
function init() {
	    //create logger
	  //  var myContainer = document.body.appendChild(document.createElement("div")); 
	//	var myLogReader = new YAHOO.widget.LogReader(myContainer); 
		
		function runObject(id){
				this.runId=id;
		}
	
		var oRun =new runObject(null);
		
		
	//add project dialog ----------------------------------

    // Define various event handlers for Dialog

	var handleCancel = function() {
		this.cancel();
		document.getElementById('addProjectFrame').src=' ';
		//reload the page
		//window.location.reload();
	};

	// Instantiate the Dialog
	var addProjectDialog = new YAHOO.widget.Dialog("addProjectDialog", 
																{ width : "500px",
																//  height : "70%",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  { text:"Done", handler:handleCancel,isDefault:true } ]
																 } );
	
	
    addProjectDialog.render();
    
    var btns2 = YAHOO.util.Dom.getElementsByClassName("addprojectLink", "a");
         
    YAHOO.util.Event.on(btns2, "click", function(e, panel) {
                	YAHOO.log('RUNG id ' + this.id);
                	document.getElementById('addProjectFrame').src='addproject.html' 
                	addProjectDialog.show();
    }, addProjectDialog);
    
     //change password dialog  -----------------
      
    var handleClosePassword = function() {
		this.cancel();
		document.getElementById('changePasswordFrame').src=' ';
		//reload the page
		//window.location.reload();
	};
	
	// Instantiate the Dialog
	var changePasswordDialog = new YAHOO.widget.Dialog("changePasswordDialog", 
																{ width : "500px",
																//  height : "70%",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  { text:"Done", handler:handleClosePassword,isDefault:true } ]
																 } );
	



	
	// Render the Dialog
	changePasswordDialog.render();

    
    var btns2 = YAHOO.util.Dom.getElementsByClassName("changepasswordLink", "a");
				
	YAHOO.log('btns2 ' + btns2);
         
    YAHOO.util.Event.on(btns2, "click", function(e, panel) {
                	YAHOO.log('RUNG id ' + this.id);
                	document.getElementById('changePasswordFrame').src='changepassword.html' 
                	changePasswordDialog.show();
    }, changePasswordDialog);
    
    //run Project dialog -----------------------
    
    var handleCloseRun = function() {
		this.cancel();
		document.getElementById('runProjectFrame').src=' ';
		//reload the page
		//window.location.reload();
	};
	
	// Instantiate the Dialog
	var runProjectDialog = new YAHOO.widget.Dialog("runProjectDialog", 
																{ width : "500px",
																//  height : "70%",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  { text:"Done", handler:handleCloseRun,isDefault:true } ]
																 } );
	



	
	// Render the Dialog
	runProjectDialog.render();

    
    var btns2 = YAHOO.util.Dom.getElementsByClassName("runProjectLink", "a");
				
	YAHOO.log('btns2 ' + btns2);
         
    YAHOO.util.Event.on(btns2, "click", function(e, panel) {
                	YAHOO.log('RUNG id ' + this.id);
                	document.getElementById('runProjectFrame').src='selectteam.html?runId=' + this.id;
                	runProjectDialog.show();
    }, runProjectDialog);
    
   
        
}

YAHOO.util.Event.onDOMReady(init);
</script>

</head>
<%@ include file="header.jsp"%>

<body class="yui-skin-sam">



<div id="columns">
<h3 style="color: rgb(100, 0, 0);"><spring:message
	code="student.project-menu" /></h3>
<div id="xsnazzy" class="bgcolorSeaBlue"
	style="width: 220px; position: relative; top: 20px;"><b
	class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b
	class="xb4"></b></b>
<div class="border1">
<h3><spring:message code="student.user" /><spring:message
	code="colon" /> <authz:authentication operation="username" /></h3>
<h5><spring:message code="msg" /><spring:message code="colon" />
<spring:message code="good.morning" /><authz:authentication
	operation="username" /></h5>

<div><spring:message code="teacher.currentlogin" /> <%=new java.util.Date().getHours()%>
<spring:message code="colon" /> <%=new java.util.Date().getMinutes()%>
<br />
<spring:message code="teacher.lastlogin" /> <%=new java.util.Date()%> <br />
<spring:message code="wise.language" /> <spring:message code="colon" />
English <br />
</div>

<h3><spring:message code="wise.account-options" /></h3>
<ul class="none">
	<li>
	
	<!--  
		this is the yahoo button that i use to test
	<span id="pushbutton" class="yui-button yui-push-button"><em
		class="first-child">
	<button type="submit" name="pushbutton" class="addProject"
		id='${run.id}'>Add Project</button>
	
	</em> </span>
	-->
	 <a href="#"
		onmouseover="swapImage('addproject','../<spring:theme code="student_add_project_roll" />');"
		onmouseout="swapImage('addproject','../<spring:theme code="student_add_project" />');"
		class="addprojectLink" > <img
		 src="../<spring:theme code="student_add_project" />"
		style="border: 0px;" /> </a></li>
		
	<li visibility="hidden"><a href="#"
		onmouseover="swapImage('studentchangepwd','../<spring:theme code="student_change_password_roll" />');"
		onmouseout="swapImage('studentchangepwd','../<spring:theme code="student_change_password" />');"
		class="changepasswordLink"> <img
		id="studentchangepwd"
		src="../<spring:theme code="student_change_password" />"
		style="border: 0px;" /> </a></li>
		
		
	<li><a href="#"
		onmouseover="swapImage('studentchangelang','../<spring:theme code="student_change_lang_roll" />');"
		onmouseout="swapImage('studentchangelang','../<spring:theme code="student_change_lang" />');"
		onclick="javascript:alert('This page is not available yet')"> <img
		id="studentchangelang"
		src="../<spring:theme code="student_change_lang" />"
		style="border: 0px;" /> </a></li>
	<li><a href="#"
		onmouseover="swapImage('studentsignout','../<spring:theme code="sign_out_roll" />');"
		onmouseout="swapImage('studentsignout','../<spring:theme code="sign_out" />');"
		onclick="javascript:alert('This page is not available yet')"> <img
		id="studentsignout" src="../<spring:theme code="sign_out" />"
		style="border: 0px;" /> </a></li>
</ul>
<!-- comment --></div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b
	class="xb2"></b><b class="xb1"></b></b></div>


<div
	style="width: 660px; position: relative; bottom: 385px; left: 250px;">

<div>
<h1>Current Runs</h1>
<table border="1">
	<thead>
		<tr>
			<th><spring:message code="run.name.heading" /></th>
			<th>Teacher name</th>
			<th>Team members</th>
			<th><spring:message code="run.options.heading" /></th>
		</tr>
	</thead>
	<c:forEach var="studentRunInfo" items="${current_run_list}">
		<tr>
			<td>${studentRunInfo.run.sdsOffering.name}</td>
			<td><c:forEach var="owner" items="${studentRunInfo.run.owners}">${owner.userDetails.username}</c:forEach></td>
			<c:choose>
			<c:when test="${studentRunInfo.workgroup == null}" >
			  <td>Not in a team yet</td>
			  <td>
			  <a href="#"
				  onmouseover="swapImage('runproject','../<spring:theme code="run_project_roll" />');"
				  onmouseout="swapImage('runproject','../<spring:theme code="run_project" />');"
				  id='${studentRunInfo.run.id}' class="runProjectLink">
			  <img id="runproject" src="../<spring:theme code="run_project" />"
			  	  style="border: 0px;" /> </a>
			  <br />
 			  <a href="javascript:alert('please talk to your teacher');">Change Period Or Team</a><br />
			  <a href="javascript:alert('please talk to your teacher');">Report a Problem</a><br />
			  <a href="javascript:alert('please talk to your teacher');">Archive this Project</a>
			  </td>
		    </c:when>
		    <c:otherwise>
		      <td><c:forEach var="member" items="${studentRunInfo.workgroup.members}">${member.userDetails.firstname} ${member.userDetails.lastname} (${member.userDetails.username}), </c:forEach></td>
		      <td>
		      <a href="${studentRunInfo.startProjectUrl}"
				  onmouseover="swapImage('runproject','../<spring:theme code="run_project_roll" />');"
				  onmouseout="swapImage('runproject','../<spring:theme code="run_project" />');"
				  id='${studentRunInfo.run.id}' class="">
			  <img id="runproject" src="../<spring:theme code="run_project" />"
			  	  style="border: 0px;" /> </a>
			  <br />
			  <a href="javascript:alert('please talk to your teacher');">Change Period Or Team</a><br />
			  <a href="javascript:alert('please talk to your teacher');">Report a Problem</a><br />
			  <a href="javascript:alert('please talk to your teacher');">Archive this Project</a>
   		      </td>
		    </c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</table>

<h1>Archived Runs</h1>
<table border="1">
	<thead>
		<tr>
			<th><spring:message code="run.name.heading" /></th>
			<th>Teacher name</th>
			<th><spring:message code="run.options.heading" /></th>
		</tr>
	</thead>
	<c:forEach var="studentRunInfo" items="${ended_run_list}">
		<tr>
			<td>${studentRunInfo.run.sdsOffering.name}</td>
			<td><c:forEach var="owner" items="${studentRunInfo.run.owners}">${owner.userDetails.username}</c:forEach></td>
			<td>You cannot Run an "Archived Run". You will be able to view
			your work on this archived run later.</td>
		</tr>
	</c:forEach>
</table>
</div>



<!-- this creates the add project dialog with iframe -->
<div id="addProjectDialog">
<div class="hd">Add Project</div>
<div class="bd">
<!-- <h1 align="left"><spring:message code="teacher.add-project" /></h1> -->
<h3 align="left"><spring:message code="teacher.add-project-info" /></h3>


<iframe id="addProjectFrame" src=" " width="100%" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>

<!-- creates change passwoerd -->
<div id="changePasswordDialog">
<div class="hd">Change Your Password</div>
<div class="bd">


<iframe id="changePasswordFrame" src=" " width="100%" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>

<!-- this creates the select team dialog with iframe -->
<div id="runProjectDialog">
<div class="hd">Select a Team</div>
<div class="bd" align="left">

<iframe id="runProjectFrame" src=" " width="100%" height="200px" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>



<!-- logger -->
<!--  
<div id="myLogger"></div>
<script type="text/javascript">
var myLogReader = new YAHOO.widget.LogReader("myLogger");
</script></div>
-->
</body>
</html>
