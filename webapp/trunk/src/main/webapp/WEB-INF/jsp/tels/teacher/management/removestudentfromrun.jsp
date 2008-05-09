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

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../.././javascript/tels/effects.js"></script>

    
<title>Archive Run Pop Up</title>
</head>

<body>


<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

<div id="popUpBoxBoundary">

<div id="largeHeader">Detach Student from Project Run</div>

<div id="blockHighlight" >
	<table>
		<tr>
			<td id="blockItem">Student:</td>
			<td>[full student name]</td>
		</tr>
		<tr>
			<td id="blockItem">Period:</td>
			<td>[registered period]</td>
		</tr>
		<tr>
			<td id="blockItem">Student Code:</td>
			<td>[student code for the project run]</td>
		</tr>
		<tr>
			<td id="blockItem">Project Run:</td>
			<td>[project title]</td>
		</tr>
	</table>
</div>			    	

	<div id="popUpNotice1">
		<h5>Sure you want to detach this student from the Project Run?</h5>
		<h5>All of the student's current work for the project will be deleted, and he/she be disassociated from the Project Run. The student's Username will disappear from the Manage Students screen.</h5>
		<h5>Note that detaching a student cannot be undone.  Make sure you want to remove this student Username!</h5>
    	<div id="popUpNotice2">Notice: detaching a student does not delete their overall WISE identity, just their association with the Project Run listed above.</div>
	</div>
	
<!-- Support for Spring errors object -->
<spring:bind path="removeStudentFromRunParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<form:form method="post" action="removestudentfromrun.html" commandName="removeStudentFromRunParameters" id="removeStudentFromRun" >
  <div style="visibility:hidden;"><label for="runId">Run ID:</label>
      <form:input disabled="true" path="runId" id="runId"/>
      <form:errors path="runId" />
  </div>
  <div style="visibility:hidden;"><label for="userId">User ID:</label>
      <form:input disabled="true" path="userId" id="userId"/>
      <form:errors path="userId" />
  </div>

<div id="responseButtons">
    <input type="submit" id="savebutton" value="Detach Student" />
    <input type="submit" onclick="javascript:window.close()" id="cancelbutton" value="Cancel" />
</div>

</form:form>

</div>
</div>    <!--    End of popUpTextBoundary -->

</body>
</html>