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
<title>Forgot password - student</title>
</head>

<body>
<%@ include file="header.jsp" %>

<h2 id="heading"> FORGOTTEN PASSWORD</h2>
<h1 id="heading2"> STUDENT </h1>


<div id="forgot2" style="border:1px solid black;"> 
<b>Forgot your Password?</b><br />
<ol>
<li>You can always ask your teacher for help looking up your
Username and Password.
</li>
<li>Or you can create a new password for your account after
successfully answering a <a href="passwordreminder.html">Password Reminder</a> question.
</li>
</ol>
</div>
<a id="linkPos3" href="../../index.html">Return to Sign In</a>
<!--
<a href="#" ><img id="signIn4" src="../../images/Sign-in-New-Account.png" alt="return to sign in" name="signInAgain" width="161" height="52" 
onmouseover="swapImage('signIn4','../../images/Sign-in-New-Account-Roll.png');"
onmouseout="swapImage('signIn4','../../images/Sign-in-New-Account.png');"
></a>
-->
</body>
</html>
