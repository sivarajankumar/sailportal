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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="studentforgotstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />    
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
   
<script type="text/javascript" src="../../javascript/tels/general.js"></script>	
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>	

<title>Teach Lost Username and/or Password:  confirmation screen</title>
</head>

<body>

<div id="centeredDiv">
    	
<%@ include file="headermain.jsp"%>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug). -->

<h1 id="lostTitleBar" class="blueText">Student Lost Username/Password</h1>

<h1> Forgot your Username and/or Password? </h1>

<div id="studentpasswordremindersuggestion"> 
	<ul>
		<li class="forgotPasswordInstructionText3">A new Username and Ppassword has been emailed to ${email}. 
You should receive this information within a few minutes.</li>
		<li class="forgotPasswordInstructionText">If you have any other problems or questions, <a href="#" onclick="displayNotAvailable('This page is not available yet');"
>contact WISE.</a></li>
	</ul>
	
</div>

<a href="../../index.html"> 
		<img id="return" src="../../<spring:theme code="return_to_homepage" />"
		onmouseover="swapImage('return', '../../<spring:theme code="return_to_homepage_roll" />');"
		onmouseout="swapImage('return', '../../<spring:theme code="return_to_homepage" />');" /></a>

</div>
</div>

</body>
</html>

