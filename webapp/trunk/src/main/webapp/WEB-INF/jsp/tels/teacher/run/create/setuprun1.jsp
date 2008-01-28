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

<%@ include file="../../projects/headerteacherprojects.jsp"%>

<%@ include file="L2projectsnohighlight.jsp"%>

<div id="titleBarSetUpRun">
    	<h1 class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1></div>
     	    	    
<div id="setUpRunBox">

<div id="stepNumber">Step 1 of 6:<span class="blueText">&nbsp Confirm Project</span></div>
<h5>This process will help you set up a <em>Project Run</em> so students can load and run the project. You can cancel this process at any time.</h5>

<h4>You have selected the following [Library/Customized] project to run in your classroom:</h4>

<table id="projectTable" border="1" cellpadding="3" cellspacing="2">
	<tr id="projectTableR1">
		<td>Project Title</td>
		<td>Project ID</td>
		<td>Topics/Keywords</td>
		<td>Grade</td>
		<td>Total Time</td>
		<td>Computer Time</td>
		<td>Usage</td>
	</tr>
	<tr id="projectTableR2">
		<td><strong>${project.curnit.sdsCurnit.name}</strong></td>
		<td>"${project.curnit.id}"</td>
		<td>
		</td>
		<td>"${project.curnit.totalTime}"</td>
		<td>"${project.curnit.computerTime}"</td>
		<td>127 runs</td>
	</tr>
	<tr id="projectTableR3">
		<td class="indent15px" colspan="7">${project.curnit.description}</td>
	</tr>
	</table>
			
<h4>If this is the correct project, select <em>Next</em> below.</h4>

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

