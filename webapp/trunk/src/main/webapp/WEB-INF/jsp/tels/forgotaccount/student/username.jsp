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

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="studentforgotstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />    
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="../../javascript/general.js"></script>	

<title>STUDENT Forgot Username </title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug). -->

<h1 id="lostTitleBar" class="blueText">Student Lost Username/Password</h1>
    	
<div align="center">

	<br/>
	<h2>Forgot your UserName?</h2>
	
	<ul id="forgotusernamelist">
		<li>Your Username consists of your first name, initial of last name, month of birth, and day of birth.</li>
		<li>For example, a user named Jane Doe with a birthday on March 24 will generally have the username "JaneD324"</li>
	</ul>
	
	
	<h4>If this doesn't help you recall your Username, try one of the following: </h4>
	
	<div id="forgotusernamesuggestions">
	<ol>
		<li>If you know your Student Code you can use it to <b><a href="enterprojectcode.html">Run a Search for your Username</a></b>.</li>
		<li>Or simply ask your teacher for help finding your Username and Password. Hooray for teachers!</li>
	</ol>
	</div>
	
	<br /> 
	<div><a href="../../index.html"> <img id="return"
				src="../../<spring:theme code="return_to_homepage" />"
				onmouseover="swapImage('return', '../../<spring:theme code="return_to_homepage_roll" />');"
				onmouseout="swapImage('return', '../../<spring:theme code="return_to_homepage" />');" /></a></div>

</div>
</div>
</div>          <!--END OF CENTERED DIV-->

</body>
</html>
