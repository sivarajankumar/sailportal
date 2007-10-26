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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
  
<title><spring:message code="signup.title" /></title>

<script type="text/javascript" src=".././javascript/pas/utils.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<div id="registrationTitle">
    	<h1 class="blueText">Teacher Registration</h1>
</div>

<div id="subtitleConfirm">
			<h4>Account Created!</h4>
			<h4> Your new Username is: <span><input id="usernameConfirm" name="username" value="${username}"/></span></h4>
			<ul>
		    <li>Please memorize/write down your Username and Password. You'll need them ever time you sign into WISE.</li>
		    <li>Note: there are no spaces between first and last name.</li>
		    <li>Note: A number may be appended to your Username if a similar name already exists.</li>
			</ul>
</div>

<table id="confirmationButtons" width="50%" border="0" cellpadding="5" cellspacing="5">
  <tr>
      <td class="width1"><a href="../index.html" 
    onmouseout="MM_swapImgRestore()" 
    onmouseover="MM_swapImage('Return to Home Page','','../themes/tels/default/images/Go-To-Home-Page-Roll.png',1)">
    <img src="../themes/tels/default/images/Go-To-Home-Page.png" alt="Go to Home Page & Sign In" width="161" height="52"  id="Return to Home Page" /></a></td>
  </tr>
  <tr>
    <td>Select this button to return to the home page. Then use your new Username and Password in the <em>Sign In</em> area.</td>
  </tr>
</table>


</div>  <!-- /* End of the CenteredDiv */-->

</body>

</html>




