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
<link href="../../<spring:theme code="teacherforgotstylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<script type="text/javascript" src="./javascript/tels/general.js"></script>			    
<title>Forgot username or password - teacher</title>
</head>

<body>

<%@ include file="header.jsp"%>

<h2 id="heading"> LOST USERNAME OR PASSWORD </h2>
<h2 id="heading2"> TEACHER/RESEARCHER </h2>

<div id="columns">

<!-- Support for Spring errors object -->
<spring:bind path="userDetails.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="forgot1"> 
<form id="username" method="post" action="index.html" commandName="userDetails">
 <ul id="forgotList">
 <li>
 <b>Remember Your Username but Forgot Your Password?</b>
Enter Your UserName below. A new password will be sent to
your registered email address.<br />
<b><label for="send_username" /><spring:message code="login.username" /></b>
<input type="text" id="username" name="username" size="40" tabindex="1" /> <br />
<input type="submit" name="sendpassword" id="sendpassword" value="Send Password" style="position:relative;
	left:300px;" />
 </li>
 <li>
 <b>Forgot Your Username?</b><br />
Enter the email address you used when registering for WISE. Your
UserName and a new Password will be sent to this email address.<br />
<b><label for="send_usernamepassword" /><spring:message code="lostpassword.teacher.email" /></b>
<input type="text" name="emailAddress" id="emailAddress" size="40" tabindex="2" /> <br />
<input type="submit" name="sendemailAndPwd" id="sendEmailAndPwd" value="Send Password + Email" style="position:relative;
	left:300px;" />
</li>

<li> If you're still stuck, <a href="#" onclick="displayNotAvailable('This page is not available yet.');">contact WISE.</a>
</li>
 </ul>
 </form>

</div>


</body>
</html>



