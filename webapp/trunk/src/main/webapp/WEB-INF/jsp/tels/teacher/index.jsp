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
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
</head>

<body>

<%@ include file="header.jsp"%>
<div id="navigation" class="north2 widthAdj4">
<ul class="bigFont1">
<li> <a href="teacher/index.html"> <img src="../<spring:theme code="home_selected" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="../<spring:theme code="projects" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="../<spring:theme code="management" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="../<spring:theme code="help" />" style="border:0px;"/> </a> </li>
</ul>
</div>

<div id="columns" class="bgColorYellow" style="width:980px;">
<div class="north_6">
<div id="xsnazzy" class="bgcolorSeaBlue widthAdj1" >
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1">
<h4 class="heading2 north6">
<spring:message code="welcome" /> 
    <authz:authentication operation="username" />
</h4>
<div id="north2">
<spring:message code="teacher.currentlogin" /> <br />
<spring:message code="teacher.lastlogin" /> <br />
<spring:message code="teacher.announcement" />
<ul id="right0"> 
<li></li>
<li></li>
</ul>
</div>
</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>

<div id="xsnazzy" class="bgColorSeaBlue widthAdj1 north5 right0 spacingVerAdj0">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1">
<form id="home" method="post" action="j_acegi_security_check">
<h4 class="heading2 north6"><spring:message code="teacher.projects-awaiting-grading" /></h4>
<ul id="lineHeightAdj2" class="north_5">
<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" class="right4"> <spring:message code="teacher.grade-by-student" /></a>

</li>

<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" class="right4"> <spring:message code="teacher.grade-by-student" /></a>
</li>

<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" class="right4"> <spring:message code="teacher.grade-by-student" /></a>
</li>

<li>
<label for="projectname">
<spring:message code="wise.projectName" />
</label>
<a href="#"> <spring:message code="teacher.grade-by-step" /></a>
<a href="#" class="right4"> <spring:message code="teacher.grade-by-student" /></a>
</li>

</ul>


</form>
<spring:message code="wise.click-any-link" />
</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>

<div id="xsnazzy" class="north1 widthAdj1">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>

<div class="border1">
<h4 class="heading2 north6">
<spring:message code="teacher.about-dashboard" />
</h4>
<p class="north4"><spring:message code="teacher.dashboard-details" /></p>

<ul id="lineHeightAdj2" class="north4 marginVerAdj3">
<li>

<spring:message code="banner.home" />
<span class="right4 justify"> <spring:message code="teacher.home-desc" /></span>
</li>

<li>
<spring:message code="banner.projects" />
<span class="right4"> <spring:message code="teacher.projects-desc" /></span>
</li>

<li>
<spring:message code="banner.management" />
<span class="right4"> <spring:message code="teacher.management-desc" /></span>
</li>

<li>
<spring:message code="banner.help" />
<span class="right4"> <spring:message code="teacher.help-desc" /></span>
</li>

</ul>

</div>

<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>

<div id="xsnazzy" class="widthAdj1 right0 north9">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<h4 class="heading2">
<spring:message code="teacher.quick-links" />
</h4>
<b><spring:message code="banner.projects" /></b> 
<b class="right4"> 
<spring:message code="banner.management" />
<spring:message code="teacher.assessment" />
</b>
<br />
    <a href="../curnitlist.html">
    <spring:message code="teacher.project-library" />
    </a>
    <a href="#">
<span class="right4"><spring:message code="teacher.grade-work-by-student-group" />
</span>
</a>
<br />
    <a href="../runlist.html">
<spring:message code="teacher.my-project-runs" />
</a>
<a href="#">
<span class="right4"><spring:message code="teacher.grade-work-by-step" />
</span>
</a>
<br />
<a href="#">
<spring:message code="teacher.my-bookmarked-projects" />
</a>
<a href="#">
<span class="right4"><spring:message code="teacher.edit-ready-made-comments" />
</span>
</a>
<br />
<a href="#">
<spring:message code="teacher.my-customized-projects" />
</a>
<a href="#">
<span class="right4"><spring:message code="teacher.real-time-class-monitor" />
</span>
</a>
<br />


<b><spring:message code="banner.management" /><spring:message code="students" /></b> 
<b class="right4"> 
<spring:message code="misc" />
</b>
<br />
<a href="#">
<spring:message code="student.view-teacher-code" />
</a>
<a href="#">
<span class="right4"><spring:message code="student.export-all-work-for-student" />
</span>
</a>
<br />
<a href="#">
<spring:message code="student.view-my-students" />
</a>
<a href="#">
<span class="right4"><spring:message code="student.export-all-work-for-class" />
</span>
</a>
<br />
<a href="#">
<spring:message code="student.my-bookmarked-projects" />
</a>
<a href="#">
<span class="right4"><spring:message code="student.print-all-work-for-student" />
</span>
</a>
<br />
<a href="#">
<spring:message code="student.my-customized-projects" />
</a>
<a href="#">
<span class="right4"><spring:message code="student.print-all-work-for-class" />
</span>
</a>
<br />
<a href="#">
<spring:message code="student.batch-set-passwords" />
</a>
<a href="#">
<span class="right4"><spring:message code="student.update-account" />
</span>
</a>
<br />

   <b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>

</div>

</body>
</html>




