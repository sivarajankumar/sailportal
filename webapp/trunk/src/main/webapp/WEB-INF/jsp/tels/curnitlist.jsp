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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<title><spring:message code="curnit.list" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<div id="titleBar">
    	<h1 class="blueText"><spring:message code="curnitlist.project.library" /></h1></div>
    	
<table align="center" border="1" cellspacing="2" cellpadding="3">
  <tr>
    <th><spring:message code="curnitlist.title" /></th>
    <th><spring:message code="curnitlist.options" /></th>
  </tr>
<c:forEach var="curnit" items="${curnitlist}">
  <tr>
  <td><c:out value="${curnit.sdsCurnit.name}"/></td>
  <td><a href="<c:url value="createrun.html"><c:param name="curnitId" value="${curnit.id}"/></c:url>">
	       <spring:message code="curnitlist.setup.for.class" />
      </a><br />
      
  </td>
  </tr>
</c:forEach>
</table>

</div>

</body>
</html>
