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

<!-- $Id$ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/rotator.js" type="text/javascript" ></script>
<title><spring:message code="run.list" /></title>
</head>

<body>
<div>
<a href="../index.html">Go back to Teacher Home page</a>
</div>
<!--  
<p class="userInfo">
${user.userDetails.username}
</p>

<div id="navigation" class="north2 widthAdj4">
<ul class="bigFont1">
<li> <a href="teacher/index.html"> <img src="<spring:theme code="home" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="projects_selected" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="management" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="help" />" style="border:0px;"/> </a> </li>
</ul>
</div>
-->

<h2 class="north2">
<spring:message code="runlist.my.project.runs" />
</h2>

<div id="columns" class="north2">

<div style="align:center;">

<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th><spring:message code="run.information" /></th>      
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${run_list}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><table border="1">
          <tr>
            <th><spring:message code="run.period" /></th>
            <th><spring:message code="teacher.project-code" /></th>
            <th><spring:message code="run.students" /></th>
          </tr>
          <c:forEach var="period" items="${run.periods}">
            <tr>
              <td>${period.name}</td>
              <td>${run.runcode}-${period.name}</td>
              <td>
                <c:choose>
                  <c:when test="${fn:length(period.members) == 0}" >
                    None
                  </c:when>
                  <c:otherwise>
                    <c:forEach var="member" items="${period.members}">${member.userDetails.firstname} ${member.userDetails.lastname}</c:forEach>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table></td>
    <td>
      <c:choose>
        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
        <spring:message code="no.workgroups" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[run]}">
              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">${workgroup.sdsWorkgroup.name}</a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>
    </td>
   </tr>
  </c:forEach>
</table>
</div>

</div>

</body>
</html>