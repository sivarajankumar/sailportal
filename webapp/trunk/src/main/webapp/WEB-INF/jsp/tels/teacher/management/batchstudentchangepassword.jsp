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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
  
<title><spring:message code="run.list" /></title>
<script language="JavaScript">

function popup(URL, title) {
  window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=300,height=300,left = 570,top = 300');
}
</script>

</head>

<body>

<div id="centeredDiv">

<div id="popUpWindowViewStudents">

	<div id="studentchangepasswordbox">
	
<h2><spring:message code="batchstudentchangepassword.menu" /></h2>

<!-- Support for Spring errors object -->
<spring:bind path="batchStudentChangePasswordParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

	<form:form method="post" action="batchstudentchangepassword.html" commandName="batchStudentChangePasswordParameters" id="batchstudentchangepassword">
	<div><label for="batchstudentchangepassword"><spring:message code="changepassword.password1" /></label>
      <form:password path="passwd1" id="batchstudentchangepassword"/>
	</div>
	
	<div><label for="batchstudentchangepassword"><spring:message code="changepassword.password2" /></label>
		<form:password path="passwd2" id="batchstudentchangepassword"/>
	</div>

    <div><input type="image" id="save" src="../../<spring:theme code="register_save" />" 
    onmouseover="swapSaveImage('save',1)" 
    onmouseout="swapSaveImage('save',0)"
    />
    <a href="index.html" onclick="javascript:window.close()"><input type="image" id="cancel" src="../../<spring:theme code="register_cancel" />" 
    onmouseover="swapCancelImage('cancel',1)"
    onmouseout="swapCancelImage('cancel',0)"
    /> </a>
    </div>

</form:form>

</div>
</div>
</div>

</body>
</html>