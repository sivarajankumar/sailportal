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


<div id="bannerArea">
<div id="wiseLogo"><a href=".././index.html" onmouseout="MM_swapImgRestore()"
		onmouseover="MM_swapImage('WISE Medium Logo','','../../themes/tels/default/images/WISE-Logo-Medium-Roll-1.png',1)"> <img
		src="../../themes/tels/default/images/WISE-Logo-Medium-1.png" alt="WISE 4 Logo" border="0" id="WISE Medium Logo" /></a></div>
<div id="teacherBannerLabel">
<p><img src="../../themes/tels/default/images/Teacher-Dashboard-Label.png" alt="Teacher Dashboard Label" /></p>
</div>

<div id="navigationMainProjects">
<ul>
		<li><a href="../index.html" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('homebutton','','../../themes/tels/default/images/teacher/Home-Buttn-Selected.png',1)"> <img
				src="../../themes/tels/default/images/teacher/Home-Buttn-Clickable.png" alt="Home Nav Button" id="homebutton" /></a>
		<li><a href="../projects/index.html" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('projectsbutton','','../../themes/tels/default/images/teacher/Projects-Buttn-Selected.png',1)">
		<img src="../../themes/tels/default/images/teacher/Projects-Buttn-Clickable.png" alt="Projects Nav Button"
				id="projectsbutton" /></a>
		<li><a href="../grading/overview.html" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('gradingbutton','','../../themes/tels/default/images/teacher/Grading-Buttn-Selected.png',1)">
		<img src="../../themes/tels/default/images/teacher/Grading-Buttn-Selected.png" alt="Grading Nav Button" id="gradingbutton" /></a>
		<li><a href="../management/overview.html" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('managementbutton','','../../themes/tels/default/images/teacher/Management-Buttn-Selected2.png',1)">
		<img src="../../themes/tels/default/images/teacher/Management-Buttn-Clickable2.png" alt="Management Nav Button"
				id="managementbutton" /></a>
		<li><a href="../help/overview.html" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('helpbutton','','../../themes/tels/default/images/teacher/Help-Buttn-Selected2.png',1)"> <img
				src="../../themes/tels/default/images/teacher/Help-Buttn-Clickable2.png" alt="Help Nav Button" id="helpbutton" /></a>
</ul>
</div>
<div id="usernameSignOutBoxTeacher">
<div id="usernameBannerTeacher"><sec:authentication property="principal.username" /></div>
<div id="signOutBannerTeacher"><a href="<c:url value="/j_spring_security_logout"/>"><spring:message code="log.out" /></a></div>
</div>
</div>
<!-- End of bannerArea   Note that NavigationMainProjects and usernameSignoutBox are relative to bannerArea -->



