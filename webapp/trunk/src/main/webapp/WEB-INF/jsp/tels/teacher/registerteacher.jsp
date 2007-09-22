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

<!-- $Id: registerteacher.jsp 1038 2007-09-08 00:08:16Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src=".././javascript/tels/rotator.js" type="text/javascript" />  
<script type="text/javascript">
function checkIfLegalAcknowledged (form, id) {
if(form.getElementById(id).checked==true){
}else{
}
}
</script>
<script lang="Javascript">
function popup(URL) {
  window.open(URL, 'Select Team', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=650,height=500,left = 570,top = 300');
}
// End -->
</script>
<title><spring:message code="signup.title" /></title>

</head>

<body>

<%@ include file="plainheader.jsp"%>


<!-- Support for Spring errors object -->
<spring:bind path="teacherAccountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>
<h2 class="center">Teacher Registration</h2>
<h4 class="center">(please fill out the following required fields)</h4>

<div class="center">
<form:form method="post" action="registerteacher.html" commandName="teacherAccountForm" id="register" >

<table align="center">
<tr>
<td>
<label for="firstname" id="firstname1"><spring:message code="signup.firstname" /></label>
</td>
<td>    
    <form:input path="userDetails.firstname" id="firstname" onfocus="Effect.toggle('showFirstNameInfo','appear');" onblur="Effect.toggle('showFirstNameInfo','appear');" size="30"/>
</td>
<td id="showFirstNameInfo" style="display:none"> This is your first name </td>
</tr>
<tr>
<td>
    <label for="lastname" id="lastname1"><spring:message code="signup.lastname"/></label>
</td>
<td>
    <form:input path="userDetails.lastname" id="lastname" onfocus="Effect.toggle('showLastNameInfo','appear');" onblur="Effect.toggle('showLastNameInfo','appear');" size="30"/>
</td>
<td id="showLastNameInfo" style="display:none"> This is your last name </td>
</tr>
<tr>
<td>
<label for="emailAddress" id="emailAddress1"><spring:message code="signup.emailAddress" /></label>
</td>
<td>    
<form:input path="userDetails.emailAddress" id="emailAddress" onfocus="Effect.toggle('showEmailAddressInfo','appear');" onblur="Effect.toggle('showEmailAddressInfo','appear');"/>
</td>
<td id="showEmailAddressInfo" style="display:none"> This is your email address </td>
</tr>
<tr>
<td>
<label for="city" id="city1"><spring:message code="signup.city" /></label> 
</td>
<td>
<form:input path="userDetails.city" id="city" onfocus="Effect.toggle('showCityInfo','appear');" onblur="Effect.toggle('showCityInfo','appear');"/>
</td>
<td id="showCityInfo" style="display:none"> This is your city </td>
</tr>
<tr>
<td>
<label for="state" id="state1" ><spring:message code="signup.state" /></label> 
</td>
<td>
<form:input path="userDetails.state" id="state"/>
        <div id="autocomplete_choices_state" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('state', 'autocomplete_choices_state', 'states.html', {paramName: 'sofar'}); 	   		
		</script>
</td>
<td id="showStateInfo" style="display:none"> This is your state </td>
</tr>
<tr>
<td>
    <label for="country" id="country1"><spring:message code="signup.country" /></label> 
</td>
<td>
    <form:input path="userDetails.country" id="country" />
        <div id="autocomplete_choices_country" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('country', 'autocomplete_choices_country', 'countries.html', {paramName: 'sofar'}); 	   		
		</script>
</td>
<td id="showCountryInfo" style="display:none"> This is your country </td>
</tr>
<tr>
<td>
    <label for="schoolname" id="schoolname1"><spring:message code="signup.schoolname" /></label> 
</td>
<td>    
    <form:input path="userDetails.schoolname" id="schoolname" onfocus="Effect.toggle('showSchoolNameInfo','appear');" onblur="Effect.toggle('showSchoolNameInfo','appear');"/>
</td>
<td id="showSchoolNameInfo" style="display:none"> This is your school name </td>
</tr>
<tr>
<td>
    <label for="curriculumsubjects" id="curriculumsubjects1"><spring:message code="signup.curriculumsubjects" /></label> 
</td>
<td>
     <div id="curriculumSubjectsBox" style="display:none" onfocus="Effect.toggle('showCurriculumInfo','appear');" onblur="Effect.toggle('showCurriculumSubjectsInfo','appear');"> 
          <c:forEach items="${curriculumsubjects}" var="curriculumsubject">
            <form:checkbox path="userDetails.curriculumsubjects" value="${curriculumsubject}" /><spring:message code="signup.curriculumsubjects.${curriculumsubject}" />
          </c:forEach>
	</div>
    <a href="javascript:Effect.toggle('curriculumSubjectsBox','appear')" > List of Curriculum Subjects </a>
</td>
<td id="showCurriculumSubjectsInfo" style="display:none"> This is your curriculum subjects </td>
</tr>     
<tr>
<td>
    <label for="schoollevel" id="schoollevel1"><spring:message code="signup.schoollevel" /></label> 
</td>
<td>
        <form:select path="userDetails.schoollevel" id="schoollevel" onfocus="Effect.toggle('showSchoolLevelInfo','appear');" onblur="Effect.toggle('showSchoolLevelInfo','appear');"> 
          <c:forEach items="${schoollevels}" var="schoollevel">
            <form:option value="${schoollevel}"><spring:message code="signup.schoollevels.${schoollevel}" /></form:option>
          </c:forEach>
        </form:select>
</td>
<td id="showSchoolLevelInfo" style="display:none"> This is your school level </td>
</tr>
<tr>
<td>
    <label for="legalAcknowledged" id="legalAcknowledged1"><spring:message code="signup.legalAcknowledged" /></label>
</td>
<td>
    <form:checkbox path="legalAcknowledged" id="legalAcknowledged"/> 
     I agree to the <a href="termsofuse.html"> terms of use. </a>
</td>     
</tr>
<tr>
<td>  
    <label for="password" id="password1"><spring:message code="signup.password" /></label>
</td>
<td>
    <form:password path="userDetails.password" id="password" onfocus="Effect.toggle('showPasswordInfo','appear');" onblur="Effect.toggle('showPasswordInfo','appear');"/>
</td>
<td id="showPasswordInfo" style="display:none"> This is your password </td>
</tr>
<tr>
<td>
<label for="repeatedPassword" id="repeatedPassword2"><spring:message code="signup.password.verify" /></label>
</td>
<td>
<form:password path="repeatedPassword" id="repeatedPassword" onfocus="Effect.toggle('showRepeatedPasswordInfo','appear');" onblur="Effect.toggle('showRepeatedPasswordInfo','appear');"/>
</td>
<td id="showRepeatedPasswordInfo" style="display:none"> This is your password repeated </td>
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
        
        
</form:form></div>


</body>
</html>