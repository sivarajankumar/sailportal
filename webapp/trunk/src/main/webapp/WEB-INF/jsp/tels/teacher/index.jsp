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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet"
    type="text/css" />
 <script type="text/javascript" src=".././javascript/tels/general.js"></script>
<title><spring:message code="application.title" /></title>
</head>

<body>
<%@ include file="teacherHeader.jsp" %> 
 
<div id="welcome">
<div id="innerWelcome">
<h1 id="headingPos">
Welcome: ${username}
</h1>
<p id="userLoginInfo"> 
	Current Log-in: 
		Thu Aug 09 13:17:23 EDT 2007
	<br />
	Last Login: 
		Thu Aug 09 13:17:23 EDT 2007
	<br />
	Announcements:
		nothing here yet
	<br />
</p>
</div>

<div id="projectsBox">
<form id="projects" method="post" action="../j_acegi_security_check">
	<h1 id="headingPos"> Projects Awaiting Grading</h1>
	<ul id="projectNames">
	<li>
	<label for="projectname">
	ProjectName:
	</label>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos0"> Grade By Step</a>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos1"> Grade By Student</a>
	</li>
	<li>
	<label for="projectname">
	ProjectName:
	</label>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos0"> Grade By Step</a>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos1"> Grade By Student</a>
	</li>
		<li>
	<label for="projectname">
	ProjectName:
	</label>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos0"> Grade By Step</a>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos1"> Grade By Student</a>
	</li>
		<li>
	<label for="projectname">
	ProjectName:
	</label>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos0"> Grade By Step</a>
	<a href="#" onclick="displayNotAvailable('This page is not available yet')" id="linkPos1"> Grade By Student</a>
	</li>
	</ul>
</form>

</div>

</div>

<div id="dashboardBox">
<div id="innerDashboard">
<h1 id="headingPos">
About the Dashboard
</h1>
<p id="dashboardMsg">Details about the four Dashboard sections at top of the screen:</p>
<table id="dashboardSections">
<tr>
<td>   
<a href="#"  onclick="displayNotAvailable('This page is not available yet')">
<img src="../<spring:theme code="home_mini" />" id="dashboardButtonPos" 
 />
</a>
</td>
<td>
Sign-in data, links to Recent Areas, links to Projects Awaiting Grading
</td>
</tr>
<tr>
<td>
<a href="#"  onclick="displayNotAvailable('This page is not available yet');">
<img src="../<spring:theme code="projects_mini" />" id="dashboardButtonPos" />
</a>
</td>
<td>
Search the library of WISE and TELS projects, edit existing projects, or create your own!
</td>
</tr>
<tr>
<td>
<a href="#"  onclick="displayNotAvailable('This page is not available yet');">
<img src="../<spring:theme code="grading_mini" />" id="dashboardButtonPos" />
</a>
</td>
<td>
Online tools for grading student work
quickly and efficiently.
</td>
</tr>

<tr>
<td>
<a href="#"  onclick="displayNotAvailable('This page is not available yet')">
<img src="../<spring:theme code="management_mini" />" id="dashboardButtonPos"/>
</a>
</td>
<td>
Online tools to manage your students, print or export reports, and update your account.
</td>
</tr>
<tr>
<td>
<a href="#"  onclick="displayNotAvailable('This page is not available yet')">
<img src="../<spring:theme code="help_mini" />" id="dashboardButtonPos" />
</a>
</td>
<td>
Resources and tools to get WISE running more smoothly in your classroom.
</td>
</tr>
</table>
</div>
<div id="quickLinksPos">
<h1 id="headingPos">
Quick Links
</h1>

<table border="0" id="quickLinksTable">
<tr>
<th align="left"> <b>Projects</b> </th>
<th align="left"><b> Management(assessment)</b></th>
</tr>
<tr>
<td align="left"><a href="../curnitlist.html">
    Project Library
    </a>
</td>
<td align="left"><a href="#" onclick="displayNotAvailable('This page is not available yet')">
Grade Work by Student-Group
</a>
</td>
</tr>
<tr>
<td align="left">
    <a href="run/myprojectruns.html?GRADING_ENABLED=FALSE">
My Project Runs
</a>
</td>
<td align="left">
<a href="run/myprojectruns.html?GRADING_ENABLED=TRUE">
Grade Work by Step
</a>
</td>
</tr>
<tr>
<td align="left"> 
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
My Bookmarked Projects
</a>
</td>

<td align="left">
</td>
</tr>
<tr>
<td align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
My Customized Projects
</a>
</td>
</tr>
<tr />
<tr>
<th align="left">
<b>Management(students)</b> 
</th>
<th align="left">
<b>Misc.</b>
</th>
</tr>
<tr>
<td align="left">
</td>
<td align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Export All Work for Student
</a>
</td>
</tr>
<tr>
<td align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
View My Students
</a>
</td>
<td align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Export All Work for a Class
</a>
</td>
</tr>
<tr>
<td align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Edit Ready-Made Comments
</a>
</td>
<td align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Print All Work for Student
</a>
</td>
</tr>
<tr>
<td>
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Real-Time Class Monitor
</a>
</td>
<td>
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Print All work for Class
</a>
</td>
</tr>
<tr>
<td>
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Batch Set Passwords
</a>
</td>
<td>
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Update My Account
</a>
</td>
</tr>
</table>

</div>
</div>


<!-- Support for Spring errors object -->





</body></html>

