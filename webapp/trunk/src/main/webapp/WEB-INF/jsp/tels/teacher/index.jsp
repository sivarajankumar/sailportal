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
<%@page import="java.text.DateFormat"%>
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<script src=".././javascript/tels/rotator.js" type="text/javascript" ></script>
<title><spring:message code="application.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>
<div id="navigation" class="north2 widthAdj4">
<ul class="bigFont1">
<li> <a href="teacher/index.html"> <img src="../<spring:theme code="home_selected" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="../<spring:theme code="projects" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="../<spring:theme code="management" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="../<spring:theme code="help" />" style="border:0px;"/> </a> </li>
</ul>
</div>
<ul id="tabnav" class="north_6" style="width:980px;">
<li class="border" style="border-bottom:2px solid #ffffff;">
Overview
</li>
</ul>
<div class="bgColorYellow north3" style="width:980px;"> 

<div id="xsnazzy" class="bgcolorSeaBlue widthAdj1 " >
	<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
	<div class="border1 spacingVerAdj1">
		<h4 class="heading2 north6">
		<spring:message code="welcome" /> 
		    <authz:authentication operation="username" />
		</h4>
		<div id="north2">
		<spring:message code="teacher.currentlogin" /> 
		 <%= new java.util.Date() %>
		<br />
		<spring:message code="teacher.lastlogin" /> 
		 <%= new java.util.Date() %>
		<br />
		<spring:message code="teacher.announcement" />
		nothing here yet
		<br />
		</div>
	</div>
	<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
	
 </div>

 <div id="xsnazzy" class="bgColorSeaBlue widthAdj1 north5 right0">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1 spacingVerAdj2">
<form id="home" method="post" action="j_acegi_security_check">
<h4 class="heading2 north6"><spring:message code="teacher.projects-awaiting-grading" /></h4>
<ul class="lineHeiightAdj2 north">
<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#" onclick="javascript:alert('This page is not available yet')"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" onclick="javascript:alert('This page is not available yet')" class="right4"> <spring:message code="teacher.grade-by-student" /></a>

</li>

<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#" onclick="javascript:alert('This page is not available yet')"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" onclick="javascript:alert('This page is not available yet')" class="right4"> <spring:message code="teacher.grade-by-student" /></a>
</li>

<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#" onclick="javascript:alert('This page is not available yet')"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" onclick="javascript:alert('This page is not available yet')" class="right4"> <spring:message code="teacher.grade-by-student" /></a>
</li>

<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#" onclick="javascript:alert('This page is not available yet')"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" onclick="javascript:alert('This page is not available yet')" class="right4"> <spring:message code="teacher.grade-by-student" /></a>
</li>

</ul>


</form>
<spring:message code="wise.click-any-link" />
</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>
 
 
<div id="xsnazzy" class="north1 widthAdj1">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>

<div class="border1 spacingVerAdj3">
<h4 class="heading2 north6">
<spring:message code="teacher.about-dashboard" />
</h4>
<p class="north4"><spring:message code="teacher.dashboard-details" /></p>


<div id="navigation">
<ul id="left" class="lineHeightAdj2 none north4 ">
<li>
<a href="#"  onclick="javascript:alert('This page is not available yet')">
<img src="../<spring:theme code="home_mini" />" style="border:0px;"  />
</a>
</li>
<li>
<p class="_right1 south1 justify widthAdj5 smallFont marginAdj3" > 
<spring:message code="teacher.home-desc" /></p>
</li>
</ul>

<ul id="left" class="lineHeightAdj2 none" style="position:relative;top:-10px;">
<li>
<a href="#"  onclick="javascript:alert('This page is not available yet')">
<img src="../<spring:theme code="projects_mini" />" style="border:0px;"/>
</a>
</li>
<li>
<p class="_right1 south1 justify widthAdj5 smallFont marginAdj3" > <spring:message code="teacher.projects-desc" /></p>
</li>
</ul>

<ul id="left" class="lineHeightAdj2 none" style="position:relative;top:30px;">
<li>
<a href="#"  onclick="javascript:alert('This page is not available yet')">
<img src="../<spring:theme code="management_mini" />" style="border:0px;"/>
</a>
</li>
<li>
<p class="_right1 south1 justify widthAdj5 smallFont marginAdj3" > <spring:message code="teacher.management-desc" /></p>
</li>
</ul>

<ul id="left" class="lineHeightAdj2 none" style="position:relative;top:70px;">
<li>
<a href="#"  onclick="javascript:alert('This page is not available yet')">
<img src="../<spring:theme code="help_mini" />" style="border:0px;"/>
</a>
</li>
<li>
<p class="_right1 south1 justify widthAdj5 smallFont marginAdj3" > <spring:message code="teacher.help-desc" /></p>
</li>
</ul>

</div>
</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>


<div id="xsnazzy" class="widthAdj1 right0 north9">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>

<div class="border1">
<h4 class="heading2 north6">
<spring:message code="teacher.quick-links" />
</h4>
<table border="0" padding="0" class="smallFont1 widthAdj1 north_4" style="margin-bottom:-39.5px;">
<tr>
<th align="left"> <b><spring:message code="banner.projects" /></b> </th>
<th align="left"><b> <spring:message code="banner.management" /><spring:message code="teacher.assessment" /></b></th>
</tr>
<tr>
<td align="left"><a href="../curnitlist.html">
    <spring:message code="teacher.project-library" />
    </a>
</td>
<td align="left"><a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="teacher.grade-work-by-student-group" />
</a>
</td>
</tr>
<tr>
<td align="left">
    <a href="run/myprojectruns.html">
<spring:message code="teacher.my-project-runs" />
</a>
</td>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="teacher.grade-work-by-step" />
</a>
</td>
</tr>
<tr>
<td align="left"> 
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="teacher.my-bookmarked-projects" />
</a>
</td>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="teacher.edit-ready-made-comments" />
</a>
</td>
</tr>
<tr>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="teacher.my-customized-projects" />
</a>
</td>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="teacher.real-time-class-monitor" />
</a>
</td>
</tr>
<tr />
<tr>
<th align="left">
<b><spring:message code="banner.management" /><spring:message code="students" /></b> 
</th>
<th align="left">
<b><spring:message code="misc" /></b>
</th>
</tr>
<tr>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.view-teacher-code" />
</a>
</td>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.export-all-work-for-student" />
</a>
</td>
</tr>
<tr>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.view-my-students" />
</a>
</td>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.export-all-work-for-class" />
</a>
</td>
</tr>
<tr>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.my-bookmarked-projects" />
</a>
</td>
<td align="left">
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.print-all-work-for-student" />
</a>
</td>
</tr>
<tr>
<td>
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.my-customized-projects" />
</a>
</td>
<td>
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.print-all-work-for-class" />
</a>
</td>
</tr>
<tr>
<td>
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.batch-set-passwords" />
</a>
</td>
<td>
<a href="#" onclick="javascript:alert('This page is not available yet')">
<spring:message code="student.update-account" />
</a>
</td>
</tr>

</table>

</div>

<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>





</div>
</body>
</html>


<!-- 

<br />


 -->

