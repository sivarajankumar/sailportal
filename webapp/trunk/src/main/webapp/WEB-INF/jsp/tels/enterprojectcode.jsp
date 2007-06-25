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

<!-- $Id: setupRun1.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<%@page import="java.text.DateFormat"%>
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script type="text/javascript">
function addProjectCode(form){
	// open the window
	win = window.open("", "Add Project Code", "width=400,height=300,scrollbars=no");
	// write to window
	win.document.writeln("<html><body style='background-color:rgb(200,50,50);'>");
	win.document.writeln("<h2 align='center'>Add A Project Code</h2>");
	win.document.writeln("<p> To add a project, fill in the information below. </p>");	
	win.document.writeln("<form><div><ul style='list-style-type:none'>");
	win.document.writeln("<li> <label name='projectCode'> Project Code: </label>");
	win.document.writeln("<input type='text' size=20 value='' />");
	win.document.writeln("</li>");
	win.document.writeln("<li> <label name='classPeriod'> Class Period: </label>");
	win.document.writeln("<input type='text' size=20 value='' />");
	win.document.writeln("</li>");	
	win.document.writeln("</ul></div>");
	win.document.writeln("<input type='button' size=10 value='Add Project' />");
	win.document.writeln("<input type='button' size=10 value='Cancel' />");
	win.document.writeln("</form>");
	win.document.writeln("</body></html>");
	
}
</script>


<title><spring:message code="teacher.setup-project-run" /></title>
</head>
<body>

<%@ include file="student/header.jsp"%>
<div class=" border widthAdj1 north0">
<p class="left"> User(s): 
<authz:authentication operation="username" />
</p>
"The time is" <%= new java.util.Date() %>
<div id="navigation" class="center fontSizeAdj widthAdj2">
<h4 id="left"> Account Options </h4>
<form> 
<div>
<input type="button" class="bgColorYellow medFont bold" size=15 value="ADD A PROJECT" onclick="addProjectCode(this.form)"/> <br />
<input type="button" disabled class="bgColorBlue medFont" size=15 value="Change Password" /> <br />
<input type="button" disabled class="bgColorBlue medFont" size=15 value="Change Language" /> <br />
<input type="button" disabled class="bgColorBlue medFont" size=15 value="Sign Out" /> <br />
</div>
</form>
</div>

<ul id="tabnav" class="north_1 widthAdj3 right1">
<li class="currentProjects bgColorLightBlue borderBottom"> <a href="#"><spring:message code="student.current-projects" /></a></li>
<li class="archivedProjects"> <a href="studentarchivedprojects.html"><spring:message code="student.archived-projects" /></a></li>
</ul>

<div class="right2 north5 widthAdj3 border"> 
<h3 class="left"> Projects with UserName1 </h3>
<table border="1">
<tr> 
<td> Name: </td>
<td> Project Name 1 </td> 
<td class="bold bgColorYellow"><a href="teamsignin.html" class="none"> <spring:message code="student.run-project" /></a></td>

</tr>
<tr> 
<td> Period: </td>
<td> 2 </td>
<td> <spring:message code="student.change-period-or-team" /> </td>
</tr>
<tr>
<td> Team: </td>
<td> Username 1 + Username 2 (sn1, sn2) </td>
<td> <spring:message code="wise.report-a-problem" /> </td>
</tr>
<tr>
<td> Last Use: </td>
<td> Tuesday, 5/23/04 </td>
<td> <spring:message code="student.archive-this-project" /></td>
</tr>
</table>
<h3 class="left"> Projects with UserName2 </h3>
<table border="1">
<tr> 
<td> Name: </td>
<td> Project Name 1 </td> 
<td class="bold bgColorYellow"><a href="teamsignin.html" class="none"> <spring:message code="student.run-project" /> </a></td>
</tr>
<tr> 
<td> Period: </td>
<td> 2 </td>
<td> <spring:message code="student.change-period-or-team" /> </td>
</tr>
<tr>
<td> Team: </td>
<td> Username 1 + Username 2 (sn1, sn2) </td>
<td> <spring:message code="wise.report-a-problem" /> </td>
</tr>
<tr>
<td> Last Use: </td>
<td> Tuesday, 5/23/04 </td>
<td> <spring:message code="student.archive-this-project" /> </td>

</tr>
</table>


</div>


<div>
<a href="javascript:Effect.toggle('waiting', 'appear')">click me</a>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>