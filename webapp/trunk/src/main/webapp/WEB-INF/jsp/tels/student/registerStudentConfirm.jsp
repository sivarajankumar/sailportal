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
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="signup.title" /></title>
<script type="text/javascript" src=".././javascript/pas/utils.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
</head>

<body>

<%@ include file="studentHeader.jsp"%>

<div class="center">
<h2><spring:message code="student.registration" /></h2>
<h3><spring:message code="login.success" /></h3>
<h4><spring:message code="login.sign-in-message" /></h4>
</div>

<div id="verticalNavigation" class="center">
<ul>
<li><spring:message code="login.username" />
<input name="username" value="${username}"/>
</li>
<li><spring:message code="login.password" />
<input name="password" type="password" value="<authz:authentication operation="password" />" />
</li>
</ul>
</div>

<div id="spacing" class="left">
<ul>
<li > <spring:message code="login.email-info-message" /></li>
<li> <spring:message code="login.remember" /></li>
<li> <spring:message code="login.username-tip" /></li>
</ul>
</div>

<div id="navigation" class="center">
<ul>
<li>
<a href="registerstudent.html"> 
<img id="registerteammate" src="../<spring:theme code="register_another_teammate" />" 
onmouseover="swapImage('registerteammate','../<spring:theme code="register_another_teammate_rollover" />');"
onmouseout="swapImage('registerteammate','../<spring:theme code="register_another_teammate" />');"
/>
</a>
</li>
<!-- 
<li>
<p style="font-size:0.8em;width:315px;margin:-85px 0px 0px 20px;position:relative;top:40px;left:500px;">  
<spring:message code="register.teammate-info" />
</p>
</li>
 -->
</ul>
</div>

<div id="navigation" class="center" style="position:relative;top:10px;">
<ul>
<li class="space1">
<a href="../index.html">
<img id="signinnewaccount" src="../<spring:theme code="sign_in_new_account" />" 
onmouseover="swapImage('signinnewaccount','../<spring:theme code="sign_in_new_account_rollover" />');"
onmouseout="swapImage('signinnewaccount','../<spring:theme code="sign_in_new_account" />');"
/>
</a>
</li>
<!--   
<li>
<p style="font-size:0.8em;width:315px;margin:-260px 0px 0px 20px;
position:relative;top:210px;left:200px;">
<spring:message code="register.team" />
</p>
</li>
-->
</ul>
</div>

</body>
</html>
