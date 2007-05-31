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

<div class="north">
<h2 class="center"><spring:message code="register.student" /></h2>
<h3 class="center"><spring:message code="login.success" /></h3>
<h4 class="center"><spring:message code="login.sign-in-message" /></h4>
</div>

<div id="navigation" class="center">
<ul>
<li class="vertical"><spring:message code="login.username" />
<input name="username" type="text" value="<authz:authentication operation="username" />" width="25"> </input>

</li>
<li class="vertical"><spring:message code="login.password" />
<input name="password" type="password" value="<authz:authentication operation="password" />" width="25"> </input>
</li>
</ul>
</div>

<div id="spacing" class="north center">
<ul align="left">
<li > <spring:message code="login.email-info-message" /></li>
<li> <spring:message code="login.remember" /></li>
<li> <spring:message code="login.username-tip" /></li>
</ul>
</div>

<div id="verticalNavigation" class="north center">
<ul>
<li class="space1">
<a href="registerstudent.html"> 
<spring:message code="register.teammate" />
</a>
</li>
<li class="space1">
<a href="teacherIndex.html">
<spring:message code="wise.signIn" />
</a>
</li>
</ul>
<ul>
<li>
<p class="justify widthAdj2 smallFont marginAdj3">  
<spring:message code="register.teammate-info" />
</p>
</li>
<li>
<p class="justify widthAdj2 smallFont marginAdj4">
<spring:message code="login.team" />
</p>
</li>

</ul>
 
</div>

</body>
</html>
