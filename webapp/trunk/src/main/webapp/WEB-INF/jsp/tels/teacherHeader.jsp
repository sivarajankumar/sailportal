<%@ include file="include.jsp" %>
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

<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z archana $ -->


<div align="center">
	<a href="teacher/index.html"><img id="none" src="<spring:theme code="dashboardbanner"/>" alt="<spring:message code="wise.banner.alttext" />" /></a>
<div id="navigation" style="position:relative;
	bottom:35px;width:990px;">
<ul style="font-size: 1em;">
<li> <a href="teacher/index.html"> <img src="<spring:theme code="home" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="<spring:theme code="projects_selected" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="<spring:theme code="grading" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="<spring:theme code="management" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:alert('This page is not available yet')"> <img src="<spring:theme code="help" />" style="border:0px;"/> </a> </li>
</ul>
</div>	


</div>
<p style="position: absolute;top:-5px;text-align:right;"> 
<authz:authentication operation="username" />
 </p>
<%@ include file="logout.jsp"%> 

<p style="margin-bottom:-60px;" />


