<%@ include file="../include.jsp"%>

<div id="bannerArea">
  <div>
    <a href="index.html" onmouseout="MM_swapImgRestore()"
	      onmouseover="MM_swapImage('WISE Main Logo','','../themes/tels/default/images/WISE-Logo-Large-v4.png',1)">
       <img src="../themes/tels/default/images/WISE-Logo-Large-v4.png" alt="WISE Large Logo" border="0" id="WISE Main Logo" />
     </a>
   </div>

<div id="usernameSignOutBoxTeacher">
		<div id="usernameBannerTeacher"><sec:authentication property="principal.username" /> </div>
		<div id="signOutBannerTeacher"><a href="<c:url value="/j_spring_security_logout"/>"><spring:message code="log.out"/></a></div> 
	</div>
	
</div>