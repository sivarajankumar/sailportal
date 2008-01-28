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
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
 
<title>Projects Overview</title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_overview.jsp"%>
 
<div id="overviewContent"> 

	<div id="overviewHeader">Project: Overview</div>
	
	<table id="overview_choices" cellspacing="20">
		<tr>
			<td class="link"><a href="projectlibrary.html">Project Library</a></td>
			<td class="description">Search the extensive library of WISE and TELS projects. Find a project and set it up to run in your classroom.</td></tr>
		<tr>
			<td class="link"><a href="../run/myprojectruns.html">My Project Runs</a></td>
			<td class="description">View projects that you are currently running in your classroom.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;" href="#">My Bookmarked Projects</a></td>
			<td class="description">View library projects that you have earmarked for future use.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;"href="#">My Customized Projects</a></td>
			<td class="description">View your customized projects. Use the Project Editor to customize Library projects or to create brand new projects.</td></tr>
	</table>
	
</div>

<h5 class="center">Interested in running WISE projects in another language? &nbsp; <a href="languagetranslations.html">Find out more. </a></h5>

</div>    <!--End of CenteredDiv-->

</body>
</html>

