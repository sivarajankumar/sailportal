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

<label for="firstname" id="firstname1"><spring:message code="signup.firstname" /></label>
    <form:input path="userDetails.firstname" id="firstname"/>
<br /> 
    <label for="lastname" id="lastname2"><spring:message code="signup.lastname" /></label>
    <form:input path="userDetails.lastname" id="lastname"/>
<br />

    <label for="emailAddress" id="emailAddress1"><spring:message code="signup.emailAddress" /></label>
    <form:input path="userDetails.emailAddress" id="emailAddress"/>
<br />

    <label for="city" id="city1"><spring:message code="signup.city" /></label> 
    <form:input path="userDetails.city" id="city" />
	<br />

    <label for="state" id="state1"><spring:message code="signup.state" /></label> 
    <form:input path="userDetails.state" id="state" />
        <div id="autocomplete_choices_state" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('state', 'autocomplete_choices_state', 'states.html', {paramName: 'sofar'}); 	   		
		</script>
	<br />

    <label for="country" id="country1"><spring:message code="signup.country" /></label> 
    <form:input path="userDetails.country" id="country" />
        <div id="autocomplete_choices_country" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('country', 'autocomplete_choices_country', 'countries.html', {paramName: 'sofar'}); 	   		
		</script>
	<br />

    <label for="schoolname" id="schoolname1"><spring:message code="signup.schoolname" /></label> 
    <form:input path="userDetails.schoolname" id="schoolname" />
<br />

    <label for="curriculumsubjects" id="curriculumsubjects1"><spring:message code="signup.curriculumsubjects" /></label> 
     <div id="curriculumSubjectsBox" style="display:none"> 
          <c:forEach items="${curriculumsubjects}" var="curriculumsubject">
            <form:checkbox path="userDetails.curriculumsubjects" value="${curriculumsubject}" /><spring:message code="signup.curriculumsubjects.${curriculumsubject}" />
          </c:forEach>
	</div>
    <a href="javascript:Effect.toggle('curriculumSubjectsBox','appear')" > List of Curriculum Subjects </a>
     
<br />
<br />
    <label for="schoollevel" id="schoollevel1"><spring:message code="signup.schoollevel" /></label> 

        <form:select path="userDetails.schoollevel" id="schoollevel"> 
          <c:forEach items="${schoollevels}" var="schoollevel">
            <form:option value="${schoollevel}"><spring:message code="signup.schoollevels.${schoollevel}" /></form:option>
          </c:forEach>
        </form:select>
  <br />

    <label for="legalAcknowledged" id="legalAcknowledged1"><spring:message code="signup.legalAcknowledged" /></label>
    <form:checkbox path="legalAcknowledged" id="legalAcknowledged"/> 
     I agree to the <a href="termsofuse.html"> terms of use. </a>
     
  <br />
  
    <label for="password" id="password1"><spring:message code="signup.password" /></label>
    <form:password path="userDetails.password" id="password"/>
  <br />
  
    <label for="repeatedPassword" id="repeatedPassword2"><spring:message code="signup.password.verify" /></label>
    <form:password path="repeatedPassword" id="repeatedPassword"/>
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