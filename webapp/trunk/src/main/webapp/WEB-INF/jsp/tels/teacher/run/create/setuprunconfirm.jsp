<%@ include file="../../../include.jsp"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<title><spring:message code="setuprun.confirmation.title" /></title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script>
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="../../projects/headerteacherprojects.jsp"%>

<%@ include file="L2projectsnohighlight.jsp"%>

<div id="titleBarSetUpRun">
    	<h1 class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1>
</div>

<div id="setUpRunBoxConfirm">

<div id="stepNumber"><span class="blueText">Project Run Created!</span></div>

	<h4>Your new project run has been placed in the <a href="teacher/run/myprojectruns.html">My Project Runs</a> area.</h4>
	
	<table id="projectRunConfirmTable" border="1" cellpadding="5" cellspacing="0" >
			<tr>
				<td style="width:30%;"><spring:message code="setuprun.confirmation.run.title" /></td>
				<td><strong><c:out value="${run.sdsOffering.name}" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="setuprun.confirmation.run.createdtime" /></td>
				<td><strong><c:out value="${run.starttime}" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="setuprun.confirmation.run.projectid" /></td>
				<td><strong><c:out value="${run.sdsOffering.sdsCurnit.sdsObjectId}" /></strong></td>
			</tr>
			<tr>
				<td><spring:message code="setuprun.confirmation.run.projectcodes" /></td>
				<td>
					<c:forEach var="period" items="${run.periods}">
			    	<strong><c:out value="${run.runcode}" />-<c:out value="${period.name}" /></strong>
			    	&nbsp;(<spring:message code="setuprun.confirmation.run.projectcodes.foryourstudentsinperiod" />
			    		<c:out value="${period.name}" />)
			    	<br />
			  		</c:forEach>
			    </td>
			</tr>
		</table>
		
<h4 class="reducedBottomMargin"><spring:message code="setuprun.confirmation.aboutprojectids.heading" /></h4>
<h5 class="indent15px"><spring:message code="setuprun.confirmation.aboutprojectids.text" /></h5>

<h4 class="reducedBottomMargin"><spring:message code="setuprun.confirmation.aboutprojectcodes.heading" /></h4>
<h5 class="indent15px"><spring:message code="setuprun.confirmation.aboutprojectcodes.text" /></h5>
		
</div>      <!-- end setUpRunBoxConfirm"-->
		
<div id="gotoMyRunsButton" class="center">
	<a href="myprojectruns.html"> 
		<img id="projectRuns"
		src="<spring:theme code="gotomyprojectruns" />"	 	
		onmouseover="swapImage('projectRuns','<spring:theme code="gotomyprojectruns_roll" />');"
		onmouseout="swapImage('projectRuns','<spring:theme code="gotomyprojectruns" />');"
		style="border:0px;"/>	</a>
</div>

</div>    <!-- end CenteredDiv" -->

</body>

</html>