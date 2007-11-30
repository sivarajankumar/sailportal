<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="studenthomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />

	
<title><spring:message code="application.title" /></title>

<%@ include file="styles.jsp"%>
<style>
.yui-panel-container select {
              _visibility: inherit;
}
</style>

<script language="JavaScript">
	function popup(URL) {
  	window.open(URL, 'Select Team', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=300,height=300,left = 570,top = 300');}
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

<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<script src=".././javascript/tels/effects.js" type="text/javascript" ></script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<script>   <!--This script is used to generate alternating striped effect for Project Tables
	 
	 $tablerow_count=0;
	 function tablerowswitch() {
	  global $tablerow_count;
	  $tablerow_count++;
	  if ($tablerow_count % 2) {
	   echo "odd";
	  }
	  else {
	   echo "even";
	  }
	 }
	 
</script>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="./studentHeader.jsp"%>

<div id="columnButtons">

<dl id="list1" >
	<dt class="listTitle1">User:</dt>
	<dd id="studentUsername">[Jimmy Slotta!] </dd>
	<dt class="listTitle1">Msg:</dt>
	<dd id="studentWelcome">[Good Morning!] </dd>
</dl>

<div class="separator"></div>

<dl id="list2">
	<dt class="listTitle2">Current Time:</dt>
	<dd id="currentSignIn">[Tues Nov. 24, 3:05pm]</dd>
	<dt class="listTitle2">Last Sign In:</dt>
	<dd id="lastSignIn">[Mon Nov. 23, 3:56pm]</dd>
	<dt class="listTitle2">Language:</dt> 
	<dd id="language">[English]</dd>
</dl>

<div class="separator"></div>

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
				
	<li><a href="#"
		onmouseover="swapImage('studentchangelang','../<spring:theme code="student_change_lang_roll" />');"
		onmouseout="swapImage('studentchangelang','../<spring:theme code="student_change_lang" />');"
		onclick="javascript:alert('This page is not available yet')"> <img
		id="studentchangelang"
		src="../<spring:theme code="student_change_lang" />"
		style="border: 0px;" /> </a></li>

	</ul>
</div>

<div id="displayAsEnglish"><a href="#">Display in English</a></div>

</div>   <!--end of columnButtons, floated to left-->


<div id="columnProjects">

<div id="columnLabel">PROJECT MENU</div>

<div id="tabSystem" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#currentRuns"><em>Current Runs</em></a></li>
        <li ><a href="#archivedRuns"><em>Archived Runs</em></a></li>
    </ul>            
    <div class="yui-content">
		<div id="currentRuns">
			<c:choose>
			<c:when test="${fn:length(current_run_list) > 0}" >
			
			<c:forEach var="studentRunInfo"  items="${current_run_list}">
					<table border="0">
			
						<tr id="projectMainRow">
				
						<td style="padding:0px;">
							<table id="tableStudentProjectData" border="0" cellpadding="0" cellspacing="0">
									<tr >
										<td class="studentTableLeftHeader">Title</td>
										<td id="studentCurrentTitleCell" class="tableBorderRight">
											<div id="studentTitleText">${studentRunInfo.run.sdsOffering.name}</div>
										</td>
									</tr>
									<tr bgcolor="#CCFF99">
										<td class="studentTableLeftHeader tableBorderTopBottom">Teacher</td>
										<td class="tableBorderTopBottom tableBorderRight">
											

											<c:choose>
											<c:when test="${fn:length(studentRunInfo.run.owners) > 0}" >
												<c:forEach var="member" items="${studentRunInfo.run.owners}">	
													${member.userDetails.firstname} 
									      			${member.userDetails.lastname}
												</c:forEach>
											</c:when>
											<c:otherwise>
												pending...				    
											</c:otherwise>	
								      		</c:choose>
								      	

										</td>
									</tr>
								  	<tr>
								  		<td class="studentTableLeftHeader tableBorderTopBottom">Period</td>
								  		<td class="tableBorderTopBottom tableBorderRight">
								  			${studentRunInfo.group.name}</td>
								  	</tr>
								  	<tr bgcolor="#CCFF99">
								  		<td class="studentTableLeftHeader tableBorderTopBottom">Team</td>
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
												pending...			    
											</c:otherwise>	
								      		</c:choose>
								      	

										</td>
								  	</tr>
								  	<tr>
								  		<td class="studentTableLeftHeader">Last Use</td>
								  		<td class="tableBorderRight">[Last Launch Date]</td>
								  	</tr>
							</table>
						</td>
						<td style="width:22%; padding:3px;">
						  	<ul id="studentActionList">
						  		<li><a id='${studentRunInfo.run.id}' href="#" class="runProjectLink">Run Project</a></li>
						  	  	<li><a style="color:#cccccc;" href="#">Change Period or Team</a></li>
						  	  	<li><a style="color:#cccccc;" href="#">Report A Problem</a></li>
						  	  	<li><a style="color:#cccccc;" href="#">Archive This Project</a></li>
						 	</ul>
						 </td>
						</tr>
					
					
						</table>
			</c:forEach>
			</c:when>
			<c:otherwise>
					Select "Add a Project" to get started.				    
			</c:otherwise>
			</c:choose>
		</div>
        <div id="archivedRuns">
			<c:choose>
			<c:when test="${fn:length(end_run_list) > 0}" >
				>
			</c:when>
			<c:otherwise>
					There are no projects in the Archive.				    
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>  
 <!--end of columnProjects, floated to left-->
 
 <!-- BEGIN DEFINITION OF FRAMES USED FOR AJAX  -->
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
<!-- BEGIN DEFINITION OF FRAMES USED FOR AJAX  -->
 
