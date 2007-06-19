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
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="signup.title" /></title>

</head>

<body>

<%@ include file="header.jsp"%>

<div id="banner">
<h1 class="center"><spring:message code="banner.heading" /></h1>
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
<h2 class="center"><spring:message code="register.teacher" /></h2>

<form:form method="post" action="registerteacher.html" commandName="teacherAccountForm" id="register" >


<div>
<label for="firstname"><spring:message code="signup.firstname" /></label>
    <form:input path="userDetails.firstname" id="firstname"/>
</div>   
 
  <div>
    <label for="lastname"><spring:message code="signup.lastname" /></label>
    <form:input path="userDetails.lastname" id="lastname"/>
  </div>

  <div>
    <label for="emailAddress"><spring:message code="signup.emailAddress" /></label>
    <form:input path="userDetails.emailAddress" id="emailAddress"/>
  </div>

<div>
    <label for="country"><spring:message code="signup.country" /></label> 
    <form:input path="userDetails.country" id="country" />
        <div id="autocomplete_choices_country" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('country', 'autocomplete_choices_country', 'countries.html', {paramName: 'sofar'}); 	   		
		</script>
  </div>

  <div>
    <label for="state"><spring:message code="signup.state" /></label> 
    <form:input path="userDetails.state" id="state" />
        <div id="autocomplete_choices_state" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('state', 'autocomplete_choices_state', 'states.html', {paramName: 'sofar'}); 	   		
		</script>
  </div>

  <div>
    <label for="city"><spring:message code="signup.city" /></label> 
    <form:input path="userDetails.city" id="city" />
  </div>

  <div>
    <label for="schoolname"><spring:message code="signup.schoolname" /></label> 
    <form:input path="userDetails.schoolname" id="schoolname" />
  </div>

  <div>
    <label for="curriculumsubjects"><spring:message code="signup.curriculumsubjects" /></label> 
  Biology: <form:checkbox path="userDetails.curriculumsubjects" value="Biology" />
  Chemistry: <form:checkbox path="userDetails.curriculumsubjects" value="Chemistry" />
  Physics: <form:checkbox path="userDetails.curriculumsubjects" value="Physics" />

  </div>

  <div>
    <label for="schoollevel"><spring:message code="signup.schoollevel" /></label> 
    <form:input path="userDetails.schoollevel" id="schoollevel" />
  </div>

  <div>
    <label for="legalAcknowledged"><spring:message code="signup.legalAcknowledged" /></label>
    <form:checkbox path="legalAcknowledged" id="legalAcknowledged"/> I agree to the terms of use.
  </div>
  
  <div>
    <label for="password"><spring:message code="signup.password" /></label>
    <form:password path="userDetails.password" id="password"/>
  </div>
  
  <div>
    <label for="repeatedPassword"><spring:message code="signup.password.verify" /></label>
    <form:password path="repeatedPassword" id="repeatedPassword"/>
  </div>



<input type="submit" value="<spring:message code="signup.submit" />"  onclick="Effect.toggle('waiting', 'appear')"/>
        <div id="waiting" style="display: none">
            <div><img src="<spring:theme code="wait"/>" alt="<spring:message code="wise.wait.alttext" />" /></div>
        </div>
        
        
        
</form:form></div>

<div>
<a href="javascript:Effect.toggle('waiting', 'appear')">click me</a>
</div>
<%@ include file="footer.jsp"%>

</body>
</html>