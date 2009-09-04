<%@ include file="include.jsp"%>

<!-- $Id: index.jsp 2450 2009-09-02 00:30:39Z supersciencefish $ -->
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

<!-- $Id: index.jsp 2450 2009-09-02 00:30:39Z supersciencefish $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/prototype.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/effects.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/rotator.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/rotatorT.js" type="text/javascript">
	
</script>

<link rel="shortcut icon" href="./themes/tels/default/images/favicon_panda.ico">
<title><spring:message code="application.title" /></title>

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="./javascript/tels/iefixes.js"></script>
<![endif]-->

<!--The next two conditional statements fix problems with the Display:Block navigation elements in older IE Browsers (MattFish)-->

<!--[if IE 5]>
<style>
#welcomeTextLinks a {
float: left;
clear: both;
width: 100%;
}
</style>
<![endif]-->

<!--[if lte IE 6]>
<style>
#welcomeTextLinks a {height: 1%;}
</style>
<![endif]-->

</head>

<body>

<div id="centeredDiv"> 

<%@ include file="headermain.jsp"%>

<form id="home" method="post" action="j_acegi_security_check">
				<dl id="signinDefinList">
						<dt><label for="username"><spring:message code="username" /></label></dt>
						<dd><input class="dataBoxStyle" type="text" name="j_username" id="j_username" size="18" maxlength="60" /></dd>

						<!--This unusually placed script gets the cursor into the First Name field immediately on page load.
									It must appear immediately after the Input field in question  (MattFish)-->
						<script type="text/javascript">
	document.getElementById('j_username').focus();
</script>

						<dt><label for="password"><spring:message code="password" /></label></dt>
						<dd><input class="dataBoxStyle" type="password" name="j_password" id="j_password" size="18" maxlength="30" /></dd>
				</dl>

				<div class="alignRight"><input type="image" id="signInButton" img src="./themes/tels/default/images/SignIn.png"
						width="100" height="27" alt="Sign In Button"
						onmouseover="MM_swapImage('signInButton','','./themes/tels/default/images/SignInRoll.png',1)"
						onmouseout="MM_swapImgRestore()" onclick="Effect.toggle('waiting', 'appear')" /></div>
				</form>

				<ul id="signInLinkPosition">
						<li><a href="forgotaccount/selectaccounttype.html" id="forgotlink"><spring:message code="findalostusername" /></a>
						</li>
						<li><a href="signup.html" id="joinlink"><spring:message code="createanewwiseaccount" /></a></li>
				</ul>
				</div>
</div>
<!-- end of #centered div-->

</body>

</html>

