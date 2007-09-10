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
<h3>EXPORT DATA </h3>
<table>
<tr> 
<td> Export Work by Student </td>
<td> Export a spreadsheet of all work done by a single student. </td>
</tr>
<tr> 
<td> Export Work by Class </td>
<td> Export a spreadsheet of all work done by the students in your class or classes. </td>
</tr>
<tr> 
<td> Export Final Grades for a Project </td>
<td> Export a spreadsheet of just the final grades earned by all students (per period or all periods). </td>
</tr>
</table>
<h3>PRINT REPORTS</h3>
<table>
<tr>
<td>Print Work by Student</td>
<td>Print all the work done by a single student</td>
</tr>
<tr>
<td>Print Work by Class</td>
<td>Print all work done by all students (per period or all periods).</td>
</tr>
<tr>
<td>Print Final Grades for a Project</td>
<td>Print just the final grades earned by all students (per period or all periods).</td>
</tr>
</table>

</div>

</body>
</html>
