<%@ include file="include.jsp"%>
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

<!-- $Id: setupRun1.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-one" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="../../headerteachersub.jsp"%> 

<div id="navigationSubHeader2">Project Run Setup<span id="navigationSubHeader1">projects</span></div> 

<h1 id="titleBarSetUpRun" class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1>
     	    	    
<div id="setUpRunBox">

<div id="stepNumber"><spring:message code="teacher.run.setup.1"/><span class="blueText">&nbsp;<spring:message code="teacher.run.setup.2"/></span></div>

<h5><spring:message code="teacher.run.setup.3"/>&nbsp;<em><spring:message code="teacher.run.setup.4"/></em>&nbsp;<spring:message code="teacher.run.setup.5"/><br/><spring:message code="teacher.run.setup.6"/></h5>

<h5><spring:message code="teacher.run.setup.7"/>&nbsp;<em>[Library/Customized]</em>&nbsp;<spring:message code="teacher.run.setup.8"/></h5>

<table id="setupProjectTable" border="1" cellpadding="3" cellspacing="2">
	<tr id="setupProjectTableR1">
		<td style="width:40%;">Project Title</td>
		<td>Project ID</td>
		<td style="width:25%;">Keywords</td>
		<td>Grade</td>
		<td>Total Time</td>
		<td>Computer Time</td>
		<td>Usage</td>
	</tr>
	<tr id="setupProjectTableR2">
		<td class="setupProjectTitle">${project.projectInfo.name}</td>
		<td>${project.id}</td>       		   
		<td>${project.projectInfo.keywords}</td>
		<td>${project.projectInfo.gradeLevel}</td>              
		<td>[# hours here]</td>              
		<td>[# hours here]</td>
		<td>[# Runs Here]
	</tr>
	<tr id="setupProjectTableR3">
		<td class="indent15px" colspan="7">Project Description: ${project.projectInfo.description}</td>
	</tr>
	</table>
			
<h5><spring:message code="teacher.run.setup.9"/>&nbsp;<em><spring:message code="teacher.run.setup.10"/></em>&nbsp;<spring:message code="teacher.run.setup.11"/></h5>

</div> <!-- /* End setUpRunBox */-->

</div>
<div align="center">
<form method="post" align="center">
<input type="submit" name="_target0" disabled value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target1" value="<spring:message code="navigate.next" />" />
</form>

</div>  <!-- /* End of the CenteredDiv */-->

</body>
</html>

