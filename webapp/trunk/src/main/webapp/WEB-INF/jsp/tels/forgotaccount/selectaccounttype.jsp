<%@ include file="../include.jsp"%>
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
<link href="../<spring:theme code="forgotstylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<script src="../javascript/tels/general.js" type="text/javascript" > </script>
<title><spring:message code="lostpassword.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<h2 id="heading"> LOST USERNAME/PASSWORD </h2>
<h2 id="heading2"> What sort of WISE account do you have?</h2>

<div id="account">  
<a href="student/index.html">
  <img id="studentAccount" src="../themes/tels/default/images/Student-Account.png" width="228" height="41"
  onmouseover="swapImage('studentAccount','../themes/tels/default/images/Student-Account-Roll.png');" 
  onmouseout="swapImage('studentAccount','../themes/tels/default/images/Student-Account.png');" > 
 </a>
  or
<a href="teacher/index.html"> 
<img id="teacherAccount" src="../themes/tels/default/images/Teacher-Account.png" height="46"
  onmouseover="swapImage('teacherAccount','../themes/tels/default/images/Teacher-Account-Roll.png');" 
  onmouseout="swapImage('teacherAccount','../themes/tels/default/images/Teacher-Account.png');" ></a>
</div>

<div id="select">
<h3 id="italic">Select a choice above.</h3>
<a href="../index.html">Return to Sign In</a>
<!-- replace with proper image when ready
<img id="signInAgain" src="../images/Sign-in-New-Account.png" 
  onmouseover="swapImage('signInAgain','../images/Sign-in-New-Account-Roll.png');"
  onmouseout="swapImage('signInAgain','../images/Sign-in-New-Account.png');" >
-->
</div>




</body>
</html>
