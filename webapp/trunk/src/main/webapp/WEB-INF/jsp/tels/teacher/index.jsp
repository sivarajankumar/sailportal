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
	
	<table id="teacherWelcomeBoxTable"  cellpadding="3" cellspacing="0" >
			<tr class="tableRowBorder">
				<td class="tableColor" style="width:25%;">Current Sign In:</td>
				<td>[Current SignIn Date/Time Stamp]</td>
			</tr>
			<tr class="tableRowBorder">
				<td class="tableColor">Last Sign In:</td>
				<td>[Most Recent SignIn Date/Time Stamp]</td>
			</tr>
			<tr>
				<td class="tableColor">Announcements:</td>
				<td>
					<ul>
					<li>[Good morning!]</li>
					<li>[You have gradable work ready in 2 projects. See links to right.]</li>
					<li>[Announcement 3]</li>
					</ul>
				</td>
			</tr>
		</table>
</div>    <!-- End of welcomeBox-->

<div id="projectsBox" class="panelStyling">
	<div id="header">Projects Awaiting Grading</div>
	<table id="projectGradeLinkBox" cellpadding="4" cellspacing="3">
		<tr>
			<td class="projectNameStyle">[Gradable Project Name A]</td>
			<td style="width:25%;" class="projectLinkStyle">Grade By Step</td>
			<td style="width:25%;" class="projectLinkStyle">Grade By Team</td>
		</tr>
		<tr>
			<td class="projectNameStyle">[Gradable Project Name B]</td>
			<td class="projectLinkStyle">Grade By Step</td>
			<td class="projectLinkStyle">Grade By Team</td>
		</tr>
		<tr>
			<td class="projectNameStyle">[Gradable Project Name C]</td>
			<td class="projectLinkStyle">Grade By Step</td>
			<td class="projectLinkStyle">Grade By Team</td>
		</tr>
	</table>
</div>  <!-- End of projectsBox-->
</div>   <!-- End of welcomeRow-->

<div id="secondRow">

<div id="dashboardBox" class="panelStyling">
	<div id="header">About the Dashboard</div>
	<div id="dashboardMsg">Details about the four Dashboard sections at top of the screen:</div>
	<table id="dashboardSections">
		<tr>
			<td><a href="#"> <img src="../<spring:theme code="home_mini" />" id="dashboardButtonPos" /></a></td>
			<td>Sign-in data, links to Recent Areas, links to Projects Awaiting Grading</td>
		</tr>
		<tr>
			<td><a href="#"><img src="../<spring:theme code="projects_mini" />" id="dashboardButtonPos" /></a></td>
			<td>Search the library of WISE and TELS projects, edit existing projects, or create your own!</td>
		</tr>
		<tr>
			<td><a href="#"><img src="../<spring:theme code="grading_mini" />" id="dashboardButtonPos" /></a></td>
			<td>Online tools for grading student work quickly and efficiently.</td>
		</tr>
		<tr>
			<td><a href="#" ><img src="../<spring:theme code="management_mini" />" id="dashboardButtonPos"/></a></td>
			<td>Online tools to manage your students, print or export reports, and update your account.</td>
		</tr>
		<tr>
			<td><a href="#"><img src="../<spring:theme code="help_mini" />" id="dashboardButtonPos" /></a></td>
			<td>Resources and tools to get WISE running more smoothly in your classroom.</td>
		</tr>
	</table>
</div>   <!-- End of dashboardBox-->

<div id="quickLinksBox" class="panelStyling">

	<div id="header">Quick Links</div>

	<table border="0" id="quickLinksTable">
		<tr>
			<th><b>Projects</b> </th>
			<th><b> Management(assessment)</b></th>
		</tr>
		<tr>
			<td><a href="../curnitlist.html">Project Library</a></td>
			<td class="hidden" align="left"><a href="#">Grade Work by Student-Group</a></td>
		</tr>
		<tr>
			<td><a href="run/myprojectruns.html?GRADING_ENABLED=FALSE">My Project Runs</a></td>
			<td><a href="run/myprojectruns.html?GRADING_ENABLED=TRUE">Grade Work by Step</a></td>
		</tr>
		<tr>
			<td class="hidden"><a href="#">My Bookmarked Projects</a></td>
			<td></td>
		</tr>
		<tr>
			<td class="hidden"><a href="#">My Customized Projects</a></td>
			<td></td>
		</tr>
		<tr>
			<th><b>Management(students)</b></th>
			<th><b>Misc.</b></th>
		</tr>
		<tr>
			<td><a href="management/viewmystudents.html">View My Students</a></td>
			<td class="hidden"><a href="#">Export All Work for Student</a></td>
		</tr>
		<tr>
			<td class="hidden" ><a href="#">Export All Work for a Class</a></td>
			<td></td>
		</tr>
		<tr>
			<td class="hidden"><a href="#">Edit Ready-Made Comments</a></td>
			<td class="hidden"><a href="#">Print All Work for Student</a></td>
		</tr>
		<tr>
			<td class="hidden""><a href="#">Real-Time Class Monitor</a></td>
			<td class="hidden"><a href="#" >Print All work for Class</a></td>
		</tr>
		<tr>
			<td class="hidden"><a href="#">Batch Set Passwords</a></td>
			<td class="hidden"><a href="management/updatemyaccount.html">Update My Account</a></td>
		</tr>
	</table>

</div>   <!-- End of quickLinksBox-->
</div>   <!-- End of secondRow-->

</div>   <!-- End of centeredDiv-->

</body>

</html>









