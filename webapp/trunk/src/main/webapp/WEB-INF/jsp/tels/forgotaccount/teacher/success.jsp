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

<!-- $Id: $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../<spring:theme code="teacherforgotstylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<script type="text/javascript" src="../../javascript/general.js"></script>	
<title>Forgot username or password - teacher</title>
</head>

<body>


<h2 id="heading"> LOST USERNAME OR PASSWORD </h2>
<h2 id="heading2"> TEACHER/RESEARCHER </h2>

<div id="forgot2"> 
 <ul id="forgotList2">
 <li>
${username}, A new password has been emailed at ${email}. 
You should receive it within a few
minutes.
</li>
<li> If you have any other problems or questions, <a href="#" onclick="displayNotAvailable('This page is not available yet');"
>contact WISE.</a>
</li>
 </ul>
<a href="../../index.html" id="signIn3"> Return to Sign In</a>
</div>

<!-- 
<a href="#" ><img id="signIn3" src="../../images/Sign-in-New-Account.png" alt="return to sign in" name="signInAgain" width="161" height="52" 
onmouseover="swapImage('signIn3','../../images/Sign-in-New-Account-Roll.png');"
onmouseout="swapImage('signIn3','../../images/Sign-in-New-Account.png');"
></a>
-->
</body>
</html>