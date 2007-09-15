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

<%@ include file="studentHeader.jsp"%>
<div class=" border marginAdj5" style="width:470px;position:relative;top:210px;">
<p class="left"> <spring:message code="wise.users" /> 
<authz:authentication operation="username" />
</p>
"The time is" <%= new java.util.Date() %>
<div id="navigation" class="center fontSizeAdj" style="width:160px;">
<h4 id="left"> <spring:message code="wise.account-options" /> </h4>
<form> 
<div>
<input type="button" style="background-color:rgb(255,255,200);font-size:1.0em;" class="bold" size=15 value="<spring:message code='wise.add-project' />" onclick="addProjectCode(this.form)"/> <br />
<input type="button" disabled style="font-size:1.0em;" class="bgColorBlue" size=15 value="<spring:message code='wise.change-password' />" /> <br />
<input type="button" disabled style="font-size:1.0em;" class="bgColorBlue" size=15 value="<spring:message code='wise.change-language' />" /> <br />
<input type="button" disabled style="font-size:1.0em;" class="bgColorBlue" size=15 value="<spring:message code='log.out' />" /> <br />
</div>
</form>
</div>
</div>
<ul id="tabnav" style="width:660px;">
<li class="currentProjects bgColorLightBlue" style="border-bottom:0px;"> <a href="#"><spring:message code="student.current-projects" /></a></li>
<li class="archivedProjects"> <a href="studentarchivedprojects.html"><spring:message code="student.archived-projects" /></a></li>
</ul>

<div class="north5 border" style="width:660px;"> 
<h3 class="left"> <spring:message code="wise.projects-with" /> UserName1 </h3>
<table border="1">
<tr> 
<td> <spring:message code="wise.name" /> </td>
<td> Project Name 1 </td> 
<td class="bold" style="background-color:rgb(255,255,200);"><a href="teamsignin.html" class="none"> <spring:message code="student.run-project" /></a></td>

</tr>
<tr> 
<td> <spring:message code="wise.period" /> </td>
<td> 2 </td>
<td> <spring:message code="student.change-period-or-team" /> </td>
</tr>
<tr>
<td> <spring:message code="wise.team" /> </td>
<td> Username 1 + Username 2 (sn1, sn2) </td>
<td> <spring:message code="wise.report-a-problem" /> </td>
</tr>
<tr>
<td> <spring:message code="wise.last-use" /> </td>
<td> Tuesday, 5/23/04 </td>
<td> <spring:message code="student.archive-this-project" /></td>
</tr>
</table>
<h3 class="left"> Projects with UserName2 </h3>
<table border="1">
<tr> 
<td> <spring:message code="wise.name" /> </td>
<td> Project Name 1 </td> 
<td class="bold" style="background-color:rgb(255,255,200);"><a href="teamsignin.html" class="none"> <spring:message code="student.run-project" /> </a></td>
</tr>
<tr> 
<td> <spring:message code="wise.period" /> </td>
<td> 2 </td>
<td> <spring:message code="student.change-period-or-team" /> </td>
</tr>
<tr>
<td> <spring:message code="wise.team" /> </td>
<td> Username 1 + Username 2 (sn1, sn2) </td>
<td> <spring:message code="wise.report-a-problem" /> </td>
</tr>
<tr>
<td> <spring:message code="wise.last-use" /> </td>
<td> Tuesday, 5/23/04 </td>
<td> <spring:message code="student.archive-this-project" /> </td>

</tr>
</table>


</div>


<div class="north marginAdj6">
<a href="javascript:Effect.toggle('waiting', 'appear')">click me</a>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>