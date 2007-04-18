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
<spring:bind path="teacherAccountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="columns">
<div id="left">
<h2><spring:message code="register.teacher" /></h2>
</div>

<div id="right">
<form:form method="post" action="registerteacher.html" commandName="teacherAccountForm" id="register" >
<table>
  <tr>
    <td><label for="firstname"><spring:message code="signup.firstname" /></label></td>
    <td><form:input path="userDetails.firstname" id="firstname"/></td>
    <td><form:errors path="userDetails.firstname" /></td>
  </tr>

  <tr>
    <td><label for="lastname"><spring:message code="signup.lastname" /></label></td>
    <td><form:input path="userDetails.lastname" id="lastname"/></td>
    <td><form:errors path="userDetails.lastname" /></td>
  </tr>

  <tr>
    <td><label for="emailAddress"><spring:message code="signup.emailAddress" /></label></td>
    <td><form:input path="userDetails.emailAddress" id="emailAddress"/></td>
    <td><form:errors path="userDetails.emailAddress"/></td>
  </tr>

  <tr>
    <td><label for="city"><spring:message code="signup.city" /></label></td> 
    <td><form:input path="userDetails.city" id="city" /></td>
    <td><form:errors path="userDetails.city" /></td>
  </tr>

  <tr>
    <td><label for="state"><spring:message code="signup.state" /></label></td> 
    <td><form:input path="userDetails.state" id="state" /></td>
    <td><form:errors path="userDetails.state" /></td>
  </tr>

  <tr>
    <td><label for="country"><spring:message code="signup.country" /></label></td> 
    <td><form:input path="userDetails.country" id="country" /></td>
    <td><form:errors path="userDetails.country" /></td>
  </tr>

  <tr>
    <td><label for="schoolname"><spring:message code="signup.schoolname" /></label></td> 
    <td><form:input path="userDetails.schoolname" id="schoolname" /></td>
    <td><form:errors path="userDetails.schoolname" /></td>
  </tr>

  <tr>
    <td><label for="curriculumsubjects"><spring:message code="signup.curriculumsubjects" /></label></td> 
  <td>Biology: <form:checkbox path="userDetails.curriculumsubjects" value="Biology" />
  Chemistry:  <form:checkbox path="userDetails.curriculumsubjects" value="Chemistry" />
  Physics: <form:checkbox path="userDetails.curriculumsubjects" value="Physics" /></td>
  <td><form:errors path="userDetails.curriculumsubjects" /></td>
  </tr>

  <tr>
    <td><label for="schoollevel"><spring:message code="signup.schoollevel" /></label></td> 
    <td><form:input path="userDetails.schoollevel" id="schoollevel" /></td>
    <td><form:errors path="userDetails.schoollevel" /></td>
  </tr>
  
  <tr>
    <td><label for="legalAcknowledged"><spring:message code="signup.legalAcknowledged" /></label></td>
    <td><form:checkbox path="legalAcknowledged" id="legalAcknowledged" /></td>
    <td><form:errors path="legalAcknowledged" /></td>
  </tr>

  <tr>
    <td><label for="password"><spring:message code="signup.password" /></label></td>
    <td><form:password path="userDetails.password" id="password"/></td>
    <td><form:errors path="userDetails.password"/></td>
  </tr>
  
  <tr>
    <td><label for="repeatedPassword"><spring:message code="signup.password.verify" /></label></td>
    <td><form:password path="repeatedPassword" id="repeatedPassword"/></td>
    <td><form:errors path="repeatedPassword" /></td>
  </tr>

  <tr>
    <td><input type="submit" value="<spring:message code="signup.submit" />" /></td>
  </tr>
</table>
</form:form></div>
</div>
<%@ include file="footer.jsp"%>


</body>
</html>




