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

<!-- $Id$ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" href="./themes/tels/default/styles/styles.css"/>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<title><spring:message code="application.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<h1>TELS HOMEPAGE</h1>

<div class="welcome"> 
<h1 class="headerPos"> WELCOME TO WISE </h1>
<p class="parastyle"> 
Harness the power of the Internet...wisely. WISE is a simple yet powerful learning environment where students examine key science curriculum within the context of real world science controversies. WISE projects complement your standards-based science curriculum with an engaging interactive approach that appeals to grade 5-12 student. Students explore information, write reflective notes, discuss theories, and organize their arguments...at school or working from home. Teachers can choose from a wide variety of projects, create their own custom projects, and grade student work online. Best of all, WISE is completely 
free! 
</p>

<ul class="blue">

<li>
<a href="http://wise.berkeley.edu/pages/intro/wiseFlashIntro.php">
Overview Of WISE
</a>
</li>

<li> Preview a Project </li>

<li>
<a href='http://www.telscenter.org/confluence/display/WPSD/WISE+FAQ'>
Common Questions
</a>
</li>

&nbsp;&nbsp;
<li>
<a href="http://wise.berkeley.edu/pages/joinBranch.php">
Join WISE
</a>
</li>

</ul>

</div>

<div align="right">
<h3> SIGN IN </h3>
<p>
<b>
(Existing WISE Members) 
</b>
<label for="username"> USERNAME: </label> 
<input type="text" id = "username"  value = "" />
</p>
<!--<label for="username"> USERNAME: </label> 
<input type="text" id = "username"  value = "" />
<label for="password"> PASSWORD: </label>
<input type="password" id = "password" value = "" />
<label for="male">Male</label>-->
<label for="male" value="male">Male</label>

Hi <br/>
<label for="lname"><font color="#000000"> Last Name: </font></label>

<input type="radio" name="sex" id="male" />
<input type="radio" name="sex" id="female" />

</div>






<br></br>


<div id="banner">
<h1><spring:message code="banner.heading" /></h1>
</div>

<div id="columns">
<div id="left">
<h2><spring:message code="welcome" /> <authz:authentication
    operation="username" /></h2>
</div>

<div>
<h2> Welcome to WISE </h2>
</div>



<div id="right">
<%@ include file="logout.jsp"%>
<a href="login.html"><spring:message code="log.in" /></a><br />
<a href="register.html"><spring:message code="joinwise" /></a><br />
</div>
</div>

<%@ include file="footer.jsp"%>

</body>
</html>
