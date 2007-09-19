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

<!-- $Id: registerstudent.jsp 989 2007-08-30 01:15:54Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src=".././javascript/tels/general.js" type="text/javascript" > </script>
<script src=".././javascript/tels/rotator.js" type="text/javascript" > </script>
  
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

<h2 class="center"><spring:message code="student.registration" /></h2>
<h3 class="center"><spring:message code="student.registration.instructions" /></h3>

<div class="center">
<form:form method="post" action="registerstudent.html" commandName="studentAccountForm" id="register" >
  <table align="center">
  <tr><td>
  <label for="firstname" id="firstname1">
  First Name:
  </label>
  </td>
  <td>
     <form:input path="userDetails.firstname" id="firstname" size="30"/>
      <form:errors path="userDetails.firstname" />
  </td>
  </tr>
  <tr>
      <td>
      <label for="lastname" id="lastname1">Last Name:</label>
	  </td>
	  <td>
      <form:input path="userDetails.lastname" id="lastname" size="30"/>
      <form:errors path="userDetails.lastname" />
      </td>
      </tr>
      <tr>
      <td>
      <label for="gender" id="gender1">Gender:</label>
	  </td>
	  <td>
      <form:select path="userDetails.gender" id="gender"> 
          <c:forEach items="${genders}" var="genderchoice">
            <form:option value="${genderchoice}"><spring:message code="genders.${genderchoice}" /></form:option>
          </c:forEach>
      </form:select>
      
      <form:errors path="userDetails.gender" />	  
	  </td>
	  </tr>
	  <tr>
	  <td>
	  <label for="birthmonth" id="birthmonth1">Birthday (Month):</label>
	  </td>
	  <td>
  	    <form:select path="birthmonth" id="birthmonth">
		  <c:forEach var="month" begin="1" end="12" step="1">
			  <form:option value="${month}">
				  <spring:message code="birthmonths.${month}" />
			  </form:option>
		  </c:forEach>
	    </form:select> 

      <form:errors path="birthmonth" />	  
	  </td>
	  </tr>
	  <tr>
	  <td>
      <label for="birthdate" id="birthdate1">Birthday (Day):</label>
	  </td>
	  <td>
	   	<form:select path="birthdate" id="birthdate">
		  <c:forEach var="date" begin="1" end="31" step="1">
			  <form:option value="${date}">
				  <spring:message code="birthdates.${date}" />
			  </form:option>
		  </c:forEach>
	    </form:select> 	    
	      <form:errors path="birthdate" />
	  </td>
	  </tr>
	  <tr>
	  <td>
	  <label for="password" id="password1">
	  Password:
	  </label>
	  </td>
	  <td>
      <form:password path="userDetails.password" id="password"/>
      <form:errors path="userDetails.password"/>      	  
	  </td>
	  </tr>
	  <tr>
	  <td>
      <label for="repeatedPassword" id="repeatedPassword1">Verify Password:</label>	  
	  </td>
	  <td>
      <form:password path="repeatedPassword" id="repeatedPassword"/>
      <form:errors path="repeatedPassword" />      	  
	  </td>
	  </tr>
	  <tr>
	  <td>
	  <label for="accountQuestion" id="accountQuestion1">Question:</label>
	  </td>
	  <td>
            <form:select path="userDetails.accountQuestion" id="accountQuestion"> 
          <c:forEach items="${accountQuestions}" var="questionchoice">
            <form:option value="${questionchoice}"><spring:message code="accountquestions.${questionchoice}"/></form:option>
          </c:forEach>
        </form:select>
        
      <form:errors path="userDetails.accountQuestion" />
	  
	  </td>
	  </tr>
	  
	  <tr>
	  <td>
	   <label for="accountAnswer" id="accountAnswer1">Answer:</label>
	  </td>
	  <td>
      <form:input path="userDetails.accountAnswer" id="accountAnswer"/>
      <form:errors path="userDetails.accountAnswer" />	  
	  </td>
	  </tr>

	  <tr>
	  <td>
	  <label for="projectCode" id="projectCode1">Project Code:</label>	  
	  </td>
	  <td>
	  	  <form:input path="projectCode" id="projectCode"/>
      	  <form:errors path="projectCode" />
	  </td>
	  </tr>	
	       
      </table>
   
<br />

 <div><input type="image" id="save" src="../<spring:theme code="register_save" />" 
     onmouseover="swapImage('save','../<spring:theme code="register_save_roll" />')" 
    onmouseout="swapImage('save','../<spring:theme code="register_save" />')"
    />
    <a href="../index.html"><input type="image" id="cancel" src="../<spring:theme code="register_cancel" />" 
    onmouseover="swapImage('cancel','../<spring:theme code="register_cancel_roll" />')" 
    onmouseout="swapImage('cancel','../<spring:theme code="register_cancel" />')"
    /> </a>
    </div>

</form:form>
</div>

</body>
</html>




