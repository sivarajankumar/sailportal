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
<title>Password reminder step 2- student</title>
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
<h2><b>Step 2: </b></h2>
<h3><b>Hello ${username} </b></h3>
<h3>
Please answer the Password Reminder that you
encountered during your initial registration.
</h3>
<form id="submittedAccountAnswer" method="post" commandName="reminderParameters" >
<p>Question: ${accountQuestion}
 <p><label for="send_accountanswer">answer</label>
  <input type="text" name="submittedAccountAnswer" id="submittedAccountAnswer"  
  style=" font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 1em;
	width: 110px;"
  tabindex="1" /></p>

<input type="submit" name="_target2" value="<spring:message code="navigate.next" />">
</form>

</div>
<a id="signIn5" href="../../index.html">Return to Sign In</a>
<!-- 
<a href="#" ><img id="signIn5" src="../../images/Sign-in-New-Account.png" alt="return to sign in" name="signInAgain" width="161" height="52" 
onmouseover="swapImage('signIn5','../../images/Sign-in-New-Account-Roll.png');"
onmouseout="swapImage('signIn5','../../images/Sign-in-New-Account.png');"
></a>
-->
</body>
</html>
