
<%@ include file="include.jsp"%>
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

<!-- $Id$ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/rotator.js" type="text/javascript" ></script>

<style type="text/css" media="screen">
  .inplaceeditor-saving {background: url(<spring:theme code="wait"/>) bottom right no-repeat; }
</style>
<title><spring:message code="login.title" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<div id="errorMsg">
<c:if test="${failed}">
  <p><spring:message code="login.failed" /></p>
</c:if>
</div>

<div id="signInReposition">

<div id="boxTableSignIn" class="panelColor">
                    			<div id="header">Sign In</div>
								<form id="home" method="post" action="j_acegi_security_check">
                                
                                <dl>
                                    <dt><label for="username">Username:  </label> </dt>
                                    <dd><input class="dataBoxStyle" type="text" name="j_username" id="j_username" maxlength="20"/></dd>
                                    <dt><label for="password">Password:	</label></dt>
                                    <dd><input class="dataBoxStyle" type="password" name="j_password" id="j_password" maxlength="20"/></dd>
                                </dl>
                                                       
                                <div class="alignRight">
                                			<input type="image" id="signInButton" src="./themes/tels/default/images/sign_in.png"   
                                            onmouseover="MM_swapImage('signInButton','','./themes/tels/default/images/sign_in_rollover.png',1)"
                                            onmouseout="MM_swapImgRestore()"
                                            onclick="Effect.toggle('waiting', 'appear')" /></div>
                                                 
                                </form>
                                
                                <ul id="signInLinkPositionTable">
                                <li>
                                <a href="forgotaccount/selectaccounttype.html" id="forgotlink">
                                      Forgot Username or Password?</a>  </li>
                                <li>
                                <a href="signup.html" id="joinlink">Want to join WISE?</a></li>
                                </ul>
                                
</div>   <!--    End of boxTableSignIn  x-->                             

</div>

</div>   <!-- end of centered div-->


</body>
</html>
