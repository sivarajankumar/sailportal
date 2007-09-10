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

<!-- $Id: index.jsp 888 2007-08-06 23:47:19Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />  
<link href="../../<spring:theme code="teachermanagementstylesheet" />" media="screen" rel="stylesheet"
    type="text/css" />  
<script type="text/javascript" src="../javascript/general.js"></script> 

<title>Teacher:Management - Exports and Reports </title>
</head>

<body>
<%@ include file="managementHeader.jsp"%>
<br />

<div id="overviewContent">

<table>
<tr> 
<td> View Getting Started Guide </td>
<td> This guide covers everything you need to know about using WISE in a
classroom, including setting up class computers, registering students, and
running projects.
</td>
</tr>
<tr> 
<td> Classroom Setup Assistant </td>
<td> Set up your classroom computers to use WISE. </td>
</tr>
<tr> 
<td> View Your Project Codes </td>
<td>Displays a list of the Project Codes you've created for each
Project Run. Students use this project code to load your project and
run it for the first time.</td>
</tr>
<tr>
<td>View My Students</td>
<td>View a list of all your current (and archived) students. Change student
passwords, class periods, student-teams, and more.</td>
</tr>
<tr>
<td>Batch Set Student Passwords</td>
<td>Change multiple student passwords at once.</td>
</tr>
<tr>
<td>Manage Extra Teachers</td>
<td>Give another teacher or proctor access to view, grade, and manage your
Projects and/or Students.</td>
</tr>
</table>

</div>

</body>
</html>
