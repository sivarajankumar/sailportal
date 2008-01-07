<%@ include file="include.jsp"%>

<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z mattfish $ -->

<div id="bannerArea">
  <div>
    <a href="index.html" onmouseout="MM_swapImgRestore()"
	      onmouseover="MM_swapImage('WISE Main Logo','','themes/tels/default/images/WISE-Logo-Large.png',1)">
       <img src="themes/tels/default/images/WISE-Logo-Large.png" alt="WISE Large Logo" border="0" id="WISE Main Logo" />
     </a>
   </div>

   <div id="usernameSignOutBoxHome">
     <authz:authorize ifAllGranted="ROLE_USER">
	   <div id="usernameBannerHome"><authz:authentication operation="username" /></div>
	   <div id="signOutBannerHome"><%@ include file="logout.jsp"%></div>
	   <authz:authorize ifAllGranted="ROLE_STUDENT">
	   	   <div id="signOutBannerHome"><a href="student/index.html">Student Home Page</a></div>
	   </authz:authorize>
	   <authz:authorize ifAllGranted="ROLE_TEACHER">
	   	   <div id="signOutBannerHome"><a href="teacher/index.html">Teacher Home Page</a></div>
	   </authz:authorize>
     </authz:authorize>
  </div>
</div>