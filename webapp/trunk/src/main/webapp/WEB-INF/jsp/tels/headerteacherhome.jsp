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

<div id="bannerArea">
    	<div id="wiseLogo"><a href="../index.html" 
    	onmouseout="MM_swapImgRestore()" 
    	onmouseover="MM_swapImage('WISE Secondary Logo','','../themes/tels/default/images/WISE-Secondary-Banner-Logo-Roll.png',1)">
    	<img src="../themes/tels/default/images/WISE-Secondary-Banner-Logo.png" alt="WISE Logo" width="209" height="24" border="0" id="WISE Secondary Logo" /></a></div>
        <div id="teacherBannerLabel"><p><img src="../themes/tels/default/images/Teacher-Dashboard-Label.png" alt="Teacher Dashboard Label" /></p></div>

<div id="navigationMainProjects">
	<ul>
		<li>
			<a href="index.html"><input type="image" id="home" src="../<spring:theme code="home_selected"/>"	
    		onmouseover="swapImage('home','../<spring:theme code="home_selected"/>')" 
    		onmouseout="swapImage('home','../<spring:theme code="home_selected"/>')"/></a> </li>
       	<li>
			<a href="projects/index.html""><input type="image" id="projects" src="../<spring:theme code="projects"/>"
    		onmouseover="swapImage('projects','../<spring:theme code="projects_selected"/>')" 
    		onmouseout="swapImage('projects','../<spring:theme code="projects"/>')"/></a> </li>
    	<li>
			<a href="index.html"><input type="image" id="grading" src="../<spring:theme code="grading"/>"
    		onmouseover="swapImage('grading','../<spring:theme code="grading_selected"/>')" 
    		onmouseout="swapImage('grading','../<spring:theme code="grading"/>')"/></a> </li>
    	<li>
			<a href="index.html"><input type="image" id="management" src="../<spring:theme code="management"/>"
    		onmouseover="swapImage('management','../<spring:theme code="management_selected"/>')" 
    		onmouseout="swapImage('management','../<spring:theme code="management"/>')"/></a> </li>
   		<li>
			<a href="index.html"><input type="image" id="help" src="../<spring:theme code="help"/>"
    		onmouseover="swapImage('help','../<spring:theme code="help_selected"/>')" 
    		onmouseout="swapImage('help','../<spring:theme code="help"/>')"/></a> </li>
   </ul>
   </div>
<div id="usernameSignOutBox">
	<div id="usernameBanner"><authz:authentication operation="username" /> </div>
	<div id="signOutBanner"> <%@ include file="logout.jsp"%></div> 
</div>
</div>   <!-- End of bannerArea   Note that NavigationMainProjects and usernameSignoutBox are relative to bannerArea -->

    

