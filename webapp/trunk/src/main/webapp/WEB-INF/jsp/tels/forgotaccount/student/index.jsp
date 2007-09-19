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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../<spring:theme code="studentforgotstylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />    
<script type="text/javascript" src="../../javascript/general.js"></script>	
<title>Forgot username or password - student</title>
</head>

<body>
<%@ include file="header.jsp"%>

<h2 class="center"> FORGOTTEN INFORMATION </h2>
<h1 class="center"> STUDENT </h1>
<div align="center"> 
<p style="font-size:1.2em;font-weight:100;">What have you forgotten?</p>
<h2 align="center"><a href="username.html">Forgot My Username</a></h2>
<p align="center">OR</p>
<h2 align="center"><a href="password.html">Forgot My Password</a></h2>
<p align="center">
If you've forgotten your Username and Password (hey, it happens to the
best of us), first follow the Username link, then follow the Password link.
</p><br />
<a align="center" href="../../index.html"> Return to Sign In </a>

</div>

<!--
<div id="footer">
If you've forgotten your Username and Password (hey, it happens to the
best of us), first follow the Username link, then follow the Password link.<br />
<a href="../../index.html"> Return to Sign In </a>
 
<img id="signInAgain" src="../../images/Sign-in-New-Account.png" width="161" height="52"
onMouseOver="swapImage('signInAgain','../../images/Sign-in-New-Account-Roll.png');"
onMouseOut="swapImage('signInAgain','../../images/Sign-in-New-Account.png');">

</div>
 -->
</body>
</html>

