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

<%@ include file="header.jsp"%>

<h1>Student lost password</h1>

<div id="columns">
<div id="left">
</div>

<spring:message code="lostpassword.what-have-you-forgotten" />
<br />
<br />
<br />
lost <a href="findstudentusername.html"><spring:message code="lostpassword.student.username" /></a>?<br />
<br />
<br />
<spring:message code="lostpassword.are-you-a" /><a href="findstudentpassword.html"><spring:message code="lostpassword.student.password" /></a>?<br /></div>

</div>



</body>
</html>
