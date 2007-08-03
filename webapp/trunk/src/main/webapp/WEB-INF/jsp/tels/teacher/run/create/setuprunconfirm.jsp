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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="setuprun.confirmation.title" /></title>
<script type="text/javascript" src="./javascript/pas/utils.js"></script>
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>

</head>

<body>

<%@ include file="../../../teacherHeader.jsp"%>
<p class="userinfo"> 
<authz:authentication operation="username" />
 </p>
<div id="navigation" class="north2 widthAdj4">
<ul class="bigFont1">
<li> <a href="./teacher/index.html"> <img src="<spring:theme code="home" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="projects_selected" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="management" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="help" />" style="border:0px;"/> </a> </li>
</ul>
</div>
<!--  
<ul id="tabnav" class="north_0 widthAdj4">
<li> <a href="#"><spring:message code="teacher.overview" /></a></li>
<li> <a href="#"><spring:message code="teacher.project-library" /></a></li>
<li> <a href="#"><spring:message code="teacher.project-runs" /></a></li>
<li> <a href="#"><spring:message code="teacher.bookmarked-projects" /></a></li>
<li> <a href="#"><spring:message code="teacher.customized-projects" /></a></li>
</ul><br />
-->

<h2 id="right2" class="widthAdj6 maroon"> <spring:message code="teacher.setup-project-classroom-run" /></h2>
<h3 class="widthAdj6"><spring:message code="setuprun.confirmation.projectruncreated" /></h3>

<b class="widthAdj6">
<spring:message code="setuprun.confirmation.whereisnewrun" />
<a href="./runlist.html" onclick="javascript:displayNothingYet('notexisting.html')"> 
<spring:message code="setuprun.confirmation.myprojectruns" />
</a>
<spring:message code="setuprun.confirmation.end" />
</b>

<div class="widthAdj6">
<ul>
<li><spring:message code="setuprun.confirmation.run.title" />
<c:out value="${run.sdsOffering.name}" />
</li>
<li><spring:message code="setuprun.confirmation.run.createdtime" />
<c:out value="${run.starttime}" />
</li>
<li><spring:message code="setuprun.confirmation.run.projectid" />
<c:out value="${run.sdsOffering.sdsCurnit.sdsObjectId}" />
</li>
<li>
<spring:message code="setuprun.confirmation.run.projectcodes" /><br />
  <c:forEach var="period" items="${run.periods}">
    <c:out value="${run.runcode}" />-<c:out value="${period.name}" />
    (<spring:message code="setuprun.confirmation.run.projectcodes.foryourstudentsinperiod" />
    <c:out value="${period.name}" />)
    <br />
  </c:forEach>
</li>
</ul>
</div>

<h4 class="widthAdj6"><spring:message code="setuprun.confirmation.aboutprojectids.heading" /></h4>
<p class="widthAdj6"><spring:message code="setuprun.confirmation.aboutprojectids.text" /></p>

<h4 class="widthAdj6"><spring:message code="setuprun.confirmation.aboutprojectcodes.heading" /></h4>
<p class="widthAdj6"><spring:message code="setuprun.confirmation.aboutprojectcodes.text" /></p>

<a href="./runlist.html" class="widthAdj6"> <img id="projectRuns"
	src="<spring:theme code="gotomyprojectruns" />"	 	
	style="border:0px;"/>
</a>

</body>
</html>
