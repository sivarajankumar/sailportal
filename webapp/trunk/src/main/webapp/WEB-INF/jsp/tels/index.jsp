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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<h1>TELS HOMEPAGE</h1>

<div id="banner">
<h1><spring:message code="banner.heading" /></h1>
</div>

<div id="columns">
<div id="left">
<h2><spring:message code="welcome" /> <authz:authentication
    operation="username" /></h2>
</div>

<div id="right">
<a href="login.html"><spring:message code="log.in" /></a><br />
<a href="register.html"><spring:message code="joinwise" /></a><br />
</div>
</div>

<%@ include file="footer.jsp"%>

</body>
</html>
