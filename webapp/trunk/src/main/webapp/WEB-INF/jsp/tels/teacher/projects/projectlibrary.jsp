<%@ include file="../include.jsp"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<title><spring:message code="curnit.list" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="./headerteacherprojects.jsp"%>

<%@ include file="./L2projects_projectlibrary.jsp"%>

<h1 id="titleBar" class="headerText"><spring:message code="curnitlist.project.library" /></h1> 
    	
<br/><br/>

<table align="center" border="1" cellspacing="2" cellpadding="3">
  <tr>
    <th><spring:message code="curnitlist.title" /></th>
    <th><spring:message code="curnitlist.options" /></th>
  </tr>
<c:forEach var="project" items="${projectList}">
  <tr >
  <td style="color:#FF0000; font-weight:bold;"><c:out value="${project.curnit.sdsCurnit.name}"/></td>
  <td style="text-align:center; line-height:150%;"><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	       Set up as a Project Run</a>
	       <br/> 
      <a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	       Preview this project</a>
	       <br/> 
      <a href="<c:url value="http://tels-develop.soe.berkeley.edu:8080/maven-jnlp-snapshot/jnlp-tests/jardiff/javachecker-1.1.jnlp"></c:url>">
           Check if your computer can preview/run this project
      </a>
  </td>
  </tr>
</c:forEach>
</table>

</div>

</body>
</html>
