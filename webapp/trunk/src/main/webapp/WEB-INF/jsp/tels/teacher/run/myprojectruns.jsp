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

<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script>
		(function() {
   		 var tabView = new YAHOO.widget.TabView('tabSystem');})();
 </script>
 
  <style type="text/css" media="screen">



	</style>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="../projects/headerteacherprojects.jsp"%>

<%@ include file="L2projects_myprojectruns.jsp"%>


<div id="tabSystem" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#currentRuns"><em>Current Runs</em></a></li>
        <li ><a href="#archivedRuns"><em>Archived Runs</em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="currentRuns">
        		<h5 id="subHeader">To manage any current Project Run select one of its Actions. To see an archived Project Run click the tab above.</h5>
        		<div id="runBox">
				
				<table id="currentRunTable" border="1" cellpadding="0" cellspacing="0" >
				    <tr>
				       <th style="width:50%;" class="tableHeaderMain">Current Project Runs</th>
				       <th style="width:34%;" class="tableHeaderMain">Run Information</th>      
				      <th  style="width:16%;" class="tableHeaderMain">Actions</th>
				    </tr>
				  <c:forEach var="run" items="${current_run_list}">
				  
				  <tr id="runTitleRow">
				    <td id="titleCell">
				    	<div id="runTitle">${run.sdsOffering.name}</div>
				      	<div id="titleSubHeader">&middot; [DATA NEEDED: project type/project id] "library project, ID 21351"&middot;</div>
				    	<div id="titleSubHeader">&middot; [DATA NEEDED: run creation date]  "created 4/12/07 &middot;</div>
				    </td>
				    <td style="vertical-align:top; padding:0px;">
				    	<table id="currentRunInfoTable" border="0" cellpadding="0" cellspacing="0">
				          <tr>
				            <th class="tableInnerHeader">Period</th>
				            <th class="tableInnerHeader">Project Code</th>
				            <th class="tableInnerHeaderRight">Students</th>
				          </tr>
				          <c:forEach var="period" items="${run.periods}">
				            <tr>
				              <td style="width:20%;" class="tableInnerData">${period.name}</td>
				              <td style="width:45%;" class="tableInnerData">${run.runcode}-${period.name}</td>
				              <td style="width:35%;" class="tableInnerDataRight">
				                [X] registered
				                
				              </td>
				            </tr>
				          </c:forEach>
				        </table>
				    </td>
				    <td style="vertical-align:top; padding:1px 0;">
					    <ul id="actionList">
					    	<li><a style="color:#cccccc;" href="#">View Project Info</a></li>
					    	<li><a style="color:#cccccc;" href="#">Edit Periods</a></li>
					    	<li><a href="../grading/gradebystep.html?runId=${run.id}">Grade Students</a></li>
					    	<li><a href="../grading/currentscore.html?runId=${run.id}">Score Summary (test)</a></li>
					    	<li><a style="color:#cccccc;" href="#">Manage Students</a></li>
					    	<li><a style="color:#cccccc;" href="#">Send Msg to Student(s)</a></li>
					    	<li><a style="color:#cccccc;" href="#">Report a Problem</a></li>
					    	<li><a href="#" onclick="javascript:popup('manage/archiveRun.html?runId=${run.id}')">Archive this Run</a></li>
					    </ul>
					</td>
				   </tr>
				  </c:forEach>
				</table>
				</div>
        </div><!-- end current runs tab -->
        <div id="archivedRuns">
        <h5 id="subHeader">The following projects have been archived. <br />
        					To manage any archived Project Run select one of its Actions. </h5>
        		<div id="runBox">
				
				<table id="currentRunTable" border="1" cellpadding="0" cellspacing="0" >
				    <tr>
				       <th style="width:50%;" class="tableHeaderMain archive">Archived Project Runs</th>
				       <th style="width:34%;" class="tableHeaderMain archive">Run Information</th>      
				       <th style="width:16%;" class="tableHeaderMain archive">Actions</th>
				    </tr>
				  <c:forEach var="run" items="${ended_run_list}">
				  
				  <tr id="runTitleRow">
				    <td id="titleCell">
				    	<div id="runTitle">${run.sdsOffering.name}</div>
				      	<div id="titleSubHeader">&middot; [DATA NEEDED: project type/project id] "library project, ID 21351"&middot;</div>
				    	<div id="titleSubHeader">&middot; [DATA NEEDED: run creation date]  "created 4/12/07" &middot;</div>
				    	<div id="titleSubHeader">&middot; [DATA NEEDED: date archived] "archived 4/12/07" &middot;</div>				    	   	 
				    </td>
				    <td style="vertical-align:top; padding:0px;">
				    	<table id="currentRunInfoTable" border="0" cellpadding="0" cellspacing="0">
				          <tr>
				            <th class="tableInnerHeader">Period</th>
				            <th class="tableInnerHeader">Project Code</th>
				            <th class="tableInnerHeaderRight">Students</th>
				          </tr>
				          <c:forEach var="period" items="${run.periods}">
				            <tr>
				              <td style="width:20%;" class="tableInnerData">${period.name}</td>
				              <td style="width:45%;" class="tableInnerData">${run.runcode}-${period.name}</td>
				              <td style="width:35%;" class="tableInnerDataRight">
				                [X] registered
				                
				              </td>
				            </tr>
				          </c:forEach>
				        </table>
				    </td>
				    <td style="vertical-align:top; padding:1px 0;">
					    <ul id="actionList">
					    	<li><a style="color:#cccccc;" href="#">View Project Info</a></li>
					    	<li><a href="#" onclick="javascript:popup('manage/startRun.html?runId=${run.id}')">Restore to Current Run</a></li>
					    	<li><a style="color:#cccccc;" href="#">View Students</a></li>
					    	<li><a style="color:#cccccc;" href="#">View Student Work</a></li>
					    	<li><a style="color:#cccccc;" href="#">Report a Problem</a></li>
					    </ul>
					</td>
				   </tr>
				  </c:forEach>
				</table>
				</div>




</body>
</html>


