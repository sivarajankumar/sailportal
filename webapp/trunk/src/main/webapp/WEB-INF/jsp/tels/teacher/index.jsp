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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<script src=".././javascript/tels/effects.js" type="text/javascript" ></script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="application.title" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="../headerteachernohighlight.jsp"%>

<%@ include file="../L2blankbar.jsp"%>

<div id="welcomeRow">

<div id="welcomeBox" class="panelStyling">

	<div id="header" class="blueText"><em>Welcome [User's Full Name Here]</em></div>
	
	<table id="teacherWelcomeBoxTable"  cellpadding="5" cellspacing="0" >
			<tr class="tableRowBorder">
				<td class="tableColor" style="width:20%;">Current Sign In:</td>
				<td><strong>[Current SignIn Date/Time Stamp]</strong></td>
			</tr>
			<tr class="tableRowBorder">
				<td class="tableColor">Last Sign In:</td>
				<td><strong>[Most Recent SignIn Date/Time Stamp]</strong></td>
			</tr>
			<tr>
				<td class="tableColor">Announcements:</td>
				<td>
					<ul>
					<li><strong>Good morning, [User FirstName]!</strong></li>
					<li><strong>Announcement 2</strong></li>
					<li><strong>Announcement 3</strong></li>
					</ul>
				</td>
			</tr>
		</table>
</div>

<div id="projectsBox" class="panelStyling">
<form id="projects" method="post" action="../j_acegi_security_check">
	<div id="header">Projects Awaiting Grading</div>
	<ul id="projectNames">
	<li>
	<label for="projectname">
	Project Name 1:
	</label>
	<div id="linkPos0"> Grade By Step</div>
	<div id="linkPos1"> Grade By Student</div>
	</li>
	<li>
	<label for="projectname">
	Project Name 2:
	</label>
	<div id="linkPos0"> Grade By Step</div>
	<div id="linkPos1"> Grade By Student</div>
	</li>
	<li>
	<label for="projectname">
	Project Name 3:
	</label>
	<div id="linkPos0"> Grade By Step</div>
	<div id="linkPos1"> Grade By Student</div>
	</li>
	<li>
	<label for="projectname">
	Project Name 4:
	</label>
	<div id="linkPos0"> Grade By Step</div>
	<div id="linkPos1"> Grade By Student</div>
	</li>
	</ul>
</form>

</div>

</div>


<div class="center">
<div id="dashboardBox"  class="panelStyling">

<div id="innerDashboard" class="panelStyling">
<div id="header">About the Dashboard</div>
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

<div id="header">Quick Links</div>


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
<td class="hidden" align="left"><a href="#" onclick="displayNotAvailable('This page is not available yet')">
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
<td class="hidden" align="left"> 
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
My Bookmarked Projects
</a>
</td>

<td align="left">
</td>
</tr>
<tr>
<td class="hidden" align="left">
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
<a href="management/viewmystudents.html">
View My Students
</a>
</td>
<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Export All Work for Student
</a>
</td>
</tr>
<tr>

<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Export All Work for a Class
</a>
</td>
</tr>
<tr>
<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Edit Ready-Made Comments
</a>
</td>
<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Print All Work for Student
</a>
</td>
</tr>
<tr>
<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Real-Time Class Monitor
</a>
</td>
<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Print All work for Class
</a>
</td>
</tr>
<tr>
<td class="hidden" align="left">
<a href="#" onclick="displayNotAvailable('This page is not available yet')">
Batch Set Passwords
</a>
</td>
<td class="hidden" align="left">
<a href="management/updatemyaccount.html">
Update My Account
</a>
</td>
</tr>
</table>

</div>
</div>
</div>
</div>



</body>

</html>









