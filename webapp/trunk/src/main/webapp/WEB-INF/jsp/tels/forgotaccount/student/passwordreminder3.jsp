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
<title>Password reminder step 3- student</title>
</head>

<body>
<%@ include file="header.jsp" %>

<h2 class="center"> PASSWORD REMINDER</h2>
<h1 class="center"> STUDENT </h1>

<spring:bind path="reminderParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="forgot2" style="padding:0px 15px 15px 15px;"> 
<h2><b>Step 3: </b></h2>
<h3><b>Correct answer.</b></h3>
<h3>
Please enter a new password below, verify it,
and click submit.
</h3>


<form id="submittedAccountPasswords" name="changedPassword" method="post" commandName="reminderParameters">
<label id="passwordform" for="send_passwords">
<spring:message code="lostpassword.student.new-password" />
</label>
<input type="password" name="newPassword" id="newPassword" size="40" tabindex="1" />
<br />
<label id="passwordform2" for="answer"><spring:message code="lostpassword.student.verify-password" /></label>
<input id="verifyPassword" name="verifyPassword" type="password" size="40" tabindex="2" />
<br />
<input name="sendpassword" type="submit" value="Submit" id="submitPos">

   <div id="waiting" style="display: none">
       <div><img src="<spring:theme code="wait"/>" alt="<spring:message code="wise.banner.alttext" />" /></div>
     </div>
   
</form>

</div>
<a href="../../index.html" id="signIn6">Return to Sign In</a>
<!--
<a href="#" ><img id="signIn5" src="../../images/Sign-in-New-Account.png" alt="return to sign in" name="signInAgain" width="161" height="52" 
onmouseover="swapImage('signIn5','../../images/Sign-in-New-Account-Roll.png');"
onmouseout="swapImage('signIn5','../../images/Sign-in-New-Account.png');"
></a>
-->

</body>
</html>
