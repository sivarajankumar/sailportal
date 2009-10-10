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

<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z archana $ -->

<div id="navigationL2" class="management2">

<!--NOTE:  The UL below has a manual CSS override, to displace some weird values being injected by Ajax on this page.-->

	<ul style="padding: 1px 0 5px 110px;">
		<li >
			<a href="overview.html"><spring:message code="teacher.manage.L2.1"/></a> </li>
		<li>
			<a href="../run/myprojectruns.html"><spring:message code="teacher.pro.lib.L2.3"/></a> </li>
       	<li>
			<a class="navigationL2_management_highlight" href="../management/projectPickerManagement.html"><spring:message code="teacher.manage.L2.2"/></a> </li>
		<li>
			<a style="color:#999999;" href="#"><spring:message code="teacher.manage.L2.3"/></a> </li>
		<li>
			<a style="color:#999999;" href="#"><spring:message code="teacher.manage.L2.4"/></a> </li>
		<li>
			<a style="color:#999999;" href="#"><spring:message code="teacher.manage.L2.5"/></a> </li>
		<li>
			<a style="color:#999999;" href="#"><spring:message code="teacher.manage.L2.6"/></a> </li>
		<li>
			<a href="../management/updatemyaccount.html"><spring:message code="teacher.manage.L2.7"/></a> </li>
   </ul>
</div>	
