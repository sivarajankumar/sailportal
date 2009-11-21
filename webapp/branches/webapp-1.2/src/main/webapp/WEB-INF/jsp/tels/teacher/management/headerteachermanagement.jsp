<%@ include file="../include.jsp"%>
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

<link rel="stylesheet" type="text/css" href="../.././themes/tels/default/styles/teacher/superfish.css" media="screen">
<script type="text/javascript" src="../.././javascript/tels/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="../.././javascript/tels/superfish.js"></script>

<script type="text/javascript">
    
            // initialise plugins
            jQuery(function(){
                jQuery('ul.sf-menu').superfish();
            });
    
            </script>

<div id="bannerArea2">

<div id="wiseLogo"><a href=".././index.html"
	onmouseout="MM_swapImgRestore()"
	onmouseover="MM_swapImage('WISE Secondary Logo','','../../themes/tels/default/images/WISE-Logo-Medium-1.png',1)">
	<img src="../../themes/tels/default/images/WISE-Logo-Medium-1.png" alt="WISE 4 Logo" border="0" id="WISE Secondary Logo" /></a></div>

<div id="teacherBannerLabel" style="display:none;">
	<img src="../../themes/tels/default/images/Teacher-Dashboard-Label.png"
	alt="Teacher Dashboard Label" /></div>

    <div id="teacherInterfaceHeader">teacher interface</div>


<div id="menuContainer">

<ul class="sf-menu">
			
			<li class="current level1">
					<a href="../../teacher/index.html" >Home</a>
			</li>

		<li class="level1"><a href="#">Projects</a>
		<ul>
				<li><a href="../../teacher/projects/index.html">Overview</a></li>
				<li><a href="#">Beta Project Library</a></li>
				<li><a href="#ab">Project Library</a></li>
				<li><a href="#">My Bookmarked Projects</a></li>
				<li><a href="#">My Custom-Authored & Shared Projects</a></li>

		</ul>
		</li>

		<li class="level1"><a href="#">Grading</a>
		<ul>
				<li><a href="../teacher/grading/overview.html">Overview</a></li>
				<li><a href="#">Grade Work by Step</a></li>
				<li><a href="#">Grade Work by Team</a></li>
				<li><a href="#">Edit Maximum Score Values for a Project Run</a></li>
				<li><a href="#">Review Student Score Summary</a></li>
				<li><a href="#">Edit Pre-Made Grading Comments</a></li>


		</ul>
		</li>

		<li class="level1"><a href="#">Management</a>
	    <ul>
            <li><a href="../../teacher/management/overview.html">Overview</a></li>
            <li><a href="#">View Project Runs</a></li>
            <li><a href="#">Manage Students</a></li>
            <li><a href="#">View Student RealTime Progress Monitor</a></li>
            <li><a href="#">Print/Export Student Work</a></li>
            <li><a href="#">Manage Extra Teachers</a></li>
            <li><a href="#">Update My Account</a></li>
        </ul>
		</li>

		<li class="level1">
				<a href="../teacher/help/overview.html">Help</a>
			</li>

		</ul>

</div>

<div id="usernameSignOutBoxTeacher">
<div id="usernameBannerTeacher"><sec:authentication	property="principal.username" /></div>
<div id="signOutBannerTeacher"><a href="<c:url value="/j_spring_security_logout"/>"><spring:message code="log.out"/></a></div>
</div>
</div> <!-- End of bannerArea   Note that NavigationMainProjects and usernameSignoutBox are relative to bannerArea -->



