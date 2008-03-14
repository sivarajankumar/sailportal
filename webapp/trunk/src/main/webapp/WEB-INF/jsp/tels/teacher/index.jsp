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

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />


<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/effects.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="application.title" /></title>

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="../javascript/tels/iefixes.js"></script>
<![endif]-->

</head>

<body>

<div id="centeredDiv">

<%@ include file="../headerteacherhome.jsp"%>

<%@ include file="../L2homebar.jsp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="teacherHomeTable1" >
<tr>
<td id="welcomePanel" class="panelStyling">
			
			<div id="headerTeacherHome">Welcome to WISE 3.0!</div>
			
			<table id="teacherWelcomeBoxTable"  cellpadding="3" cellspacing="0" >
					<tr class="tableRowBorder">
						<td class="tableColor" style="width:26%;">Current User:</td>
						<td>${user.userDetails.firstname} ${user.userDetails.lastname} </td>
					</tr>
					<tr class="tableRowBorder">
						<td class="tableColor" style="width:26%;">Current Sign In:</td>
						<c:set var="current_date" value="<%= new java.util.Date() %>" />
						<td><fmt:formatDate value="${current_date}" type="both" dateStyle="short" timeStyle="short" /></td>
					</tr>
					<tr class="tableRowBorder">
						<td class="tableColor">Last Sign In:</td>
						<td>
						<c:choose>
							<c:when test="${user.userDetails.lastLoginTime == null}">
								never
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${user.userDetails.lastLoginTime}" 
									type="both" dateStyle="short" timeStyle="short" />
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td class="tableColor">Announcements:</td>
						<td>
							<ul>
							<li><b>
							<c:choose>
						        <c:when test="${(current_date.hours>=3) && (current_date.hours<12)}" >
						            Good morning!
						        </c:when>
						        <c:when test="${(current_date.hours>=12) && (current_date.hours<18)}" >
									Good afternoon!	
						        </c:when>
						        <c:otherwise>
									Hello Night Owl!
						        </c:otherwise>
						    </c:choose>
		    				</b></li>
							<li><b>[You have gradable work ready in 2 projects. See links to right.]</b></li>
							<li><b>[Announcement 3]</b></li>
							</ul>
						</td>
					</tr>
				</table> 
</td> 

<td style="width:12px;"></td>

<td id="projectsPanel" class="panelStyling" >

			<div id="headerTeacherHome">Projects Awaiting Grading</div>

			<table id="projectGradeLinkBox" cellpadding="5" cellspacing="5">
				<tr>
					<td class="tableColor">[Gradable Project Name A]</td>
					<td style="text-decoration:line-through;"><a href="#">Grade By Step</a></td>
					<td style="text-decoration:line-through;"><a href="#">Grade By Team</a></td>
				</tr>
				<tr>
					<td class="tableColor">[Gradable Project Name B]</td>
					<td style="text-decoration:line-through;"><a href="#">Grade By Step</a></td>
					<td style="text-decoration:line-through;"><a href="#">Grade By Team</a></td>
				</tr>
				<tr>
					<td class="tableColor">[Gradable Project Name C]</td>
					<td style="text-decoration:line-through;"><a href="#">Grade By Step</a></td>
					<td style="text-decoration:line-through;"><a href="#">Grade By Team</a></td>
				</tr>
			</table>
</td>
</tr>
</table>

<table id="teacherHomeTable1" class="secondTableMargin" >
<tr>
<td id="dashboardPanel" class="panelStyling">

	<div id="headerTeacherHome">About the Dashboard</div>
	
	<table id="dashboardSections" cellspacing="4" cellpadding="2">
		<tr>
			<td><a href="../teacher/index.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('homebuttonmini','','.././themes/tels/default/images/teacher/Home-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Home-Mini-Button2.png"
				alt="Home Button Mini" id="homebuttonmini" /></a></td>
			<td>Your current location. Includes announcements and quick links to popular tools.</td>
		</tr>
		<tr>
			<td><a href="../teacher/projects/index.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('projectbuttonmini','','.././themes/tels/default/images/teacher/Projects-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Projects-Mini-Button2.png"
				alt="Project Button Mini" id="projectbuttonmini" /></a></td>
			<td>Search the WISE library, customize/create projects, view your current Project Runs.</td>
		</tr>
		<tr>
			<td><a href="../teacher/grading/overview.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('gradingbuttonmini','','.././themes/tels/default/images/teacher/Grading-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Grading-Mini-Button2.png"
				alt="Grading Button Mini" id="gradingbuttonmini" /></a></td>
			<td>View and grade student work using an assortment of time-saving tools.</td>
		</tr>
		<tr>
			<td><a href="../teacher/management/overview.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('managementbuttonmini','','.././themes/tels/default/images/teacher/Management-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Management-Mini-Button2.png"
				alt="Management Button Mini" id="managementbuttonmini" /></a></td>
			<td>Manage your students, print/export student work, update your account, and more.</td>
		</tr>
		<tr>
			<td><a href="../teacher/help/overview.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('helpbuttonmini','','.././themes/tels/default/images/teacher/Help-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Help-Mini-Button2.png"
				alt="Help Button Mini" id="helpbuttonmini" /></a></td>
			<td>Review guidelines to help you use WISE 3.0 smoothly in your classroom.</td>
		</tr>
	</table>
</td>

<td style="width:12px;"></td>

<td id="quickLinksPanel" class="panelStyling">

	<div id="headerTeacherHome">Quick Links</div>

	<div id="quickLinks1">
		<div id="linkHeader">Projects</div>
			<ul>
			<li><a href="projects/projectlibrary.html">Project Library</a></li>
			<li><a href="run/myprojectruns.html">My Project Runs</a></li>
			<li><a href="run/myprojectruns.html">View Student Codes</a></li>
			<li class="inactivecolor">My Bookmarked Projects</li>
			<li class="inactivecolor">My Customized Projects</li>
			</ul>
		<div id="linkHeader">Grading</div>
			<ul>
			<li><a href="./grading/projectPickerGrading.html">Grade Work by Step</a></li>
			<li class="inactivecolor">Grade Work by Team</li>
			<li class="inactivecolor">View Student Score Summary</li>
			<li class="inactivecolor">Edit Ready-Made Comments</li>
			</ul>		
	</div>

	<div id="quickLinks2">
		<div id="linkHeader">Management</div>
		<ul>
			<li><a href="./management/projectPickerManagement.html">Manage My Students</a></li>			
			<li class="inactivecolor">Real-Time Class Monitor</li>
			<li class="inactivecolor">Print Student Work</li>
			<li><a href="./management/updatemyaccount.html">Update My Account</a></li>
			<li style="visibility:hidden;">test</li>
		</ul>
		<div id="linkHeader">Help</div> 
		<ul>
			<li class="inactivecolor">Guide for New Users</li>
			<li><a href="../contactwisegeneral.html">Contact WISE</a></li>
		</ul>
	</div>

</td>
</tr>
</table>

</div>   <!-- End of centeredDiv-->

</body>

</html>









