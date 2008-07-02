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
<title>Manage All Project Runs</title>

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />

</head>

<body>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

<%@ include file="adminheader.jsp"%>

<h5 style="color:#0000CC;"><a href="index.html">Return to Main Menu</a></h5>

<table id="adminManageRunsTable">
  <thead>
    <tr>
      <th>Project Run Title</th>
      <th>Overview</th>
      <th>Student Code</th>
      <th>Teacher(s)</th>
      <th>Started On</th>
      <th>Ended On</th>
      <th>Management Tasks</th>
    </tr>
  </thead>
  <c:forEach var="run" items="${runList}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><a href="../teacher/projects/projectinfo.html?projectId=${run.project.id}">See Project Overview</a></td>
    <td>${run.runcode}</td>
    <td><c:forEach var="owner" items="${run.owners}">${owner.userDetails.username}</c:forEach>
    <td><fmt:formatDate value="${run.starttime}" type="both" dateStyle="short" timeStyle="short" /></td>
    <td><fmt:formatDate value="${run.endtime}" type="both" dateStyle="short" timeStyle="short" /></td>
    <td><a href="../teacher/management/viewmystudents.html?runId=${run.id}">Manage students in this run</a></td>
   </tr>
  </c:forEach>
</table>

			
</div>

</body>
</html>