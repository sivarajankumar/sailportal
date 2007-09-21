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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src="./javascript/tels/rotator.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-two" /></title>
</head>
<body>
<%@ include file="../../../teacherHeader.jsp"%>

<h2 class="center" style="color:rgb(100,0,0);"> <spring:message code="teacher.setup-project-classroom-run" /></h2>
<div align="center">
<div id="setuprunbox">
<h3 style="color:rgb(0,0,255);"><b style="color:#000000;"><spring:message code="teacher.setup-project-run-step2" /></b>
<spring:message code="teacher.setup-project-run-step2-desc" />
</h3>

<p style="font-size:1em;width:950px;">
<spring:message code="teacher.currently-active-project-runs" />
</p>
<p style="position:relative;left:20px;width:900px;font-size:0.9em;text-align:justify;">
<spring:message code="teacher.archiving-a-project-run" />
</p>
<p style="font-size:1em;width:950px;">
<spring:message code="teacher.end-or-cont-project-runs" />
<i> <spring:message code="navigate.next" /> </i>
<spring:message code="teacher.end-or-cont-project-runs-cont" />
</p>
<p><spring:message code="createrun.existing" />

<p style="width:950px;"><c:forEach var="run" items="${existingRunList}">
	<tr>
		<td>${run.sdsOffering.name}</td>
	</tr>
</c:forEach>
</p>

<div align="center">
<form method="post" align="center">
<input type="submit" name="_target0" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target2" value="<spring:message code="navigate.next" />" />
</form>
</div>

</div>
</div>
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

