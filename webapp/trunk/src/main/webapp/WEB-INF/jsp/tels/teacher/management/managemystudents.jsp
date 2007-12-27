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
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
 
<title>Manage My Students</title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteachermanagement.jsp"%>

<%@ include file="L2management_managestudents.jsp"%>
 
<div id="overviewContent"> 

	<div id="overviewHeader">Manage My Students</div>
	
	<table id="overview_choices" cellspacing="20">
		<tr>
			<td class="link"><a href="../management/projectPickerManagement.html">View My Students</a></td>
			<td class="description">View a list of all your current (and archived) students. Change student
passwords, student teams, class period assignments, and more.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;" href="#">Manage Extra Teachers</a></td>
			<td class="description">Allow other teachers or assistants to view, grade, and manage your Project Runs.</td></tr>
		<tr>
			<td class="link"><a href="../run/myprojectruns.html">View Student Project Codes</a></td>
			<td class="description">Links over to the <em>Projects: My Project Runs</em> section. Displays the Project Code you will give out to students per class period.</td></tr>

		
	</table>
	
</div>

<h5 class="center">Getting started running WISE 3.0 in your classroom? Visit the <a href="#">Help</a> section for more information.</h5>

</div>    <!--End of CenteredDiv-->

</body>
</html>

