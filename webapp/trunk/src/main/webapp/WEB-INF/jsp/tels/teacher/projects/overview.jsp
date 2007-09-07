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
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet"
    type="text/css" />
 <script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<title>Projects Overview</title>
</head>

<body>
<%@ include file="projectHeader.jsp"%>
<!-- 
<ul id="tabnav2"  >
<li> <a href="#">Current</a></li>
<li> <a href="#">Archived</a></li>
</ul>
 -->
<div id="overviewContent"> 
<br />

<table>
<tr>
<td><a href="#">Project Library</a></td>
<td>Search the extensive library of WISE and TELS projects. Find a
project and set it up to run in your classroom.</td>
</tr>
<tr>
<td><a href="../run/myprojectruns.html">My Project Runs</a></td>
<td>View projects that you are currently running in your classroom.</td>
</tr>
<tr>
<td><a href="#">My Bookmarked Projects</a></td>
<td>View library projects that you have earmarked for future use.</td>
</tr>
<tr>
<td><a href="#">My Customized Projects</a></td>
<td>View your customized projects. Use the Project Editor to customize
Library projects or to create new projects.</td>
</tr>
</table>

</div>
<p>
Interested in running WISE projects in another language? <a href="languagetranslations.html"> Find out more. </a>
</p>
</body>
</html>

