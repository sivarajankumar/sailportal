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

<link href="<../spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<title><spring:message code="application.title" /></title>
</head>

<body>
<c:if test="${closeokay}">
<c:out value="hi" />
</c:if>
<!--  <h1>Team Sign In</h1> -->
<h4><authz:authentication operation="username" /> is already signed in. All other teammates should sign in below.</h4>

<form:form method="post" action="teamsignin.html" commandName="teamSignInForm" id="teamSignInForm" >
  <label for="username1">Username 1:</label>
     <form:input disabled="true" path="username1" id="username1" />
     <br>

  <c:forEach var="teammate_index" begin="2" end="3" step="1">
    <label for="username${teammate_index}">Username ${teammate_index}:</label>
        <form:input path="username${teammate_index}" id="username${teammate_index}"/>
        <form:errors path="username${teammate_index}" />
      
    <label for="password${teammate_index}">Password:</label>
        <form:password path="password${teammate_index}" id="password${teammate_index}"/>
        <form:errors path="password${teammate_index}" />
        <br>
  </c:forEach>
<br>
<div align='right'>
  <input type="submit" name="_finish" value="Run Project" />
</div>
</form:form>

<!--  <div><a href="#" onclick="javascript:window.close()">Cancel</a></div>-->
</body>
</html>
