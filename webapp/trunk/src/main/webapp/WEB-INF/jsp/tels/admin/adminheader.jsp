<%@ include file="../include.jsp"%>

<div id="bannerArea">
  <div>
    <a href="index.html" onmouseout="MM_swapImgRestore()"
	      onmouseover="MM_swapImage('WISE Main Logo','','../themes/tels/default/images/WISE-Logo-Large.png',1)">
       <img src="../themes/tels/default/images/WISE-Logo-Large.png" alt="WISE Large Logo" border="0" id="WISE Main Logo" />
     </a>
   </div>

<div id="usernameSignOutBoxTeacher">
		<div id="usernameBannerTeacher"><authz:authentication operation="username" /> </div>
		<div id="signOutBannerTeacher"><a href="<c:url value="/j_acegi_logout"/>"><spring:message code="log.out"/></a></div> 
	</div>
	
</div>