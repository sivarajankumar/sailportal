<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="studenthomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />

	
<title><spring:message code="application.title" /></title>

<%@ include file="styles.jsp"%>

<script src=".././javascript/tels/general.js" type="text/javascript" > </script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" > </script>
<script src=".././javascript/tels/effects.js" type="text/javascript" > </script>

<style>
.yui-panel-container select {
              _visibility: inherit;
}
</style>

<script language="JavaScript">
	function popup(URL) {
  	window.open(URL, 'SelectTeam', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=650,height=500,left = 570,top = 300');}
</script>

<script>

	function init() {
	    //create logger
	  //  var myContainer = document.body.appendChild(document.createElement("div")); 
	//	var myLogReader = new YAHOO.widget.LogReader(myContainer);
	 
   		var tabView = new YAHOO.widget.TabView('tabSystem');

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
																{ width : "700px",
																//  height : "70%",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  {text:"Close", handler:handleCancel,isDefault:true } ]
																 } );
	
	
    addProjectDialog.render();
    
    var btns2 = YAHOO.util.Dom.getElementsByClassName("addprojectLink", "a");
         
    YAHOO.util.Event.on(btns2, "click", function(e, panel) {
                	YAHOO.log('RUNG id ' + this.id);
                	document.getElementById('addProjectFrame').src='addproject.html' 
                	addProjectDialog.show();
    }, addProjectDialog);
    
    
    	//Change Period or Team PopUp dialog ----------------------------------

    // Define various event handlers for Dialog

	var handleCancel = function() {
		this.cancel();
		document.getElementById('changePeriodTeamFrame').src=' ';
		//reload the page
		//window.location.reload();
	};

	// Instantiate the Dialog
	var changePeriodTeamDialog = new YAHOO.widget.Dialog("changePeriodTeamDialog", 
																{ width : "700px",
																//  height : "70%",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  {text:"Close", handler:handleCancel,isDefault:true } ]
																 } );
	
	
    changePeriodTeamDialog.render();
    
    var btns2 = YAHOO.util.Dom.getElementsByClassName("changePeriodTeamLink", "a");
         
    YAHOO.util.Event.on(btns2, "click", function(e, panel) {
                	YAHOO.log('RUNG id ' + this.id);
                	document.getElementById('changePeriodTeamFrame').src='changeperiodteam.html' 
                	changePeriodTeamDialog.show();
    }, changePeriodTeamDialog);
    
    
    
     //change password dialog  -----------------
      
    var handleClosePassword = function() {
		this.cancel();
		document.getElementById('changePasswordFrame').src=' ';
		//reload the page
		//window.location.reload();
	};
	
	// Instantiate the Dialog
	var changePasswordDialog = new YAHOO.widget.Dialog("changePasswordDialog", 
																{ width : "650px",
																  // height : "300px",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  { text:"Close", handler:handleClosePassword,isDefault:true } ]
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
																{ width : "700px",
																//  height : "70%",
																  fixedcenter : true,
																  visible : false, 
																  iframe : true,
																  //ie7 modal problem
																  //modal:false,
																  constraintoviewport : true,
																  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.25},
																  buttons : [ 
																			  { text:"Close", handler:handleCloseRun,isDefault:true } ]
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


<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src=".././javascript/tels/iefixes.js"></script>
<![endif]-->

<!--[if IE 5]>
<style>
#studentActionList a:link, #studentActionList a:visited {
	float: left;
	clear: both;
	width: 100%;
	font-family: "Gill Sans", Helvetica, Arial, "Lucida Grande", "Lucida San Unicode";
			}
</style>
<![endif]-->

<!--[if lte IE 6]>
<style>
#studentActionList a:link, #studentActionList a:visited {
	height: 1%;
	font-family: "Gill Sans", Helvetica, Arial, "Lucida Grande", "Lucida San Unicode";
		}
</style>
<![endif]-->

<script src=".././javascript/tels/classAnim.js" type="text/javascript" > </script>
<script>  YAHOO.util.Event.onAvailable("TestClassAnim", function(){ var anim = new 
		  YAHOO.mozmonkey.ClassAnim("TestClassAnim"); var start = 0; 
		  YAHOO.util.Event.addListener("TestClassAnim", "mouseover", function(){ anim.addClass("classAnimHover"); }); 
		  YAHOO.util.Event.addListener("TestClassAnim", "mouseout", function(){ anim.removeClass("classAnimHover"); }); 
		  YAHOO.util.Event.addListener("TestClassAnim", "click", function(){ if(start == 0){ start = 1; anim.addClass("classAnim2"); } else{ start = 0; anim.removeClass("classAnim2"); } }); 
		  }); 
		  </script>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="./studentHeader.jsp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>

<div id="columnButtons">

<dl id="list1" >
	<dt id="studentUsername">${user.userDetails.firstname} ${user.userDetails.lastname} </dt>
	<dd></dd>
	<dt id="studentWelcome">
		<c:set var="current_date" value="<%= new java.util.Date() %>" />
		<c:choose>
	        <c:when test="${(current_date.month == user.userDetails.birthday.month) &&
	                        (current_date.day == user.userDetails.birthday.day)}" >
	            Happy Birthday!
	        </c:when>
	        <c:when test="${(current_date.hours>=3) && (current_date.hours<12)}" >
	            Good morning!
	        </c:when>
	        <c:when test="${(current_date.hours>=12) && (current_date.hours<18)}" >
				Good afternoon!	
	        </c:when>
	        <c:otherwise>
				Hello Night Owl!
	        </c:otherwise>
	    </c:choose>
	</dt>
	<dd></dd>
</dl>

<div style="text-align:center;"><img src="../themes/tels/default/images/student/Panda.jpg" width="220"  alt="WIse 3 Panda" /></div>

<div id="accountOptions"><spring:message code="wise.account-options" /></div>

<div id="optionButtons">
	<ul>
	<li>
		<a href="#"
		onmouseover="swapImage('studentaddproject','../<spring:theme code="student_add_project_roll" />');"
		onmouseout="swapImage('studentaddproject','../<spring:theme code="student_add_project" />');"
		class="addprojectLink"> <img id="studentaddproject"
		src="../<spring:theme code="student_add_project" />" /> </a>
	</li>
						
	<li><a href="#"
		onmouseover="swapImage('studentchangepwd','../<spring:theme code="student_change_password_roll" />');"
		onmouseout="swapImage('studentchangepwd','../<spring:theme code="student_change_password" />');"
		class="changepasswordLink"> <img
		id="studentchangepwd"
		src="../<spring:theme code="student_change_password" />"
		style="border: 0px;" /> </a></li>
		
	<li><a href="<c:url value="/j_acegi_logout"/>"
		onmouseover="swapImage('studentsignout','../<spring:theme code="student_sign_out_roll" />');"
		onmouseout="swapImage('studentsignout','../<spring:theme code="student_sign_out" />');"> 
		<img id="studentsignout" src="../<spring:theme code="student_sign_out" />"
		style="border: 0px;" /> </a></li>
				
	<li style="display:none;"><a href="#"
		onmouseover="swapImage('studentchangelang','../<spring:theme code="student_change_lang_roll" />');"
		onmouseout="swapImage('studentchangelang','../<spring:theme code="student_change_lang" />');"
		onclick="javascript:alert('This page is not available yet')"> <img
		id="studentchangelang"
		src="../<spring:theme code="student_change_lang" />"
		style="border: 0px;" /> </a></li>

	</ul>
</div>

<div class="separator"></div>

<dl id="list2">
	<dt>This Sign In:</dt>
	<dd><fmt:formatDate value="${current_date}" type="both" dateStyle="short" timeStyle="short" /></dd>
	<dt>Last Sign In:</dt>
	<dd>
	<c:choose>
		<c:when test="${user.userDetails.lastLoginTime == null}">
			never
		</c:when>
		<c:otherwise>
			<fmt:formatDate value="${user.userDetails.lastLoginTime}" 
				type="both" dateStyle="short" timeStyle="short" />
		</c:otherwise>
	</c:choose>
		
	</dd>
	<dt class="listTitle2"># of Logins:</dt>
	<dd id="numberOfLogins">${user.userDetails.numberOfLogins}</dd>
	<dt class="listTitle2">Language:</dt> 
	<dd id="language">English</dd>
</dl>

<div class="separator"></div>

<div style="text-align:center;"><img src="../themes/tels/default/images/Wise-Logo-W3-Georgia.png" alt="SAIL Logo" /></div>

<div id="displayAsEnglish">WISE 3.0 &amp; Amanda the Panda <br/>&#169; 1998-2008 <a href="../contactwisegeneral.html" title="Contact WISE Link">Contact WISE</a></div>

<div style="display:none;" id="displayAsEnglish"><a href="#">Display in English</a></div>

</div>   <!--end of columnButtons, floated to left-->


<div id="columnProjects">

	<div id="columnLabel">PROJECT MENU</div>
	
	<div id="tabSystem" class="yui-navset">
	    <ul style="font-size:1.0em;" class="yui-nav">
	        <li style="margin:0 .4em 0 0px;" class="selected"><a href="#currentRuns"><em>Current Runs</em></a></li>
	        <li><a href="#archivedRuns"><em>Archived Runs</em></a></li>
	    </ul>            
	    <div class="yui-content" style="background-color:#FFFFFF;">
			<div id="currentRuns">
				<c:choose>
				<c:when test="${fn:length(current_run_list) > 0}" >
				
				<c:forEach var="studentRunInfo"  items="${current_run_list}">
						
						<table id="currentRunTable" >
				
							<tr id="projectMainRow">
								<td class="studentTableLeftHeaderCurrent">Title</td>
								<td class="tableBorderRight">
									<div id="studentTitleText">${studentRunInfo.run.sdsOffering.name}</div></td>
									
								<td rowspan="5" style="width:30%; padding:2px;">
									  	<ul id="studentActionList">
											<li><c:choose>
												<c:when test="${studentRunInfo.workgroup == null}">
													<a href="javascript:popup('startproject.html?runId=${studentRunInfo.run.id}');" id='${studentRunInfo.run.id}'>RUN PROJECT</a>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${fn:length(studentRunInfo.workgroup.members) == 1}">
															<a href="startproject.html?runId=${studentRunInfo.run.id}"
																id='${studentRunInfo.run.id}' class="">RUN PROJECT</a>
														</c:when>
														<c:otherwise>
															<a
																href="javascript:popup('teamsignin.html?runId=${studentRunInfo.run.id}');"
																id='${studentRunInfo.run.id}' class="">RUN PROJECT</a>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose></li>
											<li><a class="changePeriodTeamLink" style="letter-spacing:0px;" href="#">Change Period or Team</a></li>
											<li><a href="../contactwiseproject.html?projectId=${studentRunInfo.run.project.id}">Report A Problem</a></li>
									 	</ul>
							 	</td>
							</tr>
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent tableBorderTopBottom">Student Code</td>
								<td id="secondaryRowTightFormat" class="tableBorderTopBottom tableBorderRight">${studentRunInfo.run.runcode}-${studentRunInfo.group.name}</td>
						  	</tr>	
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent tableBorderTopBottom">Teacher</td>
								<td id="secondaryRowTightFormat" class="tableBorderTopBottom tableBorderRight">
												<c:choose>
												<c:when test="${fn:length(studentRunInfo.run.owners) > 0}" >
													<c:forEach var="member" items="${studentRunInfo.run.owners}">	
														${member.userDetails.firstname} 
										      			${member.userDetails.lastname}
													</c:forEach>
												</c:when>
												<c:otherwise>
													not established yet			    
												</c:otherwise>	
									      		</c:choose>
								</td>
								</tr>
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent tableBorderTopBottom">Period</td>
								<td id="secondaryRowTightFormat" class="tableBorderTopBottom tableBorderRight">${studentRunInfo.group.name}</td>
						  	
						  	</tr>
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent">Team</td>
								<td id="secondaryRowTightFormat" class="tableBorderRight">
												<c:choose>
												<c:when test="${studentRunInfo.workgroup != null}" >
													<c:forEach var="member" varStatus="membersStatus" items="${studentRunInfo.workgroup.members}">
													${member.userDetails.username}
											 		   <c:if test="${membersStatus.last=='false'}">
								     					&
								    				</c:if> 
													</c:forEach>
												</c:when>
												<c:otherwise>
													not established yet			    
												</c:otherwise>	
									      		</c:choose>
								</td>
								
							</tr>
					</table>
						
							
				</c:forEach>
				</c:when>
				<c:otherwise>
						To add a WISE 3.0 project click the "Add a Project" button.			    
				</c:otherwise>
				</c:choose>
			</div>
			<div id="archivedRuns">
				<c:choose>
				<c:when test="${fn:length(ended_run_list) > 0}" >
				
				<c:forEach var="studentRunInfo"  items="${ended_run_list}">
						<table id="currentRunTable" >
				
							<tr id="projectMainRow">
								<td class="studentTableLeftHeaderArchive">Title</td>
								<td id="studentCurrentTitleCell" class="tableBorderRight">
									<div id="studentTitleText">${studentRunInfo.run.sdsOffering.name}</div></td>
								<td rowspan="5" style="width:27%; padding:2px;">
									  	<ul id="studentActionList">
											<li><c:choose>
												<c:when test="${studentRunInfo.workgroup == null}">
													<a href="#" id='${studentRunInfo.run.id}' class="runProjectLink">REVIEW
													PROJECT</a>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${fn:length(studentRunInfo.workgroup.members) == 1}">
															<a href="${studentRunInfo.startProjectUrl}"
																id='${studentRunInfo.run.id}' class="">RUN PROJECT</a>
														</c:when>
														<c:otherwise>
															<a
																href="javascript:popup('teamsignin.html?runId=${studentRunInfo.run.id}');"
																id='${studentRunInfo.run.id}' class="">RUN PROJECT</a>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose></li>
										</ul>
							 	</td>
							</tr>	
							<tr>
								<td class="studentTableLeftHeaderArchive tableBorderTopBottom">Teacher</td>
								<td class="tableBorderTopBottom tableBorderRight">
												<c:choose>
												<c:when test="${fn:length(studentRunInfo.run.owners) > 0}" >
													<c:forEach var="member" items="${studentRunInfo.run.owners}">	
														${member.userDetails.firstname} 
										      			${member.userDetails.lastname}
													</c:forEach>
												</c:when>
												<c:otherwise>
													not established yet			    
												</c:otherwise>	
									      		</c:choose>
								</td>
								</tr>
							<tr>
								<td class="studentTableLeftHeaderArchive tableBorderTopBottom">Period</td>
								<td class="tableBorderTopBottom tableBorderRight">${studentRunInfo.group.name}</td>
						  	
						  	</tr>
							<tr>
								<td class="studentTableLeftHeaderArchive tableBorderTopBottom">Team</td>
								<td class="tableBorderTopBottom tableBorderRight">
												<c:choose>
												<c:when test="${studentRunInfo.workgroup != null}" >
													<c:forEach var="member" varStatus="membersStatus" items="${studentRunInfo.workgroup.members}">
													${member.userDetails.username}
											 		   <c:if test="${membersStatus.last=='false'}">
								     					&
								    				</c:if> 
													</c:forEach>
												</c:when>
												<c:otherwise>
													not established yet			    
												</c:otherwise>	
									      		</c:choose>
								</td>
							</tr>
							<tr>
								<td class="studentTableLeftHeaderArchive">Archived On</td>
								<td class="tableBorderRight">NEED DATA: Project Run End Date</td>
							</tr>
					</table>
				</c:forEach>
				</c:when>
				<c:otherwise>
						You currently have no archived projects.	    
				</c:otherwise>
				</c:choose>
			</div>
</div>   <!--end of columnProjects, floated to left-->
 
 
 <!-- BEGIN DEFINITION OF FRAMES USED FOR AJAX  -->
  
<!-- this creates the add project dialog with iframe -->
<div id="addProjectDialog">
<div class="hd">Add A Project</div>
<div class="bd">
<h3 ><spring:message code="teacher.add-project-info" /></h3>


<iframe id="addProjectFrame" src="" width="100%" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>

<!-- this creates the change period team iframe -->
<div id="changePeriodTeamDialog">
<div class="hd">Change Period or Team</div>
<div class="bd">

<iframe id="changePeriodTeamFrame" src="" width="100%" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>


<!-- creates change password -->
<div id="changePasswordDialog">
<div class="hd">Change Your Password</div>
<div class="bd">


<iframe id="changePasswordFrame" src=" " width="100%" height="250px" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>

<!-- this creates the select team dialog with iframe -->
<div id="runProjectDialog">
<div class="hd">Select a Team</div>
<div class="bd" align="left">

<iframe id="runProjectFrame" src=" " width="100%" height="400px" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>
<!-- BEGIN DEFINITION OF FRAMES USED FOR AJAX  -->

</div>
</div>
</div>
</body>
</html>

 
