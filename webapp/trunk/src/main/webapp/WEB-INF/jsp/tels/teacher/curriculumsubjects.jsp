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
<link href="<spring:theme code="registerstylesheet" />" media="screen" rel="stylesheet"
  type="text/css" />
<script type="text/javascript" src=".././javascript/general.js"></script> 
</head>
<body>
<h3 align="center">SELECT YOUR SUBJECTS </h3>
<p>Select one or more checkboxes below describing the science topic(s) you teach.</p>
<table style="font-style:bold;font-size:1.1em;">
<tr>
<td> 
Biology 
</td>
<td>AP Biology</td>
<td>Environ. Science</td>
</tr>
<tr>
<td>
Chemistry
</td>
<td>
AP Chemistry
</td>
<td>
Astronomy
</td>
</tr>
<tr>
<td>
Physics
</td>
<td>
AP Physics
</td>
<td>
Anatomy
</td>
</tr>
<tr>
<td>
Earth Science
</td>
<td>
Biotechnology
</td>
<td>
Geology
</td>
</tr>
<tr>
<td>
Integrated Science
</td>
<td>
Advanced Int. Science
</td>
<td>
Other
</td>
</tr>
</table> 

<div align="center" style="position:relative;top:30px;">
Note: Specifying your curriculum topics is optional, but very helpful to the WISE development
team. This information guides the ongoing direction of WISE projects, allowing the creation of
projects that best serve the needs of our science teacher community. Thanks for your input!
<br />
<a href="#">
 <img border="0px;" align="center" src="../<spring:theme code="register_save" />" />
</a>

<a href="#">
<img border="0px;" align="center" src="../<spring:theme code="register_cancel" />" />
</a>

</div>



</body>
</html>
