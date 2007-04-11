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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="signup.title" /></title>
<script type="text/javascript" src="./javascript/utils.js"></script>
<script type="text/javascript">
function onLoadHandler() {
  document.getElementById("username").focus();
}

addEvent(window, 'load', onLoadHandler);
</script>
</head>

<body>

<%@ include file="header.jsp"%>

<div id="banner">
<h1><spring:message code="banner.heading" /></h1>
</div>

<!-- Support for Spring errors object -->
<spring:bind path="studentAccountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="columns">
<div id="left">
<h2><spring:message code="register.student" /></h2>
</div>

<div id="right"><form:form method="post" action="registerstudent.html" commandName="studentAccountForm">

  <p><label for="firstname"><spring:message code="signup.firstname" /></label>
  <form:input path="userDetails.firstname" id="firstname"/> <form:errors path="userDetails.firstname" />
  </p>

  <p><label for="lastname"><spring:message code="signup.lastname" /></label>
  <form:input path="userDetails.lastname" id="lastname"/> <form:errors path="userDetails.lastname" />
  </p>

  <p><label for="gender"><spring:message code="signup.gender" /></label>
  <form:select path="userDetails.gender" id="gender"> 
    <c:forEach items="${genders}" var="genderchoice">
      <form:option value="${genderchoice}"><spring:message code="genders.${genderchoice}" /></form:option>
    </c:forEach>
  </form:select>
  <form:errors path="userDetails.gender" />
  </p>
  
  <p><label for="birthday"><spring:message code="signup.birthday" /></label>
  <form:input path="userDetails.birthday" id="birthday"/> <form:errors path="userDetails.birthday" />
  </p>

  <p><label for="password"><spring:message code="signup.password" /></label>
  <form:password path="userDetails.password" id="password"/> <form:errors path="userDetails.password"/>
  </p>
  
  <p><label for="repeatedPassword"><spring:message code="signup.password.verify" /></label>
  <form:password path="repeatedPassword" id="repeatedPassword"/> <form:errors path="repeatedPassword" />
  </p>
  
  <p><input type="submit" value="<spring:message code="signup.submit" />" />
  </p>
</form:form></div>
</div>
<%@ include file="footer.jsp"%>


</body>
</html>




