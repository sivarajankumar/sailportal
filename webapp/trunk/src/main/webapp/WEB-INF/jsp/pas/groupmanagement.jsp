<%@ include file="include.jsp"%>
<!--
  * Copyright (c) 2007 Encore Research Group, University of Toronto
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

<!-- $Id: -->

 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="application.title" /> <spring:message code="title.separator" /> <spring:message code="group.management" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<div id="columns">
<div id="left">
<%@ include file="menu.jsp" %>
</div>

<div id="right">
<display:table name="${grouplist}" id="group">
  <display:column property="name" titleKey="group.name.heading" />
</display:table>

<a href="addgroup.html">Add a new group</a>
</div>

</div>

<%@ include file="footer.jsp"%>

</body>
</html>