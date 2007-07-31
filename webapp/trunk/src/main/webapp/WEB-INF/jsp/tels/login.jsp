
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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src="./javascript/tels/rotator.js" type="text/javascript" ></script>

<style type="text/css" media="screen">
  .inplaceeditor-saving {background: url(<spring:theme code="wait"/>) bottom right no-repeat; }
</style>
<title><spring:message code="login.title" /></title>

</head>

<body>

<%@ include file="header.jsp"%>


<div>
<h2><spring:message code="login" /></h2>

<a href="signup.html"><spring:message code="sign.up" /></a><br />

<c:if test="${failed}">
  <p><spring:message code="login.failed" /></p>
</c:if>

<div id="right" class="marginHorAdj1" >
<form id="login" method="post" action="j_acegi_security_check">

  <label for="j_username"><spring:message code="login.username" /></label>
  <input type="text" name="j_username" id="j_username"  class="text" tabindex="1" />
<br />
  <label for="j_password"><spring:message code="login.password" /></label>
  <input type="password" name="j_password" id="j_password" class="text" tabindex="2" />
<br />

     <div id="waiting" style="display: none">
       <div><img src="<spring:theme code="wait"/>" alt="<spring:message code="wise.banner.alttext" />" /></div>
     </div>
     
 <input type="image" id="signIn" src="<spring:theme code="sign_in" />" 
        	class="_right1" tabindex="3" onmouseover="swapSignIn('signIn', 1);"
       		onmouseout="swapSignIn('signIn', 0);"
 
            onclick="Effect.toggle('waiting', 'appear')" /> 
      
 
</form>

</div>
<a href="javascript:Effect.toggle('waiting', 'appear')">click me</a>





</div>
<%@ include file="footer.jsp"%>

</body>
</html>

<!--
<input type="submit" class="buttons" tabindex="3" value="<spring:message code="login.submit" />" 
      onclick="Effect.toggle('waiting', 'appear')" />

-->