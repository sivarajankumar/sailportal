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
</head>

<body>

<table border="1">
  <thead>
    <tr>
      <th>Run Name</th>
      <th>Project Info</th>
      <th>Runcode</th>
      <th>Teacher(s)</th>
      <th>When the run was started</th>
      <th>When the run was ended</th>
      <th>Management tasks</th>
    </tr>
  </thead>
  <c:forEach var="run" items="${runList}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><a href="../teacher/projects/projectinfo.html?projectId=${run.project.id}">Get Project Info</a></td>
    <td>${run.runcode}</td>
    <td><c:forEach var="owner" items="${run.owners}">${owner.userDetails.username}</c:forEach>
    <td>${run.starttime}</td>
    <td>${run.endtime}</td>
    <td><a href="../teacher/management/viewmystudents.html?runId=${run.id}">Manage Students in this run</a></td>
   </tr>
  </c:forEach>
</table>



</body>
</html>