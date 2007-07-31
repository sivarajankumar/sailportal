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

<!-- $Id: login.jsp 341 2007-04-26 22:58:44Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="lostpassword.title" /></title>
</head>

<body>

<%@ include file="teacherHeader.jsp"%>
<h2 class="center"><spring:message code="lost.username-or-password" /></h2>
<h1 class="center"><spring:message code="lostpassword.teacher-or-researcher" /></h1>

<div id="right" class="widthAdj6">
<ul class="bigFont1">
<li><spring:message code="lostpassword.teacher.username" />
<b>${someValue}</b><spring:message code="lostpassword.teacher.error" />
</li>
</ul>
</div>

<div id="verticalNavigation">
<ul>
<li><a href="#"> <spring:message code="lostpassword.teacher.try-again" /></a></li>
<li><a href="#"><spring:message code="lostpassword.teacher.contact-wise" /></a></li>
</ul>
</div>

</body>
</html>
