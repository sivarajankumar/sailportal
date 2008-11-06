<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	
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
  	window.open(URL, 'SelectTeam', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=850,height=600,left = 570,top = 300');}
  	
  	function invalidateLink(linkID) {
  	   window.location= document.getElementById(linkID).href;
  	   document.getElementById(linkID).href="#";
  	   document.getElementById(linkID).style.backgroundColor='#000066';
  	   document.getElementById(linkID).style.color='#666666';
  	}
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
																{ width : "700px",
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
                	document.getElementById('changePasswordFrame').src='changestudentpassword.html?userName=${user.userDetails.username}';
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

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="studenthomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="./studentHeader.jsp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>

<div id="columnButtons">

<dl id="list1" >
	<dt id="studentUsername"><authz:authentication operation="firstname" /> <authz:authentication operation="lastname" /></dt>
	<dd></dd>
	<dt id="studentWelcome">
		<c:set var="current_date" value="<%= new java.util.Date() %>" />
		<c:choose>
	        <c:when test="${(current_date.hours>=3) && (current_date.hours<12)}" >
	            <spring:message code="student.index.2"/>
	        </c:when>
	        <c:when test="${(current_date.hours>=12) && (current_date.hours<18)}" >
				<spring:message code="student.index.3"/>
	        </c:when>
	        <c:otherwise>
				<spring:message code="student.index.4"/>
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
		
	<!-- note: to make the change student password into AJAX, type in class="changepasswordLink" -->	
					
	<li><a href="#"
        onclick="javascript:popup640('changestudentpassword.html?userName=${user.userDetails.username}');"	
		onmouseover="swapImage('studentchangepwd','../<spring:theme code="student_change_password_roll" />');"
		onmouseout="swapImage('studentchangepwd','../<spring:theme code="student_change_password" />');"
		> <img
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
	<dt><spring:message code="student.index.5"/></dt>
	<dd><fmt:formatDate value="${current_date}" type="both" dateStyle="short" timeStyle="short" /></dd>
	<dt><spring:message code="student.index.6"/></dt>
	<dd>
	<c:choose>
		<c:when test="${user.userDetails.lastLoginTime == null}">
			<spring:message code="student.index.7"/>
		</c:when>
		<c:otherwise>
			<fmt:formatDate value="${user.userDetails.lastLoginTime}" 
				type="both" dateStyle="short" timeStyle="short" />
		</c:otherwise>
	</c:choose>
		
	</dd>
	<dt class="listTitle2"><spring:message code="student.index.8"/></dt>
	<dd id="numberOfLogins">${user.userDetails.numberOfLogins}</dd>
	<dt class="listTitle2"><spring:message code="student.index.9"/></dt> 
	<dd id="language"><spring:message code="student.index.10"/></dd>
</dl>

<div class="separator"></div>

<div style="text-align:center;"><img src="../themes/tels/default/images/Wise-Logo-W3-Georgia.png" alt="SAIL Logo" /></div>

<div id="displayAsEnglish">WISE 3.0 &amp; Amanda the Panda <br/>&#169; 1998-2008 <a href="../contactwisegeneral.html" title="Contact WISE Link"><spring:message code="student.index.11"/></a></div>

<div style="display:none;" id="displayAsEnglish"><a href="#"><spring:message code="student.index.12"/></a></div>

</div>   <!--end of columnButtons, floated to left-->


<div id="columnProjects">

	<div id="columnLabel"><spring:message code="student.index.13"/></div>
	
	<div id="tabSystem" class="yui-navset">
	    <ul style="font-size:.8em;" class="yui-nav">
	        <li style="margin:0 .4em 0 0px;" class="selected"><a href="#currentRuns"><em><spring:message code="student.index.14"/></em></a></li>
	        <li><a href="#archivedRuns"><em><spring:message code="student.index.15"/></em></a></li>
	    </ul>            
	    <div class="yui-content" style="background-color:#FFFFFF;">
			<div id="currentRuns">
				<c:choose>
				<c:when test="${fn:length(current_run_list) > 0}" >
				
				<c:forEach var="studentRunInfo"  items="${current_run_list}">
						
						<table id="currentRunTable" >
				
							<tr id="projectMainRow">
								<td class="studentTableLeftHeaderCurrent"><spring:message code="student.index.16"/></td>
								<td>
									<div id="studentTitleText">${studentRunInfo.run.name}</div></td>
								<td rowspan="5" style="width:30%; padding:2px;">
									  	<ul id="studentActionList">   
											<li>
											<c:choose>
												<c:when test="${studentRunInfo.workgroup == null}">
													<a href="javascript:popup('startproject.html?runId=${studentRunInfo.run.id}');" id='${studentRunInfo.run.id}' ><spring:message code="student.index.17"/></a>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${fn:length(studentRunInfo.workgroup.members) == 1}">
															<a href="startproject.html?runId=${studentRunInfo.run.id}" 
																id='${studentRunInfo.run.id}' onclick="javascript:invalidateLink('${studentRunInfo.run.id}');"><spring:message code="student.index.17"/></a>
														</c:when>
														<c:otherwise>
															<a href="javascript:popup('teamsignin.html?runId=${studentRunInfo.run.id}');"   
																id='${studentRunInfo.run.id}' class=""><spring:message code="student.index.17"/></a>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
											<li><a href="${studentRunInfo.workgroup.workPDFUrl}"><spring:message code="student.index.18"/></a></li>
											<li><a style="letter-spacing:0px;" href="javascript:popup('changeperiodteam.html');"><spring:message code="student.index.19"/></a></li>
											<li><a href="../contactwiseproject.html?projectId=${studentRunInfo.run.project.id}"><spring:message code="student.index.20"/></a></li>
									 	</ul>
							 	</td>
							</tr>
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent"><spring:message code="student.index.21"/></td>
								<td id="secondaryRowTightFormat" >${studentRunInfo.run.runcode}-${studentRunInfo.group.name}</td>
						  	</tr>	
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent"><spring:message code="student.index.22"/></td>
								<td id="secondaryRowTightFormat" >
												<c:choose>
												<c:when test="${fn:length(studentRunInfo.run.owners) > 0}" >
													<c:forEach var="member" items="${studentRunInfo.run.owners}">
														${member.userDetails.displayname}
													</c:forEach>
												</c:when>
												<c:otherwise>
													<spring:message code="student.index.23"/>
												</c:otherwise>	
									      		</c:choose>
								</td>
								</tr>
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent"><spring:message code="student.index.24"/></td>
								<td id="secondaryRowTightFormat" >${studentRunInfo.group.name}</td>
						  	
						  	</tr>
							<tr>
								<td id="secondaryRowTightFormat" class="studentTableLeftHeaderCurrent"><spring:message code="student.index.25"/></td>
								<td id="secondaryRowTightFormat" >
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
													<div class="teamNotRegisteredMessage"><spring:message code="student.index.26"/></div>  
												</c:otherwise>	
									      		</c:choose>
								</td>
								
							</tr>
					</table>
						
							
				</c:forEach>
				</c:when>
				<c:otherwise>
						<spring:message code="student.index.27"/>			    
				</c:otherwise>
				</c:choose>
				<div id="firstUseBox">
					<div id="firstUseHeader"><spring:message code="student.index.28"/></div>
					<div id="instructionsArea">
						<h6><spring:message code="student.index.29"/></h6>
						<ol>
							<li><spring:message code="student.index.30A"/></li>
								<ul style="margin:5px 0 5px 25px;font-size:.9em;">
									<li><spring:message code="student.index.30B"/></li>
									<li><spring:message code="student.index.30C"/></li>
								</ul>
							<li><spring:message code="student.index.31"/></li>
							<li><spring:message code="student.index.32"/></li>
							<li><spring:message code="student.index.33"/></li>
							<li><spring:message code="student.index.34"/></li> 
						</ol>
					</div>
				</div>
			</div>
			<div id="archivedRuns">
				<c:choose>
				<c:when test="${fn:length(ended_run_list) > 0}" >
				
				<c:forEach var="studentRunInfo"  items="${ended_run_list}">
						<table id="currentRunTable" >
				
							<tr id="projectMainRow">
								<td class="studentTableLeftHeaderArchive"><spring:message code="student.index.35"/></td>
								<td id="studentCurrentTitleCell">
									<div id="studentTitleText">${studentRunInfo.run.sdsOffering.name}</div></td>
								<td rowspan="5" style="width:27%; padding:2px;">
									  	<ul id="studentActionList">
											<li><c:choose>
												<c:when test="${studentRunInfo.workgroup == null}">
													<a href="#" id='${studentRunInfo.run.id}' class="runProjectLink"><spring:message code="student.index.36"/></a>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${fn:length(studentRunInfo.workgroup.members) == 1}">
															<a href="${studentRunInfo.startProjectUrl}"
																id='${studentRunInfo.run.id}' class=""><spring:message code="student.index.36"/></a>
														</c:when>
														<c:otherwise>
															<a
																href="javascript:popup('teamsignin.html?runId=${studentRunInfo.run.id}');"
																id='${studentRunInfo.run.id}' class=""><spring:message code="student.index.36"/></a>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose></li>
										</ul>
							 	</td>
							</tr>	
							<tr>
								<td class="studentTableLeftHeaderArchive"><spring:message code="student.index.37"/></td>
								<td>
												<c:choose>
												<c:when test="${fn:length(studentRunInfo.run.owners) > 0}" >
													<c:forEach var="member" items="${studentRunInfo.run.owners}">	
														${member.userDetails.displayname}
													</c:forEach>
												</c:when>
												<c:otherwise>
													<spring:message code="student.index.38"/>			    
												</c:otherwise>	
									      		</c:choose>
								</td>
								</tr>
							<tr>
								<td class="studentTableLeftHeaderArchive"><spring:message code="student.index.39"/></td>
								<td>${studentRunInfo.group.name}</td>
						  	
						  	</tr>
							<tr>
								<td class="studentTableLeftHeaderArchive"><spring:message code="student.index.40"/></td>
								<td>
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
													<spring:message code="student.index.41"/>			    
												</c:otherwise>	
									      		</c:choose>
								</td>
							</tr>
							<tr>
								<td class="studentTableLeftHeaderArchive"><spring:message code="student.index.42"/></td>
								<td>NEED DATA: Project Run End Date</td>
							</tr>
					</table>
				</c:forEach>
				</c:when>
				<c:otherwise>
						<spring:message code="student.index.43"/>	    
				</c:otherwise>
				</c:choose>
			</div>
</div>   <!--end of columnProjects, floated to left-->
 
 
 <!-- BEGIN DEFINITION OF FRAMES USED FOR AJAX  -->
  
<!-- this creates the add project dialog with iframe -->
<div id="addProjectDialog">
<div class="hd"><spring:message code="student.index.44"/></div>
<div class="bd">
<h3><spring:message code="teacher.add-project-info" /></h3>


<iframe id="addProjectFrame" src="" width="100%" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>

<!-- this creates the change period team iframe -->
<div id="changePeriodTeamDialog">
<div class="hd"><spring:message code="student.index.45"/>/div>
<div class="bd">

<iframe id="changePeriodTeamFrame" src="" width="100%" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>


<!-- creates change password -->
<div id="changePasswordDialog">
<div class="hd"><spring:message code="student.index.46"/></div>
<div class="bd">


<iframe id="changePasswordFrame" src=" " width="100%" height="250px" FRAMEBORDER="0"
	allowTransparency="false" scrolling="no"> </iframe>
	
</div>
</div>

<!-- this creates the select team dialog with iframe -->
<div id="runProjectDialog">
<div class="hd"><spring:message code="student.index.47"/></div>
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

 
