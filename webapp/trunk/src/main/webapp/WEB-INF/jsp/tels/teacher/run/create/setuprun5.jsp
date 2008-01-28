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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-five" /></title>
</head>
<body>

<div id="centeredDiv">

<%@ include file="../../projects/headerteacherprojects.jsp"%>

<%@ include file="L2projectsnohighlight.jsp"%>

<div id="titleBarSetUpRun">
    	<h1 class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1></div>

<div id="setUpRunBox">

	<div id="stepNumber">Step 5 of 6:<span class="blueText">&nbsp Preview the Project</span></div>

	<h4><spring:message code="teacher.recommend-preview-project" />
		<a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${projectId}"/></c:url>">
		<spring:message code="teacher.preview-project" /> </a>
		<spring:message code="teacher.before-running" /></h4>
		
	<h5 class="indent15px"><spring:message code="teacher.preview-project-now" /></h5>

	<h4><spring:message code="teacher.cont-no-preview" />
		<i><spring:message code="navigate.next" /></i>
		<spring:message code="teacher.cont-no-preview-below" /></h4>
</div>

<form class="center" method="post">
<input type="submit" name="_target3" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target5" value="<spring:message code="navigate.next" />" />
</form>

</div>
</body>
</html>