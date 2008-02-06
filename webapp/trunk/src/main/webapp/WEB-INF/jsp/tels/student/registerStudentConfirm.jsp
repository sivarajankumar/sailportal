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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
  
<title><spring:message code="signup.title" /></title>

<script type="text/javascript" src=".././javascript/pas/utils.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
</head>


<body>

<div id="centeredDiv">

<%@ include file="header.jsp"%>

<h1 id="registrationTitle" class="blueText">Student Registration</h1>

<div id="subtitleConfirm">
			<h4>Account Created!</h4>
			<h4> Your new Username is: <span><input id="usernameConfirm" name="username" value="${username}"/></span></h4>
			<ul>
		    <li>Your Username combines your name and birthday information. Write it down so you don't forget!</li>
		    <li>Example Username: John Smith with birthday 3/24 would have the Username "<em>JohnS324</em>" <br />
			    <span class="smallText">(his first name + initial of last name + birthday information)</span> </li>
			</ul>
</div>

<table id="confirmationButtons" width="55%" border="0" cellpadding="5" cellspacing="5">
  <tr>
    <td class="width1"><a href="registerstudent.html" 
    onmouseout="MM_swapImgRestore()" 
    onmouseover="MM_swapImage('Register Another Student','','../themes/tels/default/images/student/Register-Another-Roll.png',1)">
    <img src="../themes/tels/default/images/student/Register-Another.png" alt="Register Another Student" width="161" height="52"  id="Register Another Student"/></a></td>
    
    <td class="width2"><a href="../index.html" 
    onmouseout="MM_swapImgRestore()" 
    onmouseover="MM_swapImage('Return to Home Page','','../themes/tels/default/images/Go-To-Home-Page-Roll.png',1)">
    <img src="../themes/tels/default/images/Go-To-Home-Page.png" alt="Go to Home Page & Sign In" width="161" height="52"  id="Return to Home Page" /></a></td>
  </tr>
  <tr>
    <td>Select this option if you are working in a team.  All teammates should register before you start the project.</td>
    <td>Select this option if everyone on your team has registered (or if you are working on your own). Return to the home page and type your new Username and Password in the <em>Sign In</em> area.</td>
  </tr>
</table>


</div>  <!-- /* End of the CenteredDiv */-->

</body>

</html>




