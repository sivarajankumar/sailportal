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

<!-- $Id: setupRun3.jsp 357 2007-05-03 00:49:48Z archana $ -->

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

<title><spring:message code="teacher.setup-project-run-step-four" /></title>
</head>
<body>

<div id="centeredDiv">

<%@ include file="../../projects/headerteacherprojects.jsp"%>

<%@ include file="L2projectsnohighlight.jsp"%>

<h1 id="titleBarSetUpRun" class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1>

<div id="setUpRunBox">

	<div id="stepNumber"><spring:message code="teacher.run.setup.32"/><span class="blueText">&nbsp;<spring:message code="teacher.run.setup.33"/></span></div>

	<h5><spring:message code="teacher.run.setup.34"/>&nbsp;<a href="#" onclick="javascript:alert('Lesson Plan not available yet')"><spring:message code="teacher.run.setup.35"/></a>
	&nbsp;<spring:message code="teacher.run.setup.36"/></h5>

	<h6 class="indent15px"><spring:message code="teacher.view-lesson-plan" htmlEscape="true" /></h6>
	<h5><spring:message code="teacher.skip-lesson-plan" />
		<i><spring:message code="navigate.next" /></i>
		<spring:message code="teacher.skip-lesson-plan-below" /></h5>
 </div>    
 
<form method="post" class="center">
<input type="submit" name="_target2" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target4" value="<spring:message code="navigate.next" />" />
</form>
</div>

<div align="centeredDiv">

</body>
</html>
