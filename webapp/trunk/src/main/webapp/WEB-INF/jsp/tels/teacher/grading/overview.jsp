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

<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teachergradingstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
 
<title>Grading</title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteachergrading.jsp"%>

<%@ include file="L2grading_overview.jsp"%>
 
<div id="overviewContentGrading"> 

	<div id="overviewHeaderGrading">Grading: Overview</div>
	
	<table id="overview_choices_Grading" cellspacing="20">
		<tr>
			<td class="link_grading"><a href="#">Grade Work by Step</a></td>
			<td class="description_grading">View and grade work, one Student Group at a time.</td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;" href="#">Grade Work By Student Group</a></td>
			<td class="description_grading">View and grade work, one Step at a time.</td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;" href="#">Review Values for Gradable Steps</a></td>
			<td class="description_grading">Review and edit the default score values for Notes and other graded 
			student steps in a project. </td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;"href="#">Review Pre/Post Questionnaire</a></td>
			<td class="description_grading">View student responses to Pre-Post Questionnaire Steps for the project.</td></tr>
		<tr>
			<td class="link_grading"><a style="color:#999999;"href="#">Edit Ready-Made Comments</a></td>
			<td class="description_grading">Create and edit a list of ready-made comments and stickers to streamline your grading.</td></tr>
	</table>
	
</div>

</div>    <!--End of CenteredDiv-->

</body>
</html>

