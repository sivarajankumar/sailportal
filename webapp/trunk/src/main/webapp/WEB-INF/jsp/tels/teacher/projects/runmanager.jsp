<%@ include file="../include.jsp" %>

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

<!-- $Id: overview.jsp 997 2007-09-05 16:52:39Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >

<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet"
    type="text/css" />
 <script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<title>Projects Overview</title>
</head>

<body>
<%@ include file="projectHeader.jsp"%>
<!-- 
<ul id="tabnav2"  >
<li> <a href="#">Current</a></li>
<li> <a href="#">Archived</a></li>
</ul>
 -->
 <a href="../run/myprojectruns.html">Back to My Project Runs </a>
 <h2>PROJECT RUN MANAGER</h2>
<div id="overviewContent"> 
<br />

<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th><spring:message code="run.information" /></th>      
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${current_run_list}">
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
      <br/>
      <a href="#" onclick="javascript:popup('manage/endRun.html?runId=${run.id}')">Archive this run</a>
    </td>
   </tr>
  </c:forEach>
</table>

</div>

<h3>Management Actions:</h3>
<table>
<tr>
<td><a href="changeperiods.html">Change Periods</a></td>
<td>Change the classroom periods for this project run.</td>
</tr>
<tr>
<td><a href="#">Project Information</a></td>
<td>View project information including <a href="overview.html">Overview</a>, <a href="#">Teacher Guide</a>, <a href="#">Learning Goals</a>, <a href="#">Credits.</a></td>
</tr>
<tr>
<td><a href="#">Manage Students</a></td>
<td>Shortcut to Management/View Students. View and manage all students registered for this project.</td>
</tr>
<tr>
<td><a href="#">Assess Students</a></td>
<td>Shortcut to Management/Assess Students. Assess work done by students registered for this project.</td>
</tr>
<tr>
<td><a href="#">Student Message</a></td>
<td>Send a message to one or more students that appears next time they sign in.</td>
</tr>
<tr>
<td><a href="#">View Project Codes</a></td>
<td>View the project codes for this project and instructions on how students use these codes.</td>
</tr>
<tr>
<td><a href="#">Report a Problem</a></td>
<td>Send a message to WISE describing content or functionality problems found in this project.</td>
</tr>
<tr>
<td><a href="#">Archive Project Run</a></td>
<td>Move a completed project run to your Archive folder (and all of your students' Archive folders)</td>
</tr>

</table>


</body>
</html>

