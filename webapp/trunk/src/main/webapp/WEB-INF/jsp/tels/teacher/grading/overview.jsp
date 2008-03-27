<%@ include file="../include.jsp" %>

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

<!-- $Id: overview.jsp 997 2007-09-05 16:52:39Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >

<html lang="en">
<head>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teachergradingstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
 
<title>Grading</title>

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="../../javascript/tels/iefixes.js"></script>
<![endif]-->

</head>

<body>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug). -->

<div id="centeredDiv">

<%@ include file="headerteachergrading.jsp"%>

<%@ include file="L2grading_overview.jsp"%>
 
<div id="overviewContentGrading"> 

	<div id="overviewHeaderGrading">grading: overview</div>
	
	<table id="overview_choices_grading" cellspacing="20">
		<tr>
			<td class="link_grading"><a href="./projectPickerGrading.html?gradeByType=step">Grade Work by Step</a></td>
			<td class="description_grading">View and grade work, one Step at a time.</td></tr>
		<tr>
			<td class="link_grading"><a href="./projectPickerGrading.html?gradeByType=group">Grade Work By Team</a></td>
			<td class="description_grading">View and grade work, one student team (workgroup) at a time.</td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;"href="#">Review Student Score Summary</a></td>
			<td class="description_grading">View a summary of student scores for a project.</td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;"href="#">Review My Grading Progress</a></td>
			<td class="description_grading">View the progress you've made in grading a project.</td></tr>
		<tr>
			<td class="link_grading"><a href="./projectPickerGrading.html?gradeByType=value">Edit Maximum Step Values</a></td>
			<td class="description_grading">Review and edit the default score values for Notes and other gradable 
			student steps in a project. </td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;"href="#">Edit Pre-Made Comment Lists</a></td>
			<td class="description_grading">Create and edit a list of ready-made comments to streamline your grading.</td></tr>
	</table>
	
</div>

</div>
</div>    <!--End of CenteredDiv-->

</body>
</html>

