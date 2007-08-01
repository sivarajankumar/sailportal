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
<script src="./javascript/tels/rotator.js" type="text/javascript" > </script>
  
<title><spring:message code="signup.title" /></title>

</head>

<body>

<%@ include file="studentHeader.jsp"%>

<!-- Support for Spring errors object -->
<spring:bind path="studentAccountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="columns">
<h2><spring:message code="student.registration" /></h2>

<div>
<h3><spring:message code="student.registration.instructions" /></h3>
</div>

<div id="right">
<form:form method="post" action="registerstudent.html" commandName="studentAccountForm" id="register" >
  <label for="firstname"><spring:message code="signup.firstname" /></label>
      <form:input path="userDetails.firstname" id="firstname"/>
      <form:errors path="userDetails.firstname" />
<br />  
      <label for="lastname"><spring:message code="signup.lastname" /></label>
      <form:input path="userDetails.lastname" id="lastname"/>
      <form:errors path="userDetails.lastname" />
<br />
      <label for="gender"><spring:message code="signup.gender" /></label>
      
        <form:select path="userDetails.gender" id="gender"> 
          <c:forEach items="${genders}" var="genderchoice">
            <form:option value="${genderchoice}"><spring:message code="genders.${genderchoice}" /></form:option>
          </c:forEach>
        </form:select>
      
      <form:errors path="userDetails.gender" />
<br />

      <label for="birthmonth"><spring:message code="signup.birthmonth" /></label>

  	    <form:select path="birthmonth" id="birthmonth">
		  <c:forEach var="month" begin="1" end="12" step="1">
			  <form:option value="${month}">
				  <spring:message code="birthmonths.${month}" />
			  </form:option>
		  </c:forEach>
	    </form:select> 

      <form:errors path="birthmonth" />
<br />

      <label for="birthdate"><spring:message code="signup.birthdate" /></label>

  	    <form:select path="birthdate" id="birthdate">
		  <c:forEach var="date" begin="1" end="31" step="1">
			  <form:option value="${date}">
				  <spring:message code="birthdates.${date}" />
			  </form:option>
		  </c:forEach>
	    </form:select> 
	    
      <form:errors path="birthdate" />
<br />

      <label for="password"><spring:message code="signup.password" /></label>
      <form:password path="userDetails.password" id="password"/>
      <form:errors path="userDetails.password"/>
<br />
  
      <label for="repeatedPassword"><spring:message code="signup.password.verify" /></label>
      <form:password path="repeatedPassword" id="repeatedPassword"/>
      <form:errors path="repeatedPassword" />
<br />

      <label for="emailAddress"><spring:message code="signup.emailAddress" /></label>
      <form:input path="userDetails.emailAddress" id="emailAddress"/>
      <form:errors path="userDetails.emailAddress"/>
<br />

      <label for="accountQuestion"><spring:message code="signup.project.accountQuestion" /></label>
      
      <form:select path="userDetails.accountQuestion" id="accountQuestion"> 
          <c:forEach items="${accountQuestions}" var="questionchoice">
            <form:option value="${questionchoice}">${questionchoice}</form:option>
          </c:forEach>
        </form:select>
        
      <form:errors path="userDetails.accountQuestion" />
<br />
	
      <label for="accountAnswer"><spring:message code="signup.project.accountAnswer" /></label>
      <form:input path="userDetails.accountAnswer" id="accountAnswer"/>
      <form:errors path="userDetails.accountAnswer" />
<br />

      <label for="projectCode"><spring:message code="signup.project.code" /></label>
      <form:input path="projectCode" id="projectCode"/>
      <form:errors path="projectCode" />
<br />
     
    <div><input type="image" id="save" src="<spring:theme code="register_save" />" 
    onmouseover="swapSaveImage('save',1)" 
    onmouseout="swapSaveImage('save',0)"
    />
    <a href="index.html"><input type="image" id="cancel" src="<spring:theme code="register_cancel" />" 
    onmouseover="swapCancelImage('cancel',1)"
    onmouseout="swapCancelImage('cancel',0)"
    /> </a>
    </div>

</form:form></div>
</div>
<%@ include file="footer.jsp"%>


</body>
</html>




