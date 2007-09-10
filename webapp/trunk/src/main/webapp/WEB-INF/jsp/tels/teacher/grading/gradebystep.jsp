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
<link href="../../<spring:theme code="teachergradingstylesheet" />" media="screen" rel="stylesheet"
    type="text/css" />  
<script type="text/javascript" src="../javascript/general.js"></script> 

<title>Teacher:Grading - Grade Student Work </title>
</head>

<body>
<%@ include file="gradingHeaderExtended.jsp"%>
<br />
<div>
<a href="#"> Grade Another Project </a>
<table>
<tr>
<td>Confirm Score Values for Graded Items</td>
<td>Review and edit the default score values for Notes and other graded student tasks.</td>
</tr>
<tr>
<td>Grade Work By Student Group</td>
<td>View and grade work, one Student Group at a time.</td>
</tr>
<tr>
<td>Grade Work By Step</td>
<td>Choose a particular step, then grade all student work for that step at once.</td>
</tr>
<tr>
<td>Review Assessment Items</td>
<td>View student responses to pre/post assesment questions for the project.</td>
</tr>
<tr>
<td>Edit Ready-Made Comments</td>
<td>Create and edit a list of ready-made comments and stickers to streamline
your grading.</td>
</tr>
</table>

</div>
</body>
</html>
