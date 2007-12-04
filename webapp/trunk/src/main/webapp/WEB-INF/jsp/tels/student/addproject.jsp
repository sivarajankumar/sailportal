<%@ include file="../include.jsp"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src=".././javascript/tels/general.js"></script>    
    
<title><spring:message code="application.title" /></title>
</head>

<body>
<!-- Support for Spring errors object -->
<spring:bind path="addProjectParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <div id="errorMessageFormat">
      	<c:out value="${error}"/>
    </div>
  </c:forEach>
</spring:bind>

<div id="centeredDiv">

<div id="popUpWindow1">

<form:form method="post" action="addproject.html" commandName="addProjectParameters" id="addproject" >
  <div><label id="projectCodeLabel" for="projectcode"><spring:message code="teacher.project-code" /></label>
      <form:input path="projectcode" id="projectcode"/>
     
   	 <input id="addProjectButton" type="image" src="../<spring:theme code="student_add_this_project" />" 
    	onmouseover="swapImage('addProjectButton','../<spring:theme code="student_add_this_project_roll" />');" 
    	onmouseout="swapImage('addProjectButton','../<spring:theme code="student_add_this_project"/>');" />
   
   </div>
</form:form>

</div>
</div>
</body>
</html>
