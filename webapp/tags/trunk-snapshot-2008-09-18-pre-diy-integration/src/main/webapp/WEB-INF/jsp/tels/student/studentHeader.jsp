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

<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z MattFish $ -->

<div id="bannerArea">

    	<div id="wiseLogo"><a href="../index.html" 
    		onmouseout="MM_swapImgRestore()" 
    		onmouseover="MM_swapImage('WISE Secondary Logo','','../themes/tels/default/images/WISE-Secondary-Banner-Logo-Roll.png',1)">
    		<img src="../themes/tels/default/images/WISE-Secondary-Banner-Logo.png" alt="WISE Logo" width="209" 
    		height="24" border="0" id="WISE Secondary Logo" /> </a></div>
    		
    	<div id="studentBannerLabel">
    		<img src="../themes/tels/default/images/student/Student-Site-Label.png" width="104" height="11"
       		alt="Student Dashboard Label" />
       	</div>
          
       	<div id="usernameSignOutBoxStudent">
			<div id="usernameBannerStudent"><authz:authentication operation="username" /> </div>
			<div id="signOutBannerStudent"><a href="<c:url value="/j_acegi_logout"/>"><spring:message code="log.out"/></a></div> 
		</div>
   
</div>