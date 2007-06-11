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

<!--<div id="banner">
	<img src="<spring:theme code="banner"/>" alt="<spring:message code="wise.banner.alttext" />" />
</div>

-->

<div class="welcome1">
	<img src="<spring:theme code="banner"/>" alt="<spring:message code="wise.banner.alttext" />" />
</div>
	<div class="center heading" ><spring:message code="wise.banner.student" />
	</div>
	<div class="right pandaAlign">	<img src="<spring:theme code="panda"/>" alt="<spring:message code="wise.banner.panda.alttext" />" />
	</div>
	    <div>
    <p class="userinfo">
    <spring:message code="login.not-logged-in" />
	</p>
	<p class="userlogout"> <%@ include file="logout.jsp"%> </p>
	</div>
	
