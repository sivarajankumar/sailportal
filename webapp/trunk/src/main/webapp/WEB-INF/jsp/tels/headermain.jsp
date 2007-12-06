<%@ include file="include.jsp"%>


<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z mattfish $ --> 

 
<div id="bannerArea">
    	<div><a href="index.html" 
    		onmouseout="MM_swapImgRestore()" 
    		onmouseover="MM_swapImage('WISE Main Logo','','themes/tels/default/images/WISE-Logo-Large.png',1)">
    		<img src="themes/tels/default/images/WISE-Logo-Large.png" 
    		 alt="WISE Large Logo" border="0" id="WISE Main Logo" /></a></div>
  		
		<div id="usernameSignOutBoxHome">
			<div id="usernameBannerHome"><authz:authentication operation="username" /> </div>
			<div id="signOutBannerHome"> <%@ include file="logout.jsp"%></div> 
		</div>
 
</div>