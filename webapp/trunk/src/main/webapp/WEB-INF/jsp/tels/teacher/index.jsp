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
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>

<h1>Teacher homepage</h1>

<div id="columns">
<div id="left"><%@ include file="../logout.jsp"%>
<h2><spring:message code="welcome" /> 
    <authz:authentication operation="username" />
</h2>
    <a href="../curnitlist.html"><spring:message code="curnit.list" /></a><br /><br /><br />
    <a href="../runlist.html"><spring:message code="run.list" /></a>
</div>

<spring:message code="signup.firstname" /><authz:authentication operation="firstname" /><br />
<spring:message code="signup.lastname" /><authz:authentication operation="lastname" /><br />
<spring:message code="signup.emailAddress" /><authz:authentication operation="emailAddress" /><br />
<spring:message code="signup.country" /><authz:authentication operation="country" /><br />
<spring:message code="signup.state" /><authz:authentication operation="state" /><br />
<spring:message code="signup.city" /><authz:authentication operation="city" /><br />
<spring:message code="signup.schoolname" /><authz:authentication operation="schoolname" /><br />
<spring:message code="signup.schoollevel" /><authz:authentication operation="schoollevel" /><br />
<spring:message code="signup.curriculumsubjects" />
  <c:forEach var="curriculumsubject" items="<authz:authentication operation='curriculumsubjects' />" >
    <b>
      <br /><c:out value="${curriculumsubject}"/>
    </b>
  </c:forEach><br />
<spring:message code="signup.signupdate" /><authz:authentication operation="signupdate" /><br /><br />

<div id="right">
<a href="registerstudent.html"><spring:message code="register.student" /></a><br />
<a href="registerteacher.html"><spring:message code="register.teacher" /></a><br /></div>

</div>


<%@ include file="../footer.jsp"%>

</body>
</html>
