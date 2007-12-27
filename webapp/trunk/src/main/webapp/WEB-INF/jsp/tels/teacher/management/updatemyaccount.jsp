


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

<%@ include file="L2management_updatemyaccount.jsp"%>
 
<div id="overviewContent"> 

	<div id="overviewHeader">Update My Account</div>
	
	<table id="overview_choices" cellspacing="20">
		<tr>
			<td class="link"><a href="#">Change My Password</a></td>
			<td class="description">Change your WISE 3.0 password.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;" href="#">Update Registration Info.</a></td>
			<td class="description">Edit your email or other registration information.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;" href="#">Update Dashboard Prefs.</a></td>
			<td class="description">Edit the preferences set when you first created your account.</td></tr>
		<tr>
			<td class="link"><a style="color:#999999;" href="#">Change Display Language</a></td>
			<td class="description">Change the language displayed in the Teacher Dashboard.</td></tr>
		
	</table>
	
</div>


</div>    <!--End of CenteredDiv-->

</body>
</html>


