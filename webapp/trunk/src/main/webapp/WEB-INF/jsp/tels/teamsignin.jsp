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

<!-- $Id: signup.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="signup.title" /></title>
<script type="text/javascript" src="./javascript/pas/utils.js"></script>
</head>

<body>

<%@ include file="studentHeader.jsp"%>

<div style="position:relative;bottom:40px;">
<h1 style="text-align:center;"><spring:message code="student.team-sign-in" /></h1>
<h3 style="text-align:center;"><spring:message code="student.user" /> Username <spring:message code="student.is-signed-in" /></h3>
<h3 style="text-align:center;"><spring:message code="student.teammates-sign-in" /> </h3>
<h5 style="text-align:center;"><spring:message code="student.absent-today-desc" /></h5>
</div>
<form:form>
<div style="border:1px solid black;text-align:center;position:relative;
	bottom:40px;">
<label> UserName 1: </label> 
<input type="text" value="" size=20 />
</div>
<div style="border:1px solid black;text-align:center;position:relative;
	bottom:40px;">
<label> UserName 2: </label> 
<input type="text" value="" size=20 />
<br />
<label> Password: </label> 
<input type="text" value="" size=20 />
<br />
</div>
<div style="border:1px solid black;text-align:center;position:relative;
	bottom:40px;">
<label> UserName 3: </label> 
<input type="text" value="" size=20 />
<br />
<label> Password: </label> 
<input type="text" value="" size=20 />
<br />
</div>
</form:form>

<div id="headRows">
<ul>
<li><a href="#"> <spring:message code="forgotten.username-or-password" /></a></li>
<li><a href="#"> <spring:message code="student.team-change" /> </a></li>
</ul>
</div>

<ul style="text-align:center;font-size: 1em;padding:22px;" class="none">
<li style="text-align:center;"> <a href="#" style="text-decoration:none;font-size: 1em;border:1px solid black;"> <spring:message code="student.run-project" /> </a> </li>
<li style="text-align:center;"> <a href="#" style="text-decoration:none;font-size: 1em;border:1px solid black;"> <spring:message code="navigate.cancel" /> </a> </li>
</ul>

<%@ include file="footer.jsp" %>
</body>
</html>




