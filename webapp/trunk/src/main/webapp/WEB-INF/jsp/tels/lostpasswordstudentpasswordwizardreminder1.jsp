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
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<title><spring:message code="teacher.setup-project-run-step-one" /></title>

</head>
<body>
<%@ include file="teacher/header.jsp"%>
<div id="navigation" style="text-align:center;position:relative;
	bottom:35px;width:990px;">
<ul style="font-size: 1em;">
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.home" /> </a> </li>
<li style="border:1px solid black;"> <a href="#"><spring:message code="banner.projects" /> </a> </li>
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.management" /> </a> </li>
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.community" /> </a> </li>
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.help" /> </a> </li>
</ul>
</div>

<div>
<!-- Support for Spring errors object -->
<spring:bind path="reminderParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>
</div>
  
<p style="font-size: 1em;" class="north0">step 1: </p> 
<form id="username" method="post" style="text-align:center;" class="north0" commandName="reminderParameters">

 <p><label for="send_username"><spring:message code="login.username" /></label>
  <input type="text" name="username" id="username" style=" font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 1em;
	width: 110px;" tabindex="1" /></p>

<div>
  <input type="submit" name="_target1" value="<spring:message code="navigate.next" />" />
  <input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
</div>

</form>
</body>
</html>