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
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/effects.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="application.title" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="../headerteacherhome.jsp"%>

<%@ include file="../L2homebar.jsp"%>

<div id="welcomeRow">

<div id="welcomeBox" class="panelStyling">

	<div id="header"><em>Welcome [User's Full Name Here]</em></div>
	
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
					<li><b>[Good morning!]</b></li>
					<li><b>[You have gradable work ready in 2 projects. See links to right.]</b></li>
					<li><b>[Announcement 3]</b></li>
					</ul>
				</td>
			</tr>
		</table>
</div>    <!-- End of welcomeBox-->

<div id="projectsBox" class="panelStyling">
	<div id="header">Projects Awaiting Grading</div>
	<table id="projectGradeLinkBox" cellpadding="5" cellspacing="5">
		<tr>
			<td class="tableColor">[Gradable Project Name A]</td>
			<td style="text-decoration:line-through;" style="width:25%;" class="projectLinkStyle"><a href="#">Grade By Step</a></td>
			<td style="text-decoration:line-through;" style="width:25%;" class="projectLinkStyle"><a href="#">Grade By Team</a></td>
		</tr>
		<tr>
			<td class="tableColor">[Gradable Project Name B]</td>
			<td style="text-decoration:line-through;" class="projectLinkStyle"><a href="#">Grade By Step</a></td>
			<td style="text-decoration:line-through;"class="projectLinkStyle"><a href="#">Grade By Team</a></td>
		</tr>
		<tr>
			<td class="tableColor">[Gradable Project Name C]</td>
			<td style="text-decoration:line-through;" class="projectLinkStyle"><a href="#">Grade By Step</a></td>
			<td style="text-decoration:line-through;" class="projectLinkStyle"><a href="#">Grade By Team</a></td>
		</tr>
	</table>
</div>  <!-- End of projectsBox-->
</div>   <!-- End of welcomeRow-->

<div id="secondRow">

<div id="dashboardBox" class="panelStyling">
	<div id="header">About the Dashboard</div>
	<table id="dashboardSections" cellspacing="8" cellpadding="2">
		<tr>
			<td><a href="#"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('homebuttonmini','','.././themes/tels/default/images/teacher/Home-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Home-Mini-Button2.png"
				alt="Home Button Mini" id="homebuttonmini" /></a></td>
			<td>Your current location. Includes announcements and quick links to popular tools.</td>
		</tr>
		<tr>
			<td><a href="#"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('projectbuttonmini','','.././themes/tels/default/images/teacher/Projects-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Projects-Mini-Button2.png"
				alt="Project Button Mini" id="projectbuttonmini" /></a></td>
			<td>Visit this section to search the WISE library, customize existing projects, or create your own!</td>
		</tr>
		<tr>
			<td><a href="#"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('gradingbuttonmini','','.././themes/tels/default/images/teacher/Grading-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Grading-Mini-Button2.png"
				alt="Grading Button Mini" id="gradingbuttonmini" /></a></td>
			<td>Visit this section to view and grade student work quickly and efficiently.</td>
		</tr>
		<tr>
			<td><a href="#"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('managementbuttonmini','','.././themes/tels/default/images/teacher/Management-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Management-Mini-Button2.png"
				alt="Management Button Mini" id="managementbuttonmini" /></a></td>
			<td>Visit this section to manage your students, print/export student work, or update your account.</td>
		</tr>
		<tr>
			<td><a href="#"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('helpbuttonmini','','.././themes/tels/default/images/teacher/Help-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Help-Mini-Button2.png"
				alt="Help Button Mini" id="helpbuttonmini" /></a></td>
			<td>This sections contains tools and guidelines to help you get WISE running smoothly in your classroom.</td>
		</tr>
	</table>
</div>   <!-- End of dashboardBox-->

<div id="quickLinksBox" class="panelStyling">

	<div id="header">Quick Links</div>

	<div id="quickLinks1">
		<div id="linkHeader">Projects</div>
		<ul>
			<li><a href="projects/curnitlist.html">Project Library</a></li>
			<li><a href="run/myprojectruns.html">My Project Runs</a></li>
			<li class="inactivecolor">My Bookmarked Projects</li>
			<li class="inactivecolor">My Customized Projects</li>
		</ul>
		<div id="linkHeader">Grading</div>
		<ul>
			<li><a href="run/myprojectruns.html?GRADING_ENABLED=TRUE">Grade Work by Step</a></li>
			<li class="inactivecolor">Grade Work by Team</li>
		</ul>
	</div>

	<div id="quickLinks2">
		<div id="linkHeader">Management</div>
		<ul>
			<li><a href="./management/projectPickerManagement.html">View My Students</a></li>			
			<li class="inactivecolor">Edit Ready-Made Comments</li>
			<li class="inactivecolor">Real-Time Class Monitor</li>
			<li class="inactivecolor">Review Score Values for Steps</li>
			<li class="inactivecolor">Export Grades</li>
			<li class="inactivecolor">Export All Work for Student</li>
			<li class="inactivecolor">Export All Work for Period</li>
			<li class="inactivecolor">Print All Work for Student</li>
			<li class="inactivecolor">Print All Work for Student</li>
			<li class="inactivecolor">Update My Account</li>
		</ul>
	</div>
	

</div>   <!-- End of quickLinksBox-->

</div>   <!-- End of secondRow-->

</div>   <!-- End of centeredDiv-->

</body>

</html>









