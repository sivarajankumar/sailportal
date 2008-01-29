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

<!-- $Id: registerstudent.jsp 989 2007-08-30 01:15:54Z MattFish $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<script src=".././javascript/tels/general.js" type="text/javascript" > </script>
<script src=".././javascript/tels/effects.js" type="text/javascript" > </script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="student.signup.title" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="header.jsp"%>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

<h1 id="registrationTitle" class="blueText">Student Registration</h1>

<div id="subtitleStudentReg">To create a student account <br/>please fill in the following fields.</div>
      
<!-- Support for Spring errors object -->
<div id="regErrorMessages">
<spring:bind path="studentAccountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
        <br /><c:out value="${error}"/>
    </c:forEach>
</spring:bind>
</div>

  <form:form id="studentRegForm" commandName="studentAccountForm" method="post" action="registerstudent.html">
  
  <dl>
  	<dt><label for="studentFirstName">First Name:</label></dt>	    
  	  	<dd><form:input path="userDetails.firstname" id="firstname" size="25" maxlength="25" tabindex="1"/>
	    <form:errors path="userDetails.firstname" />
    	<span class="hint">Required.<span class="hint-pointer"></span></span> 
   		</dd>

<!--This unusually placed script gets the cursor into the First Name field immediately on page load  (MattFish)-->
<script type="text/javascript">
document.getElementById('firstname').focus();
</script>

  	<dt><label for="studentLastName">Last Name:</label></dt>
	<dd><form:input path="userDetails.lastname" id="lastname" size="25" maxlength="25" tabindex="2"/>
	    <form:errors path="userDetails.lastname" />
    	<span class="hint">Required.<span class="hint-pointer"></span></span> 
   		</dd>
            
  	<dt><label for="studentGender">Gender:</label></dt>
	<dd><form:select path="userDetails.gender" id="gender" tabindex="3">       
          <c:forEach items="${genders}" var="genderchoice">
            <form:option value="${genderchoice}">
            	<spring:message code="genders.${genderchoice}" />
            </form:option>
          </c:forEach>
      	</form:select> 
        <span class="hint">Required.<span class="hint-pointer"></span></span> 
    	</dd>
            
    <dt><label for="studentBirthMonth">Birthday (Month):</label></dt>
	<dd><form:select path="birthmonth" id="birthmonth" tabindex="4">
		<form:errors path="birthmonth" />
		<c:forEach var="month" begin="1" end="12" step="1">
			<form:option value="${month}">
				<spring:message code="birthmonths.${month}" />
			</form:option>
		</c:forEach>
	    </form:select>
        <span class="hint">Your birthday information will be used to create a unique (and easy to remember) Username.  You'll use this Username later to sign into WISE.<span class="hint-pointer"></span></span> 
    	</dd>
        
	  <dt><label for="studentBirthDate">Birthday (Day):</label></dt>
	  <dd><form:select path="birthdate" id="birthdate" tabindex="5">
	      <form:errors path="birthdate" />
			 <c:forEach var="date" begin="1" end="31" step="1">
				  <form:option value="${date}">
				  		<spring:message code="birthdates.${date}" />
			  	  </form:option>
		  </c:forEach>
	    </form:select> 	
         </dd>
                   
	  <dt><label for="studentPassword">Password:</label></dt>
	  <dd><form:password path="userDetails.password" id="password" size="25" maxlength="25" tabindex="6"/>
      		<form:errors path="userDetails.password"/> 
      		<span class="hint">Your password can contain up to 18 letters and/or numbers. Try to create a password that you can remember!<span class="hint-pointer"></span></span> 
            </dd>

	  <dt><label for="studentPasswordRepeat">Verify Password:</label></dt>
	  <dd><form:password path="repeatedPassword" id="repeatedPassword" size="25" maxlength="25" tabindex="7"/> 
            <form:errors path="repeatedPassword" />      	  
	        <span class="hint">Retype your password.<span class="hint-pointer"></span></span>
            </dd>
      
	  <dt><label for="reminderQuestion">Password Reminder Q:</label></dt>
	  <dd><form:select path="userDetails.accountQuestion" id="accountQuestion" tabindex="8" >  
            <form:errors path="userDetails.accountQuestion" />
        	<c:forEach items="${accountQuestions}" var="questionchoice">
            <form:option value="${questionchoice}">
            	<spring:message code="accountquestions.${questionchoice}"/>
             </form:option>
          </c:forEach>
        </form:select>
        
         <span class="hint">Select a question, then answer it below. If you forget your 
password in the future this reminder helps you prove who you are.<span class="hint-pointer"></span></span>
		</dd>

	  <dt><label for="reminderAnswer" id="reminderAnswer">Password Reminder A:</label></dt>
	  <dd><form:input path="userDetails.accountAnswer" id="accountAnswer" size="25" maxlength="25" tabindex="9"/>
          <form:errors path="userDetails.accountAnswer" />	  
	      <span class="hint">Answer the password reminder question here.<span class="hint-pointer"></span></span>			
          </dd>
      
      <dt><label for="projectCode" id="projectCode1">Student Code:</label></dt>
	  <dd><form:input path="projectCode" id="projectCode" size="25" maxlength="25" tabindex="10"/>
       	  <form:errors path="projectCode" />
          <span class="hint">Ask your teacher for the Student Code.<span class="hint-pointer"></span></span></dd>

    </dl>
               
 	  <div id="regButtons">
 	    <input type="image" id="save" src="../<spring:theme code="register_save" />" 
    onmouseover="swapImage('save','../<spring:theme code="register_save_roll" />')" 
    onmouseout="swapImage('save','../<spring:theme code="register_save" />')"
    />
    <a href="../index.html"><input type="image" id="cancel" src="../<spring:theme code="register_cancel" />" 
    onmouseover="swapImage('cancel','../<spring:theme code="register_cancel_roll" />')" 
    onmouseout="swapImage('cancel','../<spring:theme code="register_cancel" />')"
    /> </a>	  </div>
 
 </form:form>
 
</div>

</div>  <!-- /* End of the CenteredDiv */-->

</body>

</html>




