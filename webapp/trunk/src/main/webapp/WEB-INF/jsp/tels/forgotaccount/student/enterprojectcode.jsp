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
<title>Enter project code - student</title>
</head>

<body>
<%@ include file="header.jsp" %>

<h2 class="center"> LOST USERNAME/PASSWORD</h2>
<h1 class="center"> STUDENT </h1>
<h2 class="center">Search Usernames in a Project Run </h2><br />

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

<div align="center"> 

<b>Fill in the information below</b><br />
(you can get this information from your teacher)

</div>


<form id="projectCode" name="projectcode" method="post" commandName="reminderParameters">
<div align="center">
<label for="send_projectcode"><b><spring:message code="lostpassword.student.projectcode"/></b></label>
 <input type="text" name="projectCodeValue" id="projectCodeValue" tabindex="1" />
  
   <div id="waiting" style="display: none">
       <div><img src="<spring:theme code="wait"/>" alt="<spring:message code="wise.banner.alttext" />" /></div>
     </div>

     <input id="search" type="submit" tabindex="3" value="<spring:message code="lostpassword.teacher.submitpassword" />" 
      onclick="Effect.toggle('waiting', 'appear')" />
  </div>    
</form>
<br />
<div align="center">
<a href="../../index.html">Return to Sign In</a>
</div>
<!--
<img id="search" name="search" src="" width="380" height="61" alt="search" style="background-color: #999900">
<a href="#" ><img id="signIn3" src="../../images/Sign-in-New-Account.png" alt="return to sign in" name="signInAgain" width="161" height="52" 
onmouseover="swapImage('signIn3','../../images/Sign-in-New-Account-Roll.png');"
onmouseout="swapImage('signIn3','../../images/Sign-in-New-Account.png');"
></a>
-->
</body>
</html>
