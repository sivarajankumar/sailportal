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
  	{window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=300,height=300,left = 570,top = 300');}
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
        		<h5 id="subHeader">To manage any Project Run, select one of its Actions.</h5>
        		<div id="runBox">
				
				<table id="currentRunTable" border="1" cellpadding="0" cellspacing="0" >
				    <tr>
				       <th style="width:50%;" class="tableHeaderMain">Project Run Title</th>
				       <th style="width:34%;" class="tableHeaderMain">Run Information</th>      
				      <th  style="width:16%;" class="tableHeaderMain">Actions</th>
				    </tr>
				  <c:forEach var="run" items="${current_run_list}">
				  
				  <tr id="runTitleRow">
				    <td style="padding:0px 0;">
				    	<div id="runTitle">${run.sdsOffering.name}</div>
				      	<div id="titleSubHeader">&middot; library project, ID 21351 &middot;</div>
				    	<div id="titleSubHeader">&middot; created 4/12/07 &middot;</div>
				    	 
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
					    	<li><a href="../grading/gradebystep.html?runId=${run.id}"">Grade Students</a></li>
					    	<li><a style="color:#cccccc;" href="#">Manage Students</a></li>
					    	<li><a style="color:#cccccc;" href="#">Send Msg to Student(s)</a></li>
					    	<li><a style="color:#cccccc;" href="#">Report a Problem</a></li>
					    	<li><a style="color:#cccccc;" href="#">Archive this Run</a></li>
					    </ul>
					</td>
				   </tr>
				  </c:forEach>
				</table>
				</div>
        </div><!-- end current runs tab -->
        <div id="archivedRuns">
        	<h5 id="subHeader">To manage any Archived Run, select one of its Actions.</h5>
        	<table border="1">
				  <thead>
				    <tr>
				      <th><spring:message code="run.name.heading" /></th>
				      <th><spring:message code="run.information" /></th>      
				      <th><spring:message code="run.options.heading" /></th>
				    </tr>
				  </thead>
				  <c:forEach var="run" items="${ended_run_list}">
				  <tr>
				    <td>${run.sdsOffering.name}</td>
				    <td><table border="1">
				          <tr>
				            <th><spring:message code="run.period" /></th>
				            <th><spring:message code="teacher.project-code" /></th>
				            <th><spring:message code="run.students" /></th>
				          </tr>
				          <c:forEach var="period" items="${run.periods}">
				            <tr>
				              <td>${period.name}</td>
				              <td>${run.runcode}-${period.name}</td>
				              <td>
				                <c:choose>
				                  <c:when test="${fn:length(period.members) == 0}" >
				                    None
				                  </c:when>
				                  <c:otherwise>
				                    <c:forEach var="member" items="${period.members}">${member.userDetails.firstname} ${member.userDetails.lastname}</c:forEach>
				                  </c:otherwise>
				                </c:choose>
				              </td>
				            </tr>
				          </c:forEach>
				        </table></td>
				    <td>
				      <c:choose>
				        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
				        <spring:message code="no.workgroups" />
				             <a href="../grading/currentscore.html?runId=${run.id}">Current Score</a><br/>
				        </c:when>
				        <c:otherwise>
				            <c:forEach var="workgroup" items="${workgroup_map[run]}">
				              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">${workgroup.sdsWorkgroup.name}</a><br />
				            </c:forEach>
				        </c:otherwise>
				      </c:choose>     
				      <br/>
				      <a href="#" onclick="javascript:popup('manage/startRun.html?runId=${run.id}')">Un-archive this run</a>
				    </td>
				   </tr>
				  </c:forEach>
				</table>
        </div><!-- end archived runs tabs -->
    </div>
</div>




</body>
</html>




























<!-- 

<h1>Archived Runs</h1>
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th><spring:message code="run.information" /></th>      
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${ended_run_list}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><table border="1">
          <tr>
            <th><spring:message code="run.period" /></th>
            <th><spring:message code="teacher.project-code" /></th>
            <th><spring:message code="run.students" /></th>
          </tr>
          <c:forEach var="period" items="${run.periods}">
            <tr>
              <td>${period.name}</td>
              <td>${run.runcode}-${period.name}</td>
              <td>
                <c:choose>
                  <c:when test="${fn:length(period.members) == 0}" >
                    None
                  </c:when>
                  <c:otherwise>
                    <c:forEach var="member" items="${period.members}">${member.userDetails.firstname} ${member.userDetails.lastname}</c:forEach>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table></td>
    <td>
      <c:choose>
        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
        <spring:message code="no.workgroups" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[run]}">
              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">${workgroup.sdsWorkgroup.name}</a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>     
      <br/>
      <a href="#" onclick="javascript:popup('manage/startRun.html?runId=${run.id}')">Un-archive this run</a>
    </td>
   </tr>
  </c:forEach>
</table>


-->