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

<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<title><spring:message code="setuprun.confirmation.title" /></title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script>
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="../../../headerteacherprojects.jsp"%>

<%@ include file="../../../L2projectsnohighlight.jsp"%>


<h2 class="center" style="color:rgb(100,0,0);"> <spring:message code="teacher.setup-project-classroom-run" /></h2>
<div class="center">
<div id="setuprunbox">
<h3><spring:message code="setuprun.confirmation.projectruncreated" /></h3>

<b>
<spring:message code="setuprun.confirmation.whereisnewrun" />
<a href="./runlist.html" onclick="javascript:alert('This page is not available yet')"> 
<spring:message code="setuprun.confirmation.myprojectruns" />
</a>
<spring:message code="setuprun.confirmation.end" />
</b>

<div>
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

<h4><spring:message code="setuprun.confirmation.aboutprojectids.heading" /></h4>
<p><spring:message code="setuprun.confirmation.aboutprojectids.text" /></p>

<h4><spring:message code="setuprun.confirmation.aboutprojectcodes.heading" /></h4>
<p><spring:message code="setuprun.confirmation.aboutprojectcodes.text" /></p>
</div>
</div>
<div class="center">
<a href="teacher/run/myprojectruns.html"> <img id="projectRuns"
	src="<spring:theme code="gotomyprojectruns" />"	 	
	onmouseover="swapImage('projectRuns','<spring:theme code="gotomyprojectruns_roll" />');"
	onmouseout="swapImage('projectRuns','<spring:theme code="gotomyprojectruns" />');"
	style="border:0px;"/>
</a>
</div>
</div>
</body>
</html>
