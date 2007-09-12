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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src="./javascript/tels/rotator.js" type="text/javascript" ></script>
<title><spring:message code="teacher.setup-project-run-step-one" /></title>

</head>

<body>
<%@ include file="../../teacherHeader.jsp"%>

<h2 id="right2" class=" maroon"> <spring:message code="teacher.setup-project-classroom-run" /></h2>
<h3 class="blueColor"><b class="blackColor"><spring:message code="teacher.setup-project-run-step1" /></b>
<spring:message code="teacher.setup-project-run-step1-desc" />
</h3>
<p class="bigFont1"> <spring:message code="teacher.selected-projects-message" />
<b><spring:message code="teacher.type-of-project" /></b> 
<spring:message code="teacher.project-run-classroom-message" /> 
</p>

<div>
<p>
<spring:message code="setuprun.confirmation.run.projectid" /> <spring:bind path="runParameters.curnitId"><c:out value="${status.value}"/></spring:bind>
</p>
<p>
<spring:message code="createrun.projectdescription" /> <c:out value="${project.description}"/>
</p>
<p class="bigFont1 ">
<b> <spring:message code="teacher.correct-project" /> </b>
</p> 
</div>

<form method="post" id="right" class="widthAdj6">
<input type="submit" name="_target0" disabled value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target1" value="<spring:message code="navigate.next" />" />
</form>
</body>
</html>

<!--  
<ul id="tabnav" class="north_0 widthAdj4">
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"><spring:message code="teacher.overview" /></a></li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"><spring:message code="teacher.project-library" /></a></li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"><spring:message code="teacher.project-runs" /></a></li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"><spring:message code="teacher.bookmarked-projects" /></a></li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"><spring:message code="teacher.customized-projects" /></a></li>
</ul><br />
-->

