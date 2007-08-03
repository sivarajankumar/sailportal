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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
<script language="JavaScript">

function popup(URL) {
  window.open(URL, 'Select Team', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=300,height=300,left = 570,top = 300');
}
</script>
</head>
<%@ include file="header.jsp"%>

<body>
<div id="columns">
<div>
<h2><spring:message code="welcome" /> 
    <authz:authentication operation="firstname" />
</h2>
</div>

</div>

<div>
    <a href="#"	onclick="javascript:popup('addproject.html')">
    <spring:message code="teacher.add-project" />
	</a>
</div>

<div>
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th>Teacher name</th>
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${run_list}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><c:forEach var="owner" items="${run.owners}">${owner.userDetails.username}</c:forEach></td>
    <td>
    <a href="#"	onclick="javascript:popup('selectteam.html?runId=' + ${run.id} )">
	  <img id="runproject" src="../<spring:theme code="run_project" />" />
	</a>
              <!--  
      <c:choose>
        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
        <spring:message code="not.in.workgroup.yet" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[run]}">
              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">
		        <img id="runproject" src="../<spring:theme code="run_project" />"  />              
              </a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>
      -->
              
    </td>
   </tr>
  </c:forEach>
</table>
</div>

</body>
</html>
