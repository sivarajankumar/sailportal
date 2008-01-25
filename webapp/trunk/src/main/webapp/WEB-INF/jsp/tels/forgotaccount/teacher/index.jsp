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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="teacherforgotstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script type="text/javascript" src="./javascript/tels/general.js"></script>			    
<title>Forgot username or password - teacher</title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="../student/headermain.jsp"%>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

<h1 id="lostTitleBar" class="blueText">Lost Username or Password</h1>
    	
<br /> 

<h2 class="center">TEACHER / RESEARCHER</h2>

<!-- Support for Spring errors object -->
<div id="errorMessageFormat">
	<spring:bind path="userDetails.*">
	  <c:forEach var="error" items="${status.errorMessages}">
	      <br /><c:out value="${error}"/>
	  </c:forEach>
	</spring:bind>
</div>

<form id="username" method="post" action="index.html" commandName="userDetails">

<div id="boxSecondary">
 <h2><b>Remember Your Username but Forgot Your Password?</b></h2>
 <h5>Enter Your Username and click the button. A new password will be sent to your registered email address.</h5>
 		<b><label for="send_username" /><spring:message code="login.username" /> </b>
		<input type="text" id="username" name="username" size="30" tabindex="1" /> <br /><br /><br />
		<input type="submit" name="sendpassword" id="sendpassword" value="Email My Password"  />
 </div>


<div id="boxSecondary">
	<h2><b>Forgot Your Username?</b></h2>
	<h5>Enter the email address you used when registering for WISE and click the button. <br />Your UserName and a new Password will be sent to this email address.</h5>
	<b><label for="send_usernamepassword" /><spring:message code="lostpassword.teacher.email" /></b>
	<input type="text" name="emailAddress" id="emailAddress" size="40" tabindex="2" /><br /><br /><br />
	<input type="submit" name="sendemailAndPwd" id="sendEmailAndPwd" value="EMail My Username and Password" />
</div>

<h3>If you're still stuck, <a href="#" onclick="displayNotAvailable('This page is not available yet.');">contact WISE.</a></h3>

 </form>

</div>
</div>
</div>

</body>
</html>



