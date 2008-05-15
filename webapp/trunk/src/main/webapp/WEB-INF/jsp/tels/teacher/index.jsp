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
			
			<div id="headerTeacherHome"><spring:message code="teacher.index.1"/></div>
			
			<table id="teacherWelcomeBoxTable"  cellpadding="3" cellspacing="0" >
					<tr class="tableRowBorder">
						<td class="tableColor" style="width:26%;"><spring:message code="teacher.index.2"/></td>
						<td>${user.userDetails.firstname} ${user.userDetails.lastname} </td>
					</tr>
					<tr class="tableRowBorder">
						<td class="tableColor" style="width:26%;"><spring:message code="teacher.index.3"/></td>
						<c:set var="current_date" value="<%= new java.util.Date() %>" />
						<td><fmt:formatDate value="${current_date}" type="both" dateStyle="short" timeStyle="short" /></td>
					</tr>
					<tr class="tableRowBorder">
						<td class="tableColor"><spring:message code="teacher.index.4"/></td>
						<td>
						<c:choose>
							<c:when test="${user.userDetails.lastLoginTime == null}">
								<spring:message code="teacher.index.5"/>
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${user.userDetails.lastLoginTime}" 
									type="both" dateStyle="short" timeStyle="short" />
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td class="tableColor"><spring:message code="teacher.index.6"/></td>
						<td>
							<ul>
							<li><b>
							<c:choose>
						        <c:when test="${(current_date.hours>=3) && (current_date.hours<12)}" >
						            <spring:message code="teacher.index.7"/>
						        </c:when>
						        <c:when test="${(current_date.hours>=12) && (current_date.hours<18)}" >
									<spring:message code="teacher.index.8"/>
						        </c:when>
						        <c:otherwise>
									<spring:message code="teacher.index.9"/>
						        </c:otherwise>
						    </c:choose>
		    				</b></li>
							<li><b>[Auto Msg regarding projects w/steps to grade]</b></li>
							<li><b>[Special Announcements here]</b></li>
							</ul>
						</td>
					</tr>
				</table> 
</td> 

<td style="width:12px;"></td>

<td id="projectsPanel" class="panelStyling" >

			<div id="headerTeacherHome"><spring:message code="teacher.index.10"/></div>

			<table id="projectGradeLinkBox" cellpadding="5" cellspacing="5">
				<tr>
					<td class="tableColor">[Project Name: Sample A]</td>
					<td style="text-decoration:line-through;"><a href="#"><spring:message code="teacher.index.11"/></a></td>
					<td style="text-decoration:line-through;"><a href="#"><spring:message code="teacher.index.12"/></a></td>
				</tr>
				<tr>
					<td class="tableColor">[Project Name: Sample B]</td>
					<td style="text-decoration:line-through;"><a href="#"><spring:message code="teacher.index.11"/></a></td>
					<td style="text-decoration:line-through;"><a href="#"><spring:message code="teacher.index.12"/></a></td>
				</tr>
				<tr>
					<td class="tableColor">[Project Name: Sample C]</td>
					<td style="text-decoration:line-through;"><a href="#"><spring:message code="teacher.index.11"/></a></td>
					<td style="text-decoration:line-through;"><a href="#"><spring:message code="teacher.index.12"/></a></td>
				</tr>
			</table>
</td>
</tr>
</table>

<table id="teacherHomeTable1" class="secondTableMargin" >
<tr>
<td id="dashboardPanel" class="panelStyling">

	<div id="headerTeacherHome"><spring:message code="teacher.index.13"/></div>
	
	<table id="dashboardSections" cellspacing="4" cellpadding="2">
		<tr>
			<td><a href="../teacher/index.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('homebuttonmini','','.././themes/tels/default/images/teacher/Home-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Home-Mini-Button2.png"
				alt="Home Button Mini" id="homebuttonmini" /></a></td>
			<td><spring:message code="teacher.index.14"/></td>
		</tr>
		<tr>
			<td><a href="../teacher/projects/index.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('projectbuttonmini','','.././themes/tels/default/images/teacher/Projects-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Projects-Mini-Button2.png"
				alt="Project Button Mini" id="projectbuttonmini" /></a></td>
			<td><spring:message code="teacher.index.15"/></td>
		</tr>
		<tr>
			<td><a href="../teacher/grading/overview.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('gradingbuttonmini','','.././themes/tels/default/images/teacher/Grading-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Grading-Mini-Button2.png"
				alt="Grading Button Mini" id="gradingbuttonmini" /></a></td>
			<td><spring:message code="teacher.index.16"/></td>
		</tr>
		<tr>
			<td><a href="../teacher/management/overview.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('managementbuttonmini','','.././themes/tels/default/images/teacher/Management-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Management-Mini-Button2.png"
				alt="Management Button Mini" id="managementbuttonmini" /></a></td>
			<td><spring:message code="teacher.index.17"/></td>
		</tr>
		<tr>
			<td><a href="../teacher/help/overview.html"	onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('helpbuttonmini','','.././themes/tels/default/images/teacher/Help-Mini-Button-Roll2.png',1)">
				<img src=".././themes/tels/default/images/teacher/Help-Mini-Button2.png"
				alt="Help Button Mini" id="helpbuttonmini" /></a></td>
			<td><spring:message code="teacher.index.18"/></td>
		</tr>
	</table>
</td>

<td style="width:12px;"></td>

<td id="quickLinksPanel" class="panelStyling">

	<div id="headerTeacherHome"><spring:message code="teacher.index.19"/></div>

	<div id="quickLinks1">
		<div id="linkHeader"><spring:message code="teacher.index.20"/></div>
			<ul>
			<li><a href="projects/projectlibrary.html"><spring:message code="teacher.index.21"/></a></li>
			<li><a href="run/myprojectruns.html"><spring:message code="teacher.index.22"/></a></li>
			<li class="inactivecolor"><spring:message code="teacher.index.23"/></li>
			<li><a href="projects/customized/index.html"><spring:message code="teacher.index.24"/></a></li>
			</ul>
		<div id="linkHeader"><spring:message code="teacher.index.25"/></div>
			<ul>
			<!-- grade-by-step disabled until optimized 
			<li><a href="./grading/projectPickerGrading.html?gradeByType=step">Grade Work by Step</a></li>
			-->
			
			<li class="inactivecolor"><spring:message code="teacher.index.26"/></li>			
			<li><a href="./grading/projectPickerGrading.html?gradeByType=group"><spring:message code="teacher.index.27"/></a></li>
			<li class="inactivecolor"><spring:message code="teacher.index.28"/></li>
			<li class="inactivecolor"><spring:message code="teacher.index.29"/></li>
			</ul>		
	</div>

	<div id="quickLinks2">
		<div id="linkHeader"><spring:message code="teacher.index.30"/></div>
		<ul>
			<li><a href="./management/projectPickerManagement.html"><spring:message code="teacher.index.31"/></a></li>			
			<li class="inactivecolor"><spring:message code="teacher.index.32"/></li>
			<li class="inactivecolor"><spring:message code="teacher.index.33"/></li>
			<li><a href="./management/updatemyaccount.html"><spring:message code="teacher.index.34"/></a></li>
			<li style="visibility:hidden;">test</li>
		</ul>
		<div id="linkHeader"><spring:message code="teacher.index.35"/></div> 
		<ul>
			<li class="inactivecolor"><spring:message code="teacher.index.36"/></li>
			<li><a href="../contactwisegeneral.html"><spring:message code="teacher.index.37"/></a></li>
		</ul>
	</div>

</td>
</tr>
</table>

</div>   <!-- End of centeredDiv-->

</body>

</html>









