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

<!-- $Id: curnitlist.jsp 660 2007-07-16 22:03:10Z hiroki $ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="success.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<div id="columns">
<div><%@ include file="logout.jsp"%>
	<a href="runlist.html"><spring:message code="run.list" /></a><br />
</div>

<div>
<h2><spring:message code="hello" /> <authz:authentication operation="username" /></h2>

<table align="center" bgcolor="#008800" border="0" cellspacing="2" cellpadding="3">
  <tr bgcolor="#CCCCCC">
    <th>Project Title</th>
    <th>Options</th>
  </tr>
<c:forEach var="curnit" items="${curnitlist}">
  <tr bgcolor="#FFFF88">
  <td><c:out value="${curnit.sdsCurnit.name}"/></td>
  <td><a href="<c:url value="createrun.html"><c:param name="curnitId" value="${curnit.id}"/></c:url>">
	       <font color="BLACK">Set up a Run For My Class</font>
      </a><br />
      
  </td>
  </tr>
</c:forEach>
</table>
</div>

</div>

<%@ include file="footer.jsp"%>

</body>
</html>
