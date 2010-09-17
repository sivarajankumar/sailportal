<%@ include file="include.jsp"%>

<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z mattfish $ -->

<div id="bannerArea1">

	<div id="betaTag"><img src="themes/tels/default/images/WISE-Logo-betatag.png" alt="Beta graphic" border="0" /></div>

    <a href="index.html" onmouseout="MM_swapImgRestore()"
	      onmouseover="MM_swapImage('WISE Main Logo','','themes/tels/default/images/WISE-Logo-Large-v4.png',1)">
       <img src="themes/tels/default/images/WISE-Logo-Large-v4.png" alt="WISE Large Logo" border="0" id="WISE Main Logo" />
     </a>

   <div id="usernameSignOutBoxHome">
     <sec:authorize ifAllGranted="ROLE_USER">
	   <div id="usernameBannerHome"><sec:authentication property="principal.username" /></div>
	   <div id="signOutBannerHome"><a id="styleOverRideSafari1" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="log.out"/></a></div>
	   <sec:authorize ifAllGranted="ROLE_STUDENT">
	   	   <div id="signOutBannerHome"><a href="student/index.html"><spring:message code="header.student"/></a></div>
	   </sec:authorize>
	   <sec:authorize ifAllGranted="ROLE_TEACHER">
	   	   <span id="signOutBannerHome"><a href="teacher/index.html"><spring:message code="header.teacher"/></a></span>
	   </sec:authorize>
	   <sec:authorize ifAllGranted="ROLE_ADMINISTRATOR">
	   	  <span id="signOutBannerHome"><a href="admin/index.html"><spring:message code="header.admin"/></a></span>
	 	</sec:authorize>
	   <sec:authorize ifAllGranted="ROLE_RESEARCHER">
	   	  <span id="signOutBannerHome"><a href="admin/index.html"><spring:message code="header.researcher"/></a></span>
	 	</sec:authorize>
     </sec:authorize>
  </div>
</div>