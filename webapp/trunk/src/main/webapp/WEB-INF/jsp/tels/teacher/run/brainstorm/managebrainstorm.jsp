<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<title>Manage Brainstorm</title>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script>
<script type="text/javascript" src="../.././javascript/tels/brainstorm.js"></script>
<script type="text/javascript" src="../.././javascript/tels/brainstormUtils.js"></script>

<script type="text/javascript">
	var sortOrder = 0;
	var pageManager;

	pageManager = new PageManager('${brainstorm.id}', '${workgroup.id}', sortOrder);
</script>

</head>
<body  style="background-color:#FFFFFF;" onload="javascript:hideallanswers('${brainstorm.id}', 'false');">

${fn:length(brainstorm.workgroupsThatRequestHelp)} students requested for help on this brainstorm:
<br />
students that requested help: <br/>
<c:forEach var='wg' items='${brainstorm.workgroupsThatRequestHelp}'>
  ${wg.sdsWorkgroup.name} <br/>
</c:forEach>





<div id="stepTypeTitleBar">Q&amp;A DISCUSSION</div>

<div id="sectionBox">

	<div id="sectionBoxHeader">
		<a id="hideLink" href="#" onclick="toggle_visibility('sectionBoxMain1');">click to hide/show</a>
		<p>Question for <span style="color:#FF0000">PERIOD 1</span>&nbsp;<span style="color:#FF0000; font-size:.8em;">(12 students)</span></p>
	</div>
	
	<div id="sectionBoxMain1">
				
		<div id="column1">
			<p>Question:</p>
		</div>
		
		<div id="column2">
			<div id="questionContent" name="questionPrompt">${brainstorm.question.prompt}</div>
			<div id="instructions1">To answer this Q&amp;A discussion question, click the <b>Create A Response</b> button below</div>
		</div>
	
	</div>
	
</div>  <!--end of Section Box-->
		
		
<div id="sectionBox">

	<div id="sectionBoxHeader">
		<a id="hideLink" href="#" onclick="toggle_visibility('sectionBoxMain2');">click to hide/show</a>
		<p id="numOfPosts">Student Responses (${fn:length(brainstorm.answers)} responses)
			<span id="createResponse"><input type="button" value="Create A Response" onclick="responsePopUp(${workgroup.id}, ${brainstorm.id})"></input></span>
		</p>
	</div>
	
	
<div id="sectionBoxMain2">

	<!-- CONDITIONAL ON WHETHER STUDENTS CAN SEE OTHER STUDENTS' POSTS OR NOT -->
	<div id="0" name="answer">
		
		<table id="responseRefreshTable">
			<tr>
				<td><input type="button" value="Show New Responses" onclick="refreshResponses()"/></td>
				<td class="col2width"><div id="numNewResponses"><i>0 new responses received<i></div></td>
				<td colspan="2"><c:set var="thisworkgrouprequestedhelp" value="false" />
					<c:forEach var="workgroupThatRequestedHelp" varStatus="wtrh" items="${brainstorm.workgroupsThatRequestHelp}">
		    			<c:if test="${workgroupThatRequestedHelp == workgroup}">
		        			<c:set var="thisworkgrouprequestedhelp" value="true" />
						</c:if>
					</c:forEach>
					<c:choose>
		    			<c:when test="${thisworkgrouprequestedhelp == true}">
		    				<input id="requesthelp_${workgroup.id}_${brainstorm.id}" checked="checked" type="checkbox" value="helpful" onclick="javascript:requestHelp('${workgroup.id}', '${brainstorm.id}')">Request Discussion Help from Teacher</input>
		    			</c:when>
		    			<c:otherwise>
		        			<input id="requesthelp_${workgroup.id}_${brainstorm.id}" type="checkbox" value="helpful" onclick="javascript:requestHelp('${workgroup.id}', '${brainstorm.id}')">Request Discussion Help from Teacher</input>
		    			</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		
		<div id="responseLinks">
			<ul>
				<li><a href="#" onclick="sortBy('time')">Sort By Time</a></li>
				<li><a href="#" onclick="sortBy('help')">Sort By Helpfulness</a></li>
				<li><a href="#" onclick="toggle_visibility_by_name('revisionrow')">Hide/Show Revisions</a></li>
				<li><a href="#" onclick="toggle_visibility_by_name('comments')">Hide/Show Comments</a></li>
			</ul>
		</div>
		


	
</div>  <!--end of Section Box-->
	
	
<div id="responseTableBody">

</div>
<div id="cannotSeeMessage">

</div>


</body>
</html>


