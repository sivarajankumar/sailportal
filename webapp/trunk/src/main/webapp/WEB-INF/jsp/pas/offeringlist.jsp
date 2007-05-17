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
<title><spring:message code="application.title" /> <spring:message code="title.separator" /> <spring:message code="offerings.list" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<div id="columns">
<div id="left"><%@ include file="logout.jsp"%>
</div>

<div id="right">
<h2><spring:message code="hello" /> ${user.userDetails.username}</h2>
<spring:message code="offerings.list" />
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="offering.name.heading" /></th>
      <th><spring:message code="offering.edit.heading" /></th>
      <th><spring:message code="offering.workgroup.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="offering" items="${offering_list}">
  <tr>
    <td>${offering.sdsOffering.name}</td>
    <td><a href="authoringjnlplauncher.html?curnit_url=${offering.sdsOffering.sdsCurnit.url}"><spring:message code="edit.curnit" /></a></td>
    <td>
      <c:choose>
        <c:when test="${fn:length(workgroup_map[offering]) == 0}" >
        <spring:message code="no.workgroups" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[offering]}">
              <a href="${http_transport.baseUrl}/offering/${offering.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">${workgroup.sdsWorkgroup.name}</a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>
    </td>
   </tr>
  </c:forEach>
</table>
</div>

</div>

<%@ include file="footer.jsp"%>

</body>
</html>