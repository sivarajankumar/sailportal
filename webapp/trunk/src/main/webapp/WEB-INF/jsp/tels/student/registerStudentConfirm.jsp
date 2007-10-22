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
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
  
<title><spring:message code="signup.title" /></title>

<script type="text/javascript" src=".././javascript/pas/utils.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
</head>


<body>

<div id="centeredDiv">

<%@ include file="studentHeader.jsp"%>

<div id="studentRegTitle">
    	<h1 class="blueText">Student Registration</h1>
</div>
     
<div id="subtitle">
	<h4>Account Created!</h4>
	<h4> Your new Username is: <span class="blueText"> <input name="username" value="${username}"/> </span></h4>
	<ul>
    <li>Please memorize (or write down) your Username and Password! You'll need them every time you sign into WISE.</li>
    <li>Note:  To help you remember your Username it combines your name and birthday information. </li>
	<li>Example:  John Smith with the birthday 3/24 has the Username "<em>JohnS324</em>" <br />
	    <span class="smallText">(first name + initial of last name + birthday information)</span>
	  </h5>
	  </li>
	</ul>
</div >

<table id="confirmationButtons" width="52%" border="0" cellpadding="5" cellspacing="5">
  <tr>
    <td width="45%"><a href="../student/studentRegister.html" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Register Another Student','','../images/Register-Another-Roll.png',1)"><img src="../images/Register-Another.png" alt="Register Another Student" width="161" height="52" border="0" id="Register Another Student"/></a></td>
    
    <td width="55%"><a href="../index.html" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Return to Home Page','','../images/Go-to-Home-Page-Roll.png',1)"><img src="../images/Go-to-Home-Page.png" alt="Go to Home Page & Sign In" width="161" height="52" border="0" id="Return to Home Page" /></a></td>
  </tr>
  <tr>
    <td valign="top">Select this option if you are working in a team.  All teammates should register before you start the project.</td>
    <td valign="top">Select this option once everyone on your team has registered (or if you are working on your own). Then return to the home page, and type your new Username and Password in the <em>Sign In</em> area.</td>
  </tr>
</table>


</div>  <!-- /* End of the CenteredDiv */-->

</body>

</html>




