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

<title>Teacher:Management - Update My Account </title>
</head>

<body>
<%@ include file="managementHeader.jsp"%>
<br />

<div id="overviewContent">
<h3>Update My Account </h3>
<table>
<tr> 
<td> Change My Password </td>
<td> Revise your WISE password. </td>
</tr>
<tr> 
<td> Update My Account Information </td>
<td> View and edit your account information </td>
</tr>
<tr> 
<td> Update Teacher Dashboard Preferences </td>
<td> View and edit your display options for the Teacher Dashboard. </td>
</tr>
<tr> 
<td> Change Display Language </td>
<td> Change the Display Language for the Teacher Dashboard. </td>
</tr>
</table>
</div>

</body>
</html>
