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
<title>Password reminder - student</title>
</head>

<body>
<%@ include file="header.jsp" %>

<h2 id="heading"> PASSWORD REMINDER</h2>
<h1 id="heading2"> STUDENT </h1>

<div>
<!-- Support for Spring errors object -->
<spring:bind path="reminderParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>
</div>

<div id="forgot2" style="padding:0px 15px 15px 15px;"> 
<h2><b>Step 1: </b></h2>
<h2><b>First, enter your username: </b></h2>
<form id="username" name="retrievepassword" method="post" commandName="reminderParameters">
<label for="send_username"><spring:message code="login.username" /></label>
  <input type="text" name="username" id="username"  
  	style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 1em; width: 110px;" 
	size="40" tabindex="1" />
<br />
  <input type="submit" name="_target1" value="<spring:message code="navigate.next" />" />
</form>
</div>
<a id="signIn4" href="../../index.html">Return to Sign In</a>
<!--
<a href="#" ><img id="signIn4" src="../../images/Sign-in-New-Account.png" alt="return to sign in" name="signInAgain" width="161" height="52" 
onmouseover="swapImage('signIn4','../../images/Sign-in-New-Account-Roll.png');"
onmouseout="swapImage('signIn4','../../images/Sign-in-New-Account.png');"
></a>
-->
</body>
</html>
