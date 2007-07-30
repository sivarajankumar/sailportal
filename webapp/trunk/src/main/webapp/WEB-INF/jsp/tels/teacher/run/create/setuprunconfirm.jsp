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

<h1><spring:message code="setuprun.confirmation.projectruncreated" /></h1>

<h3><spring:message code="setuprun.confirmation.whereisnewrun" /></h3>


<div id="navigation" class="center north4">
<ul>
<li class="vertical"><spring:message code="setuprun.confirmation.run.title" />
<c:out value="${run.sdsOffering.name}" />
</li>
<li class="vertical"><spring:message code="setuprun.confirmation.run.createdtime" />
<c:out value="${run.starttime}" />
</li>
<li class="vertical"><spring:message code="setuprun.confirmation.run.projectid" />
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

<p><a href="../../../runlist.html"> <img id="projectRuns"
	src="<spring:theme code="gotomyprojectruns" />" 	
	/>
</a></p>

</body>
</html>
