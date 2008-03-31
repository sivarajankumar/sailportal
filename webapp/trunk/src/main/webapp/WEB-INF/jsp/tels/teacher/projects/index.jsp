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

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="../../javascript/tels/iefixes.js"></script>
<![endif]-->


</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_overview.jsp"%>
 
<div id="overviewContent"> 

	<div id="overviewHeader">project: overview</div>
	
	<table id="overview_choices" cellspacing="20">
		<tr>
			<td class="link"><a href="projectlibrary.html">Project Library</a></td>
			<td class="description">Search the library of WISE and TELS interactive projects. Find a project and set it up as a Project Run for your classroom.</td></tr>
		<tr>
			<td class="link"><a href="../run/myprojectruns.html">My Project Runs & Student Codes</a></td>
			<td class="description">View the projects that you are currently running in your classroom and the Student Codes for these project runs.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;" href="#">My Bookmarked Projects</a></td>
			<td class="description">View library projects that you've earmarked for future use.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;"href="#">My Customized/Shared Projects</a></td>
			<td class="description">View your customized projects and projects being shared with you. 
			Use the Project Authoring Tool to edit existing projects or create new projects.</td></tr> 
	</table>
	
</div>

<h5 class="center">Interested in running WISE projects in another language? &nbsp; <a href="languagetranslations.html">Find out more. </a></h5>

</div>    <!--End of CenteredDiv-->

</body>
</html>

