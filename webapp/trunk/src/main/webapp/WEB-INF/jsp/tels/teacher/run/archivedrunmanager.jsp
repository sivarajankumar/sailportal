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

<!-- $Id: myprojectruns.jsp 1044 2007-09-08 21:02:03Z tony $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title>Teacher:Projects - Archived Run Manager </title>
<script language="JavaScript">

function popup(URL, title) {
  window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=300,height=300,left = 570,top = 300');
}
</script>

</head>

<body>
<%@ include file="projectHeader.jsp"%>

<div id="runContent"> 
<br />
<a href="myprojectruns.html"> Back to My Project Runs </a>
<h3 id="headingPos">
Project Run Manager (archived projects)
</h3>
<h3> Archive Actions: </h3>
<table>
<tr>
<td> <a href="#"> Reactivate Project </a> </td>
<td>Moves the archived project run back to Current folder. Also reactivates the project for registered students. </td>
</tr>
<tr>
<td> <a href="#"> Project Information </a> </td>
<td> View project information including <a href="../projects/overview.html"> Overview </a>, <a href="#"> Teacher Guide </a>, <a href="#"> Learning Goals </a>, <a href="#"> Credits. </a></td>
</tr>
<tr>
<td> <a href="#"> View Students </a></td>
<td> View a list of all students associated with this project run, per period.</td>
</tr>
<tr>
<td> <a href="#"> View Student Work </a></td>
<td> View all student work stored with the project run.</td>
</tr>
<tr>
<td> <a href="#"> Report a Problem </a></td>
<td> Send a message to WISE describing content or functionality problems found in this project.</td>
</tr>
</table>
</div>
</body>
</html>




























<!-- 

<h1>Archived Runs</h1>
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th><spring:message code="run.information" /></th>      
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${ended_run_list}">
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
      <a href="#" onclick="javascript:popup('manage/startRun.html?runId=${run.id}')">Un-archive this run</a>
    </td>
   </tr>
  </c:forEach>
</table>


-->