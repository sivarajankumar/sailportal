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

</head>

<body>

<%@ include file="header.jsp"%>

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
<h2><spring:message code="student.registration" /></h2>
</div>

<div>
<h3><spring:message code="student.registration.instructions" /></h3>
</div>

<div id="right">
<form:form method="post" action="registerstudent.html" commandName="studentAccountForm" id="register" >
  <div><label for="firstname"><spring:message code="signup.firstname" /></label>
      <form:input path="userDetails.firstname" id="firstname"/>
      <form:errors path="userDetails.firstname" />
  </div>
    <div>
      <label for="lastname"><spring:message code="signup.lastname" /></label>
      <form:input path="userDetails.lastname" id="lastname"/>
      <form:errors path="userDetails.lastname" />
    </div>

    <div>
      <label for="gender"><spring:message code="signup.gender" /></label>
      
        <form:select path="userDetails.gender" id="gender"> 
          <c:forEach items="${genders}" var="genderchoice">
            <form:option value="${genderchoice}"><spring:message code="genders.${genderchoice}" /></form:option>
          </c:forEach>
        </form:select>
      
      <form:errors path="userDetails.gender" />
    </div>

    <div>
      <label for="birthday"><spring:message code="signup.birthday" /></label>
      <form:input path="userDetails.birthday" id="birthday"/>
      <form:errors path="userDetails.birthday" />
    </div>

    <div>
      <label for="password"><spring:message code="signup.password" /></label>
      <form:password path="userDetails.password" id="password"/>
      <form:errors path="userDetails.password"/>
    </div>
  
    <div>
      <label for="repeatedPassword"><spring:message code="signup.password.verify" /></label>
      <form:password path="repeatedPassword" id="repeatedPassword"/>
      <form:errors path="repeatedPassword" />
    </div>

    <div>
      <label for="emailAddress"><spring:message code="signup.emailAddress" /></label>
      <form:input path="userDetails.emailAddress" id="emailAddress"/>
      <form:errors path="userDetails.emailAddress"/>
    </div>

    <div>
      <label for="projectCode"><spring:message code="signup.project.code" /></label>
      <form:input path="projectCode" id="projectCode"/>
      <form:errors path="projectCode" />
    </div>

  
    <div><input type="submit" value="<spring:message code="signup.save.information" />" /></div>

</form:form></div>
</div>
<%@ include file="footer.jsp"%>


</body>
</html>




