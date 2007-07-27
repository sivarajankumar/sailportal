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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
</head>

<body>

<h1>Add a Project</h1>
<h3>To add a project, fill in the information below.</h3>

<!-- Support for Spring errors object -->
<spring:bind path="addProjectParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<form:form method="post" action="addproject.html" commandName="addProjectParameters" id="addproject" >
  <div><label for="projectcode">Project Code:</label>
      <form:input path="projectcode" id="projectcode"/>
      <form:errors path="projectcode" />
  </div>

    <div><input type="image" id="save" src="../<spring:theme code="register_save" />" 
    onmouseover="swapSaveImage('save',1)" 
    onmouseout="swapSaveImage('save',0)"
    />
    <a href="index.html" onclick="javascript:window.close()"><input type="image" id="cancel" src="../<spring:theme code="register_cancel" />" 
    onmouseover="swapCancelImage('cancel',1)"
    onmouseout="swapCancelImage('cancel',0)"
    /> </a>
    </div>

</form:form>

</body>
</html>
