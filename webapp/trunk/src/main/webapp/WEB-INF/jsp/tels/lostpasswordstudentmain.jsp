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

<h2 class="center"><spring:message code="forgotten.information" /></h2>
<h1 class="center"><spring:message code="lostpassword.student" /></h1>
<p class="center bigFont1"><spring:message code="lostpassword.what-have-you-forgotten" /></p>
<div id="verticalNavigation">
<ul class="bigFont1">
<li><a href="lostpasswordstudentusername.html"><spring:message code="lostpassword.forgot-my-username" /> </a></li>
<li><a href="lostpasswordstudentpassword.html"><spring:message code="lostpassword.forgot-my-password" /></a></li>
</ul>
<div id="right" class="center border widthAdj5">
<p class="center smallFont"><spring:message code="lostpassword.what-to-do" /></p>
</div>

<ul>
<li><a href="index.html"><spring:message code="wise.return-to-sign-in" /></a></li>
</ul>
</div>
</body>
</html>
