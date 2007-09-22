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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="run.list" /></title>
<script language="JavaScript">

function popup(URL, title) {
  window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=300,height=300,left = 570,top = 300');
}
</script>

</head>

<body>
<%@ include file="projectHeader.jsp"%>
<!-- 
<ul id="tabnav2"  >
<li> <a href="#">Current</a></li>
<li> <a href="#">Archived</a></li>
</ul>
 -->
 <div align="center">
<div id="runContent"> 
<br />
<h3 id="headingPos">
To manage a Project Run, click its underlined title.
</h3>


<div id="runBox">

<div style="align:center;">
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
        <c:when test="${gradingParam =='TRUE'}">
            <a href="../grading/gradebystep.html?runId=${run.id}">Grade By Step</a><br/>
			 <a href="#" onclick="javascript:alert('not implemented yet')">Grade By Gdfdfdroup</a><br/>
        </c:when>
        <c:otherwise>
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
		</c:otherwise>
    </c:choose>
    </td>
   </tr>
  </c:forEach>
</table>


</div>

</div>

</div>
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